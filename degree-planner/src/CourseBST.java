/*
=================================
CLASS ID   : CLS-09
CLASS NAME : CourseBST
PURPOSE    :
    A Binary Search Tree that stores completed courses ordered by grade.
    Supports insert, search by grade, find highest grade, find lowest grade.

    BST was chosen because grades are numeric and ordered.
    Searching in a BST is O(log n) on average,
    which is faster than a linear list search at O(n).

    This is Feature 4 of the Degree Planner.

    NOTE: The BST here is a simple unbalanced BST, same as taught in class.
    If courses are inserted in sorted order, the worst case becomes O(n).
    A self-balancing BST (AVL, Red-Black) would fix this but was NOT
    covered in class, so it is not implemented here.

USED BY:
    Menu
    Main

USES:
    Course
    BSTNode
=================================
*/

public class CourseBST {

    // root is the top node of the BST
    // starts null for an empty tree
    private BSTNode root;

    /*
    FUNCTION ID  : F-27
    USED IN      : Main (created once at program start)
    PURPOSE      : Create an empty BST
    COMPLEXITY   : O(1)
    */
    public CourseBST() {
        root = null;
    }

    /*
    FUNCTION ID  : F-28
    USED IN      : Menu option 5 (Add Completed Course)
    PURPOSE      : Insert a completed course into the BST ordered by grade.
                   Uses an iterative while loop, same style as BST.java from class.
                   If grade is less than current node, go left.
                   If grade is greater or equal, go right.
    COMPLEXITY   : O(log n) average, O(n) worst case (unbalanced tree)
    */
    public void insert(Course course) {
        BSTNode newNode = new BSTNode(course);

        // case 1: tree is empty, new node becomes root
        if (root == null) {
            root = newNode;
            return;
        }

        BSTNode current = root;

        // loop: keep going down the tree until we find an empty spot
        // same pattern as lecturer's BST.java insert method
        while (true) {
            // go to left subtree if grade is smaller
            if (course.getGrade() < current.course.getGrade()) {
                if (current.left == null) {
                    current.left = newNode; // found empty spot on left
                    break;
                }
                current = current.left; // keep going left
            }
            // go to right subtree if grade is larger or equal
            else {
                if (current.right == null) {
                    current.right = newNode; // found empty spot on right
                    break;
                }
                current = current.right; // keep going right
            }
        }
        System.out.println("Completed course added to BST: " + course.getCourseID());
    }

    /*
    FUNCTION ID  : F-29
    USED IN      : Menu option 6 (Search Course By Grade)
    PURPOSE      : Search for a course by its exact grade.
                   Works the same way as BST.java search() from class.
                   Go left if target is smaller, right if larger.
    COMPLEXITY   : O(log n) average, O(n) worst case
    */
    public Course searchGrade(int grade) {
        BSTNode current = root;

        // loop: keep going while there are nodes to check
        while (current != null) {
            // check if we found the matching grade
            if (current.course.getGrade() == grade) {
                return current.course; // found
            } else if (grade < current.course.getGrade()) {
                current = current.left; // go left
            } else {
                current = current.right; // go right
            }
        }

        // if loop ends without finding, grade does not exist
        System.out.println("Grade not found"); // error message for P5
        return null;
    }

    /*
    FUNCTION ID  : F-30
    USED IN      : Menu option 7 (Highest Grade Course)
    PURPOSE      : Find the course with the highest grade.
                   In a BST, the highest value is always the RIGHTMOST node.
                   Loop right until there is no more right child.
    COMPLEXITY   : O(h) where h is the height of the tree
                   O(log n) average, O(n) worst case
    */
    public Course findHighest() {
        // check if BST is empty
        if (root == null) {
            System.out.println("No completed courses in BST.");
            return null;
        }

        BSTNode current = root;

        // loop: keep going right until the rightmost node
        while (current.right != null) {
            current = current.right; // highest grade is always to the right
        }
        return current.course;
    }

    /*
    FUNCTION ID  : F-31
    USED IN      : Menu option 8 (Lowest Grade Course)
    PURPOSE      : Find the course with the lowest grade.
                   In a BST, the lowest value is always the LEFTMOST node.
                   Loop left until there is no more left child.
    COMPLEXITY   : O(h) where h is the height of the tree
                   O(log n) average, O(n) worst case
    */
    public Course findLowest() {
        // check if BST is empty
        if (root == null) {
            System.out.println("No completed courses in BST.");
            return null;
        }

        BSTNode current = root;

        // loop: keep going left until the leftmost node
        while (current.left != null) {
            current = current.left; // lowest grade is always to the left
        }
        return current.course;
    }

    /*
    FUNCTION ID  : F-32
    USED IN      : Menu (display BST contents in order)
    PURPOSE      : Print all completed courses in grade order (low to high).
                   Uses iterative inorder traversal.
                   NOTE: Recursive inorder was shown in class (BST.java inorder()),
                   but the assignment says to avoid recursion when a loop is possible.
                   This iterative version uses a manual stack (int array of nodes).

                   NOTE: This uses a simple node array as a manual stack because
                   the Java Stack class and HashMap were NOT covered in class.
                   This approach uses only arrays and simple loops.
    COMPLEXITY   : O(n) - visits every node once
    */
    public void displayInOrder() {
        if (root == null) {
            System.out.println("No completed courses in BST.");
            return;
        }

        System.out.println("===== Completed Courses (by grade) =====");

        // NOTE: HashMap and Java's built-in Stack were not covered in class.
        // We use a simple array to simulate a stack for iterative inorder traversal.
        // This is the same concept as the iterative inorder in BST.java from class,
        // but using a plain array instead of a linked-stack object.

        BSTNode[] stack = new BSTNode[100]; // simple array acting as a stack
        int stackTop = -1; // index for our manual stack (-1 means empty)

        BSTNode current = root;

        // loop: continue while there are nodes to process
        while (current != null || stackTop >= 0) {

            // go as far left as possible, pushing nodes onto our array-stack
            while (current != null) {
                stackTop++;
                stack[stackTop] = current; // push
                current = current.left;
            }

            // pop the top node from our array-stack
            current = stack[stackTop];
            stackTop--;

            // visit this node (print it)
            System.out.println(current.course.toString());

            // now move to the right subtree
            current = current.right;
        }
        System.out.println("=========================================");
    }
}
