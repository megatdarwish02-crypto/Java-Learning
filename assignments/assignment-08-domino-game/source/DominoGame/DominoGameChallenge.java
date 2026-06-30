// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2026T1, Assignment 8
 * Name: Megat Adam
 * Username:
 * ID: 300683739
 */

import ecs100.*;
import java.util.*;
import java.awt.Color;

/**
 *  Lets a player play a simple Solitaire dominoes game.
 *  Dominoes are rectangular tiles with two numbers from 0 to 6 on
 *  them (shown with dots).
 *  The player has a "hand" which can contain up to five dominoes.
 *  They can
 *  - reorder the dominoes in their hand,
 *  - place dominoes from their hand onto the table,
 *  - pick up more dominoes from a bag to fill the gaps in their "hand".
 *  - replace a domino from their hand back into the bag.
 *  The core does not involve any of the matching of real dominoes games.
 *  The completion introduces checking rules.
 *
 *  PROGRAM DESIGN
 *  The dominoes are represented by objects of the Domino class.
 *  The Domino constructor will construct a new, random domino.
 *  Dominos have a draw(double x, double y, boolean horiz) method that will draw the
 *    Domino centered at the specified position, either vertically or horizontally.
 *  
 *  The program has three key fields:
 *    hand:  an array that can hold 5 Dominos. 
 *    table: an ArrayList of the Dominos that have been placed on the table.
 *    applyRules: a boolean that states whether the game follows the placing rules.
 *    
 *  The hand should be displayed near the top of the Graphics pane with a
 *   rectangular border and each domino drawn at its place in the hand.
 *  Empty spaces in the hand should be represented by nulls and displayed as empty.
 *
 *  The user can select a position on the hand using the mouse.
 *  The selected domino (or empty space) should be highlighted with
 *  a border around it.
 *  
 *  The user can use the "Left" or "Right" button to move the selected domino
 *  (or the space) to the left or the right, in which case the domino is
 *  swapped with the contents of the adjacent position in the hand.
 *  If the selected position contains a domino, the user
 *  can use the "Place" button to move the selected domino to the table.
 *  
 *  If there are any empty positions on the hand, the user can use the
 *  "Pickup" button to get a new (random) domino which will be added to
 *  the hand at the leftmost empty position.
 *
 *  The table is represented by an ArrayList of dominos.
 *  At the beginning of the game the table should be empty.
 *  Dominos should be added to the end of the table.
 *  The table should be displayed in rows at the top of the graphics pane.
 */

public class DominoGameChallenge {
    public static final int NUM_HAND = 5;    // Number of dominos in hand
 
    // Fields: hand, table and selectedPos
    private Domino[] hand;              // the hand (fixed size array of Dominos)
    private ArrayList<Domino> table;    // the table (variable sized list of Dominos)
 
    private int selectedPos = 0;        // selected position in the hand.
 
    private boolean applyRules = false; // Whether standard matching rules are enforced.
 
    // --- Drag and Drop state fields ---
    private boolean dragging = false;   // true while a drag is in progress
    private int dragFromPos = -1;       // which hand index the drag started from
    private Domino dragDomino = null;   // the domino currently being dragged
    private double dragX = 0;          // current mouse X during drag (for visual feedback)
    private double dragY = 0;          // current mouse Y during drag (for visual feedback)
 
    // =========================================================
    // CORE METHODS
    // =========================================================
 
    /**
     * CORE
     * Reset the game state so a new game can begin.
     */
    public void restart() {
        this.hand = new Domino[NUM_HAND];
        this.table = new ArrayList<Domino>();
        this.selectedPos = 0;
        this.dragging = false;
        this.dragFromPos = -1;
        this.dragDomino = null;
        this.redraw();
    }
 
    /**
     * CORE
     * If there is at least one empty position on the hand, create a new random
     * domino and put it into the first empty position on the hand.
     */
    public void pickup() {
        for (int i = 0; i < NUM_HAND; i++) {
            if (this.hand[i] == null) {
                this.hand[i] = new Domino();
                break;
            }
        }
        this.redraw();
    }
 
