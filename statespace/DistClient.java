
// package statespace;

// State, SearchNode, Goal, Successors, SearchController, ControlDecorator,SearchPath, Search

public class DistClient {

    public static void main(String[] args) {
        Util.DEBUG = false;
        DistanceState startState = DistanceState.createCity("A");
        DistanceState targetState = DistanceState.createCity("D");
        Goal<DistanceState> goal = new DistanceGoal<DistanceState>(targetState);
        SearchController<DistanceState> controller = new BFSPriorityController<DistanceState>();
        // new BFSPriorityController<DistanceState>();
        // new BFSController<JugState>();
        // controller = new BFSGraphController<JugState>(controller);
        // SearchController<JugState> controller = new DFSController<JugState>();
        // controller = new DFSGraphController<JugState>(controller);
        Successors<DistanceState> successors = new DistanceSuccessor<DistanceState>();
        assert startState != null : "Start state is null";
        assert targetState != null : "target State is null";
        assert goal != null : "goal is null";
        assert controller != null : "controller is null";
        Search<DistanceState> search = new Search<DistanceState>(startState, goal, controller, successors);
        assert search != null : "search is null";
        SearchPath<DistanceState> searchpath = new SearchPath<DistanceState>();
        assert searchpath != null : "searchpath is null";
        searchpath = search.findSolution();
        assert searchpath != null : "searchpath is null after finding soln";
        searchpath.showPath();

    }

}