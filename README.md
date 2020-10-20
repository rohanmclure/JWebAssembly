# How to build + run JWebAssembly

## Prerequisites
To run this repo, firstly you need wizard-engine, layed out in the following directory structure.

- `...`
  - `JWebAssembly`
  - `wizard-engine`
  
## Building JWebAssembly
JWebAssembly relies on Gradle to build it. Gradle will build a gradle wrapper, which can then be used to build and run tests.

> Note: Gradle spawns a daemon to start with. This means the first build will be fairly slow, but subsequent builds/runs will be significantly faster.

To build the wrapper, we need Gradle (version >= 4.1). To build gradle, simply run `gradle build`, which will both build the wrapper, as well as run all unit tests.

For those that do not have Gradle installed, you can also use the gradle wrapper script and provided gradle-wrapper jar to run tests.
Those on windows can use `gradlew.bat` and linux users can use `gradlew`.

To run tests, use the following command to run all tests in the jawa suite:
```bash
./gradlew test --tests "de.inetsoftware.jwebassembly.jawa.tests.RunJawaTests"
```
Alternatively, use the following command to run a single test in the test suite:
```bash
./gradlew test --tests "de.inetsoftware.jwebassembly.jawa.tests.RunJawaTests.array02"
```

A similar process applied for JOlden tests, except replace the Jawa with Jolden.
