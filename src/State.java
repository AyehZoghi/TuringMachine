import java.util.List;

public class State {
    private String name;
    private boolean isFinal;
    private List<Transition> transitions;

    public State(String name, boolean isFinal, List<Transition> transitions) {
        this.name = name;
        this.isFinal = isFinal;
        this.transitions = transitions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }
}
