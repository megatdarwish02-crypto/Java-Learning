// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2026T1, Assignment 3
 * Name: Megat Adam 
 * Username: binmegmega
 * ID:
 */

import ecs100.*;

/**
 * Program to create simple animated animal character using the
 * Animal class.  
 */

public class PetShow{

    /** CORE
     * animate creates two or several animals on the window.
     *  Then animates them according to a fixed script by calling a series
     *  of methods on the animals.
     */
    public void animate(){
         Animal dog = new Animal("dog", "kikki", 200, 200);
         dog.jump(50);
         dog.goRight(50);
         dog.speak("watashino kikki");
         
         Animal tiger = new Animal("tiger", "Gaga", 100, 100);
         tiger.speak("wo shi gaga");
         tiger.goRight(100);
         tiger.speak("I will eat you kikki, here i come");

    }

    /**
     *  threeAnimalsRoutine creates three animals on the window.
     *  Then makes each animal do the same routine in turn.
     *  You should define a routine method, and threeAnimalsRoutine
     *   should call the routine method three times, to make
     *   each of the three animals perform the routine in turn.
     */
    public void threeAnimalsRoutine(){
        Animal dog = new Animal("dog", "kikki", 200, 200);
        Animal tiger = new Animal("tiger", "Gaga", 100, 100);
        Animal snake = new Animal("snake", "rara", 300, 300);
        routine(dog);
        routine(tiger);
        routine(snake);

    }

    // uncomment this for the completion.
    // public void routine(... ){
    public void routine(Animal performer){
        performer.introduce("konnichiwa");
        performer.goLeft(50);
        performer.jump(50);
        performer.goRight(100);
    }

    // }public void routine(double 50
    //public void routine(Animal typeOfAnimal

    /**
     * Make buttons to let the user run the methods
     */
    public void setupGUI(){
        UI.initialise();
        UI.addButton("Clear", UI::clearGraphics );
        UI.addButton("Animate", this::animate );
        UI.addButton("Three", this::threeAnimalsRoutine );
        UI.addButton("Quit", UI::quit );
        UI.setDivider(0);       // Expand the graphics area
    }

    /**
     * Create object and call setupGUI on it
     */
    public static void main(String[] args){
        PetShow ps = new PetShow();
        ps.setupGUI();
    }
}

