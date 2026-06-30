# `==` vs `.equals()`

This was one of the Java concepts that felt small but turned out to be very important.

## `==`

For primitive values, `==` compares the actual values.

```java
int a = 5;
int b = 5;
System.out.println(a == b); // true
```

That makes sense because both variables directly store the value `5`.

## Objects and References

For objects, `==` compares whether two variables point to the exact same object.

```java
String s1 = new String("green");
String s2 = new String("green");
```

Even though the text is the same, these can be two different objects.

```text
s1 ──► "green"
s2 ──► "green"
```

They look the same, but they are not necessarily the same object.

## `.equals()`

`.equals()` is used when I care about the content or meaning of the object.

```java
state.equals("green")
```

I read this as:

> Does the content of `state` match the word `green`?

## Why This Was Important

In COMP102, this mattered for checking Strings, object states, and conditions.

Instead of writing:

```java
state == "green"
```

I should write:

```java
state.equals("green")
```

## My Takeaway

- Use `==` for primitives.
- Use `.equals()` when comparing the contents of objects such as Strings.
- For objects, `==` asks: *same object?*
- `.equals()` asks: *same value/meaning?*
