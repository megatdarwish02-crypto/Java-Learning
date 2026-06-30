# Object-Oriented Programming Reflection

Object-oriented programming started making more sense when I stopped thinking of classes as just files.

A class is more like a blueprint for something that has:

- information it stores,
- actions it can perform,
- and rules for how other code should interact with it.

## Example Mental Model

```java
public class Domino {
    private int first;
    private int second;
}
```

A domino object stores its own numbers.

```text
Domino object
- first number
- second number
```

The game does not need to manually track every number separately. It can ask each Domino object for information.

## Why This Was Important

In earlier assignments, most code was inside one class. Later assignments forced me to think in terms of multiple classes interacting.

Examples:

- `Animal` and `PetShow`
- `Frog`, `Snake`, and `FrogSnakeGame`
- `Domino` and `DominoGame`

This made the code feel more like a system.

## My Takeaway

Good object-oriented code is not just about having many classes. It is about giving each class a clear responsibility.

When designing a class, I should ask:

1. What information should this object store?
2. What actions should this object be responsible for?
3. What should other classes be allowed to access?
4. What should stay private?
