
// package statespace;

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
        // State currentState = startState;
        // assert currentState != null : "Current State is null";
        SearchNode<State> searchNode = this.successors.convertStateToSearchNode("start", startState);
        assert searchNode != null : "searchNode is null";
        controller.insert(searchNode);
        while (!controller.isEmpty()) {
            searchNode = controller.remove();
            System.out.println("the removed searchNode is " + searchNode);
            searchPath.append(searchNode);
            State currentState = searchNode.getState();
            if (goal.satisfied(currentState)) {
                // searchPath.append(searchNode);
                return searchPath;
            }
            List<SearchNode<State>> generatedSuccessors = successors.expand(currentState);
            for (SearchNode<State> node : generatedSuccessors) {
                System.out.println("the generated searchnode is" + node);
            }
            controller.insertBatch(generatedSuccessors);
            System.out.println("finished inserting batch");

        }
        return null;

    }

}