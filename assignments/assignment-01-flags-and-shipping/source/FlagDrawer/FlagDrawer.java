// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2026T1, Assignment 1
 * Name: Megat Adam Darwish
 * Username: binmegmega
 * ID: 300683739
 */

import ecs100.*;
import java.awt.Color;

/**
 * Draws various flags
 *
 * You can find lots of flag details (including the correct dimensions and colours)
 * from  http://www.crwflags.com/fotw/flags/    
 */

public class FlagDrawer{

    public static final double LEFT = 100;  // the left side of the flags
    public static final double TOP = 50;    // the top of the flags

    /**
     * CORE
     *
     * Draw the flag of Belgium.
     * The flag has three vertical stripes;
     * The left is black, the middle is yellow, and the right is red.
     * The flag is 13/15 as high as it is wide (ratio 13:15).
     */
    
    public void drawBelgiumFlag(){
        UI.clearGraphics();
        UI.println("Belgium Flag");
        double width = UI.askDouble("How wide: ");
        double height = width * (15.0/13.0);
        double stripe = width/3;
        UI.setColor(Color.black);
        UI.fillRect(LEFT, TOP, stripe, height);
        UI.setColor(Color.yellow);
        UI.fillRect(LEFT + stripe, TOP, stripe, height);
        UI.setColor(Color.red);
        UI.fillRect(LEFT + (stripe + stripe), TOP, stripe, height);
        
        UI.setColor(Color.black);
        UI.drawRect(LEFT, TOP, width, height);
    }

    /*
     * CORE
     *
     * The Red Cross flag consists of a white square with a red cross in the centre
     * The cross can be drawn as a horizontal rectangle and a vertical rectangle.
     */
    public void drawRedCrossFlag() {
        UI.clearGraphics();
        UI.println("Red Cross Flag: ");
        double size = UI.askDouble("How wide: ");
        UI.setColor(Color.white);
        UI.fillRect(LEFT, TOP, size, size);
        double pendek = size * 0.2;
        double panjang = size * 0.6;
        UI.setColor(Color.red);
        UI.fillRect(LEFT + (size * 0.4), TOP + (size * 0.2), pendek, panjang);
        UI.fillRect(LEFT + (size * 0.2), TOP + (size * 0.4), panjang, pendek);
        UI.setColor(Color.black);
        UI.drawRect(LEFT, TOP, size, size);
    }

    /**
     * COMPLETION
     *
     * Pacman
     * A red pacman facing up on a black background chasing yellow, green, and blue dots.
     */
    public  void drawPacman() {
        UI.clearGraphics();        
        UI.println("Pacman Flag");
        double width = UI.askDouble("How wide: ");
        double tall = width*1.5;
        UI.setColor(Color.black);
        UI.fillRect(LEFT, TOP, width, tall);
        UI.setColor(Color.red);
        UI.fillOval(LEFT+width*0.25, TOP+ width*0.7, width *0.5, width *0.5);
        UI.setColor(Color.black);
        UI.fillArc(LEFT+width*0.25, TOP+ width*0.7, width *0.5, width *0.5, 70, 40);
        UI.setColor(Color.yellow);
        UI.fillOval(LEFT+width*0.45, TOP+ width*0.6, width *0.1, width *0.1);
        UI.setColor(Color.green);
        UI.fillOval(LEFT+width*0.45, TOP+ width*0.4, width *0.1, width *0.1);
        UI.setColor(Color.blue);
        UI.fillOval(LEFT+width*0.45, TOP+ width*0.2, width *0.1, width *0.1);

    }

    /**
     * COMPLETION
     *
     * Draw the flag of Greenland.
     * The top half of the flag is white, and the bottom half is red.
     * There is a circle in the middle (off-set to left) which is
     * also half white/red but on the opposite sides.
     * The flag is 2/3 as high as it is wide (ratio 2:3).
     */
    public void drawGreenlandFlag() {
        UI.clearGraphics();
        UI.println("Greenland Flag");
        double width = UI.askDouble("How wide: ");
        double heigth = width * (2.0/3.0);
        UI.setColor(Color.white);
        UI.fillRect(LEFT, TOP, width, heigth);
        UI.setColor(Color.red);
        UI.fillRect(LEFT, TOP + heigth*0.5, width, heigth*0.5);
        UI.fillOval(LEFT + width*0.1, TOP + heigth*0.15, heigth *0.7, heigth *0.7);
        UI.setColor(Color.white);
        UI.fillArc(LEFT + width*0.1, TOP + heigth*0.15, heigth *0.7, heigth *0.7, 180, 180);
        UI.setColor(Color.black);
        UI.drawRect(LEFT, TOP, width, heigth);
    }

    /**
     * CHALLENGE
     *
     * Draw the People's Republic of Korea flag (1945-1946)
     * The background of the flag is white with three red horizontal stripes 
     * on each side of a central circle.
     * The central "taegeuk" circle is divided into a red left half
     *    and a blue right half separated by a vertical S-curve.
     * A thin white border separates this central circle from the red stripes.
     * The flag is 2/3 as high as it is wide (ratio 2:3).
     */    
    public void drawPRKFlag(){
        UI.clearGraphics();
        UI.println("Flag of South Korea");
        double width = UI.askDouble("How wide: ");
        double heigth = width * (2.0/3.0);
        
        UI.setColor(Color.white);
        UI.fillRect(LEFT, TOP, width, heigth);
        
        UI.setColor(Color.red);
        UI.fillRect(LEFT, TOP + heigth*0.35, width, heigth*0.3);
        
        UI.setColor(Color.white);
        UI.setLineWidth(3.0);
        UI.drawLine(LEFT, TOP + heigth*0.45, LEFT + width, TOP + heigth*0.45);
        UI.drawLine(LEFT, TOP + heigth*0.55, LEFT + width, TOP + heigth*0.55);
        
        UI.setColor(Color.red);
        UI.fillOval(LEFT + width*0.15, TOP + heigth*0.2, heigth *0.6, heigth *0.6);
        UI.setColor(Color.blue);
        UI.fillArc(LEFT + width*0.15, TOP + heigth*0.2, heigth *0.6, heigth *0.6, 270, 180);
        
        UI.setColor(Color.red);
        UI.fillOval(LEFT + width*0.25, TOP + heigth*0.2, heigth *0.3, heigth *0.3);
        UI.setColor(Color.blue);
        UI.fillOval(LEFT + width*0.25, TOP + heigth*0.5, heigth *0.3, heigth *0.3);
        
        UI.setColor(Color.white);
        UI.drawOval(LEFT + width*0.15, TOP + heigth*0.2, heigth *0.6, heigth *0.6);
        
        UI.setLineWidth(1.0);
        UI.setColor(Color.black);
        UI.drawRect(LEFT, TOP, width, heigth);
        

    }


    /**
     * Set up the GUI to have buttons to call each method
     */
    public void setupGUI(){
        UI.addButton("Clear", UI::clearPanes);
        UI.addButton("Core: Flag of Belgium", this::drawBelgiumFlag);
        UI.addButton("Core: Red Cross Flag",  this::drawRedCrossFlag);
        // COMPLETION
        UI.addButton("Completion: Pacman Flag", this::drawPacman);
        UI.addButton("Completion: Flag of Greenland", this::drawGreenlandFlag);
        // CHALLENGE
        UI.addButton("Challenge: People's Republic of Korea flag", this::drawPRKFlag);
        UI.addButton("Quit", UI::quit);

        UI.setDivider(0.3);
    }

    public static void main(String[] arguments){
        FlagDrawer fd = new FlagDrawer();
        fd.setupGUI();
    }

}
