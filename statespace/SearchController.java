
// package statespace;

import java.util.*;

public abstract class SearchController<State> {
    // constructor
    public SearchController() {
    }

    // member functions
    public void insertBatch(List<SearchNode<State>> batch) {
        for (SearchNode<State> searchnode : batch) {
            this.insert(searchnode);
        }
    }

    public abstract void insert(SearchNode<State> searchnode);

    public abstract Boolean isEmpty();

    public abstract SearchNode<State> remove();
}

class BFSController<State> extends SearchController<State> {
    private Queue<SearchNode<State>> queue = new LinkedList<SearchNode<State>>();

    @Override
    public void insert(SearchNode<State> node) {
        assert node != null : "Node is null";
        this.queue.add(node);
    }

    @Override
    public Boolean isEmpty() {
        return this.queue.isEmpty();
    }

    @Override
    public SearchNode<State> remove() {
        SearchNode<State> node = null;
        if (!this.queue.isEmpty()) {
            node = this.queue.poll();
        }
        return node;
    }
}

class DFSController<State> extends SearchController<State> {
    private Stack<SearchNode<State>> stack = new Stack<SearchNode<State>>();

    @Override
    public void insert(SearchNode<State> node) {
        this.stack.add(node);
    }

    @Override
    public Boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public SearchNode<State> remove() {
        SearchNode<State> node = null;
        if (!this.stack.isEmpty()) {
            node = this.stack.pop();
        }
        return node;
    }
}
