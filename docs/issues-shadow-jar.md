# Shadow JAR issues

## 1. Missing mergeServiceFiles() in embeddableJar

`formver.compiler-plugin/build.gradle.kts`

The `embeddableJar` task is a custom `ShadowJar` registered via `tasks.registering`. Unlike
`tasks.shadowJar` (pre-configured by the Shadow plugin), it does not call `mergeServiceFiles()`
by default. If any bundled jar contains `META-INF/services/` entries with the same name as
another, Shadow silently overwrites them instead of merging — causing `ServiceLoader` failures
at runtime with no build-time warning.

Fix:

```kotlin
val embeddableJar by tasks.registering(ShadowJar::class) {
    ...
    mergeServiceFiles()
    ...
}
```

## 2. Relocation list is manually maintained with no integration test

`formver.compiler-plugin/build.gradle.kts`

The `embeddableJar` task relocates two packages to match `kotlin-compiler-embeddable`:
- `com.intellij` → `org.jetbrains.kotlin.com.intellij`
- `kotlinx.collections.immutable` → `org.jetbrains.kotlin.kotlinx.collections.immutable`

The `:formver.compiler-plugin:viper` dependency is added with `isTransitive = true` and bundles
Silicon, which includes `com.google.common` (Guava) — a package that `kotlin-compiler-embeddable`
also relocates. If any plugin class references Guava through the compiler API, the missing
relocation will produce a `NoClassDefFoundError` at runtime in a Gradle build.

There is no integration test that runs the plugin in a real Gradle daemon, so a missing
relocation would not be caught at CI time.

To check whether `com.google.common` is currently present in the embeddable jar:

```bash
jar tf formver.compiler-plugin/build/libs/formver.compiler-plugin-embeddable-*.jar \
  | grep "^com/google" | head -5
```
