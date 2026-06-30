// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2026T1, Assignment 5
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;
import java.util.*;
import java.nio.file.*;
import java.io.*;
import java.awt.Color;

/**
 * WeatherReporter
 * Analyses weather data from files of weather-station measurements.
 *
 * The weather data files consist of a set of measurements from weather stations around
 * New Zealand at a series of date/time stamps.
 * For each date/time, the file has:
 *  A line with the date and time (four integers for day, month, year, and time)
 *   eg "24 01 2021 1900"  for 24 Jan 2021 at 19:00 
 *  A line with the number of weather-stations for that date/time 
 *  Followed by a line of data for each weather station:
 *   - name: one token, eg "Cape-Reinga"
 *   - (x, y) coordinates on the map: two numbers, eg   186 38
 *   - four numbers for temperature, dew-point, suface-pressure, and sea-level-pressure
 * Some of the data files (eg weather1-hot.txt, and weather1-cold.txt) have data for just one date/time.
 * The weather-all.txt has data for lots of times. The date/times are all in order.
 * You should look at the files before trying to complete the methods below.
 *
 * Note, the data files were extracted from MetOffice weather data from 24-26 January 2021
 */

public class WeatherReporter{

    public static final double DIAM = 10;       // The diameter of the temperature circles.    
    public static final double LEFT_TEXT = 10;  // The left of the date text
    public static final double TOP_TEXT = 50;   // The top of the date text

    /**   CORE
     * Plots the temperatures for one date/time from a file on a map of NZ
     * Asks for the name of the file and opens a Scanner
     * It is good design to call plotSnapshot, passing the Scanner as an argument.
     */
    public void plotTemperatures(){
        //ask the user which file report in the same folder to be scanned first
        String filename = UI.askString("Enter a file name:");
        
        try {
        
        //create the object file to match with the chosen filename
        File realFile = new File (filename);
        
        //create the cursor mouse/ scanner (an object) for the real file
        Scanner sc = new Scanner (realFile);
        
        //pass the object of scanner as an argument to the next method
        this.plotSnapshot(sc);
        
    } catch (IOException e) {
        UI.println ("the file not found" + filename );
    }
    }

    


    /**
     * CORE:
     *  Plot the temperatures for the next snapshot in the file by drawing
     *   a filled coloured circle (size DIAM) at each weather-station location.
     *  The colour of the circle should indicate the temperature.
     *
     *  The method should
     *   - read the date/time and draw the date/time at the top-left of the map.
     *   - read the number of stations, then
     *   - for each station,
     *     - read the name, coordinates, and data, and
     *     - plot the temperature for that station. 
     *   (Hint: You will find the getTemperatureColor(...) method useful.)
     *
     *  COMPLETION:
     *  Also finds the highest and lowest temperatures at that time, and
     *  plots them with a larger circle.
     *  (Hint: If more than one station has the highest (or coolest) temperature,
     *         you only need to draw a larger circle for one of them.
     */     
    public void plotSnapshot(Scanner sc){
        UI.drawImage("map-new-zealand.gif", 0, 0);
        
        //use nextInt to scan around using the scanner
        
        //using the nextInt to assigned the first 5 values into variables
        int day = sc.nextInt();
        int month = sc.nextInt();
        int year = sc.nextInt();
        int time = sc.nextInt();
        int numOfStations = sc.nextInt();
        
        //print / draw the header
        String header = day + "/" + month + "/" + year + " " + time;
        UI.drawString (header , LEFT_TEXT, TOP_TEXT);
        
        //null variable to find the max and min temp
        double highestTemp = -999; 
        int xHighest = 0;
        int yHighest = 0;
        
        double lowestTemp = 999;
        int xLowest = 0;
        int yLowest = 0;
        
        //create a loop for the scanner to read 7 datas in one line, and print the circle for every line
        for( int i = 0; i < numOfStations; i ++) {
            
            String nameOfStations= sc.next();
            int xCoordinate = sc.nextInt();
            int yCoordinate = sc.nextInt();
            double temperature = sc.nextDouble();
            double dewPoint = sc.nextDouble();
            double surfacePressure = sc.nextDouble();
            double seaLevelPressure = sc.nextDouble();
            
            if(temperature > highestTemp){
                highestTemp = temperature;
                xHighest = xCoordinate;
                yHighest = yCoordinate;
            }
            
            else if(temperature < lowestTemp) {
                lowestTemp = temperature;
                xLowest = xCoordinate;
                yLowest = yCoordinate;
            }
            
            Color currentColor = this.getTemperatureColor(temperature);
            UI.setColor(currentColor);
            UI.fillOval(xCoordinate - DIAM, yCoordinate - DIAM, DIAM, DIAM);
            
        }
        
        //draw the max and min temp stations
        Color currentColor1 = this.getTemperatureColor(highestTemp);
        UI.setColor(currentColor1);
        UI.fillOval(xHighest - DIAM, yHighest - DIAM, DIAM * 2, DIAM * 2);
            
        Color currentColor2 = this.getTemperatureColor(lowestTemp);
        UI.setColor(currentColor2);
        UI.fillOval(xLowest - DIAM, yLowest - DIAM, DIAM * 2, DIAM * 2);

    }

