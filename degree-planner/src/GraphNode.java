/*
=================================
CLASS ID   : CLS-10
CLASS NAME : GraphNode
PURPOSE    :
    Represents one course in the Graph's adjacency list.
    Stores the course ID and a list of course IDs
    that are UNLOCKED after completing this course.

    The Graph was kept intentionally simple because
    the assignment only requires showing which courses
    become available after completing another course.
    No complex prerequisite objects are needed.

USED BY:
    Graph

USES:
    Nothing (pure data node)
=================================
*/

public class GraphNode {

    // courseID is the ID of this course (e.g. "CS101")
    String courseID;

    // unlockedCourses is a simple array of IDs that this course unlocks
    // NOTE: Java's ArrayList and HashMap were not used in class examples.
    // We use a plain String array here and resize it when needed,
    // the same way the lecturer's ArrayList.java resizes its array.
    String[] unlockedCourses;

    // count tracks how many courses have been added to the array
    int count;

    // next points to the next GraphNode in the graph's node list
    GraphNode next;

    /*
    FUNCTION ID  : F-33
    USED IN      : Graph (addCourse)
    PURPOSE      : Create a new graph node for a course
    COMPLEXITY   : O(1)
    */
    GraphNode(String courseID) {
        this.courseID      = courseID;
        this.unlockedCourses = new String[5]; // start with space for 5 courses
        this.count         = 0;
        this.next          = null;
    }

    /*
    FUNCTION ID  : F-34
    USED IN      : Graph (addEdge)
    PURPOSE      : Add a course ID to the list of courses this node unlocks.
                   Resizes the array if it is full.
                   Same resize pattern as lecturer's ArrayList.java.
    COMPLEXITY   : O(1) normally, O(n) if resize is needed
    */
    public void addUnlocked(String courseID) {
        // check if array is full - if so, resize
        if (count == unlockedCourses.length) {
            // resize: same pattern as ArrayList.java from class
            String[] newArr = new String[unlockedCourses.length * 2];

            // deep copy - same loop style as class
            for (int i = 0; i < unlockedCourses.length; i++) {
                newArr[i] = unlockedCourses[i];
            }
            // rename - same as class
            unlockedCourses = newArr;
        }
        // add the new course ID at the end
        unlockedCourses[count] = courseID;
        count++;
    }
}
