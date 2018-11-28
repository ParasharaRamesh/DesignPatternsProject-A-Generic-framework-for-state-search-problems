import java.util.*;

public class Search<State> {
    // constructor
    public Search(State startState, Goal<State> goal, SearchController<State> controller,
            Successors<State> successors) {
        this.goal = goal;
        this.controller = controller;
        this.successors = successors;
        this.startState = startState;
    }

    // private members
    private Goal<State> goal;
    private SearchController<State> controller;
    private Successors<State> successors;
    private State startState;

    // methods
    public SearchPath<State> findSolution() {
        SearchPath<State> searchPath = new SearchPath<State>();
        SearchNode<State> searchNode = this.successors.convertStateToSearchNode("start", startState, null);
        assert searchNode != null : "searchNode is null";
        controller.insert(searchNode);

        while (!controller.isEmpty()) {
            searchNode = controller.remove();
            Util.debug("the removed searchnode is" + searchNode.toString());
            searchPath.append(searchNode);
            State currentState = searchNode.getState();
            if (goal.satisfied(currentState)) {
                return searchPath;
            }
            List<SearchNode<State>> generatedSuccessors = successors.expand(searchNode);
            for (SearchNode<State> node : generatedSuccessors) {
                Util.debug("the generated searchnode is" + node.toString());
            }
            controller.insertBatch(generatedSuccessors);
        }
        return null;

    }

}