    /**
     * CORE
     * Remove domino from selected position on hand (if there is one).
     */
    public void removeDomino() {
        if (this.hand[this.selectedPos] != null) {
            this.hand[this.selectedPos] = null;
        }
        this.redraw();
    }
 
    /**
     * CORE
     * Move the domino at the selected position to the table (no rules).
     */
    public void placeDominoCore() {
        if (this.hand[this.selectedPos] != null) {
            this.table.add(this.hand[this.selectedPos]);
            this.hand[this.selectedPos] = null;
        }
        this.redraw();
    }
 
    // =========================================================
    // COMPLETION METHODS
    // =========================================================
 
    /**
     * COMPLETION
     * Move the selected position's contents to the leftmost position,
     * shifting items that were to its left one step to the right.
     */
    public void moveToLeftEnd() {
        Domino selected = this.hand[this.selectedPos];
        for (int i = this.selectedPos; i > 0; i--) {
            this.hand[i] = this.hand[i - 1];
        }
        this.hand[0] = selected;
        this.selectedPos = 0;
        this.redraw();
    }
 
    /**
     * COMPLETION
     * Move the selected position's contents to the rightmost position,
     * shifting items that were to its right one step to the left.
     */
    public void moveToRightEnd() {
        Domino selected = this.hand[this.selectedPos];
        for (int i = this.selectedPos; i < NUM_HAND - 1; i++) {
            this.hand[i] = this.hand[i + 1];
        }
        this.hand[NUM_HAND - 1] = selected;
        this.selectedPos = NUM_HAND - 1;
        this.redraw();
    }
 
    /**
     * COMPLETION
     * Place domino with rules enforced (or not).
     * Orients the domino correctly to maintain the chain.
     */
    public void placeDominoCompl() {
        Domino domino = this.hand[this.selectedPos];
        if (domino == null) {
            this.redraw();
            return;
        }
 
        if (!applyRules) {
            this.table.add(domino);
            this.hand[this.selectedPos] = null;
        } else {
            if (this.table.isEmpty()) {
                if (domino.getFirst() == domino.getSecond()) {
                    this.table.add(domino);
                    this.hand[this.selectedPos] = null;
                } else {
                    UI.printMessage("Invalid move: the first domino placed must be a double.");
                }
            } else {
                Domino lastOnTable = this.table.get(this.table.size() - 1);
                int requiredNumber = lastOnTable.getSecond();
 
                if (domino.getFirst() == requiredNumber) {
                    this.table.add(domino);
                    this.hand[this.selectedPos] = null;
                } else if (domino.getSecond() == requiredNumber) {
                    domino.flipNums();
                    this.table.add(domino);
                    this.hand[this.selectedPos] = null;
                } else {
                    UI.printMessage("Invalid move: neither number on the domino matches ["
                        + requiredNumber + "].");
                }
            }
        }
        this.redraw();
    }
 
    // =========================================================
    // CHALLENGE METHOD 1: SMART SUGGESTION (7 points)
    // =========================================================
 
    /**
     * CHALLENGE
     * Scans the hand to find a domino that can legally be placed on the table
     * according to the standard domino rules, regardless of whether applyRules
     * is currently toggled on or off.
     *
     * Rules checked:
     *   - If the table is empty: only a double (same number on both sides) is valid.
     *   - Otherwise: a domino is valid if either its first or second number matches
     *     the second number of the last domino on the table.
     *
     * Reports the result via UI.printMessage.
     */
    public void suggestDomino() {
        if (this.table.isEmpty()) {
            // Need a double to start
            for (int i = 0; i < NUM_HAND; i++) {
                Domino d = this.hand[i];
                if (d != null && d.getFirst() == d.getSecond()) {
                    UI.printMessage("The [" + d.getFirst() + "|" + d.getSecond()
                        + "] domino can be placed (it's a double to start the chain).");
                    return;
                }
            }
            UI.printMessage("No legal moves: you need a double to start, but none are in your hand.");
        } else {
            // Match the right-hand number of the last table domino
            Domino lastOnTable = this.table.get(this.table.size() - 1);
            int required = lastOnTable.getSecond();
 
            for (int i = 0; i < NUM_HAND; i++) {
                Domino d = this.hand[i];
                if (d != null) {
                    if (d.getFirst() == required || d.getSecond() == required) {
                        // Report in the orientation it would be placed
                        int shownFirst = (d.getFirst() == required) ? d.getFirst() : d.getSecond();
                        int shownSecond = (d.getFirst() == required) ? d.getSecond() : d.getFirst();
                        UI.printMessage("The [" + shownFirst + "|" + shownSecond
                            + "] domino can be placed (matches [" + required + "] on the table).");
                        return;
                    }
                }
            }
            UI.printMessage("No legal moves: no domino in your hand matches ["
                + required + "] on the table.");
        }
    }
 
