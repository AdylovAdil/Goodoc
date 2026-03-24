# Goodoc — Medicine Management System

Goodoc — это backend-приложение для управления лекарствами, разработанное на Spring Boot. Система позволяет пользователям добавлять, просматривать, обновлять и удалять лекарства, формы их выпуска и расписания приёма. Доступ к API защищён с помощью JWT-аутентификации.

---

## Стек технологий

| Технология | Версия |
|---|---|
| Java | 17 |
| Spring Boot | 3.3.4 |
| Spring Security | (в составе Boot) |
| Spring Data JPA | (в составе Boot) |
| PostgreSQL | — |
| JWT (jjwt) | 0.11.5 |
| Lombok | — |
| SpringDoc OpenAPI (Swagger) | 2.0.2 |
| Maven | — |

---

## Архитектура

Проект следует классической многоуровневой (layered) архитектуре:

```
Client
  │
  ▼
Controller  (REST API, обработка HTTP-запросов)
  │
  ▼
Service     (бизнес-логика, интерфейс + реализация)
  │
  ▼
Repository  (Spring Data JPA, работа с базой данных)
  │
  ▼
Database    (PostgreSQL)
```

### Структура пакетов

```
com.example.goodoc
├── config/              # Spring Security, JWT фильтр, OpenAPI конфигурация
├── controller/          # REST-контроллеры
├── dto/                 # Data Transfer Objects (Request / Response)
│   ├── auth/
│   ├── medicine/
│   ├── reception/
│   ├── releaseForm/
│   └── user/
├── enums/               # Перечисления (Role)
├── exception/           # Обработка ошибок (GlobalException, CustomException)
├── mapper/              # Маппинг между моделями и DTO
│   └── impl/
├── model/               # JPA-сущности
├── repository/          # Spring Data JPA репозитории
└── service/             # Сервисный слой
    └── impl/
```

---

## Модели данных

### `Medicine` — Лекарство
| Поле | Тип | Описание |
|---|---|---|
| `id` | `Long` | Уникальный идентификатор |
| `name` | `String` | Название лекарства |
| `releaseForm` | `ReleaseForm` | Форма выпуска (таблетки, капсулы и т.д.) |
| `reception` | `Reception` | Способ приёма (утром, вечером и т.д.) |
| `nextDose` | `LocalDate` | Дата следующего приёма |
| `time` | `LocalTime` | Время приёма |
| `additionally` | `String` | Дополнительные заметки |

### `ReleaseForm` — Форма выпуска
| Поле | Тип | Описание |
|---|---|---|
| `id` | `Long` | Уникальный идентификатор |
| `name` | `String` | Название формы (например: «таблетки», «сироп») |

### `Reception` — Способ приёма
| Поле | Тип | Описание |
|---|---|---|
| `id` | `Long` | Уникальный идентификатор |
| `name` | `String` | Название способа (например: «утром», «после еды») |

### `User` — Пользователь
| Поле | Тип | Описание |
|---|---|---|
| `id` | `Long` | Уникальный идентификатор |
| `number` | `String` | Номер телефона (используется как логин) |
| `password` | `String` | Пароль (хранится в зашифрованном виде) |
| `role` | `Role` | Роль: `ADMIN` или `USER` |

---

## Быстрый старт

### Требования

- Java 17+
- Maven 3.6+
- PostgreSQL 14+

### 1. Клонирование репозитория

```bash
git clone https://github.com/AdylovAdil/Goodoc.git
cd Goodoc
```

### 2. Создание базы данных

```sql
CREATE DATABASE goodoc_db;
```

### 3. Настройка `application.properties`

Откройте файл `src/main/resources/application.properties` и укажите данные вашей базы данных:

```properties
spring.application.name=goodoc
server.port=9999

spring.datasource.url=jdbc:postgresql://localhost:5432/goodoc_db
spring.datasource.username=postgres
spring.datasource.password=ваш_пароль

spring.jpa.hibernate.ddl-auto=create

jwt.secret-key=ваш_секретный_ключ
jwt.expiration=86400000
```

> ⚠️ При первом запуске `spring.jpa.hibernate.ddl-auto=create` создаёт таблицы автоматически. После успешного запуска рекомендуется сменить значение на `update`, чтобы не потерять данные.

### 4. Запуск приложения

```bash
./mvnw spring-boot:run
```

Приложение запустится на порту **9999**: `http://localhost:9999`

### 5. Swagger UI

