package ControllerDecorator;

import StateSpaceSearch.Controller;
import StateSpaceSearch.SearchNode;

import java.util.List;

public abstract class ControllerDecorator<State> extends Controller<State> {

    public abstract SearchNode<State> remove();

    public abstract void insert(SearchNode<State> searchnode);

    public abstract void insertBatch(List<SearchNode<State>> batch);
}
