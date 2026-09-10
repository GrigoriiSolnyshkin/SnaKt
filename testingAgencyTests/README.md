# Testing agency tests

These are focused, manually launched probes of SnaKt. They are deliberately
not wired into Gradle, GitHub Actions, or any other CI configuration.

Each executable in `tests/` covers one topic by selecting one of SnaKt's
golden-file tests through the repository's supported test driver. Do not run a
test speculatively or repeatedly: record every launch in `DIARY.md`, including
its UTC time and result.
