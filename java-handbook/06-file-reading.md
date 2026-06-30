# File Reading in Java

File reading felt confusing at first because it combines several things at once:

- opening a file,
- scanning through data,
- handling lines or tokens,
- converting text into useful values.

## Basic Pattern

A common COMP102 pattern was:

```java
try {
    Scanner scan = new Scanner(new File(filename));
    while (scan.hasNext()) {
        String token = scan.next();
        // process token
    }
    scan.close();
} catch (IOException e) {
    UI.println("File error: " + e);
}
```

## How I Read This

```text
Open file
↓
While there is still data
↓
Read the next piece
↓
Convert/store/use it
↓
Repeat
```

## Why It Matters

File reading turns a program from something that only reacts to one user input into something that can process real data.

Assignments like RoadProfiler and WeatherReporter made this clearer because the program had to read structured information and produce useful output.

## My Takeaway

The hard part is not only reading the file. The hard part is understanding the structure of the data.

Before coding, I should ask:

- Is the file line-based or token-based?
- What does each column mean?
- Do I need numbers, strings, or both?
- Am I reading one item or many items?
