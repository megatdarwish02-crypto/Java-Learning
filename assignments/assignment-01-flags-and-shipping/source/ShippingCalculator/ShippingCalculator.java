// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2026T1, Assignment 1
 * Name:Megat Adam Darwish
 * Username: binmegmega
 * ID:300693739
 */

import ecs100.*;

/** Program for calculating cost of shipping a package */

public class ShippingCalculator{

    public static final double SIZE_RATE = 375.00; // cost per cubic meter
    public static final double WEIGHT_RATE = 1.90; // cost per kg
    public static final double HANDLING = 2.10;    // cost of handling a shipment
    
    /**
     * Calculates and prints cost of shipping a package.
     *
     * CORE
     */
    public void calculateShippingCore(){
         UI.println("Total charge:(Size charge + Weight charge) × number of zones + handling charge");
         UI.println();
         double lenght = UI.askDouble("What is the lenght of package (cm)?:");
         double widht = UI.askDouble("What is the width of package (cm)?:");
         double height = UI.askDouble("What is the height of package (cm)?:");
         double weight = UI.askDouble("What is the weight of package (kg)?:");
         double zones = UI.askDouble("Number of zones (minimum 1):");
         
         double sizeCharge = SIZE_RATE * (lenght * widht * height/1000000);
         UI.printf("size charge per zone: $%.2f\n", sizeCharge);
         
         double weightCharge = WEIGHT_RATE * weight ;
         UI.printf("weight charge per zone: $%.2f\n", weightCharge);
         
         UI.printf("Zones: %.0f\n", zones);
         
         double totalCharge = (sizeCharge + weightCharge) * zones + HANDLING;
         UI.printf("Total Charge: $%.2f\n", totalCharge);

    }

    /**
     * Calculates and prints cost of shipping a collection of packages
     *   after discount
     *
     * COMPLETION
     */
    public void calculateShippingCompletion(){
        UI.println("Shipping Calculator Completion");
        double zones = UI.askDouble("Number of zones (minimum 1):");
        // mintak data
        double packages1 = UI.askDouble("Number of packages of first size:");
        double lenght1 = UI.askDouble("Lenght (cm):");
        double width1 = UI.askDouble("Width (cm):");
        double height1 = UI.askDouble("Height (cm):");
        double weight1 = UI.askDouble("Weight (kg):");
        
        double packages2 = UI.askDouble("Number of packages of second size:");
        double lenght2 = UI.askDouble("Lenght (cm):");
        double width2 = UI.askDouble("Width (cm):");
        double height2 = UI.askDouble("Height (cm):");
        double weight2 = UI.askDouble("Weight (kg):");
        
        UI.println("------------------------------");
        
        UI.printf("Zones: %.0f\n", zones);
        // kira size charge dengan weight charge for GROUP 1 and GROUP 2
        double sizeCharge1 = SIZE_RATE * (lenght1 * width1 * height1/1000000)*packages1;
        UI.printf("Group 1 size charge per zone: $%.2f\n", sizeCharge1);
        double weightCharge1 = WEIGHT_RATE * weight1*packages1 ;
        UI.printf("Group 1 weight charge per zone: $%.2f\n", weightCharge1);
        
        double sizeCharge2 = SIZE_RATE * (lenght2 * width2 * height2/1000000)*packages2;
        UI.printf("Group 2 size charge per zone: $%.2f\n", sizeCharge2);
        double weightCharge2 = WEIGHT_RATE * weight2 *packages2 ;
        UI.printf("Group 2 weight charge per zone: $%.2f\n", weightCharge2);
        // kira total harga before
        double beforeDiscount = (((sizeCharge1 + weightCharge1) + (sizeCharge2 + weightCharge2))* zones + HANDLING);
        UI.printf("Total before discount: $%.2f\n", beforeDiscount);
        //kira harga after discount
        double numberPackages = packages1+packages2;
        double discount = beforeDiscount * ((1.0 - 1.0/numberPackages) / 3.0);
        UI.printf("Discount for %.0f items: $%.2f\n", numberPackages, discount);
        
        double afterDiscount = beforeDiscount - discount;
        UI.printf("Total after discount: $%.2f", afterDiscount);

    }

    /**
     * Calculates and prints cost of shipping a collection of packages
     *   after discount with a more sensible formula
     *
     * CHALLENGE
     */
    public void calculateShippingChallenge() {
    double maxDiscount = 0.33;
    double k = 7.0; // Tuning constant for gradual growth

    // 1. Print Discount Table
    UI.println("--- Discount Schedule ---");
    UI.println("33% * (n - 1) / (n + k)");
    for (int n = 1; n <= 20; n += 1) {
        double disc = maxDiscount * (n - 1) / (n + k);
        UI.printf("%d packages: %.2f%%\n", n, disc * 100);
    }
    UI.println("-------------------------");

    // 2. Multi-group Logic
    int totalPackages = 0;
    double totalCost = 0;
    
    while (true) {
        int qty = UI.askInt("Enter number of packages for this group (0 to stop): ");
        if (qty == 0) break;
        
        if (qty < 10) {
            UI.println("Error: Each additional group must have at least 10 packages.");
            continue;
        }

        double lenght = UI.askDouble("What is the lenght of package (cm)?:");
        double widht = UI.askDouble("What is the width of package (cm)?:");
        double height = UI.askDouble("What is the height of package (cm)?:");
        double weight = UI.askDouble("What is the weight of package (kg)?:");
        double zones = UI.askDouble("Number of zones (minimum 1):");
         
        double sizeCharge = SIZE_RATE * (lenght * widht * height/1000000);
        UI.printf("size charge per zone: $%.2f\n", sizeCharge);
         
        double weightCharge = WEIGHT_RATE * weight;
        UI.printf("weight charge per zone: $%.2f\n", weightCharge);
         
        UI.printf("Zones: %.0f\n", zones);
         
        double totalCharge = (sizeCharge + weightCharge) * zones + HANDLING;
        totalPackages += qty;
        totalCost += (qty * totalCharge);
        UI.printf("Total Cost: $%.2f\n", totalCost);
        
    }

    // 3. Apply the global discount based on total scale
    double discountRate = maxDiscount * (totalPackages - 1) / (totalPackages + k);
    double savings = totalCost * discountRate;
    double finalPrice = totalCost - savings;

    // 4. Formatted Output (Vertical Alignment)
    UI.println("\nFinal Invoice:");
    UI.printf("Subtotal:       $%10.2f\n", totalCost);
    UI.printf("Discount (%2.0f%%): -$%10.2f\n", discountRate * 100, savings);
    UI.printf("----------------------------\n");
    UI.printf("Total price after discount:      $%10.2f\n", finalPrice);
}

    

    public void setupGUI(){
        UI.initialise();
        UI.addButton("Core", this::calculateShippingCore ); 
        UI.addButton("Completion", this::calculateShippingCompletion );
        UI.addButton("Challenge", this::calculateShippingChallenge );
        UI.addButton("Quit", UI::quit);
        UI.setDivider(1);    // Expand the Text pane
    }    

    public static void main(String[] args){
        ShippingCalculator sc = new ShippingCalculator();
        sc.setupGUI();
    }

}
