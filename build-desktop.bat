@echo off
echo 🔨 Сборка desktop дистрибутива для Windows...
gradlew.bat :composeApp:createMsi
if %ERRORLEVEL% EQU 0 (
    echo ✅ Сборка завершена! Проверьте папку composeApp\build\compose\binaries\main\
) else (
    echo ❌ Ошибка при сборке
)
pause 