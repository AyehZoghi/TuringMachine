//Transition consists of a rule and a target state

public class Transition {
    private Rule rule;
    private String nextState;

    public Transition(Rule rule, String nextState) {
        this.rule = rule;
        this.nextState = nextState;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public String getNextState() {
        return nextState;
    }

    public void setNextState(String nextState) {
        this.nextState = nextState;
    }
}