    // =========================================================
    // CHALLENGE METHOD 2: INTRA-HAND DRAG AND DROP (13 points)
    // =========================================================
 
    /**
     * Helper: converts an x-coordinate to a hand index, or -1 if outside the hand.
     */
    private int handIndexAt(double x, double y) {
        if (Math.abs(y - HAND_Y) > Domino.LENGTH / 2) return -1;
        if (x < HAND_LEFT - Domino.WIDTH / 2) return -1;
        if (x > HAND_LEFT + (NUM_HAND - 0.5) * (Domino.WIDTH + SPACING)) return -1;
        int idx = (int) ((x - (HAND_LEFT - Domino.WIDTH / 2)) / (Domino.WIDTH + SPACING));
        if (idx < 0 || idx >= NUM_HAND) return -1;
        return idx;
    }
 
    /**
     * CHALLENGE
     * Handles all mouse events for both hand selection and drag-and-drop.
     *
     * "pressed"  – start a drag if the mouse is pressed on a domino in the hand.
     * "dragged"  – update the drag position for visual feedback.
     * "released" – finish the drag: insert at target position (with shifting) or
     *              place in an empty slot.  Falls back to plain selection if no
     *              drag was started.
     */
    public void doMouse(String action, double x, double y) {
        if (action.equals("pressed")) {
            int idx = handIndexAt(x, y);
            if (idx != -1 && this.hand[idx] != null) {
                // Start dragging
                this.dragging = true;
                this.dragFromPos = idx;
                this.dragDomino = this.hand[idx];
                this.dragX = x;
                this.dragY = y;
                // Temporarily remove from hand so the slot looks empty during drag
                this.hand[idx] = null;
                this.redraw();
            }
 
        } else if (action.equals("dragged")) {
            if (this.dragging) {
                this.dragX = x;
                this.dragY = y;
                this.redraw();   // redraws hand + table, then draws the floating domino
            }
 
        } else if (action.equals("released")) {
            if (this.dragging) {
                this.dragging = false;
                int targetIdx = handIndexAt(x, y);
 
                if (targetIdx == -1) {
                    // Dropped outside the hand — return to original position
                    this.hand[this.dragFromPos] = this.dragDomino;
                } else if (this.hand[targetIdx] == null) {
                    // --- Dropped onto an EMPTY slot: simply place it there ---
                    this.hand[targetIdx] = this.dragDomino;
                    this.selectedPos = targetIdx;
                } else {
                    // --- Dropped onto a DOMINO: insert with shifting ---
                    //
                    // The dragged domino goes to targetIdx.
                    // All dominoes between dragFromPos and targetIdx shift
                    // one step toward dragFromPos to fill the gap, preserving
                    // their relative order.
                    if (targetIdx > this.dragFromPos) {
                        // Shift items left from dragFromPos+1 up to targetIdx
                        for (int i = this.dragFromPos; i < targetIdx; i++) {
                            this.hand[i] = this.hand[i + 1];
                        }
                    } else {
                        // Shift items right from dragFromPos-1 down to targetIdx
                        for (int i = this.dragFromPos; i > targetIdx; i--) {
                            this.hand[i] = this.hand[i - 1];
                        }
                    }
                    this.hand[targetIdx] = this.dragDomino;
                    this.selectedPos = targetIdx;
                }
 
                this.dragDomino = null;
                this.dragFromPos = -1;
                this.redraw();
 
            } else {
                // Plain click (no drag): select position in hand
                int idx = handIndexAt(x, y);
                if (idx != -1) {
                    this.selectedPos = idx;
                    this.redraw();
                }
            }
        }
    }
 
