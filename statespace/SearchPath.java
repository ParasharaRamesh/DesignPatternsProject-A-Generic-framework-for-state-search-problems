
// package statespace;

import java.util.*;

public class SearchPath<State> {
    private List<SearchNode<State>> path;

    public SearchPath() {
        this.path = new ArrayList<SearchNode<State>>();
    }

    public void append(SearchNode<State> searchnode) {
        this.path.add(searchnode);
    }

    public void showPath() {
        for (SearchNode<State> searchnode : path) {
            System.out.print("-----------------------");
            System.out.println(searchnode);
            System.out.print("-----------------------");
        }
    }
    // TODO: add some maniplation of list methods
}