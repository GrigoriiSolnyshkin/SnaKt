# Ревью PR #279 — «Use uniqueness and locality results in verification»

Ревью по коду ветки `refs/pull/279/head` (тесты не запускались). Номера строк — по файлам в состоянии PR.

## Суть изменения

Раньше uniqueness/locality-чекеры были opt-in side-channel, а conversion сам перечитывал `@Unique`/`@Borrowed` через `hasAnnotation`. Теперь FIR-расширения атрибутов и резолверы регистрируются безусловно, conversion читает `resolvedType.scopeUniqueness` / `.locality`, а диагностики остаются под флагами (по умолчанию включены). `@Unique`/`@Borrowed` сузились до `@Target(TYPE)`, спец-DSL помечен `@SpecificationHelper` и трактуется как uniqueness-нейтральный.

---

## Замечания

### 1. Дублирование вайринга locality в двух местах (не баг, но риск рассинхрона)

`UniquenessExtensionRegistrar` и `UniquenessAdditionalCheckers` удалены целиком, а `LocalityExtensionRegistrar` оставлен. Асимметрия объяснима: у модуля locality есть собственный standalone-тестовый харнесс, который его использует, а у uniqueness такого нет.

- `formver.compiler-plugin/locality/src/org/jetbrains/kotlin/formver/locality/plugin/LocalityExtensionRegistrar.kt:16` — класс остаётся живым;
- `formver.compiler-plugin/locality/test-fixtures/org/jetbrains/kotlin/formver/plugin/services/ExtensionRegistrarConfigurator.kt:21` — единственный оставшийся потребитель (тесты locality-модуля).

**Риск:** теперь набор locality-резолверов/диагностик прописан дважды — в `LocalityExtensionRegistrar` (для тестов модуля) и в `FormalVerificationPluginExtensionRegistrar` (для реального плагина). Списки могут разъехаться. Стоит либо вынести общий список регистрации в одну точку, либо явно оставить комментарий про два независимых пути.

- `formver.compiler-plugin/plugin/src/org/jetbrains/kotlin/formver/plugin/compiler/FormalVerificationPluginExtensionRegistrar.kt:47-52` — вайринг для плагина.

### 2. Возможные ложноположительные срабатывания на не-DSL вызовах внутри спецификации

`isInSpecificationContext()` смотрит только на самый внутренний узел `callsOrAssignments.lastOrNull()`.

- `formver.compiler-plugin/plugin/src/org/jetbrains/kotlin/formver/plugin/compiler/SpecificationAwareChecker.kt:22-23`

Для `verify(f(x))`, где `f` — обычная функция, внутренний узел `f(x)` предикатом нейтральности не принимается (у `f` нет `@SpecificationHelper`), и анализатор посчитает `x` перемещённым — хотя `verify` в рантайме не исполняется.

- `formver.compiler-plugin/uniqueness/src/org/jetbrains/kotlin/formver/uniqueness/plugin/GraphUniquenessStatesAnalyzer.kt:171` и `:197` — короткое замыкание нейтрального предиката только для самого спец-вызова, не для вложенных.

Это тот же класс, что задокументированный лимит про `verify(a === b)` (см. `uniqueness/README.md`). Если поведение осознанное — стоит закрепить тестом именно на вложенный не-DSL вызов; если нет — это FP.

### 3. `@SpecificationHelper` объявлен `internal`, а читается кросс-модульно через FIR

- `formver.annotations/src/org/jetbrains/kotlin/formver/plugin/Builtins.kt:8-9` — `@Target(CONSTRUCTOR, FUNCTION) internal annotation class SpecificationHelper`, без явного `@Retention`.

Retention по умолчанию `BINARY`, поэтому `hasAnnotation(annotationId("SpecificationHelper"), session)` в чекере отработает. Но связка «internal-маркер + чтение из метадаты пользовательского модуля» хрупкая: рефактор на `@Retention(SOURCE)` молча сломает всю нейтрализацию спецификаций без ошибки компиляции.

