import java.awt.List;

public abstract class Succesors<State> {
    public abstract List<SearchNode<State>> expand(State state);
}

// inherit from successors
public class JugSuccessor<State> extends Succesors<State> {

    private Integer capacity1 = JugState.capacityJug1;
    private Integer capacity2 = JugState.capacityJug2;

    // transformation functions
    public State fillJug1(State inpState) {
        if (inpState.jug1 < capacity1) {

            inpState.jug1 = capacity1;
            return inpState;
        }
        return null;
    }

    public State fillJug2(State inpState) {
        if (inpState.jug2 <= capacity2) {
            inpState.jug2 = capacity2;
        }
        return null;
    }

    public State emptyJug1(State inpState) {
        if (inpState.jug1 >= 0) {
            inpState.jug1 = 0;
            return inpState;
        }
        return null;
    }

    public State emptyJug2(State inpState) {
        if (inpState.jug2 >= 0) {
            inpState.jug2 = 0;
            return inpState;
        }
        return null;
    }

    public State transferJug1ToJug2(State inpState) {
        if (inpState.jug2 == capacity2) {
            return null;
        } else if (inpState.jug1 + inpState.jug2 <= capacity2) {
            inState.jug2 += inpState.jug1;
            inpState.jug1 = 0;
            return inpState;
        } else {
            int diff = capacity2 - jug2;
            inpState.jug1 -= diff;
            inpState.jug2 = capacity2;
            return inpState;
        }
    }

    public State transferJug2ToJug1(State inpState) {
        if (inpState.jug1 == capacity1) {
            return null;
        } else if (inpState.jug1 + inpState.jug2 <= capacity1) {
            inState.jug1 += inpState.jug2;
            inpState.jug2 = 0;
            return inpState;
        } else {
            int diff = capacity1 - jug1;
            inpState.jug2 -= diff;
            inpState.jug1 = capacity1;
            return inpState;
        }
    }

    // helper function
    private SearchNode<State> convertStateToSearchNode(String operation, State newState) {
        SearchNode<State> snode = new SearchNode<State>(newState);
        snode.setTransformation(operation);
        return snode;
    }

    @Override
    public List<SearchNode<State>> expand(State state) {
        List<SearchNode<State>> states;
        State s1 = this.fillJug1(inpState);
        State s2 = this.fillJug2(inpState);
        State s3 = this.emptyJug1(inpState);
        State s4 = this.emptyJug2(inpState);
        State s5 = this.transferJug1ToJug2(inpState);
        State s6 = this.transferJug2ToJug1(inpState);
        if (s1 != null) {
            states.add(this.convertStateToSearchNode("fillJug1", s1));
        }
        if (s2 != null) {
            states.add(this.convertStateToSearchNode("fillJug2", s2));
        }
        if (s3 != null) {
            states.add(this.convertStateToSearchNode("emptyJug1", s3));
        }
        if (s4 != null) {
            states.add(this.convertStateToSearchNode("emptyJug2", s4));
        }
        if (s5 != null) {
            states.add(this.convertStateToSearchNode("transferJug1ToJug2", s5));
        }
        if (s6 != null) {
            states.add(this.convertStateToSearchNode("transferJug2ToJug1", s6));
        }
        return states;
    }
    // TODO: if we want an extra transformation make it a visitor

}
