package StateSpaceSearch;

import java.util.Objects;

public class SearchNode<State> {

    private State state;
    private State prevstate;
    private String transformation;
    private SearchNode<State> prevSearchNode;

    public SearchNode(State state) {
        this.state = state;
    }

    public SearchNode<State> getPrevSearchNode() {
        return this.prevSearchNode;
    }

    public void setPrevSearchNode(SearchNode<State> prevSearchNode) {
        this.prevSearchNode = prevSearchNode;
    }

    @Override
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

    public String getTransformation() {
        return this.transformation;
    }

    public void setTransformation(String transformation) {
        this.transformation = transformation;
    }

    @Override
    public boolean  equals (Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        }
        SearchNode<State> other = (SearchNode<State>) object;

        if (!this.state.equals(other.state))
        {
            return false;
        }

        if (!this.transformation.equals(other.transformation))
        {
            return false;
        }

        if (!this.prevstate.equals(other.prevstate))
        {
            return false;
        }

        if (this.prevSearchNode.equals(other.prevSearchNode))
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.state);
    }

}
