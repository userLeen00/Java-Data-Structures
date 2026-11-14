# Java Data Structures - Degree Planner

A console application for managing a student's degree plan, built for the Data Structures & Algorithms course at Al Hussein Technical University (HTU). Every data structure is **implemented from scratch** (no `java.util` collections) and kept behind a clear interface, using encapsulation and abstraction.

## Features

| Menu option | Feature | Data structure |
|---|---|---|
| Add / remove / view courses | Dynamic course list | **Singly linked list** (`CourseLinkedList`) |
| Undo last change | Undo of course add/remove actions | **Stack, LIFO** (`UndoStack`, `StackNode`, `StackInterface`) |
| Add completed course (with grade), search by grade, highest / lowest grade | Completed-course records ordered by grade | **Binary search tree** (`CourseBST`, `BSTNode`) |
| Show unlocked courses | Prerequisite chains between courses | **Graph with adjacency lists** (`Graph`, `GraphNode`) |

Each class and method carries a header comment stating its purpose, dependencies and time complexity (for example, BST insert and search are O(log n) on average and O(n) in the worst case).

## Project structure

```
degree-planner/src/
├── Main.java              entry point
├── Menu.java              console menu, connects all features
├── Course.java, CourseNode.java
├── CourseLinkedList.java
├── Action.java, UndoStack.java, StackNode.java, StackInterface.java
├── CourseBST.java, BSTNode.java
└── Graph.java, GraphNode.java
```

## Run

Requires JDK 11 or later.

```bash
cd degree-planner
javac -d out src/*.java
java -cp out Main
```

## Tech

Java (OOP, abstract data types), Eclipse IDE

## Author

Leen Shannak - Computer Science student, HTU
