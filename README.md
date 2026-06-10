# Recipe Planner 🍳

A modern Android Recipe Search application built with **Kotlin**, **Jetpack Compose**, **MVI Architecture**, **Clean Architecture**, and **Offline-First principles**.

The application allows users to search recipes from TheMealDB API, save favorites for offline access, view recipe details, and manage preferences using DataStore.

---

## ✨ Features

### 🔍 Recipe Search

* Search recipes by keyword
* Debounced search requests
* Pull-to-refresh support
* Loading skeletons (Shimmer)
* Error handling and retry mechanism

### ❤️ Favorites

* Add recipes to favorites
* Remove recipes from favorites
* Offline persistence using Room
* Favorites available without internet connection
* Undo delete support
* Animated favorite list updates

### 📖 Recipe Details

* Full recipe information
* Category display
* Instructions section
* Favorite toggle directly from detail screen

### ⚙️ Settings

* Dark Mode support
* Preference persistence using DataStore

### 🌐 Connectivity Awareness

* Internet connection monitoring
* Offline state handling

---

## 🏗 Architecture

The project follows:

* Clean Architecture
* MVI (Model-View-Intent)
* Repository Pattern
* Single Source of Truth
* Unidirectional Data Flow

Architecture layers:

```text
presentation
│
├── ui
├── viewmodel
├── contract
└── reducer

domain
│
├── model
└── repository

data
│
├── remote
├── local
├── mapper
└── repository
```

---

## 📦 Tech Stack

### UI

* Jetpack Compose
* Material 3
* Navigation Compose
* Coil

### Dependency Injection

* Hilt

### Networking

* Retrofit
* OkHttp
* Kotlin Serialization

### Local Storage

* Room Database
* DataStore Preferences

### Concurrency

* Kotlin Coroutines
* Flow

### Testing

* JUnit
* Turbine
* Coroutine Test

---

## 📂 Project Structure

```text
com.geekstudio.recipeplanner

├── core
│   ├── network
│   ├── ui
│   │   ├── colors
│   │   ├── components
│   │   ├── loading
│   │   ├── spacing
│   │   └── theme
│
├── data
│   ├── local
│   │   ├── dao
│   │   ├── database
│   │   ├── entity
│   │   └── mapper
│   │
│   ├── remote
│   │   ├── api
│   │   ├── dto
│   │   └── mapper
│   │
│   ├── preferences
│   └── repository
│
├── domain
│   ├── model
│   └── repository
│
├── navigation
│
├── presentation
│   ├── home
│   ├── detail
│   ├── favorites
│   ├── settings
│   └── main
│
└── di
```

---

## 🧠 MVI Flow

```text
User Action
     │
     ▼
 Intent
     │
     ▼
 ViewModel
     │
     ▼
 Repository
     │
     ▼
 PartialState
     │
     ▼
 Reducer
     │
     ▼
 State
     │
     ▼
 Compose UI
```

---

## 🗄 Database

Room is used for:

### Recipes Table

Stores cached search results.

### Favorites Table

Stores full recipe information for offline access.

### Search History Table

Stores previous user searches.

---

## 🔄 Offline Strategy

The application follows an offline-first approach:

1. Search results are cached locally.
2. Favorite recipes are stored separately.
3. Favorite recipes remain accessible without internet.
4. Detail screens can load favorite recipes from local storage.

---

## 🧪 Testing

Implemented test types:

### Reducer Tests

Verifies state transitions.

### ViewModel Tests

Verifies intent processing and state updates.

### Repository Tests

Verifies data layer behavior.

### Flow Tests

Uses Turbine to validate Flow emissions.

Run all tests:

```bash
./gradlew test
```

Run a specific test:

```bash
./gradlew testDebugUnitTest
```

---

## 🚀 Getting Started

### Clone Repository

```bash
git clone https://github.com/your-username/RecipePlanner.git
```

### Open Project

Open with Android Studio:

```text
Android Studio Hedgehog+
```

### Build

```bash
./gradlew assembleDebug
```

### Run

```bash
./gradlew installDebug
```

---

## 📡 API

Powered by:

TheMealDB

https://www.themealdb.com/

Example endpoint:

```http
GET /search.php?s=pizza
```

---

## 🎯 Future Improvements

* Search history screen
* Paging 3 integration
* Dynamic theming
* Recipe ingredients section
* Recipe area/country information
* Share recipe functionality
* Tablet support
* UI animations and transitions
* Screenshot testing
* GitHub Actions CI/CD pipeline

---

## 👨‍💻 Author

**Saman Shahsavari**

Android Developer

Built with:

* Kotlin
* Jetpack Compose
* Clean Architecture
* MVI
* Hilt
* Room
* Retrofit
* Coroutines
* Flow
