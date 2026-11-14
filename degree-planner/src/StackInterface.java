/*
=================================
CLASS ID   : CLS-05
CLASS NAME : StackInterface
PURPOSE    :
    Defines WHAT the Stack ADT can do.
    This is the interface (ADT definition).
    The UndoStack class defines HOW each operation is done.

    This follows the same pattern as the lecturer's Stack.java
    interface which separates the ADT from the implementation.

    NOTE: top() is used instead of peek() as per assignment requirements.
          top() clearly represents returning the current top element.

USED BY:
    UndoStack (implements this interface)
    Menu (uses UndoStack through this contract)

USES:
    Action
=================================
*/

public interface StackInterface {

    // stack ADT: separation between what to do <-> how to do
    // what to do:

    /*
    Push an action onto the top of the stack.
    */
    void push(Action action);

    /*
    Remove and return the top action from the stack.
    */
    Action pop();

    /*
    Return the top action WITHOUT removing it.
    NOTE: Named top() instead of peek() to clearly
          represent the top element of the stack.
    */
    Action top();

    /*
    Check if the stack has no actions.
    */
    boolean isEmpty();

    /*
    Return the number of actions in the stack.
    */
    int size();
}
