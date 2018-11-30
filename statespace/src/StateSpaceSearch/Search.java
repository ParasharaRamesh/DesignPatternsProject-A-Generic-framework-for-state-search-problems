package StateSpaceSearch;

import java.util.List;

public class Search<State> {

    public Search(State startState, Goal<State> goal, Controller<State> controller,
                  Successors<State> successors) {
        this.goal = goal;
        this.controller = controller;
        this.successors = successors;
        this.startState = startState;
    }

    private Goal<State> goal;
    private Controller<State> controller;
    private Successors<State> successors;
    private State startState;

    public SearchPath<State> findSolution() {
        SearchPath<State> searchPath = new SearchPath<State>();
        SearchNode<State> searchNode = this.successors.convertStateToSearchNode("start", startState, null);
        assert searchNode != null : "searchNode is null";
        controller.insert(searchNode);

        while (!controller.isEmpty()) {
            searchNode = controller.remove();
            Util.debug("The removed Search Node is: " + searchNode.toString());
            searchPath.append(searchNode);
            State currentState = searchNode.getState();
            if (goal.satisfied(currentState)) {
                return searchPath;
            }
            List<SearchNode<State>> generatedSuccessors = successors.expand(searchNode);
            for (SearchNode<State> node : generatedSuccessors) {
                Util.debug("The generated Search Node is" + node.toString());
            }
            Util.debug("");
            controller.insertBatch(generatedSuccessors);
        }
        return null;
    }

}
