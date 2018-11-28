// package statespace;

public abstract class Goal<State> {
    public abstract Boolean satisfied(State state);
}

// concrete domain specific goal
class JugGoal<State> extends Goal<State> {
    private State target;

    public JugGoal(State target) {
        this.target = target;
    }

    public Boolean satisfied(State state) {
        return state.equals(target);
    }
}

class DistanceGoal<State> extends Goal<State> {
    private State target;

    public DistanceGoal(State target) {
        this.target = target;
    }

    public Boolean satisfied(State state) {
        return state.equals(target);
    }
}
