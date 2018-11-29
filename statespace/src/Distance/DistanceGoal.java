package Distance;

import StateSpaceSearch.Goal;

public class DistanceGoal<State> extends Goal<State> {
    private State target;

    public DistanceGoal(State target) {
        this.target = target;
    }

    @Override
    public Boolean satisfied(State state) {
        return state.equals(target);
    }
}
