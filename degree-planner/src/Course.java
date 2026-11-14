/*
=================================
CLASS ID   : CLS-01
CLASS NAME : Course
PURPOSE    :
    Store all information about a single course.
    This is the data object used by the Linked List,
    BST, Stack (via Action), and Graph.

USED BY:
    CourseNode
    CourseBST
    Action
    Menu

USES:
    Nothing (pure data class)
=================================
*/

public class Course {

    // courseID is private to prevent direct modification
    // from outside the Course class. (Encapsulation)
    private String courseID;

    // title is private for the same reason
    private String title;

    // grade stores the numeric grade (0-100)
    // used by the BST to order courses
    private int grade;

    // completed tells us if the course has a grade yet
    private boolean completed;

    /*
    FUNCTION ID  : F-01
    USED IN      : Menu (all options that create a Course)
    PURPOSE      : Create a new Course with all its details
    COMPLEXITY   : O(1)
    */
    public Course(String courseID, String title, int grade, boolean completed) {
        this.courseID  = courseID;
        this.title     = title;
        this.grade     = grade;
        this.completed = completed;
    }

    // --- Getters ---
    // O(1) - just return the value

    /*
    FUNCTION ID  : F-02
    USED IN      : CourseLinkedList, CourseBST, Menu (display)
    PURPOSE      : Return the course ID
    COMPLEXITY   : O(1)
    */
    public String getCourseID() {
        return courseID;
    }

    /*
    FUNCTION ID  : F-03
    USED IN      : Menu (display)
    PURPOSE      : Return the course title
    COMPLEXITY   : O(1)
    */
    public String getTitle() {
        return title;
    }

    /*
    FUNCTION ID  : F-04
    USED IN      : CourseBST (insert, search by grade)
    PURPOSE      : Return the grade of the course
    COMPLEXITY   : O(1)
    */
    public int getGrade() {
        return grade;
    }

    /*
    FUNCTION ID  : F-05
    USED IN      : Menu (when adding to BST, check if completed)
    PURPOSE      : Return whether the course is completed
    COMPLEXITY   : O(1)
    */
    public boolean isCompleted() {
        return completed;
    }

    // --- Setters ---

    /*
    FUNCTION ID  : F-06
    USED IN      : Menu (mark course as completed)
    PURPOSE      : Set the grade and mark course as completed
    COMPLEXITY   : O(1)
    */
    public void setGrade(int grade) {
        this.grade = grade;
        this.completed = true; // setting a grade means it is completed
    }

    /*
    FUNCTION ID  : F-07
    USED IN      : Menu (display), CourseLinkedList (print)
    PURPOSE      : Return a readable string of the course info
    COMPLEXITY   : O(1)
    */
    public String toString() {
        // build a simple readable line for printing
        if (completed) {
            return "[" + courseID + "] " + title + " | Grade: " + grade;
        } else {
            return "[" + courseID + "] " + title + " | Not completed yet";
        }
    }
}
