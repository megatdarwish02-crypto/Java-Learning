// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2026T1, Assignment 2
 * Name: Megat Adam Darwish
 * Username: binmegmega
 * ID: 300683739
 */

import ecs100.*;

/**
 * Key:
 * Core:       Method must report whether the key is valid, or
 *             report that it is invalid and give one reason why it is invalid.
 *             To be valid, the key must
 *             - be at least 7 characters and at most 15 characters long,
 *             - not end with the special characters '&' or '%',
 *             - not have an underscore ('_') character anywhere.
 *            
 * Completion: Method should either report that the key is valid, or
 *             report that it is invalid and list ALL the reasons that it is invalid.
 *             To be valid, the key must
 *             - satisfy all of the conditions above AND
 *             - have at least one Upper case character and at least one Lower case character,
 *             - not start with the same character as the first character of the user's name (case sensitive)
 *             - contain either a '&' or a '%', but not both.
 *
 * Challenge:  Same as completion, except that to be valid, the key must
 *             - satisfy all of the conditions above AND
 *             - have a mix of numbers and letters
 *             - must contain exactly 4 digits
 *             - the sum of those 4 digits must be an even number. 
 *
 * Hint.  Look at the documentation in the String class.
 * You will definitely find the length(), endsWith(...), and contains(...)
 * methods to be helpful
 */

public class KeyValidator {

    /**
     * Asks user for key word and then checks if it is a valid key word.
     */
    public void doCore(){
        UI.clearText();
        String key = UI.askString("Key:   ");
        UI.println();
        this.validateKeyCore(key);
    }

    /** CORE
     * Report "Valid" or "Invalid: ...reason...."
     */
    public void validateKeyCore(String key){
        int num = key.length();
        boolean ends = key.endsWith("&") || key.endsWith("%");
        boolean underscore = key.contains("_");
        
        if (num >= 7 && num < 15 && ends == false && underscore == false) {
            UI.println("the key is valid");
        }
        else
        {
            UI.println("the key is invalid");
        }
    }

    /**
     * Asks user for key word and the name and then checks if it is a valid key word.
     */
    public void doCompletion(){
        UI.clearText();
        String key = UI.askString("Key:   ");
        String name = UI.askString("Your name:   ");
        UI.println();
        this.validateKeyCompletion(key, name);
    }

    /** COMPLETION
     * Report that the key is valid or report ALL the rules that the key failed.
     */
    public void validateKeyCompletion(String key, String name){
        int num = key.length();
        boolean ends = key.endsWith("&") || key.endsWith("%");
        boolean underscore = key.contains("_");
        boolean foundUpper = false;
        boolean foundLower = false;
        boolean and = key.contains("&");
        boolean percentage = key.contains("%");
        
        boolean no1 = false;
        boolean no2 = false;
        boolean no3 = false;
        boolean no4 = false;
        boolean no5 = false;
        boolean no6 = false;
        
    
        
        if (num >= 7 && num < 15) {
             no1 = true;
        }
        else {
             no1 = false;
        }
        
        if (ends == false) {
             no2 = true;
        }
        else {
             no2 = false;
        }
        
        if (underscore == false) {
             no3 = true;
        }
        else {
             no3 = false;
        }
        
        /** scan everyword ada upper or lower case tak
         * 
         */
        //scanning every char in the key
        for (int i = 0; i < num; i++) {
            char c = key.charAt(i); //take the character at position "i"
            
            if (Character.isUpperCase(c)) {
                foundUpper = true;
            }
            if (Character.isLowerCase(c)) {
                foundLower = true;
            }
            }
            
         if (foundUpper == true && foundLower == true) {
             no4 = true;
        }
        else {
             no4 = false;
        }   
        
        for (int n = 0; n < 1 ; n++) {
        char m = name.charAt(n);
        char f = key.charAt(0);
        if (m != f) {
             no5 = true;
        }
        else {
             no5 = false;
        }
        }
        
        if (percentage == true && and == false || percentage == false && and == true) {
             no6 =true;
        }
        else {
             no5 = false;
        }
        
        
        //report valid ke tak
        if (no1 && no2 && no3 && no4 && no5 && no6){
            UI.println("the key is valid");
        }
        else {
            UI.println("the key is not valid");
        }
        
        //kalau x valid, report problem
        if (no1 == false){
            UI.println("The key must be at least 7 characters long, and no more than 15 characters long");
        }
        if (no2 == false){
            UI.println("The key must not end with the special characters '&' or '%");
        }
        if (no3 == false){
            UI.println("The key must not have an underscore ('_') character anywhere");
        }
        if (no4 == false){
            UI.println("The key must have at least one uppercase character and at least one lowercase character");
        }if (no5 == false){
            UI.println("The key must NOT start with the same character as the first character of the user's name. Note: This is case sensitive (e.g., if the name is 'Monique', the key cannot start with 'M' but can start with 'm')");
        }
        if (no6 == false){
            UI.println("The key must contain at least one '&' or at least one '%', but must not contain a mix of '&' and '%' characters");
        }
        
    }

