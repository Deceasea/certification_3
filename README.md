# QATests

## Описание

Этот проект создан для автоматизации тестирования веб-приложения с использованием Selenide и JUnit5. Тесты включают сценарии для проверки профиля пользователя, добавления и удаления книг.

## Требования

- Java 17
- Maven 3.6.3 или выше
- ChromeDriver, совместимый с установленной версией Google Chrome
- Установленный Google Chrome

## Установка

1. Клонируйте репозиторий:
    ```sh
    git clone https://github.com/Deceasea/certification_3.git
    ```
2. Перейдите в каталог проекта:
    ```sh
    cd QATests
    ```
3. Установите зависимости Maven:
    ```sh
    mvn clean install
    ```

## Запуск тестов

Для запуска всех тестов используйте следующую команду Maven:
```sh
mvn test
```
## Генерация Allure отчета

1. Запустите тесты и соберите Allure результаты:
```sh
mvn clean test
```

2. Генерация Allure отчета:
```sh
mvn allure:report
```
3. Откройте Allure отчет:

```sh
mvn allure:serve
```