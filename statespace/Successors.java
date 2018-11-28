import java.util.*;

public abstract class Successors<State> {
    public abstract List<SearchNode<State>> expand(SearchNode<State> searchNode);

    public abstract SearchNode<State> convertStateToSearchNode(String operation, State newState, State prevState);

}

// inherit from successors
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
    // TODO: if we want an extra transformation make it a visitor

}
