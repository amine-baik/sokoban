# Sokoban — Java Puzzle Game

Puzzle game implemented in Java with MVC architecture.
Academic project — Licence 2, Université d'Artois.

## Features
- Graphical interface (Swing) and text mode (terminal)
- 3 levels of increasing difficulty
- Move counter
- Restart function
- Automatic level progression

## How to run

**Graphical version (recommended)**

````java -jar sokoban.jar```

**Text version**

```java -jar sokoban-text.jar```

> Java must be installed on your machine. Download it at https://www.java.com

## Project structure

```
src/
├── modele/        — game logic (Carte, Robot, Direction...)
├── vueGraphique/  — Swing graphical interface
└── vueTexte/      — terminal display
map/               — level files (map1.txt, map2.txt, map3.txt)
img/               — game images
````

## Technologies

Java · Swing · OOP · MVC
