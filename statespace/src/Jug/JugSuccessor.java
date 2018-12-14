package Jug;

import StateSpaceSearch.SearchNode;
import StateSpaceSearch.Successors;

import java.util.ArrayList;
import java.util.List;

public class JugSuccessor<State extends JugState> extends Successors<State> {

    private Integer capacity1 = JugState.capacityJug1;
    private Integer capacity2 = JugState.capacityJug2;
    private State temp;

    public State fillJug1(State inpState) {
        Integer jug1 = inpState.getJug1();
        Integer jug2 = inpState.getJug2();
        State opState = null;
        if (jug1 < this.capacity1) {
            try {
                opState = (State) inpState.clone();
            }
            catch (CloneNotSupportedException e)
            {
                e.printStackTrace();
            }
            opState.setJug1(this.capacity1);
        }
        return opState;
    }

    public State fillJug2(State inpState) {
        Integer jug2 = inpState.getJug2();
        Integer jug1 = inpState.getJug1();
        State opState = null;
        if (jug2 < this.capacity2) {
            try {
                opState = (State) inpState.clone();
            }
            catch (CloneNotSupportedException e)
            {
                e.printStackTrace();
            }
            opState.setJug2(this.capacity2);
        }
        return opState;
    }

    public State emptyJug1(State inpState) {
        Integer jug1 = inpState.getJug1();
        Integer jug2 = inpState.getJug2();
        State opState = null;
        if (jug1 > 0) {
            jug1 = 0;
            try{
                opState = (State) inpState.clone();
            }
            catch (CloneNotSupportedException e)
            {
                e.printStackTrace();
            }
            opState.setJug1(jug1);
        }
        return opState;
    }

    public State emptyJug2(State inpState) {
        Integer jug2 = inpState.getJug2();
        State opState = null;
        if (jug2 > 0) {
            jug2 = 0;
            try {
                opState = (State) inpState.clone();
            }
            catch (CloneNotSupportedException e)
            {
                e.printStackTrace();
            }
            opState.setJug2(jug2);
        }
        return opState;
    }

    public State transferJug1ToJug2(State inpState) {
        Integer jug1 = inpState.getJug1();
        Integer jug2 = inpState.getJug2();
        State opState = null;
        try {
            opState = (State) inpState.clone();

            if (jug2.equals(this.capacity2)) {
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
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }

        return opState;
    }

    public State transferJug2ToJug1(State inpState) {
        Integer jug1 = inpState.getJug1();
        Integer jug2 = inpState.getJug2();
        State opState = null;
        try {
            opState = (State) inpState.clone();

            if (jug1.equals(this.capacity1)) {
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
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        return opState;
    }

    // Helper Function

    @Override
    public SearchNode<State> convertStateToSearchNode(String operation, State newState, State prevState) {
        SearchNode<State> sNode = new SearchNode<State>(newState);
        sNode.setPrevState(prevState);
        sNode.setTransformation(operation);
        return sNode;
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
        SearchNode<State> currNode = null;
        if (s1 != null) {
            currNode = this.convertStateToSearchNode("fillJug1", s1, inpState);
            currNode.setPrevSearchNode(searchNode);
            states.add(currNode);
        }
        if (s2 != null) {
            currNode = this.convertStateToSearchNode("fillJug2", s2, inpState);
            currNode.setPrevSearchNode(searchNode);
            states.add(currNode);
        }
        if (s3 != null) {
            currNode = this.convertStateToSearchNode("emptyJug1", s3, inpState);
            currNode.setPrevSearchNode(searchNode);
            states.add(currNode);
        }
        if (s4 != null) {
            currNode = this.convertStateToSearchNode("emptyJug2", s4, inpState);
            currNode.setPrevSearchNode(searchNode);
            states.add(currNode);
        }
        if (s5 != null) {
            currNode = this.convertStateToSearchNode("transferJug1ToJug2", s5, inpState);
            currNode.setPrevSearchNode(searchNode);
            states.add(currNode);
        }
        if (s6 != null) {
            currNode = this.convertStateToSearchNode("transferJug2ToJug1", s6, inpState);
            currNode.setPrevSearchNode(searchNode);
            states.add(currNode);
        }
        return states;
    }

}
