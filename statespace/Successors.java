import java.util.*;

public abstract class Successors<State> {
    public abstract List<SearchNode<State>> expand(State state);

    public abstract SearchNode<State> convertStateToSearchNode(String operation, State newState);

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
    public SearchNode<State> convertStateToSearchNode(String operation, State newState) {
        SearchNode<State> snode = new SearchNode<State>(newState);
        snode.setTransformation(operation);
        return snode;
    }

    @Override
    public List<SearchNode<State>> expand(State inpState) {
        // System.out.println("capacity1:" + this.capacity1);
        // System.out.println("capacity2:" + this.capacity2);
        // return null;
        List<SearchNode<State>> states = new ArrayList<SearchNode<State>>();
        State s1 = this.fillJug1(inpState);
        State s2 = this.fillJug2(inpState);
        State s3 = this.emptyJug1(inpState);
        State s4 = this.emptyJug2(inpState);
        State s5 = this.transferJug1ToJug2(inpState);
        State s6 = this.transferJug2ToJug1(inpState);
        if (s1 != null) {
            // System.out.println("\t\tdone fillJug1");
            // System.out.println("\t\t\tfillJug1 is" + s1);
            states.add(this.convertStateToSearchNode("fillJug1", s1));
        }
        if (s2 != null) {
            // System.out.println("\t\tdone fillJug2");
            states.add(this.convertStateToSearchNode("fillJug2", s2));
        }
        if (s3 != null) {
            // System.out.println("\t\tdone emptyJug1");
            // System.out.println("\t\t\temptyJug1 is" + s3);
            states.add(this.convertStateToSearchNode("emptyJug1", s3));
        }
        if (s4 != null) {
            // System.out.println("\t\tdone emptyJug2");
            // System.out.println("\t\t\temptyJug2 is" + s4);
            states.add(this.convertStateToSearchNode("emptyJug2", s4));
        }
        if (s5 != null) {
            // System.out.println("\t\tdone transferJug1ToJug2");
            states.add(this.convertStateToSearchNode("transferJug1ToJug2", s5));
        }
        if (s6 != null) {
            // System.out.println("\t\tdone transferJug2ToJug1");
            states.add(this.convertStateToSearchNode("transferJug2ToJug1", s6));
        }
        return states;
    }
    // TODO: if we want an extra transformation make it a visitor

}
