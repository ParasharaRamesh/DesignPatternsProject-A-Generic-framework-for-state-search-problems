package StateSpaceSearch;

import java.util.List;

public abstract class Successors<State> {

    public abstract List<SearchNode<State>> expand(SearchNode<State> searchNode);

    public abstract SearchNode<State> convertStateToSearchNode(String operation, State newState, State prevState);

}
