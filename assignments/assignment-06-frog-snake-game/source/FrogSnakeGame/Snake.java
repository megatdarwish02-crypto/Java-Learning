// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2026T1, Assignment 6
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;
import java.awt.Color;

/** The snake is created at a random position with a random 360 degree direction.
 * The constructor does not have any parameters.
 * A snake can move
 *  - makes it go forward one step in its current direction.
 *  - if outside arena boundaries, makes it go backward one step, and
 *         then turn to a new (random) direction.
 *  The walls of the arena are determined by the constants:
 *    ARENA_SIZE, TOP_WALL, BOT_WALL, LEFT_WALL and RIGHT_WALL
 * It can report its current position (x and y) with the
 *  getX() and getY() methods.
 *  move() will make it move in the direction it is going. 
 *  draw() will make it draw itself (image size should be 30).
 */



public class Snake{

    public static final int ARENA_SIZE = FrogSnakeGame.ARENA_SIZE;
    public static final int LEFT_WALL = FrogSnakeGame.LEFT_WALL;
    public static final int RIGHT_WALL = FrogSnakeGame.RIGHT_WALL;
    public static final int TOP_WALL = FrogSnakeGame.TOP_WALL;
    public static final int BOT_WALL = FrogSnakeGame.BOT_WALL;

    private double x;
    private double y;
    private double angle; // in radians
    private static final int SNAKE_SIZE = 30;

    
    public Snake() {
    this.x = LEFT_WALL + Math.random() * (ARENA_SIZE - SNAKE_SIZE);
    this.y = TOP_WALL + Math.random() * (ARENA_SIZE - SNAKE_SIZE);
    this.angle = Math.random() * 2 * Math.PI;
    }
    public void draw() {
    UI.drawImage("snake.jpg", x, y, SNAKE_SIZE, SNAKE_SIZE);
    }
    
    public void move() {
    double nextX = x + Math.cos(angle);
    double nextY = y + Math.sin(angle);

    // Wall collision: step back and pick new random angle
    if (nextX < LEFT_WALL || nextX + SNAKE_SIZE > RIGHT_WALL || 
        nextY < TOP_WALL || nextY + SNAKE_SIZE > BOT_WALL) {
        this.angle = Math.random() * 2 * Math.PI;
    } else {
        this.x = nextX;
        this.y = nextY;
    }
    }
    
    public double getX() { return x + SNAKE_SIZE / 2.0; }
    
    public double getY() { return y + SNAKE_SIZE / 2.0; }
    
}

