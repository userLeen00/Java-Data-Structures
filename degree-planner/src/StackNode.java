/*
=================================
CLASS ID   : CLS-06
CLASS NAME : StackNode
PURPOSE    :
    A single node in the UndoStack.
    Holds one Action and a pointer to the node below it.
    Same node pattern used in lecturer's linked list examples.

USED BY:
    UndoStack

USES:
    Action
=================================
*/

public class StackNode {

    // data holds the Action stored in this stack node
    Action data;

    // next points to the node below this one in the stack
    // null means this is the bottom of the stack
    StackNode next;

    /*
    FUNCTION ID  : F-19
    USED IN      : UndoStack (push)
    PURPOSE      : Create a new stack node holding one Action
    COMPLEXITY   : O(1)
    */
    StackNode(Action data) {
        this.data = data;
        next = null; // no node below this one yet
    }
}
