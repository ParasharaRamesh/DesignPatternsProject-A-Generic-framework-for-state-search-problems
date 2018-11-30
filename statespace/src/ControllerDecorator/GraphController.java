package ControllerDecorator;

import StateSpaceSearch.Controller;
import StateSpaceSearch.SearchNode;
import StateSpaceSearch.Util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class GraphController<State> extends ControllerDecorator<State> {
    private Controller<State> controller;
    private HashSet<SearchNode<State>> set;

    public GraphController(Controller<State> controller) {
        this.controller = controller;
        this.set = new HashSet<SearchNode<State>>();
    }

    @Override
    public SearchNode<State> remove() {
        return this.controller.remove();
    }

    @Override
    public void insert(SearchNode<State> searchNode) {
        if (!set.contains(searchNode)) {
            Util.debug("Added to the Controller: " + searchNode.toString());
            this.controller.insert(searchNode);
            set.add(searchNode);
        }
        else
        {
            Util.debug("Not Added to the Controller: " + searchNode.toString());
        }
    }

    @Override
    public Boolean isEmpty() {
        return this.controller.isEmpty();
    }

    @Override
    public void insertBatch(List<SearchNode<State>> batch)
    {
        Iterator<SearchNode<State>> nodeIterator = batch.iterator();
        while(nodeIterator.hasNext())
        {
            SearchNode<State> curNode = nodeIterator.next();
            if (set.contains(curNode)) {
                Util.debug("Not Added to the Controller: " + curNode.toString());
                nodeIterator.remove();
            }
            else
            {
                Util.debug("Added to the Controller: " + curNode.toString());
                set.add(curNode);
            }
        }
        Util.debug("");
        this.controller.insertBatch(batch);
    }
}