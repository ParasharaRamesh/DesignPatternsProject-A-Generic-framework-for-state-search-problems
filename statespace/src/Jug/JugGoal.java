package Jug;

import StateSpaceSearch.Goal;

public class JugGoal<State> extends Goal<State> {
    private State target;

    public JugGoal(State target) {
        this.target = target;
    }

    @Override
    public Boolean satisfied(State state) {
        return state.equals(target);
    }
}
