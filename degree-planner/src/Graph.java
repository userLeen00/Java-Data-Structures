/*
=================================
CLASS ID   : CLS-11
CLASS NAME : Graph
PURPOSE    :
    A simple directed graph that models which courses
    are unlocked after completing another course.

    The Graph was intentionally kept simple.
    It only stores: courseID -> list of unlocked course IDs.
    Example:
        CS101 -> CS201
        CS201 -> CS301, CS202

    This satisfies Feature 5 without over-engineering.
    Course dependencies naturally form relationships,
    which is why a graph is the right data structure here.

    NOTE: HashMap and Java's built-in adjacency structures
    were NOT covered in class. This Graph uses a simple
    linked list of GraphNode objects instead,
    following the same linked-node pattern from class.

    NOTE: BFS (Breadth First Search) traversal to show ALL
    unlocked courses (direct and indirect) was covered
    theoretically in the Graph lecture slides.
    The implementation here uses a simple queue-like array,
    since Java's Queue class was not used in class.

USED BY:
    Menu
    Main

USES:
    GraphNode
=================================
*/

public class Graph {

    // head points to the first GraphNode in our adjacency list
    // we store graph nodes as a linked list of GraphNode objects
    private GraphNode head;

    // size tracks how many courses are in the graph
    private int size;

    /*
    FUNCTION ID  : F-35
    USED IN      : Main (created once and pre-loaded with sample data)
    PURPOSE      : Create an empty graph
    COMPLEXITY   : O(1)
    */
    public Graph() {
        head = null;
        size = 0;
    }

    /*
    FUNCTION ID  : F-36
    USED IN      : Menu option 9 / Main setup (addEdge calls this first)
    PURPOSE      : Add a new course to the graph as a GraphNode.
                   If the course already exists, do nothing.
    COMPLEXITY   : O(n) - must loop to check if course already exists
    */
    public void addCourse(String courseID) {
        // check if this course is already in the graph
        if (findNode(courseID) != null) {
            return; // already exists, do nothing
        }

        // create a new graph node for this course
        GraphNode newNode = new GraphNode(courseID);

        // add it to the front of our linked list of graph nodes
        newNode.next = head;
        head = newNode;
        size++;
    }

    /*
    FUNCTION ID  : F-37
    USED IN      : Main setup (pre-loading prerequisite edges)
    PURPOSE      : Add a directed edge: completing 'from' unlocks 'to'.
                   Makes sure both courses exist first, then links them.
    COMPLEXITY   : O(n) - must find both nodes in the list
    */
    public void addEdge(String from, String to) {
        // make sure both courses exist in the graph
        addCourse(from); // adds only if not already present
        addCourse(to);   // adds only if not already present

        // find the 'from' node and add 'to' as an unlocked course
        GraphNode fromNode = findNode(from);

        // check if fromNode was found (should always be true after addCourse)
        if (fromNode != null) {
            fromNode.addUnlocked(to);
        }
    }

    /*
    FUNCTION ID  : F-38
    USED IN      : Menu option 9 (Show Unlocked Courses)
    PURPOSE      : Show all courses that are directly unlocked
                   by completing the given course.
                   Only shows direct neighbours (one level).
                   NOTE: BFS for ALL reachable courses (multi-level)
                   was covered theoretically in Graph lecture.
                   See showAllUnlocked() below for the BFS version.
    COMPLEXITY   : O(n) to find node + O(k) to print k unlocked courses
    */
    public void showDirectUnlocked(String courseID) {
        GraphNode node = findNode(courseID);

        // check if this course exists in the graph
        if (node == null) {
            System.out.println("Course not found in graph: " + courseID);
            return;
        }

        // check if this course unlocks anything
        if (node.count == 0) {
            System.out.println("No courses unlocked by: " + courseID);
            return;
        }

        System.out.println("Courses unlocked after completing " + courseID + ":");

        // loop through all directly unlocked courses
        for (int i = 0; i < node.count; i++) {
            System.out.println("  -> " + node.unlockedCourses[i]);
        }
    }

