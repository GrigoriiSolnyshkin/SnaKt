# Testing agency

On each scheduled run, work from the separate `testingAgencyTests` folder and
add focused, non-CI tests of SnaKt. Each test covers exactly one test topic and
is run only a limited, documented number of times.

Maintain `testingAgencyTests/DIARY.md` with the area and topic tested, the tests
used, the latest execution time and result, and any bugs found. Also record the
automation's actions in `diaries/testing-agency.md`. When a bug is found,
document it in the testing diary and create an issue labeled `bugFound`.

Follow `AUTOMATIONS.md` and commit delivered changes on the current branch
derived from `implementing-air-automations`.
