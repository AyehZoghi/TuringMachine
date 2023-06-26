import java.util.List;

public class TuringMachine {
    private List<State> states;

    public TuringMachine(List<State> states) {
        this.states = states;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }
}
