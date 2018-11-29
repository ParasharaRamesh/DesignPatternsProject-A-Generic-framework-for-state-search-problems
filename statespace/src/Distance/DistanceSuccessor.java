package Distance;

import StateSpaceSearch.SearchNode;
import StateSpaceSearch.Successors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DistanceSuccessor<State extends DistanceState> extends Successors<State> {
    private HashMap<State, ArrayList<Pair<State, Integer>>> distances = null;

    public DistanceSuccessor() {
        this.distances = new HashMap<State, ArrayList<Pair<State, Integer>>>();
        this.populateAdjacencyList();
    }

    public void populateAdjacencyList() {
        State A = (State) State.createCity("A");
        State B = (State) State.createCity("B");
        State C = (State) State.createCity("C");
        State D = (State) State.createCity("D");
        State E = (State) State.createCity("E");
        ArrayList<Pair<State, Integer>> Aneighbours = new ArrayList<Pair<State, Integer>>();
        ArrayList<Pair<State, Integer>> Bneighbours = new ArrayList<Pair<State, Integer>>();
        ArrayList<Pair<State, Integer>> Cneighbours = new ArrayList<Pair<State, Integer>>();
        ArrayList<Pair<State, Integer>> Dneighbours = new ArrayList<Pair<State, Integer>>();
        ArrayList<Pair<State, Integer>> Eneighbours = new ArrayList<Pair<State, Integer>>();

        Aneighbours.add(new Pair<State, Integer>(B, 1));
        Aneighbours.add(new Pair<State, Integer>(C, 2));

        Bneighbours.add(new Pair<State, Integer>(A, 1));
        Bneighbours.add(new Pair<State, Integer>(C, 3));
        Bneighbours.add(new Pair<State, Integer>(D, 4));
        Bneighbours.add(new Pair<State, Integer>(E, 1));

        Cneighbours.add(new Pair<State, Integer>(A, 2));
        Cneighbours.add(new Pair<State, Integer>(B, 3));
        Cneighbours.add(new Pair<State, Integer>(D, 5));
        Cneighbours.add(new Pair<State, Integer>(E, 6));

        Dneighbours.add(new Pair<State, Integer>(B, 4));
        Dneighbours.add(new Pair<State, Integer>(C, 5));
        Dneighbours.add(new Pair<State, Integer>(E, 1));

        Eneighbours.add(new Pair<State, Integer>(C, 6));
        Eneighbours.add(new Pair<State, Integer>(B, 1));
        Eneighbours.add(new Pair<State, Integer>(D, 1));

        this.distances.put(A, Aneighbours);
        this.distances.put(B, Bneighbours);
        this.distances.put(C, Cneighbours);
        this.distances.put(D, Dneighbours);
        this.distances.put(E, Eneighbours);
    }

    @Override
    public List<SearchNode<State>> expand(SearchNode<State> searchNode) {
        List<SearchNode<State>> states = new ArrayList<SearchNode<State>>();
        assert searchNode.getState() != null : "searchNode doesn't have a current State!";
        assert this.distances.get(searchNode.getState()) != null : "The distances HashMap does'nt have this entry!";

        ArrayList<Pair<State, Integer>> neighbours = this.distances.get(searchNode.getState());
        assert neighbours != null : "Neighbours are null!";

        for (Pair<State, Integer> neighbour : neighbours) {
            State newState = neighbour.getFirst();
            Integer cost = neighbour.getSecond();
            SearchNode<State> sNode = this.convertStateToSearchNode(cost.toString(), newState, searchNode.getState());
            sNode.setPrevSearchNode(searchNode);
            states.add(sNode);
        }
        return states;
    }

    @Override
    public SearchNode<State> convertStateToSearchNode(String operation, State newState, State prevState) {
        SearchNode<State> sNode = new SearchNode<State>(newState);
        sNode.setPrevState(prevState);
        sNode.setTransformation(operation);
        return sNode;
    }
}
