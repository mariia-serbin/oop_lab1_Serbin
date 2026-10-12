# Овочевий салат (Лабораторна робота №1, ООП)

Консольний застосунок на Java: будує салат з овочів, рахує його калорійність,
сортує овочі за заданим параметром і знаходить овочі в указаному діапазоні калорійності.

## Технології

- Java 21
- Gradle (wrapper)
- JUnit 5, Mockito

## Структура пакетів

- `oop.lab1.serbin.model` — ієрархія овочів
- `oop.lab1.serbin.service` — логіка салату
- `oop.lab1.serbin.io` — читання даних з файлів
- `oop.lab1.serbin.app` — консольний вхід

## Ієрархія овочів

```
Vegetable (abstract)
├── LeafyVegetable
├── RootVegetable
└── FruitingVegetable
```

`Vegetable` інкапсулює назву, вагу (г) і калорійність на 100 г; калорійність порції
рахується як `weight / 100 * caloriesPer100g`. Кожен нащадок додає власне поле та
перевизначає `getCategory()` (поліморфізм).

## Вхідні файли (`src/main/resources`)

- `salad.csv` — овочі салату, формат: `type;name;weightGrams;caloriesPer100g;extra`,
  де `type` = `LEAFY` / `ROOT` / `FRUITING`
- `salad.properties`:
  - `salad.name` — назва салату
  - `salad.sort.parameter` — `CALORIES` або `WEIGHT`
  - `salad.calorie.range.min`, `salad.calorie.range.max` — діапазон (ккал/100 г)

## Запуск

```
./gradlew run
```

Меню: `1` — калорійність, `2` — сортування, `3` — пошук за діапазоном, `0` — вихід.

## Тести

```
./gradlew test
```

68 unit-тестів (JUnit 5 + Mockito) покривають усі класи.
