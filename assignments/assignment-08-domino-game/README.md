# Assignment 08 — Domino Game

## Overview

This assignment was one of the most complete Java projects in COMP102. It involved building an interactive domino game using arrays, ArrayLists, objects, rules, mouse interaction, and graphical display.

## What I Built

- Domino class
- Domino hand using an array
- Domino table using an ArrayList
- Rule-based placement
- Mouse selection and placement
- Challenge features such as suggestions and dragging

## My Approach

I separated the domino itself from the game controller. The `Domino` class stores and draws individual dominoes, while the game class manages the hand, table, selected position, placement rules, and user interaction. Arrays were useful for the fixed-size hand, while an ArrayList worked better for the table because it changes size during the game.

## Key Takeaways

- Arrays and ArrayLists are useful in different situations.
- Object-oriented design helps separate game pieces from game logic.
- Interactive games require careful tracking of state, rules, and user input.

## Looking Back

If I revisited this assignment today, I would:

- Separate rule checking into its own helper methods or class.
- Remove practice/test code from the final project folder.
- Improve naming consistency in the `Domino` class.
- Add clearer comments for the challenge features.
