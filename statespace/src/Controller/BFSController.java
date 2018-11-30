package Controller;

import StateSpaceSearch.Controller;
import StateSpaceSearch.SearchNode;
import java.util.LinkedList;
import java.util.Queue;

public class BFSController<State> extends Controller<State> {
    private Queue<SearchNode<State>> queue;

    public BFSController()
    {
        this.queue = new LinkedList<SearchNode<State>>();
    }

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
