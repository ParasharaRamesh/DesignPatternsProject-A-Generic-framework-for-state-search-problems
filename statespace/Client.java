// package statespace;

// State, SearchNode, Goal, Successors, SearchController, ControlDecorator,SearchPath, Search

public class Client {

    public static void main(String[] args) {
        JugState startState = new JugState(0, 0);
        JugState targetState = new JugState(0, 4);
        Goal<JugState> goal = new JugGoal<JugState>(targetState);
        SearchController<JugState> controller = new DFSController<JugState>();
        // controller = new BFSGraphController<JugState>(controller);
        Successors<JugState> successors = new JugSuccessor<JugState>();
        assert startState != null : "Start state is null";
        assert targetState != null : "target State is null";
        assert goal != null : "goal is null";
        assert controller != null : "controller is null";
        Search<JugState> search = new Search<JugState>(startState, goal, controller, successors);
        assert search != null : "search is null";
        SearchPath<JugState> searchpath = new SearchPath<JugState>();
        assert searchpath != null : "searchpath is null";
        searchpath = search.findSolution();
        assert searchpath != null : "searchpath is null after finding soln";
        searchpath.showPath();

    }

}