    /**   COMPLETION
     * Displays an animated view of the temperatures over all
     * the times in a weather data files, plotting the temperatures
     * for the first date/time, as in the core, pausing for half a second,
     * then plotting the temperatures for the second date/time, and
     * repeating until all the data in the file has been shown.
     * 
     * (Hint, use the plotSnapshot(...) method that you used in the core)
     */
    public void animateTemperatures(){
        
        String filename = UI.askString("Enter file name: ");
    
    try {
        // 2. Open the scanner ONCE (Outside the loop)
        Scanner sc = new Scanner(new File(filename));
        
        // 3. Loop as long as there is data left in that one file
        while (sc.hasNext()) { 
            UI.clearGraphics();
            
            // 4. Call the worker method, NOT the orchestrator
            plotSnapshot(sc);
            
            // 5. Pause
            UI.sleep(1000);
        }
        
        sc.close();
    } catch (IOException e) {
        UI.println("File error: " + e);

    }
}


    /**   COMPLETION
     * Prints a table of all the weather data from a single station, one line for each day/time.
     * Asks for the name of the station.
     * Prints a header line
     * Then for each line of data for that station in the weather-all.txt file, it prints 
     * a line with the date/time, temperature, dew-point, surface-pressure, and  sealevel-pressure
     * If there are no entries for that station, it will print a message saying "Station not found".
     * Hint, the \t in a String is the tab character, which helps to make the table line up.
     */
    public void reportStation(){ 

        // 1. Ask for the target station 
    String target = UI.askString("Station name:");
    
    // 2. Benchmark for the presence of the station name
    boolean found = false;
    
    //playsafe to scan a big file
    try {
        
        Scanner sc = new Scanner(new File("weather-all.txt"));
        
        // 3. Print the Table Header (Step 5 of your design)
        UI.println("----------------------------------------------------------------");
        UI.printf("%-15s %-10s %-10s %-10s %-10s\n", "Date/Time", "Temp", "Dew", "SurfP", "SeaP");
        UI.println("----------------------------------------------------------------");

        // 4. Master Loop: Navigate the snapshots
        while (sc.hasNext()) {
            // Read the header: day, month, year, time
            int day = sc.nextInt();
            int month = sc.nextInt();
            int year = sc.nextInt();
            int time = sc.nextInt();
            
            // Read the number of stations in this snapshot
            int numStations = sc.nextInt();
            
            // 5. Inner Loop: Check every station in this snapshot
            for (int i = 0; i < numStations; i++) {
                String name = sc.next();
                int x = sc.nextInt();
                int y = sc.nextInt();
                double temp = sc.nextDouble();
                double dew = sc.nextDouble();
                double surfP = sc.nextDouble();
                double seaP = sc.nextDouble();
                
                // Logic: If the current station matches our target, print its data
                if (name.equalsIgnoreCase(target)) {
                    String dateStr = day + "/" + month + " " + time;
                    UI.printf("%-15s %-10.1f %-10.1f %-10.1f %-10.1f\n", 
                              dateStr, temp, dew, surfP, seaP);
                    found = true;
                }
            }
        }
        sc.close();
        
        // 6. Final check: Was the station ever found?
        if (!found) {
            UI.println("Station not found: " + target);
        }
        
    } catch (IOException e) {
        UI.println("Error reading file: " + e);
    }

    }

    /**
     * Returns a color representing that temperature
     * The colors are increasingly blue below 15 degrees, and
     * increasingly red above 15 degrees.
     */
    public Color getTemperatureColor(double temp){
        double max = 37, min = -5, mid = (max+min)/2;
        if (temp < min || temp > max){
            return Color.white;
        }
        else if (temp <= mid){ //blue range: hues from .7 to .5
            double tempFracOfRange = (temp-min)/(mid-min);
            double hue = 0.7 -  tempFracOfRange*(0.7-0.5); 
            return Color.getHSBColor((float)hue, 1.0F, 1.0F);
        }
        else { //red range: .15 to 0.0
            double tempFracOfRange = (temp-mid)/(max-mid);
            double hue = 0.15 -  tempFracOfRange*(0.15-0.0); 
            return Color.getHSBColor((float)hue, 1.0F, 1.0F);
        }
    }

    /**
     * Setup the interface with buttons
     */
    public void setupGUI(){
        UI.initialise();
        UI.addButton("Plot temperature", this::plotTemperatures);
        UI.addButton("Animate temperature", this::animateTemperatures);
        UI.addButton("Report",  this::reportStation);
        UI.addButton("Quit", UI::quit);
        UI.setWindowSize(800,750);
        UI.setFontSize(18);
    }

    /**
     *  Main: Create object and call setupGUI on it
     */
    public static void main(String[] arguments){
        WeatherReporter obj = new WeatherReporter();
        obj.setupGUI();
    }    

}
