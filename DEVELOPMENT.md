# 🛠 Инструкции для разработчиков

## 🚀 Быстрые команды

### Desktop версия
```bash
# Запуск в режиме разработки
./run-desktop.sh                    # macOS/Linux
run-desktop.bat                     # Windows

# Сборка дистрибутива
./build-desktop.sh                  # macOS/Linux
build-desktop.bat                   # Windows

# Ручные команды
./gradlew :composeApp:run           # Запуск
./gradlew :composeApp:createDmg     # Сборка .dmg (macOS)
./gradlew :composeApp:createMsi     # Сборка .msi (Windows)
./gradlew :composeApp:createDeb     # Сборка .deb (Linux)
```

### Android версия
```bash
# Отладочная сборка
./gradlew :composeApp:assembleDebug
./gradlew :composeApp:installDebug

# Релизная сборка
./gradlew :composeApp:assembleRelease
```

### Общие команды
```bash
# Очистка проекта
./gradlew clean

# Проверка зависимостей
./gradlew dependencyUpdates

# Запуск тестов
./gradlew test

# Проверка кода
./gradlew ktlintCheck
```

## 📁 Структура проекта

```
Cemp/
├── composeApp/                 # UI слой (Compose Multiplatform)
│   ├── src/
│   │   ├── commonMain/        # Общий UI код
│   │   ├── androidMain/       # Android-специфичный код
│   │   ├── iosMain/           # iOS-специфичный код
│   │   └── jvmMain/           # Desktop-специфичный код
│   └── build.gradle.kts
│
├── shared/                     # Бизнес-логика и данные
│   ├── src/
│   │   ├── commonMain/        # Общий код
│   │   ├── androidMain/       # Android-специфичный код
│   │   ├── iosMain/           # iOS-специфичный код
│   │   └── jvmMain/           # Desktop-специфичные реализации
│   └── build.gradle.kts
│
└── iosApp/                    # iOS проект (Xcode)
```

## 🔧 Настройка среды разработки

### Требования
- **JDK 17+**
- **Android Studio** (для Android разработки)
- **Xcode** (для iOS разработки, только macOS)

### Настройка IDE
1. Откройте проект в **Android Studio** или **IntelliJ IDEA**
2. Убедитесь, что JDK 17+ настроен как Project SDK
3. Включите Kotlin Multiplatform Mobile plugin

## 🐛 Отладка

### Desktop
- Логи отображаются в консоли IDE
- Можно использовать обычные breakpoints в коде

### Проблемы и решения

**Ошибка "Main class not found":**
```bash
# Убедитесь, что mainClass правильно указан в build.gradle.kts
mainClass = "sample.app.MainKt"
```

**Проблемы с зависимостями:**
```bash
# Очистите и пересоберите проект
./gradlew clean
./gradlew build
```

**Ошибки компиляции Compose:**
```bash
# Обновите версии в gradle/libs.versions.toml
compose = "1.7.3"
```

## 📦 Сборка релиза

### Desktop
```bash
# Сборка установщика для текущей ОС
./build-desktop.sh

# Артефакты будут в:
# composeApp/build/compose/binaries/main/
```

### Android
```bash
# Создание подписанного APK
./gradlew :composeApp:assembleRelease

# Создание AAB для Google Play
./gradlew :composeApp:bundleRelease
``` 