После запуска документация API доступна по адресу:

```
http://localhost:9999/swagger-ui/index.html
```

---

## REST API

Все эндпоинты, кроме `/auth/**`, требуют JWT-токен в заголовке:

```
Authorization: Bearer <ваш_токен>
```

---

### Аутентификация — `/auth`

#### `POST /auth/register` — Регистрация

**Request body:**
```json
{
  "number": "77001234567",
  "password": "secret123",
  "confirmPassword": "secret123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

---

#### `POST /auth/login` — Вход в систему

**Request body:**
```json
{
  "number": "77001234567",
  "password": "secret123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

---

### Лекарства — `/medicine`

| Метод | Эндпоинт | Описание |
|---|---|---|
| `GET` | `/medicine/all` | Получить все лекарства |
| `GET` | `/medicine/findById/{id}` | Получить лекарство по ID |
| `POST` | `/medicine/addMedicine` | Добавить лекарство |
| `PUT` | `/medicine/updateById/{id}` | Обновить лекарство по ID |
| `DELETE` | `/medicine/deleteById/{id}` | Удалить лекарство по ID |

#### `POST /medicine/addMedicine` — Добавить лекарство

**Request body:**
```json
{
  "name": "Парацетамол",
  "releaseForm": "таблетки",
  "reception": "после еды",
  "month": "APRIL",
  "day": 15,
  "year": 2025,
  "hour": 8,
  "minute": 30,
  "timePeriod": "AM",
  "additionally": "Запивать водой"
}
```

#### `GET /medicine/all` — Пример ответа

```json
[
  {
    "name": "Парацетамол",
    "releaseForm": "таблетки",
    "reception": "после еды",
    "nextDose": "2025-04-15",
    "time": "08:30:00",
    "additionally": "Запивать водой"
  }
]
```

---

### Формы выпуска — `/releaseForm`

| Метод | Эндпоинт | Описание |
|---|---|---|
| `GET` | `/releaseForm/all` | Получить все формы выпуска |
| `GET` | `/releaseForm/findById/{id}` | Получить форму выпуска по ID |
| `POST` | `/releaseForm/create` | Создать форму выпуска |
| `PUT` | `/releaseForm/updateById/{id}` | Обновить форму выпуска по ID |
| `DELETE` | `/releaseForm/deleteById/{id}` | Удалить форму выпуска по ID |

#### `POST /releaseForm/create` — Создать форму выпуска

**Request body:**
```json
{
  "name": "таблетки"
}
```

---

### Способы приёма — `/reception`

| Метод | Эндпоинт | Описание |
|---|---|---|
| `GET` | `/reception/all` | Получить все способы приёма |
| `GET` | `/reception/findById/{id}` | Получить способ приёма по ID |
| `POST` | `/reception/create` | Создать способ приёма |
| `PUT` | `/reception/updateById/{id}` | Обновить способ приёма по ID |
| `DELETE` | `/reception/deleteById/{id}` | Удалить способ приёма по ID |

#### `POST /reception/create` — Создать способ приёма

**Request body:**
```json
{
  "name": "после еды"
}
```

---

### Пользователи — `/user`

> Требует роль `ADMIN`.

| Метод | Эндпоинт | Описание |
|---|---|---|
| `GET` | `/user/all` | Получить всех пользователей |
| `GET` | `/user/findById/{id}` | Получить пользователя по ID |
| `DELETE` | `/user/deleteById/{id}` | Удалить пользователя по ID |

---

## Обработка ошибок

Все ошибки возвращаются в едином формате через `GlobalException`:

```json
{
  "message": "User not found",
  "status": 404
}
```

| HTTP-статус | Описание |
|---|---|
| `400 Bad Request` | Неверные данные запроса (например, пароли не совпадают) |
| `302 Found` | Пользователь уже существует |
| `401 Unauthorized` | Отсутствует или недействительный JWT-токен |
| `404 Not Found` | Ресурс не найден |

---

## Безопасность

- Аутентификация реализована через **JWT** (JSON Web Token).
- Пароли хранятся в зашифрованном виде (BCrypt).
- Все эндпоинты, кроме `/auth/**` и Swagger UI, требуют валидного JWT-токена.
- Приложение работает в **stateless**-режиме (без хранения сессий на сервере).

---

## Запуск тестов

```bash
./mvnw test
```

Тесты охватывают все контроллеры: `MedicineController`, `ReceptionController`, `ReleaseFormController`, `UserController`.
