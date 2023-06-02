# MVVM-Clean-Kotlin-Android-Architecture
A minimalistic Android application consuming a [TMDB Movie API](https://developer.themoviedb.org/) to display popular movie list. Built with Clean architecture and Jetpack component (Jetpack Compose, MVVM Clean, Kotlin, HILT, ROOM DB, Retrofit2, Coroutines, Kotlin Flow and many more..). It was made to describe the latest android development trend. This app will be updated further to adapt to latest development trend.

**App features:**

- List of latest popular movies
- Movie Detail Screen

## Screenshots
<div style="display:flex;">
  <img src="art/Movie_List_Screen.png" alt="Image 1" style="width:300px; height:600px;">
  <img src="art/Movie_Detail_screen.png" alt="Image 2" style="width:300px; height:600px;">
</div>

## Architecture
Uses concepts of the Uncle Bob's architecture called [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html).</br>

* Better separation of concerns. Each module has a clear API., Feature related classes life in different modules and can't be referenced without explicit module dependency.
* Features can be developed in parallel eg. by different teams
* Each feature can be developed in isolation, independently from other features
* faster compile time

This architecture design is mostly inspired by [Android-Clean-Architecture-Boilerplate](https://github.com/bufferapp/android-clean-architecture-boilerplate).

<img src="art/architecture.png" height="450">

## Modules:
* **app** - It uses all the components and classes related to Android Framework. It gets the data from domain layer and shows on UI. (**access all the modules**)
* **domain** - The domain layer contains the UseCases that encapsulate a single and very specific task that can be performed. This task is part of the business logic of the application. (Kotlin module that **cannot access any other module**)
* **data** - The data layer implements the repository interface that the domain layer defines. This layer provide a single source of truth for data. (Kotlin module that **can only access domain module**)
* **remote** - Handles data interacting with the network. (**can only access data module**)
* **cache** - Handles data interacting with the local storing (Room DB). (**can only access data module**)

## Tech stack - Library:

- [Kotlin](https://kotlinlang.org/) - Kotlin is Google's preferred language for Android app development.
- [Kotlin Coroutines](https://github.com/Kotlin/kotlinx.coroutines) - A coroutine is a concurrency design pattern that you can use on Android to simplify code that executes asynchronously
- [Flow API](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) - Flow is used to pass (send) a stream of data that can be computed asynchronously
- [Dagger-Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - for dependency injection.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture)
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
    - [Room](https://developer.android.com/topic/libraries/architecture/room) - Used to create room db and store the data.
    - [Navigation](https://developer.android.com/guide/navigation/navigation-getting-started) - Used to navigate between fragments.
    - [Compose](https://developer.android.com/jetpack/compose) - Jetpack Compose is Android’s recommended modern toolkit for building native UI.
- [Material-Components](https://github.com/material-components/material-components-android) - Material design components like ripple animation, cardView.
- [Retrofit](https://github.com/square/retrofit) - Used for REST api communication.
- [OkHttp](http://square.github.io/okhttp/) - HTTP client that's efficient by default: HTTP/2 support allows all requests to the same host to share a socket
- [Moshi](https://github.com/square/moshi) - Used to convert Java Objects into their JSON representation and vice versa.
- [Coil](https://github.com/chrisbanes/accompanist/blob/main/coil/README.md) - An image loading library for Android backed by Kotlin Coroutines
- [Accompanist](https://google.github.io/accompanist/) - Accompanist is a group of libraries that aim to supplement Jetpack Compose with features that are commonly required by developers but not yet available.
- [Mockito](https://site.mockito.org) - Mockito is a popular open source framework for mocking objects in software test.
- [Junit](https://junit.org/junit4/) - JUnit is one of the most popular unit testing framework

## TODO
- [ ] Kotlin-DSL
- [ ] Paging 3.0

## How to run this app
- Clone this repository
- Register in  [TMDB](https://developers.themoviedb.org/) and get the API_KEY , BASE_URL and POSTER_URL
- Build the application