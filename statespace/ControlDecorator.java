
// package statespace;

import java.util.HashSet;

public abstract class ControlDecorator<State> extends SearchController<State> {

    public abstract SearchNode<State> remove();

    public abstract void insert(SearchNode<State> searchnode);

}

class BFSGraphController<State> extends ControlDecorator<State> {
    private SearchController<State> controller;
    private HashSet<SearchNode<State>> set;

    public BFSGraphController(SearchController<State> controller) {
        this.controller = controller;
    }

    @Override
    public SearchNode<State> remove() {
        return this.controller.remove();
    }

    @Override
    public void insert(SearchNode<State> searchnode) {
        if (!set.contains(searchnode)) {
            this.controller.insert(searchnode);
            set.add(searchnode);
        }
    }

    @Override
    public Boolean isEmpty() {
        return this.controller.isEmpty();
    }

}

class DFSGraphController<State> extends ControlDecorator<State> {
    private SearchController<State> controller;

    private HashSet<SearchNode<State>> set;

    public DFSGraphController(SearchController<State> controller) {
        this.controller = controller;
    }

    @Override
    public SearchNode<State> remove() {
        return this.controller.remove();
    }

    @Override
    public void insert(SearchNode<State> searchnode) {
        if (!set.contains(searchnode)) {
            this.controller.insert(searchnode);
            set.add(searchnode);
        }
    }


    @Override 
    public Boolean isEmpty(){
        return this.controller.isEmpty();
    }

}