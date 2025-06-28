#!/bin/bash

echo "🔨 Сборка desktop дистрибутива..."

# Определяем ОС
OS="$(uname -s)"
case "${OS}" in
    Linux*)     
        echo "🐧 Сборка .deb пакета для Linux..."
        ./gradlew :composeApp:createDeb
        ;;
    Darwin*)    
        echo "🍎 Сборка .dmg пакета для macOS..."
        ./gradlew :composeApp:createDmg
        ;;
    CYGWIN*|MINGW32*|MSYS*|MINGW*)
        echo "🪟 Сборка .msi пакета для Windows..."
        ./gradlew :composeApp:createMsi
        ;;
    *)          
        echo "❓ Неизвестная ОС: ${OS}"
        echo "🔨 Сборка всех доступных форматов..."
        ./gradlew :composeApp:createDistributable
        ;;
esac

echo "✅ Сборка завершена! Проверьте папку composeApp/build/compose/binaries/main/" 