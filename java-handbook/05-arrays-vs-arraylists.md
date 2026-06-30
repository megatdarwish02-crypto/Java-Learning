# Arrays vs ArrayLists

Arrays and ArrayLists both store multiple values, but they feel different in practice.

## Arrays

Arrays have a fixed size.

```java
Domino[] hand = new Domino[5];
```

This means the hand always has 5 slots.

```text
hand
[0] [1] [2] [3] [4]
```

This was useful in the Domino game because the player's hand had a fixed number of positions.

## ArrayLists

ArrayLists can grow and shrink.

```java
ArrayList<Domino> table = new ArrayList<>();
```

This was useful for the dominoes placed on the table because the number of dominoes changes during the game.

```text
table
[Domino] [Domino] [Domino] ...
```

## Key Difference

```text
Array      = fixed size
ArrayList  = flexible size
```

## Why This Matters

If I know exactly how many items I need, an array can make sense.

If the number of items changes while the program runs, an ArrayList is usually easier.

## My Takeaway

The Domino assignment helped me see that data structure choice is not random. The structure should match the problem.

- Fixed hand → array
- Growing table → ArrayList