    // =========================================================
    // GUI AND DISPLAY METHODS — provided / adapted for Challenge
    // =========================================================
 
    /**
     * Set up the GUI with buttons and mouse.
     * Adds the Suggest button for the Challenge.
     */
    public void setupGUI() {
        UI.setMouseListener(this::doMouse);
        UI.addButton("Pickup", this::pickup);
        UI.addButton("Put back", this::removeDomino);
        UI.addButton("Place", this::placeDomino);
        UI.addButton("Left", this::moveToLeftEnd);
        UI.addButton("Right", this::moveToRightEnd);
        UI.addButton("Suggest", this::suggestDomino);       // CHALLENGE button
        UI.addButton("Rules Enforced", this::toggleRules);
        UI.addButton("Restart", this::restart);
        UI.addButton("Quit", UI::quit);
 
        UI.setWindowSize(1100, 500);
        UI.setDivider(0.0);
        this.restart();
    }
 
    /** Toggle the rules flag. */
    public void toggleRules() {
        applyRules = !applyRules;
    }
 
    /** Dispatch to core or completion place method. */
    public void placeDomino() {
        if (applyRules) {
            placeDominoCompl();
        } else {
            placeDominoCore();
        }
    }
 
    // Layout constants
    public static final int HAND_LEFT = 80;
    public static final int HAND_Y    = 60;
    public static final int TABLE_LEFT = 60;
    public static final int TABLE_Y   = 170;
    public static final int SPACING   = 4;
 
    /**
     * Redraw everything. If a drag is in progress, also draw the floating domino.
     */
    public void redraw() {
        UI.clearGraphics();
        UI.printMessage("");
        this.drawHand();
        this.drawTable();
        // Draw the domino following the cursor during a drag
        if (this.dragging && this.dragDomino != null) {
            this.dragDomino.draw(this.dragX, this.dragY, false);
        }
    }
 
    /**
     * Draws the hand with its border and the green selection highlight.
     */
    public void drawHand() {
        for (int t = 0; t < this.hand.length; t++) {
            if (this.hand[t] != null) {
                int x = HAND_LEFT + t * (Domino.WIDTH + SPACING);
                this.hand[t].draw(x, HAND_Y, false);
            }
        }
        UI.setLineWidth(2);
        UI.setColor(Color.black);
        double border = 4;
        UI.drawRect(HAND_LEFT - Domino.WIDTH / 2 - border, HAND_Y - Domino.LENGTH / 2 - border,
            (Domino.WIDTH + SPACING) * NUM_HAND + 2 * border, Domino.LENGTH + 2 * border);
        UI.setLineWidth(2);
        UI.setColor(Color.green);
        int selLeft = HAND_LEFT - Domino.WIDTH / 2 + (this.selectedPos * (Domino.WIDTH + SPACING)) - 2;
        UI.drawRect(selLeft, HAND_Y - Domino.LENGTH / 2 - 2, Domino.WIDTH + SPACING, Domino.LENGTH + 4);
    }
 
    /**
     * Draws the dominos on the table in rows of 7.
     */
    public void drawTable() {
        int x = TABLE_LEFT;
        int y = TABLE_Y;
        int count = 0;
        for (Domino domino : this.table) {
            domino.draw(x, y, true);
            x = x + Domino.LENGTH + SPACING;
            count++;
            if (count == 7) {
                x = TABLE_LEFT;
                y = y + Domino.WIDTH + 3 * SPACING;
                count = 0;
            }
        }
    }
 
    /** Entry point. */
    public static void main(String[] args) {
        DominoGameChallenge obj = new DominoGameChallenge();
        obj.setupGUI();
        obj.restart();
    }
}
