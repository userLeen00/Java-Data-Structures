/*
=================================
CLASS ID   : CLS-04
CLASS NAME : Action
PURPOSE    :
    Represents one undoable action performed on the course list.
    Stores what type of action happened (ADD or REMOVE)
    and which course was involved.

    This is used by the UndoStack (Feature 2).

USED BY:
    UndoStack
    Menu

USES:
    Course
=================================
*/

public class Action {

    // ActionType tells us if the action was an ADD or REMOVE
    // NOTE: 'enum' was not explicitly shown in class examples,
    //       but it is a basic Java concept. It is used here
    //       to clearly label action types without magic strings.
    public enum ActionType {
        ADD,    // means a course was added
        REMOVE  // means a course was removed
    }

    // type stores whether this was an ADD or REMOVE action
    // private to protect it from outside changes (Encapsulation)
    private ActionType type;

    // course stores which course this action involved
    private Course course;

    /*
    FUNCTION ID  : F-16
    USED IN      : Menu option 1 (after addCourse, push ADD action)
                   Menu option 2 (after removeCourse, push REMOVE action)
    PURPOSE      : Create a new Action recording what happened and to which course
    COMPLEXITY   : O(1)
    */
    public Action(ActionType type, Course course) {
        this.type   = type;
        this.course = course;
    }

    /*
    FUNCTION ID  : F-17
    USED IN      : Menu option 4 (Undo - check if action was ADD or REMOVE)
    PURPOSE      : Return the type of this action (ADD or REMOVE)
    COMPLEXITY   : O(1)
    */
    public ActionType getType() {
        return type;
    }

    /*
    FUNCTION ID  : F-18
    USED IN      : Menu option 4 (Undo - get the course to restore or re-remove)
    PURPOSE      : Return the course this action was performed on
    COMPLEXITY   : O(1)
    */
    public Course getCourse() {
        return course;
    }
}
