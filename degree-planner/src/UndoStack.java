/*
=================================
CLASS ID   : CLS-07
CLASS NAME : UndoStack
PURPOSE    :
    Implements the StackInterface using linked nodes.
    Stores Actions so the user can undo the last change.

    A Stack was chosen because undo functionality always
    reverses the MOST RECENT action first.
    Stack is LIFO (Last In First Out) which matches this perfectly.

    A Queue was NOT chosen because Queue is FIFO
    (First In First Out) - it would undo the OLDEST action first,
    which is the wrong behavior for undo.

    This is Feature 2 of the Degree Planner.

USED BY:
    Menu
    Main

USES:
    Action
    StackNode
    StackInterface
=================================
*/

public class UndoStack implements StackInterface {

    // top points to the most recently pushed node
    // same idea as head in the linked list, but called top for stack clarity
    private StackNode top;

    // size tracks how many actions are in the stack
    private int size;

    /*
    FUNCTION ID  : F-20
    USED IN      : Main (created once at program start)
    PURPOSE      : Create an empty stack
    COMPLEXITY   : O(1)
    */
    public UndoStack() {
        top  = null;
        size = 0;
    }

    /*
    FUNCTION ID  : F-21
    USED IN      : Menu option 1 (after addCourse, push ADD action)
                   Menu option 2 (after removeCourse, push REMOVE action)
    PURPOSE      : Push a new action onto the top of the stack.
                   New node becomes the new top. O(1) because
                   we only touch the top pointer, no loop needed.
    COMPLEXITY   : O(1)
    */
    public void push(Action action) {
        // O(1)
        StackNode newNode = new StackNode(action);

        // link new node to point down to the old top
        newNode.next = top;

        // new node is now the top
        top = newNode;
        size++;
    }

    /*
    FUNCTION ID  : F-22
    USED IN      : Menu option 4 (Undo - pop last action and reverse it)
    PURPOSE      : Remove and return the top action from the stack.
                   If stack is empty, print error and return null.
    COMPLEXITY   : O(1)
    */
    public Action pop() {
        // O(1)
        // check if there is anything to pop
        if (isEmpty()) {
            System.out.println("No actions to undo"); // error message for P5
            return null;
        }

        // save the action from the top node
        Action action = top.data;

        // move top down to the next node
        top = top.next;
        size--;
        return action;
    }

    /*
    FUNCTION ID  : F-23
    USED IN      : Menu (preview what will be undone)
    PURPOSE      : Return the top action WITHOUT removing it.
                   Named top() instead of peek() to clearly
                   represent the top of the stack.
    COMPLEXITY   : O(1)
    */
    public Action top() {
        // O(1)
        // check if there is anything to look at
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }
        return top.data;
    }

    /*
    FUNCTION ID  : F-24
    USED IN      : pop(), top(), Menu (guard checks)
    PURPOSE      : Check if the stack has no actions stored
    COMPLEXITY   : O(1)
    */
    public boolean isEmpty() {
        return top == null; // same pattern as lecturer's isEmpty()
    }

    /*
    FUNCTION ID  : F-25
    USED IN      : Menu (display how many actions can be undone)
    PURPOSE      : Return the number of actions in the stack
    COMPLEXITY   : O(1)
    */
    public int size() {
        return size; // O(1)
    }
}
