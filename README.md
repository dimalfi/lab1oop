# Лабораторна робота 1 — Лицар (Java, консольний додаток)

## Завдання
Визначити ієрархію амуніції лицаря. Екіпірувати лицаря. Порахувати вартість.
Провести сортування амуніції на основі ваги. Знайти елементи амуніції, що
відповідають заданому діапазону параметрів ціни.

## Технології
- Java 17
- Maven
- Unit-тести: JUnit 5 + Mockito

## Структура
- `ua.edu.knight.model` — сутності (Knight)
- `ua.edu.knight.model.ammunition` — ієрархія амуніції (успадкування/поліморфізм)
- `ua.edu.knight.repository` — завантаження початкових даних з файлу CSV
- `ua.edu.knight.service` — бізнес-логіка (екіпірування, сортування, фільтрація)
- `ua.edu.knight.app` — точка входу (мінімальний консольний вивід)

## Запуск
У корені проєкту:

### Запустити тести
```bash
mvn test
```

### Запустити додаток
```bash
mvn -q exec:java
```

## Ініціалізація даних
Файл `src/main/resources/equipment.csv` містить початкову амуніцію у форматі:

```
TYPE,NAME,WEIGHT_KG,PRICE_GOLD,PARAM
```

де `PARAM` — це:
- `damage` для `WEAPON`
- `defense` для захисної амуніції (`ARMOR/HELMET/SHIELD/BOOTS`)

## Рекомендації по git
- Робіть невеликі логічні коміти, наприклад:
  - `init maven project structure`
  - `add ammunition hierarchy`
  - `implement sorting and filtering`
  - `add unit tests for services`
