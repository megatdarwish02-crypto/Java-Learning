// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2026T1, Assignment 7
 * Name: Adam
 * Username:binmegmega
 * ID:
 */

import ecs100.*;
import java.awt.Color;
import javax.swing.JColorChooser;
import java.util.ArrayList;
/**
 * A simple drawing program.
 * The user can select from a variety of tools and options using the buttons and
 *   elements down the left side, and can use the mouse to add elements to the drawing
 *   according to the current tool and options
 * Note, most of the "action" in the program happens in response to mouse events;
 *   the buttons, textFields, and sliders mostly record information that is used
 *   later when responding to mouse events
 */


public class MiniPaintChallenge{

    // fields to remember:
    //  - the "tool" - what will be drawn when the mouse is next released.
    //                 may be a shape, or a caption,
    //    [Completion] or eraser
    //  - whether the shape should be filled or not
    //  - the position the mouse was pressed,
    //  - the string for the text caption
    //  - [Completion] the width of the lines and the font size of the text captions.
    //  - [Completion] the colours for the border and fill for shapes and captions

    private String tool = "Line";   // the current tool, governing what the mouse will do.
                                    // Initial value is "Line";  changed by the buttons.

    // More fields
    private double currentX, currentY;
    private boolean fill;
    private Color colorLine;
    private Color currentColor;
    private String caption;
    private double widthLine;
    private double captionSize;
    
    private ArrayList<Double> polyX = new ArrayList<>();
    private ArrayList<Double> polyY = new ArrayList<>();

    /**
     * Set up the interface:
     *   buttons, textfields, sliders,
     *   listening to the mouse
    */
    public void setupGUI(){
        UI.addButton("Clear", UI::clearGraphics);
        UI.addButton("Line", this::doSetLine); 
        UI.addButton("Rectangle", this::doSetRect);
        UI.addButton("Oval", this::doSetOval);
        UI.addTextField("add caption textfield", this::doCaption);
        
        UI.setMouseMotionListener(this::doMouse);
        UI.setMouseListener(this::doMouse);

        UI.addButton("fill/not filled", this::doFill);
        UI.setMouseListener(this::doMouse);
        UI.addButton("Color for line",this::doChooseColourLine);
        UI.addButton("Color for shapes",this::doChooseColour);
        UI.addButton( "Eraser", this::addEraser);
        
        UI.addSlider( "Line Width", 1, 50, 8, this::lineWidth);
        UI.addSlider( "Text Size",1, 50, 20, this::textSize);
        
        UI.addButton("Polygon", this::doPolygon);
        UI.addButton("Finish Poly", this::finishPolygon);
        
        UI.addButton("Quit", UI::quit);
        UI.setDivider(0.0);  // Hide the text area.
        
        this.updateStatus();
    }
    
    public void updateStatus() {
    UI.clearGraphics(); // Note: This clears the whole pane; for a professional look,
                        // just print to the message bar.
    UI.printMessage("Tool: " + tool + " | Fill: " + fill + " | Color: " + colorLine.toString());
}

    // ---------------------------------------------------------
    // Methods to respond to the buttons, textfield, and sliders
    // Mostly, These methods just save information to the fields.
    // ---------------------------------------------------------

    /** Respond to the Line button */
    public void doSetLine(){
        this.tool = "line";
    }
    
    public void doSetRect() {
        this.tool = "Rect";
    }
    
    public void doSetOval() {
        this.tool = "Oval";
    }
    
    public void doCaption(String text) {
        this.tool = "Caption";
        caption = text;
    }

    public void doFill() {
        this.fill =! fill;
        if (this.fill) {
            UI.println("Switched to FILL mode");
        }
        else {
            UI.println("Switched to OUTLINE mode");
        }
    }
    
    public void doChooseColour(){
        this.currentColor = JColorChooser.showDialog(null, "Choose Color", this.currentColor);
    }
      
    public void doChooseColourLine(){
        this.colorLine = JColorChooser.showDialog(null, "Choose Color", this.colorLine);
    }
    
    public void lineWidth (double size) {
        widthLine = size;
    }
    
    public void textSize (double font) {
        captionSize = font;
    }
    
    public void addEraser () {
        this.tool = "Eraser";
    }
    
    public void doPolygon() {
        this.tool = "Polygon";
    }
    // The method header for doSetLine was given as an example;
    // you will need other methods for each button/textfield/slider

    // -------------------------------
    // Methods to respond to the mouse
    // -------------------------------
    
