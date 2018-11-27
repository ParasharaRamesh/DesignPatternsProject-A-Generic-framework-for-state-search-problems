// package statespace;

public class SearchNode<State> {
    // member variables
    private State state;
    private String transformation;

    // constructor
    public SearchNode(State state) {
        this.state = state;
    }

    public String toString() {
        return "Transformation : " + this.transformation + ";" + this.state.toString();
    }
    // can you hear me??

    public State getState() {
        return this.state;
    }

    public void setTransformation(String transformation) {
        this.transformation = transformation;
    }
    // transformation functions applicable on each searchnode

    // fill later
}
