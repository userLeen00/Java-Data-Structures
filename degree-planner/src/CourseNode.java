/*
=================================
CLASS ID   : CLS-02
CLASS NAME : CourseNode
PURPOSE    :
    A single node in the CourseLinkedList.
    Holds one Course object and a pointer to the next node.
    This follows the same Node pattern shown in class
    (Node.java and Linked_list.java from the lecturer).

USED BY:
    CourseLinkedList

USES:
    Course
=================================
*/

public class CourseNode {

    // data holds the actual course stored in this node
    Course data;

    // next points to the next node in the list
    // null means this is the last node
    CourseNode next;

    /*
    FUNCTION ID  : F-08
    USED IN      : CourseLinkedList (addCourse)
    PURPOSE      : Create a new node that holds one Course
    COMPLEXITY   : O(1)
    */
    CourseNode(Course data) {
        this.data = data;
        next = null; // new node has no next yet
    }
}
