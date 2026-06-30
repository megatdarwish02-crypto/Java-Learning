// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2026T1, Assignment 8
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;
import java.awt.Color;

/**
 * Domino
 * Represents individual Dominos - rectangular tiles with two numbers
 * The constructor will return a new Domino with two random numbers (0-6) on it
 * Methods:
 *   draw(double x, double y, boolean horiz)  draws the domino centered at (x,y),
 *      horizontally (first number on left, second on right) if horiz is true;
 *      vertically (first number above second) if horiz is false
 *    
 *   flipNums() swaps the two numbers on the domino
 *   getFirst() and getSecond() return the first/second numbers of the domino
 *   
 */

public class Domino{

    private int first;
    private int secnd;

    public static final int WIDTH = 50;       // the short dimension of the domino 
    public static final int LENGTH = WIDTH*2;  // the long dimension of the domino 
    public static final int DIAM = WIDTH/6;   // the diameter of the spots

    /**
     * Construct a new Domino object with a pair of random values on it
     */
    public Domino(){
        this.first = (int)(Math.random()*7); // between 0 and 6 inclusive
        this.secnd = (int)(Math.random()*7); // between 0 and 6 inclusive
    }

    /**
     * Switches the numbers on the domino
     */
    public void flipNums(){
        if (this.first != this.secnd){
            int tmp = this.first;
            this.first = this.secnd;
            this.secnd = tmp;
        }
    }

    /**
     * Return the left number on the Domino
     */
    public int getFirst(){
        return this.first;
    }

    /**
     * Return the right number on the Domino
     */
    public int getSecond(){
        return this.secnd;
    }

    /**
     * Draws the domino centered at the position (x, y)
     * either vertically or horizontally
     */
    public void draw(double x, double y, boolean horiz){
        UI.setLineWidth(2);
        UI.setColor(Color.black);
        double wd = (horiz)?LENGTH:WIDTH;
        double ht = (horiz)?WIDTH:LENGTH;
        double left = x - wd/2;
        double top = y - ht/2;
        UI.fillRect(left, top, wd, ht);
        UI.setColor(Color.red.darker());
        UI.drawRect(left, top, wd, ht);

        UI.setLineWidth(2);
        UI.setColor(Color.gray);
        if (horiz){
            UI.drawLine(x, top+2, x, top+ht-2);
            UI.setLineWidth(1);
            this.drawNumber(this.first, x-wd/4, y);
            this.drawNumber(this.secnd, x+wd/4, y);
        }
        else {
            UI.drawLine(left+2, y, left+wd-2, y);
            UI.setLineWidth(1);
            this.drawNumber(this.first, x, y-ht/4);
            this.drawNumber(this.secnd, x, y+ht/4);
        }
    }

    /**
     * Draw the number in the square centered at (x, y)
     * using  white circles
     * The size of the square is WIDTH times WIDTH
     */
    public void drawNumber(int num, double x, double y){
        double xOff = x-DIAM/2;  // offset by radius of spots
        double yOff = y-DIAM/2;  // offset by radius of spots
        double left  = xOff-WIDTH*0.25;
        double centr = xOff;
        double right = xOff+WIDTH*0.25;
        double top = yOff-WIDTH*0.25;
        double mid = yOff;
        double bot = yOff+WIDTH*0.25;
        UI.setColor(Color.white);
        if (num%2 == 1){   // 1, 3, 5
            UI.fillOval(centr, mid, DIAM, DIAM);
        }
        if (num>1){    // 2, 3, 4, 5, 6
            UI.fillOval(left, top, DIAM, DIAM);
            UI.fillOval(right, bot, DIAM, DIAM);
        }
        if (num>3){ //4, 5, 6
            UI.fillOval(left, bot, DIAM, DIAM);
            UI.fillOval(right, top, DIAM, DIAM);
        }
        if (num==6){
            UI.fillOval(left, mid, DIAM, DIAM);
            UI.fillOval(right, mid, DIAM, DIAM);
        }
    }


}
