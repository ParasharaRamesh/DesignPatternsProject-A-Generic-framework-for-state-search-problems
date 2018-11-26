
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

    private List<State> generated;
    private List<State> expanded;

    // methods
    public SearchPath<State> findSolution() {
        // todo
        SearchPath<State> searchPath;
        State currentState = startState;
        controller.insert(startState);
        searchPath.append(startState);
        while (!controller.isEmpty()) {
            if (goal.satisfied(currentState)) {
                searchPath.append(currentState);
                return searchPath;
            }
            List<SearchNode<State>> generatedSuccessors = successors.expand(currentState);
            controller.insertBatch(generatedSuccessors);
            currentState = controller.remove();
            searchPath.append(currentState);
        }
        return null;

    }

}