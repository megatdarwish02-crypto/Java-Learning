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


/**
 * Manages a frog whose movement is controlled by the player.
 *
 * A new frog starts at the given position, with the given direction, and
 *   has either a "light" or "dark" shade.
 * Frogs can turn in 4 directions: left, right, up, and down. 
 * A frog moves around at a constant speed within an enclosed arena following
 *   its direction, until it hits a wall. At which point it stops moving.
 * Frogs can grow in size and, for the completion task, can also shrink by 
 *   resetting their size to the original value.
 *
 * The walls of the arena are determined by the following constants:
 *   FrogGame.TOP_WALL, FrogGame.BOT_WALL, FrogGame.LEFT_WALL, and FrogGame.RIGHT_WALL.
 */

public class Frog {
    // Constants
    public static final int INITIAL_SIZE = 30;
    public static final int GROWTH_RATE = 2;
    public static final int SPEED = 2;

    public static final int LEFT_WALL = FrogGame.LEFT_WALL;
    public static final int RIGHT_WALL = FrogGame.RIGHT_WALL;
    public static final int TOP_WALL = FrogGame.TOP_WALL;
    public static final int BOT_WALL = FrogGame.BOT_WALL;

    // Fields
    private String frog;
    private double currentX;
    private double currentY;
    private String dir;
    private int size;
    
    

    /*# YOUR CODE HERE */

    //Constructor 
    /** 
     * Make a new frog
     * The parameters specify the initial position of the top left corner,
     *   the direction, and the shade of the Frog ("light" or "dark")
     * We assume that the position is within the boundaries of the arena
     */
    public Frog(double left, double top, String dir, String shade)  {
        
        this.currentX = left;
        this.currentY = top;
        this.dir = dir;
        this.frog = shade;
        this.size = INITIAL_SIZE;
    }

    /**
     * Turn right  (don't redraw)
     */
    public void turnRight(){
        this.dir = "Right";

    }

    /**
     * Turn left  (don't redraw)
     */
    public void turnLeft(){
        this.dir = "Left";

    }

    /**
     * Turn up  (don't redraw)
     */
    public void turnUp(){
        this.dir = "Up";

    }

    /**
     * Turn down  (don't redraw)
     */
    public void turnDown(){
        this.dir = "Down";

    }

    /**
     * Moves the frog: 
     *   use SPEED unit forward in the correct direction
     *   by changing the position of the frog.
     * Make sure that the frog does not go outside the arena, by making sure 
     *  - the top of the frog is never smaller than FrogGame.TOP_WALL
     *  - the bottom of the frog is never greater than FrogGame.BOT_WALL
     *  - the left of the frog is never smaller than FrogGame.LEFT_WALL
     *  - the right of the frog is never greater than FrogGame.RIGHT_WALL
     *  DO NOT REDRAW THE FROG!!!
     */
    public void move() {
        if (dir.equals("Right") && (currentX + size + SPEED <= RIGHT_WALL)) { currentX += SPEED; }
        else if (dir.equals("Left") && (currentX - SPEED >= LEFT_WALL)) { currentX -= SPEED; }
        else if (dir.equals("Up") && (currentY - SPEED >= TOP_WALL)) { currentY -= SPEED; }
        else if (dir.equals("Down") && (currentY + size + SPEED <= BOT_WALL)) { currentY += SPEED; }

    }

    /**
     * Check whether the frog isTouching the given point, eg, whether the
     *   given point is included in the area covered by the frog.
     * Return true if the frog is on the top of the position (x, y)
     * Return false otherwise
     */
    public boolean isTouching(double x, double y) {
        return (x >= currentX && x <= currentX + size && y >= currentY && y <= currentY + size);

    }


    /**
     * The Frog has just eaten a bug
     * Makes the frog grow larger by GROWTH_RATE.
     */
   public void grow(){
        this.size += GROWTH_RATE;

    }

    /**
     * The Frog has just bumped into a snake
     * Makes the frog size reset to its original size
     * ONLY NEEDED FOR COMPLETION
     */
    public void shrink(){
        this.size = INITIAL_SIZE;

    }

    /**
     * Draws the frog at the current position and the current size.
     * Use the correct image file (darkfrog.jpg or lightfrog.jpg)
     */
    public void draw(){
        String fname = null;
        fname = this.frog + "frog.jpg"; 
        UI.drawImage(fname, this.currentX, this.currentY, this.size, this.size);

    }
}

