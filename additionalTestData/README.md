# Isolated additional verification cases

This tree deliberately stays outside `formver.compiler-plugin/testData`, so
these cases do not enlarge the normal generated suite.

Run `./additionalTestData/verify-isolated.sh --refresh` after an intentional
solution change. This temporarily stages just these cases in a dedicated test
directory, runs SnaKt's full conversion and verification pipeline, saves
their goldens back here, and restores the generated runner on exit. Read all
printed diagnostics: a non-empty verification diagnostics golden makes the
command fail.

For a non-mutating check against the saved goldens, run
`./additionalTestData/verify-isolated.sh`. The script refuses to overwrite an
existing staging directory and cleans its exact staging path via an exit trap.

The repository's Kotlin/Gradle toolchain currently rejects JDK 25 with an
`IllegalArgumentException` while parsing version `25.0.2`. Use a supported JDK
(JDK 21 was exercised during this run) through `JAVA_HOME` when running this
script. Full verification also requires the `z3` executable on `PATH`, or its
location in `Z3_EXE`, as described in the repository README.