- Чтение: `formver.compiler-plugin/core/src/org/jetbrains/kotlin/formver/core/FirUtils.kt` — `isSpecificationFunction()` / `isSpecificationCall()`.

**Рекомендация:** проставить явный `@Retention(AnnotationRetention.BINARY)` и комментарий, что аннотацию читает FIR.

### 4. Сужение таргетов `@Unique`/`@Borrowed` — breaking change публичного API

- `formver.annotations/src/org/jetbrains/kotlin/formver/plugin/Annotations.kt:13-17`

Было `{FUNCTION, PROPERTY, VALUE_PARAMETER, LOCAL_VARIABLE, TYPE}`, стало `@Target(TYPE)`. Пользовательский declaration-site `@Unique fun foo()` перестанет компилироваться. testData в PR обновлён, но для публичного модуля `formver.annotations` это стоит отметить в changelog / release notes.

### 5. `checkLocality` / `checkUniqueness` по умолчанию `true` при безусловной регистрации атрибут-расширений — footgun

- `formver.common/src/org/jetbrains/kotlin/formver/common/PluginConfiguration.kt:16-18`
- `formver.compiler-plugin/cli/src/org/jetbrains/kotlin/formver/cli/FormalVerificationPluginComponentRegistrar.kt:41-42`

Атрибут-расширения регистрируются всегда, поэтому выключение `check_uniqueness` оставляет `@Unique` в Viper-кодировании, но без валидации. Поведение задокументировано (в `PluginConfiguration` и README), просто UX-ловушка — держать в виду.

### 6. `isUnique` / `isBorrowed` тихо возвращают `false` для необрабатываемых символов и читают тип-атрибуты в обход резолверов

- `formver.compiler-plugin/core/src/org/jetbrains/kotlin/formver/core/FirUtils.kt:90-102`

```kotlin
val FirBasedSymbol<*>.isUnique: Boolean
    get() = when (this) {
        is FirReceiverParameterSymbol -> resolvedType.scopeUniqueness == Uniqueness.Unique   // :92
        is FirCallableSymbol<*>       -> resolvedReturnType.scopeUniqueness == Uniqueness.Unique // :93
        else -> false                                                                         // :94
    }

val FirBasedSymbol<*>.isBorrowed: Boolean
    get() = when (this) {
        is FirReceiverParameterSymbol -> resolvedType.locality == Locality.Local              // :100
        is FirValueParameterSymbol    -> resolvedReturnType.locality == Locality.Local        // :101
        else -> false                                                                         // :102
    }
```

Две тонкости:
- Ветки читают тип-атрибуты напрямую (в conversion нет `CheckerContext`), поэтому для **выведенных** типов атрибута не будет — тихая смена семантики против прежнего `hasAnnotation`. Комментарий в файле это оговаривает («explicitly typed declarations only»), но это реальный источник «почему аннотацию не подхватило».
- Асимметрия `resolvedType` (receiver) vs `resolvedReturnType` (value parameter) корректна для FIR (у value-параметра тип моделируется как return type), но её стоит держать под тестом — это единственное место, где эквивалентность неочевидна.

---

## Что проверено и вопросов не вызывает

- `FunctionUniquenessStateRenderingChecker` переведён в `object`, внутренний guard заменён на условие регистрации `config.checkUniqueness && config.dumpUniquenessCFG` — эквивалентно (`PluginAdditionalCheckers.kt:68-69`).
- Нейтральный предикат прокинут через фабрику корректно: `GraphUniquenessStatesResolver.getFactory { it.isSpecificationCall() }` (`FormalVerificationPluginExtensionRegistrar.kt:43`), интерфейс `UniquenessNeutralCallPredicate` с `context(CheckerContext)` (`GraphUniquenessStatesResolver.kt:23`).
- Флоу-чувствительные функции обёрнуты `asSpecificationAware()` (`PluginAdditionalCheckers.kt:76-78`, `:149`).