    /*
    FUNCTION ID  : F-39
    USED IN      : Menu option 9 (Show ALL Unlocked Courses - multi-level)
    PURPOSE      : Show ALL courses reachable from a given course
                   using a BFS-like traversal.
                   This finds both direct AND indirect unlocked courses.

                   NOTE: BFS was covered theoretically in class (Graph lecture).
                   Java's Queue class was NOT covered in class, so we
                   simulate the queue using a simple String array.

                   BFS works by:
                   1. Start from the given course
                   2. Visit all its direct neighbours
                   3. Then visit all their neighbours
                   4. Continue until no more unvisited nodes

    COMPLEXITY   : O(V + E) where V = courses, E = edges (prerequisite links)
    */
    public void showAllUnlocked(String courseID) {
        GraphNode startNode = findNode(courseID);

        // check if the course exists in the graph
        if (startNode == null) {
            System.out.println("Course not found in graph: " + courseID);
            return;
        }

        System.out.println("All courses unlocked (directly and indirectly) after " + courseID + ":");

        // simulate a BFS queue using a simple String array
        // NOTE: Java's Queue was not covered in class, so we use an array
        String[] queue    = new String[100]; // array acting as a queue
        int queueFront    = 0; // index of the front of the queue
        int queueBack     = 0; // index where next item will be added

        // also keep track of visited courses to avoid visiting twice
        String[] visited  = new String[100];
        int visitedCount  = 0;

        // start BFS from the given course
        queue[queueBack] = courseID;
        queueBack++;

        // mark starting course as visited
        visited[visitedCount] = courseID;
        visitedCount++;

        boolean foundAny = false;

        // BFS loop: keep processing while the queue is not empty
        while (queueFront < queueBack) {
            // dequeue the front course
            String current = queue[queueFront];
            queueFront++;

            // find this course's node in the graph
            GraphNode currentNode = findNode(current);

            // skip if node not found (safety check)
            if (currentNode == null) {
                continue;
            }

            // loop through all courses directly unlocked by 'current'
            for (int i = 0; i < currentNode.count; i++) {
                String neighbour = currentNode.unlockedCourses[i];

                // check if we have already visited this neighbour
                if (!isVisited(visited, visitedCount, neighbour)) {
                    // not visited yet - print it and add to queue
                    System.out.println("  -> " + neighbour);
                    foundAny = true;

                    // mark as visited
                    visited[visitedCount] = neighbour;
                    visitedCount++;

                    // enqueue this neighbour
                    queue[queueBack] = neighbour;
                    queueBack++;
                }
            }
        }

        // check if anything was found at all
        if (!foundAny) {
            System.out.println("  No courses unlocked by: " + courseID);
        }
    }

    /*
    FUNCTION ID  : F-40
    USED IN      : showAllUnlocked (helper to check visited array)
    PURPOSE      : Check if a courseID is already in the visited array.
                   Simple linear search through the visited list.
    COMPLEXITY   : O(n)
    */
    private boolean isVisited(String[] visited, int visitedCount, String courseID) {
        // loop through visited array to check if courseID is there
        for (int i = 0; i < visitedCount; i++) {
            if (visited[i].equals(courseID)) {
                return true; // already visited
            }
        }
        return false; // not visited yet
    }

    /*
    FUNCTION ID  : F-41
    USED IN      : addEdge, showDirectUnlocked, showAllUnlocked
    PURPOSE      : Find and return the GraphNode with the given courseID.
                   Returns null if not found.
                   Loops through the linked list of GraphNodes.
    COMPLEXITY   : O(n)
    */
    private GraphNode findNode(String courseID) {
        GraphNode current = head;

        // loop through all graph nodes to find the matching course
        while (current != null) {
            // check if this node has the matching course ID
            if (current.courseID.equals(courseID)) {
                return current; // found
            }
            current = current.next; // move to next node
        }
        return null; // not found
    }

    /*
    FUNCTION ID  : F-42
    USED IN      : Menu (display all courses in the graph)
    PURPOSE      : Print all courses and their direct unlocked courses
    COMPLEXITY   : O(V + E)
    */
    public void displayGraph() {
        if (head == null) {
            System.out.println("Graph is empty.");
            return;
        }

        System.out.println("===== Course Prerequisites Graph =====");
        GraphNode current = head;

        // loop through every node in the graph
        while (current != null) {
            System.out.print(current.courseID + " -> ");

            // check if this course unlocks anything
            if (current.count == 0) {
                System.out.print("(no courses unlocked)");
            } else {
                // loop through all unlocked courses
                for (int i = 0; i < current.count; i++) {
                    System.out.print(current.unlockedCourses[i]);
                    // print comma between items but not after the last
                    if (i < current.count - 1) {
                        System.out.print(", ");
                    }
                }
            }
            System.out.println(); // new line after each course
            current = current.next;
        }
        System.out.println("======================================");
    }
}
