/*
=================================
CLASS ID   : CLS-12
CLASS NAME : Menu
PURPOSE    :
    Handles all console interaction with the user.
    Displays the menu, reads input, and calls the
    correct methods on each data structure.

    This class connects all features together:
    Feature 1 -> CourseLinkedList
    Feature 2 -> UndoStack
    Feature 4 -> CourseBST
    Feature 5 -> Graph

USED BY:
    Main

USES:
    CourseLinkedList
    UndoStack
    CourseBST
    Graph
    Course
    Action
    Scanner (Java standard library - used in class for input reading)
=================================
*/

import java.util.Scanner;

public class Menu {

    // all four data structures are held here
    // private to prevent direct access from outside (Encapsulation)
    private CourseLinkedList courseList;
    private UndoStack undoStack;
    private CourseBST courseBST;
    private Graph prereqGraph;

    // Scanner reads user input from the console
    private Scanner scanner;

    /*
    FUNCTION ID  : F-43
    USED IN      : Main (created once, then run() is called)
    PURPOSE      : Create the Menu and initialise all data structures.
                   Pre-loads the graph with sample prerequisite edges.
    COMPLEXITY   : O(1) setup, O(k) for k pre-loaded edges
    */
    public Menu() {
        courseList  = new CourseLinkedList();
        undoStack   = new UndoStack();
        courseBST   = new CourseBST();
        prereqGraph = new Graph();
        scanner     = new Scanner(System.in);

        // pre-load some sample prerequisite relationships
        prereqGraph.addEdge("CS101", "CS201");
        prereqGraph.addEdge("CS101", "CS102");
        prereqGraph.addEdge("CS201", "CS301");
        prereqGraph.addEdge("CS201", "CS202");
        prereqGraph.addEdge("CS301", "CS401");
        prereqGraph.addEdge("MATH101", "MATH201");
        prereqGraph.addEdge("MATH201", "MATH301");
    }

    /*
    FUNCTION ID  : F-44
    USED IN      : Main (entry point to start the program loop)
    PURPOSE      : Show the menu and handle user choices in a loop.
                   Keeps looping until the user picks option 10 (Exit).
    COMPLEXITY   : O(1) per menu display, varies per option chosen
    */
    public void run() {
        int choice = 0;

        // loop: keep showing the menu until user exits
        while (choice != 10) {
            displayMenu();
            choice = readInt();

            // each case calls the right method for that feature
            // Option 1  - Uses CourseLinkedList | Feature 1 | Criteria: P4, M4
            if (choice == 1) {
                handleAddCourse();
            }
            // Option 2  - Uses CourseLinkedList | Feature 1 | Criteria: P4, M4, P5
            else if (choice == 2) {
                handleRemoveCourse();
            }
            // Option 3  - Uses CourseLinkedList | Feature 1 | Criteria: P4, M4
            else if (choice == 3) {
                courseList.displayCourses();
            }
            // Option 4  - Uses UndoStack | Feature 2 | Criteria: P4, P5, M4
            else if (choice == 4) {
                handleUndo();
            }
            // Option 5  - Uses CourseBST | Feature 4 | Criteria: P4, M4
            else if (choice == 5) {
                handleAddCompletedCourse();
            }
            // Option 6  - Uses CourseBST | Feature 4 | Criteria: P4, P5, M4
            else if (choice == 6) {
                handleSearchByGrade();
            }
            // Option 7  - Uses CourseBST | Feature 4 | Criteria: P4, M4
            else if (choice == 7) {
                handleHighestGrade();
            }
            // Option 8  - Uses CourseBST | Feature 4 | Criteria: P4, M4
            else if (choice == 8) {
                handleLowestGrade();
            }
            // Option 9  - Uses Graph | Feature 5 | Criteria: P4, P5, M4
            else if (choice == 9) {
                handleShowUnlocked();
            }
            // Option 10 - Exit
            else if (choice == 10) {
                System.out.println("Goodbye!");
            }
            // Invalid input - error handling for P5
            else {
                System.out.println("Invalid option"); // error message for P5
            }
        }
    }

    /*
    FUNCTION ID  : F-45
    USED IN      : run() (called at the start of every loop)
    PURPOSE      : Print the main menu to the console
    COMPLEXITY   : O(1)
    */
    public void displayMenu() {
        System.out.println();
        System.out.println("===== DEGREE PLANNER =====");
        System.out.println("1. Add Course");
        System.out.println("2. Remove Course");
        System.out.println("3. View Courses");
        System.out.println("4. Undo Last Change");
        System.out.println("5. Add Completed Course (with grade)");
        System.out.println("6. Search Course By Grade");
        System.out.println("7. Highest Grade Course");
        System.out.println("8. Lowest Grade Course");
        System.out.println("9. Show Unlocked Courses");
        System.out.println("10. Exit");
        System.out.print("Enter choice: ");
    }

    /*
    FUNCTION ID  : F-46
    USED IN      : run() option 1
    PURPOSE      : Ask user for course details and add to the linked list.
                   Also pushes an ADD action onto the undo stack.
    COMPLEXITY   : O(1) for add + O(1) for push
    */
    private void handleAddCourse() {
        System.out.print("Enter Course ID: ");
        String id = scanner.nextLine().trim();

        System.out.print("Enter Course Title: ");
        String title = scanner.nextLine().trim();

        // check if course ID is valid (not empty)
        if (id.isEmpty() || title.isEmpty()) {
            System.out.println("Invalid input. Course ID and title cannot be empty.");
            return;
        }

        // create the Course object
        Course course = new Course(id, title, 0, false);

        // add it to the linked list (Feature 1)
        courseList.addCourse(course);

        // push an ADD action so we can undo this later (Feature 2)
        Action action = new Action(Action.ActionType.ADD, course);
        undoStack.push(action);
    }

