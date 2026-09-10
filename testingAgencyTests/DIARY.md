# SnaKt testing agency diary

These tests are manually launched probes and are intentionally not wired into CI. Each script checks one topic by running one focused golden test. Run counts below are deliberately bounded and cumulative from the entries recorded in this diary.

## Test inventory

| Test | SnaKt area | Single topic checked | Last launched (UTC) | Recorded launches | Latest result |
| --- | --- | --- | --- | ---: | --- |
| `test-exists-conversion.sh` | Compiler-plugin conversion | `exists` specifications convert to Viper existential quantifiers | 2026-09-10 07:02 | 1 | Blocked before tests: unsupported Java 25.0.2 |
| `test-exists-impure-body-diagnostic.sh` | Compiler-plugin diagnostics | An impure `exists` body is rejected with the expected purity diagnostic | 2026-09-10 07:03 | 1 | Blocked before tests: unsupported Java 25.0.2 |

## Run log

### 2026-09-10 07:00 UTC — existential quantifiers

- Planned launch limit: once per test in this run.
- `test-exists-conversion.sh`: launched once. Gradle failed before producing test results because the environment supplies Java 25.0.2, which this build cannot parse.
- `test-exists-impure-body-diagnostic.sh`: launched once. It hit the same pre-test Java 25.0.2 failure.
- Environment recovery attempted: no compatible JDK was installed; downloads of JDK 21 from the Adoptium and Oracle endpoints were denied with HTTP 403.
- Bugs found: none. The launches did not exercise SnaKt, so the environment failure is not classified as a SnaKt bug and no `bugFound` issue was created.
- Automation context: `AUTOMATIONS.md` was not present in the checkout, so no shared instructions could be applied.
- Repository checks: shell syntax and test-data checks passed. Gradle `check` was blocked by Java 25.0.2. The pre-commit check was skipped; installing `pre-commit` in an isolated virtual environment was attempted, but the package download was denied by the proxy with HTTP 403.

## Future topics

Each future probe should remain a separate script so that it checks only its named topic.

- Universal quantifier trigger conversion.
- Loop-invariant preservation.
- Nullable smart-cast conversion.
- Integer overflow semantics.
- Verification target selection.
- User-friendly diagnostic rendering.
