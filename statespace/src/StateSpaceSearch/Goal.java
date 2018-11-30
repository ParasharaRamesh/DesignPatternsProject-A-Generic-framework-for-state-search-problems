package StateSpaceSearch;

public abstract class Goal<State> {
    public abstract Boolean satisfied(State state);
}
