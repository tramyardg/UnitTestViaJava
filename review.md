# Review SOEN 345

- __legacy code__ is simply code without tests.
- __unit test__ is a test that runs in less than 1/10th of a second and is small enough to help you localize problems when it fails.
- __test-driven development__ is a development process that consists of writing failing test cases and satisfying them one at a time. As you do this, you refactor to keep the code as simple as possible. Code developed using TDD has test coverage, by default.
## TDD Algorithm
1. Write a failing test case.
2. Get it to compile.
3. Make it pass.
4. Remove duplication
5. Repeat.
- __characterization test__ is a test written to document the current behavior of a piece of code. The tests document the actual behavior of the system.
## Characterization Test Algorithm
1. Use a piece of code in a test harness.
2. Write an assertion that you know will fail.
3. Let the failure tell you what the behavior is.
4. Change the test so that it expects the behavior that the code produces.
5. Repeat.
