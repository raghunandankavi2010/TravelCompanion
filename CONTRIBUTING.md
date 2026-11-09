# Contributing to TravelCompanion

First off, thank you for taking the time to contribute! 🎉 TravelCompanion is an Android application built with modern Android tech stacks (Jetpack Compose, CameraX, ML Kit, Material 3, and Kotlin Coroutines).

The following is a set of guidelines for contributing to TravelCompanion. These are mostly guidelines, not hard rules. Use your best judgment, and feel free to propose changes to this document in a pull request/merge request.

---

## Table of Contents
1. [Code of Conduct](#code-of-conduct)
2. [Getting Started](#getting-started)
3. [Development Workflow](#development-workflow)
4. [Building and Testing Locally](#building-and-testing-locally)
5. [Coding Guidelines](#coding-guidelines)
6. [Submitting Contributions](#submitting-contributions)

---

## Code of Conduct

By participating in this project, you agree to abide by our terms. Please treat fellow contributors with respect, inclusivity, and professionalism.

---

## Getting Started

### Prerequisites
- **Android Studio**: Latest stable version (Hedgehog or newer recommended).
- **Java Development Kit (JDK)**: Version 17.
- **Android SDK**: `compileSdk` and `targetSdk` set to **37** (API level 37).
- **Gradle**: Managed via Gradle Wrapper (`./gradlew`).

### Setting Up Your Environment
1. Fork and clone the repository:
   ```bash
   git clone https://github.com/your-username/travelcompanion.git
   cd travelcompanion
   ```
2. Open the project in **Android Studio**.
3. Let Gradle sync dependencies.
4. If working with signed release builds or specific API keys, create a `keystore.properties` file in the root directory following project guidelines (never commit secrets or keystores to version control).

---

## Development Workflow

1. **Create a Branch**: Branch off `main` or `develop` with a descriptive name.
   ```bash
   git checkout -b feature/my-new-feature
   ```
2. **Make Changes**: Follow the project's coding and architecture standards.
3. **Test Locally**: Ensure all tests and lint checks pass before submitting.
4. **Commit Changes**: Write clear, concise commit messages following conventional commit style (e.g., `feat: add offline translation caching`, `fix: resolve camera preview rotation issue`).

---

## Building and Testing Locally

You can run builds and tests via terminal using the Gradle wrapper:

- **Run Unit Tests**:
  ```bash
  ./gradlew testDebugUnitTest
  ```
- **Run Lint Checks**:
  ```bash
  ./gradlew lintDebug
  ```
- **Build Debug APK**:
  ```bash
  ./gradlew assembleDebug
  ```

Our **GitLab CI/CD pipeline** automatically runs these checks on every commit and merge request.

---

## Coding Guidelines

- **Language**: Kotlin 2.x
- **UI Toolkit**: Jetpack Compose with Material 3 design system.
- **Asynchronous Operations**: Kotlin Coroutines and `Flow`.
- **Architecture**: MVVM (Model-View-ViewModel) pattern with unidirectional data flow (UDF).
- **Formatting**: Follow standard Kotlin coding conventions. Keep functions small, focused, and well-documented where necessary.

---

## Submitting Contributions

1. Push your branch to your fork:
   ```bash
   git push origin feature/my-new-feature
   ```
2. Open a **Merge Request (MR)** / **Pull Request (PR)** against the main repository.
3. Fill in the MR description detailing:
   - What changes were made.
   - How you tested the changes.
   - Any screenshots or recordings if UI changes were introduced.
4. Ensure all CI/CD pipeline checks pass successfully.

Thank you for helping make TravelCompanion better! 🚀
