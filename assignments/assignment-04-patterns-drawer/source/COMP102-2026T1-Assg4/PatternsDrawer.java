// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2026T1, Assignment 4
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;
import java.awt.Color;

/** PatternsDrawer: 
 * Draws four different repetitive patterns. */

public class PatternsDrawer{

    public static final double PATTERN_LEFT = 50.0;   // Left side of the pattern
    public static final double PATTERN_TOP = 50.0;    // Top of the pattern
    public static final double PATTERN_SIZE = 300.0;  // The size of the pattern on the window

    /** 
     * Draws a star pattern consisting of a circle containing black rays 
     * separated by white regions.
     * Asks the user for the number of rays, and calculates the angle of the rays.
     * Hint: use filled arcs to draw the rays,
     */
    public void drawStar(){
        UI.clearGraphics();
        UI.setColor(Color.black);
        double num = UI.askInt("How many rays:");
        double angle = 360 / num;
        
        //draw the circle
        UI.drawOval(PATTERN_LEFT, PATTERN_TOP, PATTERN_SIZE, PATTERN_SIZE);
        
        //draw the black ray, skipping one pie
        for( int i = 0; i < num ; i += 2) {
            UI.fillArc( PATTERN_LEFT, PATTERN_TOP, PATTERN_SIZE, PATTERN_SIZE, angle * i , angle);
        }

    }

    /** 
     * Draws a triangular grid of equally sized squares that creates the
     * optical illusion of dark circles appearing at the intersections. 
     * The gap between the squares should be 1/3 the size of the squares.
     * It asks the user for the number of rows. 
     * The top row should have one square; the next row should have two squares;
     * and so on. The last row will have as many squares as there are rows.
     * Hint: requires a nested loop
     */
    public void drawTriGrid(){
        UI.clearGraphics();
        UI.setColor(Color.black);
        int num = UI.askInt("How many rows:");
        
        //generalized the size of square - based on the longest row
        double square = PATTERN_SIZE / ( num + ( (num - 1.0)/3.0));
        
        for (int i = 1; i < num + 1; i ++){
            double height = (PATTERN_TOP + (4.0/3.0)* square * i) - square;
            for( int column = 0; column < i; column ++) {
                UI.fillRect(PATTERN_LEFT + (4.0/3.0)*square*column , height, square, square);
            }
        }
        

    }

    /**
     * Draws a rectangle containing a row of randomly sized blue circles
     * that fit within the rectangle.
     * The width of the rectangle is PATTERN_SIZE.
     * The method asks user for the height of the rectangle, which must be
     * less than the width: the method should keep asking for a height until
     * the user gives a height less than the width.
     * It then draws the rectangle and fills it from left to right with
     * randomly sized blue circles (each with a diameter between 3 units and
     * the specified height)
     *
     * Hint: use a while loop to ask for the height,
     * Hint: use another while loop to work out the size and draw each circle,
     *  stopping when the next circle won't fit.
     *  At the end, if there is still room, draw the appropriate sized circle
     *  to fill the gap.
     */
    public void drawRandomCircles(){
        UI.clearGraphics();
        double width = PATTERN_SIZE;
        
        //asking for the height within the patternsize lenght
        double height = UI.askDouble("What is the height of the rectangle?");
        while( height <= 0 && height > PATTERN_SIZE) {
        height = UI.askDouble("What is the height of the rectangle?");
        }
        
        //draw the rectangle
        UI.setColor(Color.black);
        UI.drawRect(PATTERN_LEFT, PATTERN_TOP, width, height);
        
        //draw the circle, one by one moving to the right until the total diameter < width
        UI.setColor(Color.blue);
        double totalDiameter = 0;
        double diameter = 0;
        while(totalDiameter < width) {
            totalDiameter += diameter;
            diameter = 3 + Math.random() * height;
            if ( totalDiameter + diameter < width ) {
            UI.fillOval(PATTERN_LEFT + totalDiameter, PATTERN_TOP + ((height - diameter)/2 ), diameter , diameter);
            }
            else {
                break;
            }
        }
        
        
        
        //draw the last circle, the "reminder"
        UI.fillOval( PATTERN_LEFT + totalDiameter, PATTERN_TOP + ((height - (width - totalDiameter))/2 ), width - totalDiameter, width - totalDiameter);
        
    }
    

    
    /**
     * Draws a square spiral pathway made of small circles arranged in the
     * shape of a square spiral
     *  Asks the user for the size of the circles
     *   and computes the number of circles needed to fit the PATTERN_SIZE
     *  Then draws all the circles from the outside to the inside.
     *  The gap between the legs of the spiral should be the width of the circles
     */
    
   

    public void drawSpiralPath() {
        UI.clearGraphics();
        double diameter = UI.askDouble("What is the size of the circle?");
        double x = PATTERN_LEFT;  // Starting X coordinate
        double y = PATTERN_TOP;  // Starting Y coordinate
        double legLength = PATTERN_SIZE;
        char direction = 'D'; 
        int turnCount = 0;

        // Loop until the path is too small to draw another circle
        while (legLength >= diameter) {
            
            // 1.Draw the leg
            drawCircles(x, y, legLength, diameter, direction);

            // 2.Update the start position for the NEXT leg
            // We move the start coordinate to the end of the leg we just drew
            if (direction == 'R') { 
                x = x + legLength; 
                direction = 'U'; 
            } else if (direction == 'D') { 
                y = y + legLength; 
                direction = 'R'; 
            } else if (direction == 'L') { 
                x = x - legLength; 
                direction = 'D'; 
            } else if (direction == 'U') { 
                y = y - legLength; 
                direction = 'L'; 
            }

            // 3.Update the shrinking logic
            turnCount++;
            if (turnCount % 2 == 0) {
                legLength = legLength - diameter*2;
            }
        }
    }

    /**
     * Worker Method: Only handles drawing one line of circles.
     */
    public void drawCircles(double x, double y, double length, double diameter, char direction) {
        int numCircles = (int) (length / diameter);

        for (int i = 0; i < numCircles; i++) {
            UI.drawOval(x, y, diameter, diameter);

            // Advance the local drawing position for the next circle in this line
            if (direction == 'R') { x = x + diameter; }
            else if (direction == 'D') { y = y + diameter; }
            else if (direction == 'L') { x = x - diameter; }
            else if (direction == 'U') { y = y - diameter; }
        }
    }

    
    
    

    /**
     * Set up the GUI and buttons
     */
    public void setupGUI(){
        UI.initialise();
        UI.addButton("Clear",UI::clearPanes);
        UI.addButton("Core: Star", this::drawStar);
        UI.addButton("Core: TriGrid", this::drawTriGrid);
        UI.addButton("Completion: Random", this::drawRandomCircles);
        UI.addButton("Challenge: Spiral", this:: drawSpiralPath);
        UI.addButton("Quit", UI::quit);
    }   

    /**
     * main: create object and call setupGUI
     */
    public static void main(String[] arguments){
        PatternsDrawer pd = new PatternsDrawer();
        pd.setupGUI();
    }
}
