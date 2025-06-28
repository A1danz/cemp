# Cemp

🎮 **CS 2 Esports Tournament Platform**

👾 **Kotlin Multiplatform App**

🚀 **Platforms: iOS, Android, Desktop**

## 🏃‍♂️ Быстрый старт

### Desktop версия

#### Запуск в режиме разработки:
```bash
# macOS/Linux
./run-desktop.sh

# Windows
run-desktop.bat

# Или напрямую через Gradle
./gradlew :composeApp:run
```

#### Сборка дистрибутива:
```bash
# Сборка всех форматов
./gradlew :composeApp:createDistributable

# Сборка конкретного формата
./gradlew :composeApp:createDmg     # macOS
./gradlew :composeApp:createMsi     # Windows
./gradlew :composeApp:createDeb     # Linux
```

### Android версия
```bash
./gradlew :composeApp:installDebug
```

### iOS версия
Откройте `iosApp/iosApp.xcworkspace` в Xcode и запустите проект.

## 🛠 Технологии

- **Kotlin Multiplatform** - Общий код для всех платформ
- **Compose Multiplatform** - Современный UI фреймворк
- **Decompose** - Навигация и архитектура
- **Koin** - Dependency Injection
- **Ktor** - HTTP клиент
- **SQLDelight** - Локальная база данных
- **Moko Resources** - Мультиплатформенные ресурсы

## 📱 Возможности

- 🏆 Просмотр турниров CS 2
- 📊 Таблица лидеров команд
- 🎯 Детальная информация о матчах
- 👥 Информация о командах и игроках
- 🔐 Система авторизации

## 🏗 Архитектура

Проект использует **Clean Architecture** с разделением на слои:
- **Presentation** (`composeApp`) - UI слой
- **Domain** (`shared`) - Бизнес логика
- **Data** (`shared`) - Работа с API и локальными данными
