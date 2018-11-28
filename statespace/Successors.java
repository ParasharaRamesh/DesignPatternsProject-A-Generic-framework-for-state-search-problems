import java.util.*;

class Pair<A, B> {
    private A first;
    private B second;

    public Pair(A first, B second) {
        super();
        this.first = first;
        this.second = second;
    }

    public int hashCode() {
        int hashFirst = first != null ? first.hashCode() : 0;
        int hashSecond = second != null ? second.hashCode() : 0;

        return (hashFirst + hashSecond) * hashSecond + hashFirst;
    }

    public boolean equals(Object other) {
        if (other instanceof Pair) {
            Pair otherPair = (Pair) other;
            return ((this.first == otherPair.first
                    || (this.first != null && otherPair.first != null && this.first.equals(otherPair.first)))
                    && (this.second == otherPair.second || (this.second != null && otherPair.second != null
                            && this.second.equals(otherPair.second))));
        }

        return false;
    }

    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    public A getFirst() {
        return first;
    }

    public void setFirst(A first) {
        this.first = first;
    }

    public B getSecond() {
        return second;
    }

    public void setSecond(B second) {
        this.second = second;
    }
}

public abstract class Successors<State> {
    public abstract List<SearchNode<State>> expand(SearchNode<State> searchNode);

    public abstract SearchNode<State> convertStateToSearchNode(String operation, State newState, State prevState);

}

class JugSuccessor<State extends JugState> extends Successors<State> {

    private Integer capacity1 = JugState.capacityJug1;
    private Integer capacity2 = JugState.capacityJug2;
    private State temp;
    // private JugTransformer transformer;

    // transformation functions
    public State fillJug1(State inpState) {
        Integer jug1 = inpState.getJug1();
        Integer jug2 = inpState.getJug2();
        State opState = null;
        if (jug1 < this.capacity1) {
            opState = (State) inpState.clone();
            opState.setJug1(this.capacity1);
            // System.out.println("done...filljug1!!" + opState);
        }
        return opState;
    }

    public State fillJug2(State inpState) {
        Integer jug2 = inpState.getJug2();
        Integer jug1 = inpState.getJug1();
        State opState = null;
        if (jug2 < this.capacity2) {
            opState = (State) inpState.clone();
            opState.setJug2(this.capacity2);
            // System.out.println("done...filljug2!!" + opState);
        }
        return opState;
    }

    public State emptyJug1(State inpState) {
        Integer jug1 = inpState.getJug1();
        Integer jug2 = inpState.getJug2();
        State opState = null;
        if (jug1 > 0) {
            jug1 = 0;
            opState = (State) inpState.clone();
            opState.setJug1(jug1);
        }
        return opState;
    }

    public State emptyJug2(State inpState) {
        Integer jug2 = inpState.getJug2();
        State opState = null;
        if (jug2 > 0) {
            jug2 = 0;
            opState = (State) inpState.clone();
            opState.setJug2(jug2);
        }
        return opState;
    }

    public State transferJug1ToJug2(State inpState) {
        Integer jug1 = inpState.getJug1();
        Integer jug2 = inpState.getJug2();
        State opState = (State) inpState.clone();
        if (jug2 == this.capacity2) {
            opState = null;
        } else if (jug1 + jug2 <= this.capacity2) {
            jug2 += jug1;
            jug1 = 0;
            opState.setJug1(jug1);
            opState.setJug2(jug2);

        } else {
            int diff = this.capacity2 - jug2;
            jug1 -= diff;
            jug2 = this.capacity2;
            opState.setJug1(jug1);
            opState.setJug2(jug2);
        }
        return opState;
    }

    public State transferJug2ToJug1(State inpState) {
        Integer jug1 = inpState.getJug1();
        Integer jug2 = inpState.getJug2();
        State opState = (State) inpState.clone();
        if (jug1 == this.capacity1) {
            opState = null;
        } else if (jug1 + jug2 <= this.capacity1) {
            jug1 += jug2;
            jug2 = 0;
            opState.setJug1(jug1);
            opState.setJug2(jug2);
        } else {
            int diff = this.capacity1 - jug1;
            jug2 -= diff;
            jug1 = this.capacity1;
            opState.setJug1(jug1);
            opState.setJug2(jug2);
        }
        return opState;
    }

    // helper function

    @Override
    public SearchNode<State> convertStateToSearchNode(String operation, State newState, State prevState) {
        SearchNode<State> snode = new SearchNode<State>(newState);
        snode.setPrevState(prevState);
        snode.setTransformation(operation);
        return snode;
    }

    @Override
    public List<SearchNode<State>> expand(SearchNode<State> searchNode) {
        State inpState = searchNode.getState();

        List<SearchNode<State>> states = new ArrayList<SearchNode<State>>();
        State s1 = this.fillJug1(inpState);
        State s2 = this.fillJug2(inpState);
        State s3 = this.emptyJug1(inpState);
        State s4 = this.emptyJug2(inpState);
        State s5 = this.transferJug1ToJug2(inpState);
        State s6 = this.transferJug2ToJug1(inpState);
        SearchNode<State> currnode = null;
        if (s1 != null) {
            currnode = this.convertStateToSearchNode("fillJug1", s1, inpState);
            currnode.setPrevSearchNode(searchNode);
            states.add(currnode);
        }
        if (s2 != null) {
            currnode = this.convertStateToSearchNode("fillJug2", s2, inpState);
            currnode.setPrevSearchNode(searchNode);
            states.add(currnode);
        }
        if (s3 != null) {
            currnode = this.convertStateToSearchNode("emptyJug1", s3, inpState);
            currnode.setPrevSearchNode(searchNode);
            states.add(currnode);
        }
        if (s4 != null) {
            currnode = this.convertStateToSearchNode("emptyJug2", s4, inpState);
            currnode.setPrevSearchNode(searchNode);
            states.add(currnode);
        }
        if (s5 != null) {
            currnode = this.convertStateToSearchNode("transferJug1ToJug2", s5, inpState);
            currnode.setPrevSearchNode(searchNode);
            states.add(currnode);
        }
        if (s6 != null) {
            currnode = this.convertStateToSearchNode("transferJug2ToJug1", s6, inpState);
            currnode.setPrevSearchNode(searchNode);
            states.add(currnode);
        }
        return states;
    }

}

class DistanceSuccessor<State extends DistanceState> extends Successors<State> {
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
        assert searchNode.getState() != null : "searchNode doesnt have a currstate!";
        assert this.distances.get(searchNode.getState()) != null : "the distances hashmap does'nt have this!";
        // if (this.distances.get(searchNode.getState())) {
        // System.out.println("distance hashmap doesn't have this!");
        // }
        ArrayList<Pair<State, Integer>> neighbours = this.distances.get(searchNode.getState());
        assert neighbours != null : "neighbours are null!";
        // if (neighbours == null) {
        // System.out.println("The neighbours are null");
        // }
        for (Pair<State, Integer> neighbour : neighbours) {
            // System.out.println("inside neighbors loop!");
            State newState = neighbour.getFirst();
            Integer cost = neighbour.getSecond();
            SearchNode<State> snode = this.convertStateToSearchNode(cost.toString(), newState, searchNode.getState());
            snode.setPrevSearchNode(searchNode);
            states.add(snode);
        }
        return states;
    }

    @Override
    public SearchNode<State> convertStateToSearchNode(String operation, State newState, State prevState) {
        SearchNode<State> snode = new SearchNode<State>(newState);
        snode.setPrevState(prevState);
        snode.setTransformation(operation);
        return snode;
    }
}
