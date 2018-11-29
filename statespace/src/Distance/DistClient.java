package Distance;

import Controller.*;
import ControllerDecorator.*;
import StateSpaceSearch.*;


public class DistClient {
    public static void main(String[] args)
    {
        Util.DEBUG = true;

        DistanceState startState = DistanceState.createCity("A");
        DistanceState targetState = DistanceState.createCity("D");
        Goal<DistanceState> goal = new DistanceGoal<DistanceState>(targetState);

        Controller<DistanceState> controller = new GraphController<DistanceState>(new BFSController<DistanceState>());

        Successors<DistanceState> successors = new DistanceSuccessor<DistanceState>();

        assert startState != null : "Start state is null";
        assert targetState != null : "Target State is null";
        assert goal != null : "Goal is null";
        assert controller != null : "Controller is null";

        Search<DistanceState> search = new Search<DistanceState>(startState, goal, controller, successors);
        assert search != null : "Search is null";

        SearchPath<DistanceState> searchpath = new SearchPath<DistanceState>();
        assert searchpath != null : "SearchPath is null";

        searchpath = search.findSolution();
        assert searchpath != null : "SearchPath is null after finding solution.";
        searchpath.showPath();
    }
}
