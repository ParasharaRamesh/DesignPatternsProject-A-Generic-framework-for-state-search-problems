package Jug;

import Controller.*;
import ControllerDecorator.*;
import StateSpaceSearch.*;

public class JugClient {
    public static void main(String[] args) {
        Util.DEBUG = true;
        JugState.capacityJug1 = 3;
        JugState.capacityJug2 = 5;
        JugState startState = new JugState(0, 0);
        JugState targetState = new JugState(0, 4);
        Goal<JugState> goal = new JugGoal<JugState>(targetState);

        Controller<JugState> controller = new BFSController<JugState>();
        controller = new GraphController<JugState>(controller);
        Successors<JugState> successors = new JugSuccessor<JugState>();

        assert startState != null : "Start State is null";
        assert targetState != null : "Target State is null";
        assert goal != null : "Goal is null";
        assert controller != null : "Controller is null";

        Search<JugState> search = new Search<JugState>(startState, goal, controller, successors);
        assert search != null : "Search is null";
        SearchPath<JugState> searchPath = new SearchPath<JugState>();
        assert searchPath != null : "searchPath is null";
        searchPath = search.findSolution();
        assert searchPath != null : "searchPath is null after finding solution";
        searchPath.showPath();
    }
}
