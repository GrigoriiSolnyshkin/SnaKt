# Testing agency tests

These are manually launched, bounded smoke tests for SnaKt. They are deliberately outside Gradle source sets and CI configuration.

Each script exercises exactly one topic through the repository's golden-file test driver. A script may be launched at most three times over its lifetime. Before launching one, check `DIARY.md`; after launching it, update the run count, timestamp, command, and result there.

Do not regenerate goldens from these wrappers: a passing conversion must agree with the committed golden.
