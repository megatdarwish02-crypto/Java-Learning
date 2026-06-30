# How I Think About Java

When I first learned Java, the hardest part was not the syntax. The harder part was understanding what the code *means*.

For example, this line:

```java
light.state.equals("green")
```

is not just random dots and words. I now read it as:

> Check the `state` field that belongs to the `light` object, and compare its value with `"green"`.

That way of reading code matters. Java becomes much easier when I stop memorising syntax and start reading it like object relationships.

## My Mental Model

```text
object.field
object.method()
```

means:

```text
Go to this object
↓
Access something that belongs to it
```

So when I see:

```java
car.speed
car.accelerate()
```

I read it as:

- `car.speed` = the speed value stored inside this specific car object
- `car.accelerate()` = call the behaviour that belongs to this specific car object

## Why This Was Important

Earlier, I used to think too much in terms of isolated lines of code. COMP102 slowly pushed me to think in terms of:

- objects,
- state,
- behaviour,
- references,
- and interactions between classes.

That shift is important because cybersecurity and software engineering both require understanding how systems behave, not just writing syntax that compiles.
