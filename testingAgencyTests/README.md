# Testing agency tests

These are manually scheduled probes for SnaKt. They are deliberately outside
the Gradle source sets and are not wired into CI.

Run one topic at a time from the repository root. Do not use
`--update-goldens`: an agency probe must compare SnaKt's current output with the
reviewed repository golden, not bless new output.

Each script performs a small semantic sanity check in addition to launching the
focused repository test. The diary records the limited run count and result.
