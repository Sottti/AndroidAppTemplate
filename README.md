# Android App Template (AAT)

![API](https://img.shields.io/badge/dynamic/toml?url=https://raw.githubusercontent.com/Sottti/AndroidAppTemplate/refs/heads/main/gradle/libs.versions.toml&query=$.versions.minSdk&label=API&color=brightgreen&suffix=%2B&logo=android&logoColor=white)
![Compose BOM](https://img.shields.io/badge/dynamic/toml?url=https://raw.githubusercontent.com/Sottti/AndroidAppTemplate/refs/heads/main/gradle/libs.versions.toml&query=$.versions.compose-bom&label=Compose%20BOM&color=007ACC&logo=jetpackcompose&logoColor=white)
![Navigation](https://img.shields.io/badge/dynamic/toml?url=https://raw.githubusercontent.com/Sottti/AndroidAppTemplate/refs/heads/main/gradle/libs.versions.toml&query=$.versions.compose-navigation&label=Navigation&color=4CAF50&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/dynamic/toml?url=https://raw.githubusercontent.com/Sottti/AndroidAppTemplate/refs/heads/main/gradle/libs.versions.toml&query=$.versions.kotlin&label=Kotlin&color=7F52FF&logo=kotlin&logoColor=white)
![GitHub last commit](https://img.shields.io/github/last-commit/Sottti/AndroidAppTemplate?logo=github&logoColor=white)
![GitHub repo size](https://img.shields.io/github/repo-size/Sottti/AndroidAppTemplate)
[![License](https://img.shields.io/badge/License-MIT-blue?style=flat&logo=opensourceinitiative&logoColor=white)](https://opensource.org/licenses/MIT)

A clean, modern, and opinionated Android application template to bootstrap your new projects
quickly. This template is built with a focus on modern Android development (MAD), scalability, and
best practices.

## Overview

This project serves as a starter kit for new Android applications. It's designed to save you from
the repetitive setup process of configuring dependencies, setting up architecture, and implementing
common utilities. Just clone or use this template, rename the package, and start building your app's
features immediately.

## ✨ Features

This template is packed with the latest libraries and tools from the Android ecosystem:

* **Tech Stack:** 100% [Kotlin](https://kotlinlang.org/)
* **UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose) for declarative UI.
    * **Theming:** [Material 3](https://m3.material.io/) (Material You) support.
    * **Navigation:
      ** [Compose Navigation 3](https://developer.android.com/guide/navigation/navigation-3)
      for all screen transitions.
* **Architecture:** Follows Google's official "Guide to app architecture".
    * [MVVM](https://developer.android.com/jetpack/guide) (Model-View-ViewModel).
    * **UI Layer:** State-driven UI with `ViewModel`, `State`, and `Actions`.
    * **Domain Layer:** (Optional but recommended) for business logic.
    * **Data Layer:** `Repository` pattern.
* **Asynchronicity:
  ** [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flows](https://developer.android.com/kotlin/flow)
  for managing background threads and streams of data.
* **Dependency Injection:** [Hilt](https://dagger.dev/hilt/) for managing dependencies.
* **Networking:** [Ktor Client](https://ktor.io/docs/client-overview.html) for efficient REST API
  communication.
* **Serialization:** [Kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) (used
  with Ktor) for fast and modern JSON parsing.
* **Testing:**
    * **Unit Tests:** [JUnit 4](https://junit.org/junit4/)
    * **Screenshot Tests:** [Paparazzi](https://github.com/cashapp/paparazzi)
    * **UI Tests:** [Compose Test Rules](https://developer.android.com/jetpack/compose/testing)

### 🌟 Additional Highlights

* **Dynamic system theming:** The `presentation` layer streams system theme and contrast updates,
  maps them through the `data/system-features` module, and applies them across the Compose UI while
  synchronizing system bars for a fully adaptive experience.
* **Navigation infrastructure:** A dedicated Compose Navigation 3 stack coordinates screen changes
  via a `NavigationManager`, with `Navigator` components observing command channels and handling
  saveable state, back stack pops, and simultaneous navigation requests.
* **Snapshot tooling:** The `presentation` module includes a Paparazzi test toolkit featuring a
  custom Pixel 10 Pro XL device profile and helpers that generate day/night parameter sets for rich
  screenshot coverage.
* **Coroutine utilities:** The `utils` module ships reusable coroutine extensions such as
  `stateInWhileSubscribed` and `observeConfigurationChanges` for concise and lifecycle-aware state
  management in ViewModels.
* **Build hygiene:** Gradle is configured for Kotlin explicit API mode, context receivers, and the
  Versions Plugin to keep dependencies up to date and compiler flags consistent across modules.
* **Testable system features:** The `data/system-features` module exposes fake managers backed by
  Turbine-based tests, showcasing how to stub system services when exercising the settings feature.
* **Streamlined startup:** A Hilt-enabled `Application`, splash activity, and edge-to-edge
  `HomeActivity` combine with the navigation manager to launch directly into themed Compose content
  with minimal boilerplate.

## 🏗️ Project Structure

This project follows a standard multi-module setup, which is highly recommended for separation of
concerns and build speed.

## 🏛️ Architecture (MVVM + Clean)

This template uses a state-driven MVVM (Model-View-ViewModel) architecture combined with principles
from Clean Architecture.

* **UI (Compose)**: Observes `State` from the `ViewModel` and sends `Actions` (user actions) to it.
  It is passive and dumb.
* **ViewModel**: Follows
  a [declarative approach](https://proandroiddev.com/loading-initial-data-in-launchedeffect-vs-viewmodel-f1747c20ce62).
  Handles business logic for the screen. It consumes `Actions`, interacts with
  UseCases/Repositories, and exposes a single `State` Flow for the UI to observe.
* **UseCases (Domain Layer)**: (Optional) Encapsulates a single piece of business logic (e.am.,
  `GetUserProfileUseCase`). This makes logic reusable and easier to test.
* **Repository (Data Layer)**: The single source of truth for data. It abstracts away the data
  source (network or local database) and provides a clean API for the `ViewModel` or `UseCases` to
  consume.

  ## 🚀 How to Use This Template

1. **Create Your Repository:**
   Click the "Use this template" button on the GitHub repository page. This will create a new
   repository in your account with the same file structure.

2. **Clone Your New Repository:**
   ```bash
   git clone https://github.com/Sottti/android-app-template.git
   cd android-app-template
   ```

3. **Rename Package Name:**
   The current package name is `com.sottti.android.app.template`. To rename it:
    * In Android Studio, right-click the root `com.sottti.android.app.template` package in the
      `kotlin` directory.
    * Select **Refactor > Rename**.
    * In the dialog, choose **Rename package**.
    * Enter your new package name (e.g., `com.mycompany.myapp`).
    * Click **Do Refactor**.
    * Manually update the `applicationId` in `app/build.gradle.kts` to match your new package name.

4. **Sync & Build:**
   Sync the Gradle files and build the project to ensure everything is set up correctly.

5. **Start Coding!**
   You're all set. Start building your app's unique features.

## 🤝 Contributing

This is a template for *your* projects, but if you have ideas on how to improve the template itself,
contributions are welcome!

1. **Fork** the repository.
2. Create a new branch (`git checkout -b feature/my-new-feature`).
3. Make your changes.
4. Commit your changes (`git commit -am 'Add some feature'`).
5. Push to the branch (`git push origin feature/my-new-feature`).
6. Create a new **Pull Request**.

## License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT) - see
the [LICENSE](LICENSE) file for details.