    /**
     * Asks user for key word and the name and then checks if it is a valid key word.
     */
    public void doChallenge(){
        UI.clearText();
        String key = UI.askString("Key:   ");
        String name = UI.askString("Your name:   ");
        UI.println();
        this.validateKeyChallenge(key, name);
    }
    
    /** CHALLENGE
     * Report that the key is valid or report ALL the rules that the key failed.
     */
    public void validateKeyChallenge(String key, String name){
        // Existing rules from Completion
        int num = key.length();
        boolean validLength = (num >= 7 && num <= 15);
        boolean validEnd = !(key.endsWith("&") || key.endsWith("%"));
        boolean noUnderscore = !key.contains("_");
        boolean validStart = (key.charAt(0) != name.charAt(0));
        boolean noMix = (key.contains("&") ^ key.contains("%")); // Use XOR for "one or the other but not both"

        // New Flags for Challenge
        boolean foundUpper = false;
        boolean foundLower = false;
        boolean foundLetter = false;
        int digitCount = 0;
        int digitSum = 0;

        // One single loop to scan for everything
        for (int i = 0; i < num; i++) {
            char c = key.charAt(i);
            if (Character.isUpperCase(c)) foundUpper = true;
            if (Character.isLowerCase(c)) foundLower = true;
            if (Character.isLetter(c)) foundLetter = true;
            
            if (Character.isDigit(c)) {
                digitCount++;
                digitSum += Character.getNumericValue(c); // Accumulate the sum
            }
        }

        // Final Challenge Rule checks 
        boolean hasMix = (foundLetter && digitCount > 0); 
        boolean exactlyFourDigits = (digitCount == 4);    
        boolean evenSum = (digitSum % 2 == 0);            

        // 1. Check if EVERYTHING passed
        if (validLength && validEnd && noUnderscore && foundUpper && foundLower && 
            validStart && noMix && hasMix && exactlyFourDigits && evenSum) {
            UI.println("Key is valid!");
        } else {
            // 2. Report ALL failures 
            UI.println("Key is INVALID. Failed rules:");
            if (!validLength) UI.println("Must be between 7-15 characters");
            if (!validEnd) UI.println("Cannot end with & or %");
            if (!noUnderscore) UI.println("Cannot contain underscores");
            if (!foundUpper || !foundLower) UI.println("Must have mix of Upper and Lower case");
            if (!validStart) UI.println("Cannot start with " + name.charAt(0));
            if (!noMix) UI.println("Must contain & or %, but not both");
            if (!hasMix) UI.println("Must include a mix of letters and numbers");
            if (!exactlyFourDigits) UI.println("Must contain exactly 4 digits (Found: " + digitCount + ")");
            if (exactlyFourDigits && !evenSum) UI.println("The sum of the 4 digits must be even (Sum: " + digitSum + ")");
        }
    }
 

    public void setupGUI(){
        UI.initialise();
        UI.addButton("Clear", UI::clearText );
        UI.addButton("Validate Key Core", this::doCore );
        UI.addButton("Validate Key Completion", this::doCompletion );
        UI.addButton("Validate Key Challenge", this::doChallenge );
        UI.addButton("Quit", UI::quit );
        UI.setDivider(1);       // Expand the text area
    }

    public static void main(String[] args){
        KeyValidator kv = new KeyValidator();
        kv.setupGUI();
    }
}
