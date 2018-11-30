package StateSpaceSearch;

import java.util.List;

public abstract class Controller<State> {

    public void insertBatch(List<SearchNode<State>> batch) {
        for (SearchNode<State> searchNode : batch) {
            this.insert(searchNode);
        }
    }

    public abstract void insert(SearchNode<State> searchNode);

    public abstract Boolean isEmpty();

    public abstract SearchNode<State> remove();
}
