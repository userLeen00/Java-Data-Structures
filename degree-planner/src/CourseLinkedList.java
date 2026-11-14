/*
=================================
CLASS ID   : CLS-03
CLASS NAME : CourseLinkedList
PURPOSE    :
    Manage all courses in the degree plan using a Linked List.
    Supports adding, removing, and displaying courses.
    This is Feature 1 of the Degree Planner.

    The Linked List was chosen because courses are frequently
    added and removed. Linked List gives O(1) insert at head
    and avoids the shifting cost of an array.

USED BY:
    Menu
    Main

USES:
    Course
    CourseNode
=================================
*/

public class CourseLinkedList {

    // head points to the first node in the list
    // follows same style as lecturer's LinkedList.java
    CourseNode head;

    // tail points to the last node (for fast insertLast)
    CourseNode tail;

    // size tracks how many courses are in the list
    int size;

    /*
    FUNCTION ID  : F-09
    USED IN      : Main (created once at program start)
    PURPOSE      : Create an empty linked list
    COMPLEXITY   : O(1)
    */
    public CourseLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /*
    FUNCTION ID  : F-10
    USED IN      : Menu option 1 (Add Course)
    PURPOSE      : Add a new course to the END of the list.
                   If the list is empty, head and tail both point to the new node.
                   Otherwise, the current tail links to the new node.
    COMPLEXITY   : O(1) - we use the tail pointer so no loop is needed
    */
    public void addCourse(Course course) {
        // create a new node holding this course
        CourseNode newNode = new CourseNode(course);

        // if list is empty, this node is both head and tail
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            // link the current last node to the new node
            tail.next = newNode;
            // update tail to be the new last node
            tail = newNode;
        }
        size++; // O(1)
        System.out.println("Course added: " + course.getCourseID());
    }

    /*
    FUNCTION ID  : F-11
    USED IN      : Menu option 2 (Remove Course)
    PURPOSE      : Remove a course by its ID.
                   Loops through the list to find the matching course,
                   then unlinks that node.
    COMPLEXITY   : O(n) - must loop to find the node
    */
    public boolean removeCourse(String courseID) {
        // check if list is empty before trying to remove
        if (isEmpty()) {
            System.out.println("Course not found"); // error message for P5
            return false;
        }

        // special case: removing the first node
        if (head.data.getCourseID().equals(courseID)) {
            // save the course before removing (returned for undo use)
            CourseNode toRemove = head;
            head = head.next; // move head forward

            // if list is now empty, tail must also be null
            if (head == null) {
                tail = null;
            }
            toRemove.next = null; // disconnect the removed node
            size--;
            System.out.println("Course removed: " + courseID);
            return true;
        }

        // loop through the rest of the list to find the course
        // we keep a 'prev' pointer so we can unlink the found node
        CourseNode prev = head;
        CourseNode current = head.next;

        // loop: keep going while there are more nodes to check
        while (current != null) {
            // check if this node has the course we want to remove
            if (current.data.getCourseID().equals(courseID)) {
                // unlink current from the list
                prev.next = current.next;

                // if we removed the last node, update tail
                if (current.next == null) {
                    tail = prev;
                }
                current.next = null; // disconnect the removed node
                size--;
                System.out.println("Course removed: " + courseID);
                return true;
            }
            // move both pointers forward
            prev = current;
            current = current.next;
        }

        // if we reach here, the course was not found
        System.out.println("Course not found"); // error message for P5
        return false;
    }

    /*
    FUNCTION ID  : F-12
    USED IN      : Menu option 3 (View Courses)
    PURPOSE      : Print all courses in the list from head to tail.
                   Same style as print() in lecturer's LinkedList.java.
    COMPLEXITY   : O(n) - must visit every node
    */
    public void displayCourses() {
        // check if there is anything to print
        if (isEmpty()) {
            System.out.println("No courses in the list.");
            return;
        }

        System.out.println("===== Course List =====");
        CourseNode current = head;

        // loop: visit every node from head to tail
        while (current != null) {
            System.out.println(current.data.toString());
            current = current.next; // move to next node
        }
        System.out.println("=======================");
    }

    /*
    FUNCTION ID  : F-13
    USED IN      : Menu option 4 (Undo - to get the course back)
                   Menu option 1 (to check if a course already exists)
    PURPOSE      : Find and return a Course by its ID.
                   Returns null if not found.
    COMPLEXITY   : O(n) - must loop through the list
    */
    public Course findCourseByID(String courseID) {
        CourseNode current = head;

        // loop through all nodes looking for matching ID
        while (current != null) {
            // check if the current node has the matching ID
            if (current.data.getCourseID().equals(courseID)) {
                return current.data; // found it
            }
            current = current.next; // move forward
        }
        return null; // not found
    }

    /*
    FUNCTION ID  : F-14
    USED IN      : addCourse, removeCourse, displayCourses (guard check)
    PURPOSE      : Check if the list has no courses
    COMPLEXITY   : O(1)
    */
    public boolean isEmpty() {
        return head == null; // same as lecturer's isEmpty()
    }

    /*
    FUNCTION ID  : F-15
    USED IN      : Menu (display count)
    PURPOSE      : Return the number of courses in the list
    COMPLEXITY   : O(1)
    */
    public int size() {
        return size; // O(1)
    }
}
