# Assignment 07 — Mini Paint

## Overview

This assignment involved building a simple drawing application. The program responds to mouse input and allows the user to draw different shapes, choose colours, change line width, add text, erase, and use challenge features such as polygon drawing.

## What I Built

- Line, rectangle, and oval tools
- Fill and colour options
- Caption tool
- Eraser tool
- Line width and text size controls
- Polygon challenge feature

## My Approach

I used a `tool` variable to track the current drawing mode. When the mouse was pressed, dragged, or released, the program decided what to do based on the selected tool. For the challenge version, I used collections to store polygon points before drawing the completed shape.

## Key Takeaways

- GUI programs are driven by user events.
- A program can change behaviour depending on its current mode or state.
- Mouse input requires tracking positions across multiple actions.

## Looking Back

If I revisited this assignment today, I would:

- Use separate classes for different tools.
- Reduce the size of the main mouse-handling method.
- Make the tool state easier to manage.
- Add undo/redo support if expanding the project further.
