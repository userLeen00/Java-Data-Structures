/*
=================================
CLASS ID   : CLS-13
CLASS NAME : Main
PURPOSE    :
    Entry point of the Degree Planner program.
    Creates the Menu object and starts the program.

USED BY:
    Nobody (this is the starting class)

USES:
    Menu
=================================
*/

public class Main {

    /*
    FUNCTION ID  : F-55
    USED IN      : JVM (called automatically to start the program)
    PURPOSE      : Start the Degree Planner by creating a Menu and calling run()
    COMPLEXITY   : O(1)
    */
    public static void main(String[] args) {
        // create the menu - this also sets up all data structures
        Menu menu = new Menu();

        // start the program loop
        menu.run();
    }
}
