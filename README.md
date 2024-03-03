# News App Project

## Overview

The News App project is designed to provide users with a platform to read and explore news articles.
It offers functionalities such as fetching recent and old articles, sorting articles based on their
publication dates, and displaying them in an intuitive user interface.

## Technologies Used

- **Kotlin**: The primary programming language used for developing the News App.
- **Android Jetpack**: Utilized Jetpack components such as LiveData, ViewModel, and Data Binding for
  building robust and efficient Android apps.
- **Compose**: Jetpack Compose is used for building the user interface, offering a modern and
  declarative way to create UI layouts in Android apps.
- **Hilt**: Hilt is used for dependency injection, making it easier to manage dependencies and
  improve app maintainability.
- **Coroutines**: Leveraged Coroutines for asynchronous and non-blocking programming, facilitating
  smooth and responsive app behavior.
- **Clean-MVVM**: Integrated Moengage for analytics and user engagement, enabling data-driven
  insights and personalized user experiences.

## Features

- **Modular Architecture**: The project follows a modular architecture, separating concerns into
  different modules for better organization and maintainability.
- **Clean Architecture**: Utilizes Clean Architecture principles to enforce separation of concerns
  between layers, promoting a more scalable and maintainable codebase.
- **MVVM Pattern**: Adopts the MVVM architectural pattern for the presentation layer, enabling
  separation of UI logic from business logic and facilitating better testability.
- **Dependency Injection**: Uses Dagger Hilt for dependency injection, allowing for easier
  management of dependencies and improving app maintainability.
- **Coroutines**: Leverages Kotlin Coroutines for asynchronous and non-blocking programming,
  ensuring smooth and responsive app behavior.
- **Unit Testing**: Includes unit tests for business logic and data layers, promoting code quality
  and reliability.
- **API Integration**: Demonstrates integration with external APIs to fetch and display data in the
  application.

## Project Structure

The project is structured into several modules, each serving a specific purpose:

- **app**: Contains the main application code, including activities, fragments, and the overall user
  interface.
- **core**: Houses core utility classes, network components, and shared resources used across the
  application.
- **data**: Implements data sources, repositories, and data models for managing app data.
- **domain**: Defines business logic and use cases, encapsulating the application's core
  functionalities.
- **presentation**: Contains view models and UI components responsible for handling user
  interactions and displaying data.
- **presentation**: Contains view models and UI components responsible for handling user
  interactions and displaying data.

## Getting Started

To run the News App project locally, follow these steps:

1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Build and run the project on an Android emulator or physical device.
