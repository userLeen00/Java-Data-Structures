/*
=================================
CLASS ID   : CLS-08
CLASS NAME : BSTNode
PURPOSE    :
    A single node in the CourseBST (Binary Search Tree).
    Holds one Course and pointers to left and right children.
    Same node pattern as the lecturer's BST Node in BST.java.

USED BY:
    CourseBST

USES:
    Course
=================================
*/

public class BSTNode {

    // course is the data stored in this BST node
    Course course;

    // left points to the node with a SMALLER grade
    BSTNode left;

    // right points to the node with a LARGER grade
    BSTNode right;

    /*
    FUNCTION ID  : F-26
    USED IN      : CourseBST (insert)
    PURPOSE      : Create a new BST node holding one completed course
    COMPLEXITY   : O(1)
    */
    BSTNode(Course course) {
        this.course = course;
        left  = null; // no left child yet
        right = null; // no right child yet
    }
}
