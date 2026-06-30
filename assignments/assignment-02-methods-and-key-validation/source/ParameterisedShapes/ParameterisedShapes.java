// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2026T1, Assignment 2
 * Name: Mehat Adam Darwish 
 * Username: binmegmega
 * ID: 300682729
 */

import ecs100.*;
import java.awt.Color;
import javax.swing.JColorChooser;


/** Parameterised Shapes:
 * Core: draw a three stripes vertical flag
 * Completion: draw rectangles with three vertical stripes containing circles
 * Challenge:  draw rectangles with three vertical stripes containing stars
 */

public class ParameterisedShapes{

    //Constants for CORE
    public static final double FLAG_WIDTH = 200;
    public static final double FLAG_HEIGHT = 133;

    /**
     * CORE: draw three-striped flags
     *
     * Asks user for a position and three colours, then calls the
     * drawThreeStripeFlag method, passing the appropriate arguments
     */
    public void drawFlags(){
        double left = UI.askDouble("Left of flag");
        double top = UI.askDouble("Top of flag");
        UI.println("Choose the colours");
        Color col1 = JColorChooser.showDialog(null, "Left Stripe", Color.white);
        Color col2 = JColorChooser.showDialog(null, "Middle Stripe", Color.white);
        Color col3 = JColorChooser.showDialog(null, "Right Stripe", Color.white);
        
        //calling method
        this.drawThreeStripeFlag(left, top, col1, col2, col3);
    
    }

    /**
     * CORE
     * draws a three stripes flag at the given position consisting of
     * three equal size stripes of the given colors
     * The stripes are vertical
     * The size of the flag is specified by the constants FLAG_WIDTH and FLAG_HEIGHT
     */
    public void drawThreeStripeFlag(double left, double top, Color col1, Color col2, Color col3){
        //draw first stripe
        UI.setColor(col1);
        UI.fillRect(left, top, FLAG_WIDTH/3.0, FLAG_HEIGHT);
        
        //draw sec stripe
        UI.setColor(col2);
        UI.fillRect(left + FLAG_WIDTH/3.0, top, FLAG_WIDTH/3.0, FLAG_HEIGHT);
        
        //draw third stripe
        UI.setColor(col3);
        UI.fillRect(left + FLAG_WIDTH/3.0 + FLAG_WIDTH/3.0, top, FLAG_WIDTH/3.0, FLAG_HEIGHT);
        
        //outline
        UI.setColor(Color.black);
        UI.drawRect(left, top, FLAG_WIDTH, FLAG_HEIGHT);
    }

    /****************************************************************/
    /**
     * COMPLETION
     *
     * Asks user for a position, three colours, three widths and whether the circles are filled.
     * Then calls the drawFancyRect method, passing the appropriate arguments
     */
    public void doFancyRect(){
        double left = UI.askDouble("Left of rectangle");
        double top = UI.askDouble("Top of rectangle");
        UI.println("Now choose the colours");
        
        //choose colour
        Color col1 = JColorChooser.showDialog(null, "Left Stripe", Color.white);
        Color col2 = JColorChooser.showDialog(null, "Middle Stripe", Color.white);
        Color col3 = JColorChooser.showDialog(null, "Right Stripe", Color.white);
        
        //choose size
        double width1 = UI.askDouble("Width for first stripe:");
        double width2 = UI.askDouble("Width for second stripe:");
        double width3 = UI.askDouble("Width for third stripe:");
        
        //size of the flag
        double WIDTH = width1 + width2 + width3;
        double HEIGTH = WIDTH *2.0/3.0;
        
        //ask whether cirle is filled or not using boolean
        /** buat boolean tanya dulu, yes or no, 
         *then buat dummy variable, 1,0 - associate with variable
         *nanti dekat drawstripe, buat if variable =1, then print, conditional.
         */
        boolean filled = UI.askBoolean("Do you want to fill the circles?");
        
        
        this.drawFancyRect(left, top, width1, width2, width3, col1, col2, col3, filled, HEIGTH);

    }

    /**
     * COMPLETION
     *
     * Calculates the total height and width of the rectangle.
     * The height of the rectangle is 2/3 the width of the rectangle.
     * It then calls drawStripe three times to draw the three stripes,
     * and outlines the rectangle with a black contour.
     */
    public void drawFancyRect(double left, double top, double width1, double width2, double width3, Color col1, Color col2, Color col3, boolean filled, double HEIGTH){
        UI.clearGraphics();
        
        //outline
        UI.setColor(Color.black);
        UI.drawRect(left, top, FLAG_WIDTH, FLAG_HEIGHT);
        
        this.drawStripe(left, top, width1, width2, width3, col1, col2, col3, filled, HEIGTH);

    }

    /**
     * COMPLETION
     *
     * Draws a stripe at the given position that has the right circle at the right place.
     */
    public void drawStripe(double left, double top, double width1, double width2, double width3, Color col1, Color col2, Color col3, boolean filled, double HEIGTH){
        //draw three stripes
        UI.setColor(col1);
        UI.fillRect(left, top, width1, HEIGTH);
        UI.setColor(col2);
        UI.fillRect(left + width1, top, width2, HEIGTH);
        UI.setColor(col3);
        UI.fillRect(left + width1 + width2, top, width3, HEIGTH);
        
        //fill circle
        if (filled) {
        UI.setColor(Color.black);
        UI.fillOval(left + width1*0.4, top + HEIGTH*0.1, width1*0.2, width1*0.2);
        UI.fillOval(left + width1 + width2*0.4, top + HEIGTH*0.43, width2*0.2, width2*0.2);
        UI.fillOval(left + width1 + width2 + width3*0.4, top + HEIGTH*0.76, width3*0.2, width3*0.2);
            
    }
    
        //outline all three circle
        UI.setColor(Color.black);
        UI.drawOval(left + width1*0.4, top + HEIGTH*0.1, width1*0.2, width1*0.2);
        UI.drawOval(left + width1 + width2*0.4, top + HEIGTH*0.43, width2*0.2, width2*0.2);
        UI.drawOval(left + width1 + width2 + width3*0.4, top + HEIGTH*0.76, width3*0.2, width3*0.2);

    }
    
