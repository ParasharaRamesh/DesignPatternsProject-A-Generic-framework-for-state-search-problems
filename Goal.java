public abstract class Goal<State> {
    public abstract Boolean satisfied(State state);
}

// concrete domain specific goal
class JugGoal<JugState> {
    private JugState target;

    public JugGoal(JugState target) {
        this.target = target;
    }

    public Boolean satisfied(JugState state) {
        return state.equals(target);
    }
}