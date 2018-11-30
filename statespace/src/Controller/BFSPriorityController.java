package Controller;

import StateSpaceSearch.Controller;
import StateSpaceSearch.SearchNode;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BFSPriorityController<State> extends Controller<State> {

    class DistanceComparator implements Comparator<SearchNode<State>> {

        @Override
        public int compare(SearchNode<State> s1, SearchNode<State> s2) {
            int dist1 = Integer.parseInt(s1.getTransformation());
            int dist2 = Integer.parseInt(s2.getTransformation());
            if (dist1 > dist2) {
                return 1;
            } else if (dist1 < dist2) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    private PriorityQueue<SearchNode<State>> queue;

    public BFSPriorityController() {
        queue = new PriorityQueue<SearchNode<State>>(5, new DistanceComparator());
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