    public void doFancyRectChallenge(){
        double left = UI.askDouble("Left of rectangle");
        double top = UI.askDouble("Top of rectangle");
        UI.println("Now choose the colours");
        
        //choose colour
        Color col1 = JColorChooser.showDialog(null, "Left Stripe", Color.white);
        Color col2 = JColorChooser.showDialog(null, "Middle Stripe", Color.white);
        Color col3 = JColorChooser.showDialog(null, "Right Stripe", Color.white);
        
        //choose size
        double width1 = UI.askDouble("Width for first stripe:");
        double width2 = UI.askDouble("Width for second stripe:");
        double width3 = UI.askDouble("Width for third stripe:");
        
        //size of the flag
        double WIDTH = width1 + width2 + width3;
        double HEIGTH = WIDTH *2.0/3.0;
        double oneThirdH = HEIGTH / 3.0;
        
        //ask whether cirle is filled or not using boolean
        boolean filled = UI.askBoolean("Do you want to fill the stars?");
        
        this.drawFancyRectChallege(left, top, width1, width2, width3, col1, col2, col3, filled, HEIGTH, oneThirdH, WIDTH);
    }
    
    public void drawFancyRectChallege(double left, double top, double width1, double width2, double width3, Color col1, Color col2, Color col3, boolean filled, double HEIGTH, double oneThirdH, double WIDTH){
        UI.clearGraphics();
        
        //outline
        UI.setColor(Color.black);
        UI.drawRect(left, top, FLAG_WIDTH, FLAG_HEIGHT);
        
        this.drawStripeWisthStars(left, top, width1, width2, width3, col1, col2, col3, filled, HEIGTH, oneThirdH, WIDTH);
    }

    public void drawStripeWisthStars(double left, double top, double width1, double width2, double width3, Color col1, Color col2, Color col3, boolean filled, double HEIGTH, double oneThirdH, double WIDTH){
        UI.setColor(col1);
        UI.fillRect(left, top, width1, HEIGTH);
        UI.setColor(col2);
        UI.fillRect(left + width1, top, width2, HEIGTH);
        UI.setColor(col3);
        UI.fillRect(left + width1 + width2, top, width3, HEIGTH);
        
        // STRIPE 1: Left. midY is at the center of the TOP third
        double midY1 = top + (oneThirdH / 2.0);
        this.drawStripe(left, top, width1, HEIGTH, col1, midY1, filled);

        // STRIPE 2: Middle. midY is at the center of the MIDDLE third
        double midY2 = top + oneThirdH + (oneThirdH / 2.0);
        this.drawStripe(left + width1, top, width2, HEIGTH, col2, midY2, filled);

        // STRIPE 3: Right. midY is at the center of the BOTTOM third
        double midY3 = top + (2 * oneThirdH) + (oneThirdH / 2.0);
        this.drawStripe(left + width1 + width2, top, width3, HEIGTH, col3, midY3, filled);

        // Outline the full rectangle in black 
        UI.setColor(Color.black);
        UI.drawRect(left, top, WIDTH, HEIGTH);
        
    }
    
    /**
     * Draws a single stripe and the star inside it.
     */
    public void drawStripe(double x, double y, double w, double HEIGTH, Color col, double midY, boolean filled) {
        // Draw the background stripe
        UI.setColor(col);
        UI.fillRect(x, y, w, HEIGTH);

        // Calculate Star properties
        UI.setColor(Color.black);
        double midX = x + (w / 2.0);
        double radius = (w * 0.2) / 2.0; // Diameter is 20% of stripe width 

        // Star Geometry Logic (10 points) 
        double[] xPoints = new double[10];
        double[] yPoints = new double[10];
        for (int i = 0; i < 10; i++) {
            double r = (i % 2 == 0) ? radius : radius * 0.45; // Alternating tips and pits
            double angle = Math.toRadians(-90 + (i * 36)); // 36 degrees per point
            xPoints[i] = midX + r * Math.cos(angle);
            yPoints[i] = midY + r * Math.sin(angle);
        }

        // Use the boolean "switch" to decide how to draw
        if (filled) {
            UI.fillPolygon(xPoints, yPoints, 10);
        } else {
            UI.drawPolygon(xPoints, yPoints, 10);
        }
   
  
}
    
    
    public void setupGUI(){
        UI.initialise();
        UI.addButton("Clear", UI::clearPanes );
        UI.addButton("Core: Flags", this::drawFlags );
        UI.addButton("Completion: Fancy Rect", this::doFancyRect );
        UI.addButton("Challenge: Fancy Rect with stars", this::doFancyRectChallenge );
        // Add a button here to call your method for the challenge part
        UI.addButton("Quit", UI::quit );
    }

    public static void main(String[] args){
        ParameterisedShapes ps = new ParameterisedShapes ();
        ps.setupGUI();
    }

}



