# References, Heap, and Stack

This was one of the most important ideas for me in Java.

At first, I thought a variable that stores an object somehow contained the full object. Later I realised that object variables usually store references.

## Stack

The stack stores local variables and method information.

Example:

```java
int x = 5;
Animal pet = new Animal("dog", 100, 200);
```

A simplified view:

```text
Stack
-----
x   = 5
pet = reference/address
```

## Heap

The heap stores objects created with `new`.

```text
Heap
----
Animal object
- type: dog
- x: 100
- y: 200
```

So the full picture is:

```text
Stack                    Heap
-----                    ----
x = 5
pet ───────────────────► Animal object
                         type: dog
                         x: 100
                         y: 200
```

## Why This Helped Me

This helped me understand code like:

```java
ArrayList<Animal> animals = new ArrayList<>();
animals.add(new Animal("dog", 100, 200));
```

The ArrayList does not magically become the animal. It stores references to Animal objects.

```text
animals ──► ArrayList ──► Animal object
```

## My Takeaway

When Java code becomes confusing, I try to ask:

> Am I working with the actual value, or am I working with a reference to an object?

That question makes object-oriented programming much easier to reason about.