    /**
     * Respond to mouse events
     * When pressed, remember the position.
     * When released, draw what is specified by current tool
     * Uses the value stored in the field to determine which kind of tool to draw.
     *  It should call the drawALine, drawARectangle, drawAnOval, etc, methods
     *  passing the pressed and released positions
     * [Completion] should respond to "dragged" events also to do erasing and freehand
     */
    public void doMouse(String action, double x, double y) {
        if (action.equals("pressed")) {
            this.currentX = x;
            this.currentY = y;
            
            if (this.tool.equals("Polygon")) {
            polyX.add(x);
            polyY.add(y);
            UI.fillOval(x-2, y-2, 4, 4);
            
        }
        else if (action.equals("released")) {
            if (tool.equals("line") ){
                    this.drawALine(currentX, currentY, x, y );
                }
            if (tool.equals("Rect") ) {
                    this.drawARectangle (currentX, currentY,  x, y );
                }
            if (tool.equals("Oval") ){
                    this.drawAnOval(currentX, currentY, x , y);
                }
            if (tool.equals("Caption")) {
                    this.setAddCaption( caption, x, y);
                }
            UI.repaintGraphics();
            }
        else if (action.equals("dragged")) {
            if (this.tool.equals("Eraser")) {
            this.doErase(x, y);
        }
        }
    }
}
    
    // -----------------------------------------------------
    // Methods called by doMouse to actually draw the shapes
    // -----------------------------------------------------
    /**
     * Draw a line between the two positions (x1, y1) and (x2, y2)
     */
    public void drawALine(double x1, double y1, double x2, double y2){
        UI.setColor(Color.black);
        UI.setColor(colorLine);
        UI.setLineWidth(widthLine);
        UI.drawLine( x1, y1, x2, y2);
    }

    /**
     * Draw a rectangle between the two diagonal corners
     * [Completion] Works out the left, top, width, and height 
     * Then draws the rectangle, based on the options
     */
    public void drawARectangle(double x1, double y1, double x2, double y2){
        double left = Math.min(x1, x2);
        double top = Math.min(y1, y2);
        double width = Math.abs(x1 - x2);
        double height = Math.abs(y1 - y2);
        
        if (this.fill) {
            UI.setColor(Color.black);
            UI.setColor(currentColor);
            UI.fillRect(left, top, width, height);
            
            UI.setColor(Color.black);
            UI.setColor(colorLine);
            UI.drawRect(left, top, width, height);
        } else {
            UI.setColor(Color.black);
            UI.setColor(colorLine);
            UI.drawRect(left, top, width, height);
        }
    }
    
    public void drawAnOval (double x1, double y1, double x2, double y2) {
        double left = Math.min(x1, x2);
        double top = Math.min(y1, y2);
        double width = Math.abs(x1 - x2);
        double height = Math.abs(y1 - y2);
        
        if (this.fill) {
            UI.setColor(Color.black);
            UI.setColor(currentColor);
            UI.fillOval(left, top, width, height);
            
            UI.setColor(Color.black);
            UI.setColor(colorLine);
            UI.drawOval(left, top, width, height);
        } else {
            UI.setColor(Color.black);
            UI.setColor(colorLine);
            UI.drawOval(left, top, width, height);
        }
    }
    
    public void setAddCaption(String caption, double x, double y) {
        UI.setColor(Color.black);
        UI.setColor(colorLine);
        UI.setFontSize(captionSize);
        UI.drawString( caption, x, y);
    }
    
    public void doErase (double x, double y) {
        double size = 50;
        UI.setColor(Color.white);
        UI.fillOval(x - size/2, y - size/2, size, size);
    }
    
    public void finishPolygon() {
    if (polyX.size() < 3) return;
    
    double[] xPoints = new double[polyX.size()];
    double[] yPoints = new double[polyY.size()];
    for (int i = 0; i < polyX.size(); i++) {
        xPoints[i] = polyX.get(i);
        yPoints[i] = polyY.get(i);
    }

    if (this.fill) {
        UI.setColor(this.currentColor);
        UI.fillPolygon(xPoints, yPoints, polyX.size());
    }
    UI.setColor(this.colorLine);
    UI.drawPolygon(xPoints, yPoints, polyX.size());
    
    polyX.clear();
    polyY.clear();
}


private void drawInverted(double x1, double y1, double x2, double y2) {
    double left = Math.min(x1, x2);
    double top = Math.min(y1, y2);
    double width = Math.abs(x1 - x2);
    double height = Math.abs(y1 - y2);

    if (this.tool.equals("Line")) { UI.invertLine(x1, y1, x2, y2); }
    else if (this.tool.equals("Rect")) { UI.invertRect(left, top, width, height); }
    else if (this.tool.equals("Oval")) { UI.invertOval(left, top, width, height); }
}

    
    // drawALine and drawARect were given as an example;
    // you will need other methods for the other shapes

    

    // Main:  constructs a new MiniPaint object and set up GUI
    public static void main(String[] arguments){
        MiniPaint mp = new MiniPaint();
        mp.setupGUI();
    }
}
