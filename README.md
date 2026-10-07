# Лабораторна робота №1 - Data-Oriented Programming у Java

**Дисципліна:** Технології розробки на платформі Java
**Тема:** Сучасні можливості Java та підхід Data-Oriented Programming
**Виконала:** Студентка групи ІС-41 Малик Поліна

## Розрахунок варіанта

Для обчислення варіанту було використано порядковий номер у списку групи: **N = 11**.

```
V = N mod 3 = 11 mod 3 = 2
```

Таким чином маємо **Варіант V = 2** - система сповіщень (`Notification`).

## Опис проєкту

Усі класи знаходяться в пакеті `ua.kpi.comsys.dop`.

### Ієрархія даних (sealed interface + records)

```
+ Notification (sealed interface)
|--- TextMsg(String sender, String text)
|--- ImageMsg(String sender, String url, int sizeKb)
|--- AudioMsg(String sender, int durationSeconds)
```

Записи незмінні (immutable) і перевіряють дані в компактних конструкторах:

- `sender`, `text`, `url` не можуть бути `null`, порожніми або складатися лише з пробілів;
- `sizeKb` і `durationSeconds` мають бути більшими за 0.

У разі порушення кидається `IllegalArgumentException`.

### Обробник

`NotificationProcessor.process(Notification)` містить лише логіку (дані та алгоритми розділені за принципами DOP):

- для `null` кидає `IllegalArgumentException("Notification cannot be null")`;
- використовує вичерпний `switch` expression з record patterns без гілки `default`;
- формує результат через Text Blocks:

| Тип        | Результат                                                   |
|------------|-------------------------------------------------------------|
| `TextMsg`  | `Notification: TEXT`<br>`From: _`<br>`Message: _`           |
| `ImageMsg` | `Notification: IMAGE`<br>`From: _`<br>`URL: _ (_ KB)`       |
| `AudioMsg` | `Notification: AUDIO`<br>`From: _`<br>`Duration: _ seconds` |

### Структура

```
src/main/java/ua/kpi/comsys/dop/
    Notification.java           sealed interface
    TextMsg.java, ImageMsg.java, AudioMsg.java   records
    Validation.java             спільні перевірки для конструкторів
    NotificationProcessor.java  обробник
src/test/java/ua/kpi/comsys/dop/
    NotificationProcessorTest.java   усі гілки switch та null
    NotificationValidationTest.java  валідація конструкторів
```

## Запуск тестів

Потрібні JDK 21+ та Maven 3.9+.

```bash
mvn test
```

Звіти JUnit з'являються в `target/surefire-reports/`.