    /*
    FUNCTION ID  : F-47
    USED IN      : run() option 2
    PURPOSE      : Ask user for a course ID and remove it from the list.
                   Also pushes a REMOVE action onto the undo stack.
    COMPLEXITY   : O(n) to find and remove
    */
    private void handleRemoveCourse() {
        System.out.print("Enter Course ID to remove: ");
        String id = scanner.nextLine().trim();

        // check if course exists before removing
        Course found = courseList.findCourseByID(id);

        // if course does not exist, removeCourse will print the error
        if (found == null) {
            System.out.println("Course not found"); // error for P5
            return;
        }

        // remove from linked list (Feature 1)
        boolean removed = courseList.removeCourse(id);

        // if removal was successful, record it for undo (Feature 2)
        if (removed) {
            Action action = new Action(Action.ActionType.REMOVE, found);
            undoStack.push(action);
        }
    }

    /*
    FUNCTION ID  : F-48
    USED IN      : run() option 4
    PURPOSE      : Pop the last action and reverse it.
                   If last action was ADD, remove that course.
                   If last action was REMOVE, add that course back.
    COMPLEXITY   : O(1) for pop, O(n) for the reversal operation
    */
    private void handleUndo() {
        // pop the last action from the stack (Feature 2)
        Action action = undoStack.pop();

        // pop() already prints "No actions to undo" if empty
        if (action == null) {
            return;
        }

        // check what type of action to reverse
        if (action.getType() == Action.ActionType.ADD) {
            // last action was ADD, so we need to remove the course
            System.out.println("Undoing ADD: removing " + action.getCourse().getCourseID());
            courseList.removeCourse(action.getCourse().getCourseID());
        } else {
            // last action was REMOVE, so we need to add the course back
            System.out.println("Undoing REMOVE: adding back " + action.getCourse().getCourseID());
            courseList.addCourse(action.getCourse());
        }
    }

    /*
    FUNCTION ID  : F-49
    USED IN      : run() option 5
    PURPOSE      : Ask user for a completed course's details and
                   insert it into the BST ordered by grade.
    COMPLEXITY   : O(log n) average for BST insert
    */
    private void handleAddCompletedCourse() {
        System.out.print("Enter Course ID: ");
        String id = scanner.nextLine().trim();

        System.out.print("Enter Course Title: ");
        String title = scanner.nextLine().trim();

        System.out.print("Enter Grade (0-100): ");
        int grade = readInt();

        // check if grade is a valid range
        if (grade < 0 || grade > 100) {
            System.out.println("Invalid grade. Must be between 0 and 100.");
            return;
        }

        // create completed course and insert into BST (Feature 4)
        Course course = new Course(id, title, grade, true);
        courseBST.insert(course);
    }

    /*
    FUNCTION ID  : F-50
    USED IN      : run() option 6
    PURPOSE      : Ask for a grade and search the BST for a matching course.
                   Prints the course or "Grade not found".
    COMPLEXITY   : O(log n) average for BST search
    */
    private void handleSearchByGrade() {
        System.out.print("Enter grade to search: ");
        int grade = readInt();

        // search BST (Feature 4)
        Course result = courseBST.searchGrade(grade);

        // check if a course was found
        if (result != null) {
            System.out.println("Found: " + result.toString());
        }
        // if null, searchGrade() already printed "Grade not found"
    }

    /*
    FUNCTION ID  : F-51
    USED IN      : run() option 7
    PURPOSE      : Find and print the course with the highest grade
    COMPLEXITY   : O(h) where h is the BST height
    */
    private void handleHighestGrade() {
        Course result = courseBST.findHighest();

        // check if a result was found (BST might be empty)
        if (result != null) {
            System.out.println("Highest grade course: " + result.toString());
        }
        // if null, findHighest() already printed an empty message
    }

    /*
    FUNCTION ID  : F-52
    USED IN      : run() option 8
    PURPOSE      : Find and print the course with the lowest grade
    COMPLEXITY   : O(h) where h is the BST height
    */
    private void handleLowestGrade() {
        Course result = courseBST.findLowest();

        // check if a result was found (BST might be empty)
        if (result != null) {
            System.out.println("Lowest grade course: " + result.toString());
        }
        // if null, findLowest() already printed an empty message
    }

    /*
    FUNCTION ID  : F-53
    USED IN      : run() option 9
    PURPOSE      : Ask for a course ID and show all courses
                   that are unlocked after completing it.
                   Uses BFS to find direct AND indirect unlocked courses.
    COMPLEXITY   : O(V + E)
    */
    private void handleShowUnlocked() {
        System.out.print("Enter Course ID: ");
        String id = scanner.nextLine().trim();

        // show all unlocked courses using BFS (Feature 5)
        prereqGraph.showAllUnlocked(id);
    }

    /*
    FUNCTION ID  : F-54
    USED IN      : All handle methods that need an integer from the user
    PURPOSE      : Read an integer from the console safely.
                   If the user types something that is not a number,
                   print an error and return -1 (invalid option).
    COMPLEXITY   : O(1)
    */
    private int readInt() {
        // try to read an integer from the user
        try {
            String line = scanner.nextLine().trim();
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            // user typed something that is not a valid number
            System.out.println("Invalid option"); // error message for P5
            return -1; // return -1 to signal invalid input
        }
    }
}
