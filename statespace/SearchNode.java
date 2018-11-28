// package statespace;

public class SearchNode<State> {
    // member variables
    private State state;
    private State prevstate;
    private String transformation;

    private SearchNode<State> prevSearchNode;

    // constructor
    public SearchNode(State state) {
        this.state = state;
    }

    public SearchNode<State> getPrevSearchNode() {
        return this.prevSearchNode;
    }

    public void setPrevSearchNode(SearchNode<State> prevSearchNode) {
        this.prevSearchNode = prevSearchNode;
    }

    public String toString() {
        return "{ Prevstate: (" + prevstate + ") ; Transformation : (" + this.transformation + ")" + " ; CurrState : ("
                + this.state.toString() + ")}";
    }

    public State getState() {
        return this.state;
    }

    public State getPrevState() {
        return this.prevstate;
    }

    public void setPrevState(State prevstate) {
        this.prevstate = prevstate;
    }

    public void setTransformation(String transformation) {
        this.transformation = transformation;
    }
    // transformation functions applicable on each searchnode

    // fill later
}
