package Controller;

import StateSpaceSearch.Controller;
import StateSpaceSearch.SearchNode;
import java.util.Stack;

public class DFSController<State> extends Controller<State> {
    private Stack<SearchNode<State>> stack;

    public DFSController()
    {
        this.stack = new Stack<SearchNode<State>>();
    }

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
