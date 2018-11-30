package StateSpaceSearch;

import java.util.ArrayList;
import java.util.List;

public class SearchPath<State> {
    private List<SearchNode<State>> storedPath;
    private List<SearchNode<State>> actualPath;

    public SearchPath() {
        this.storedPath = new ArrayList<SearchNode<State>>();
        this.actualPath = new ArrayList<SearchNode<State>>();
    }

    public void append(SearchNode<State> searchNode) {
        this.storedPath.add(searchNode);
    }

    public void showPath() {
        System.out.println("The SearchPath is:");
        SearchNode<State> currLastNode = this.storedPath.get(this.storedPath.size() - 1);

        while (currLastNode != null) {
            this.actualPath.add(0, currLastNode);
            currLastNode = currLastNode.getPrevSearchNode();
        }

        for (SearchNode<State> currnode : this.actualPath) {
            System.out.println(currnode);
        }
    }

}
