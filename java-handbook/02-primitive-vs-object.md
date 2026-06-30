# Primitive vs Object

One concept that became important in COMP102 was the difference between primitive values and objects.

## Primitive Values

Primitive variables store the actual value directly.

Examples:

```java
int age = 20;
double price = 12.50;
boolean valid = true;
char grade = 'A';
```

My mental model:

```text
age ── 20
price ── 12.50
valid ── true
```

The variable directly contains the value.

## Objects

Objects are different. A variable does not store the whole object directly. It stores a reference to an object somewhere else in memory.

Example:

```java
Animal dog = new Animal("dog", 100, 200);
```

My mental model:

```text
dog ──► Animal object
        type: dog
        x: 100
        y: 200
```

The variable `dog` points to an object.

## Why This Matters

This explains why object behaviour can feel confusing at first.

```java
Animal a1 = new Animal("dog", 100, 200);
Animal a2 = a1;
```

This does not create two animals. It creates two references to the same animal object.

```text
a1 ──┐
     ├──► Animal object
a2 ──┘
```

If the object changes through `a1`, it also appears changed through `a2` because both variables point to the same object.

## My Takeaway

Primitives are values. Objects are referenced.

That sentence helped me understand why Java behaves differently when comparing numbers, Strings, arrays, ArrayLists, and custom objects.
