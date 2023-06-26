import java.util.ArrayList;
import java.util.List;

public class Main {
    //test examples to run
    public static void main(String[] args) {
        TuringMachine tm1 = new TuringMachine(List.of(
                new State("q0", false, List.of(
                        new Transition(new Rule("a", "x", Direction.Right), "q1")
                )),
                new State("q1", false, List.of(
                        new Transition(new Rule("a", "x", Direction.Right), "q2")
                )),
                new State("q2", false, List.of(
                        new Transition(new Rule("b", "y", Direction.Right), "q2"),
                        new Transition(new Rule("c", "z", Direction.Right), "q3")
                )),
                new State("q3", false, List.of(
                        new Transition(new Rule("_", "_", Direction.Left), "q4")
                )),
                new State("q4", true, List.of())
        ));

        TuringMachine tm2 = new TuringMachine(List.of(
                new State("q0", false, List.of(
                        new Transition(new Rule("a", "x", Direction.Right), "q1"),
                        new Transition(new Rule("y", "y", Direction.Right), "q3")
                )),
                new State("q1", false, List.of(
                        new Transition(new Rule("y", "y", Direction.Right), "q1"),
                        new Transition(new Rule("a", "a", Direction.Right), "q1"),
                        new Transition(new Rule("b", "y", Direction.Left), "q2")
                )),
                new State("q2", false, List.of(
                        new Transition(new Rule("y", "y", Direction.Left), "q2"),
                        new Transition(new Rule("a", "a", Direction.Left), "q2"),
                        new Transition(new Rule("x", "x", Direction.Right), "q0")
                )),
                new State("q3", false, List.of(
                        new Transition(new Rule("y", "y", Direction.Right), "q3"),
                        new Transition(new Rule("_", "_", Direction.Left), "q4")
                )),
                new State("q4", true, List.of())
        ));

        // Input string for aab*c
        String inputString1 = "aabbbbc";
        // Input string for a^nb^n
        String inputString2 = "aaabbb";
        // checkInput(tm1, inputString1);
        checkInput(tm2, inputString2);
    }

    public static void checkInput(TuringMachine tm, String inputString) {
        List<String> tape = new ArrayList<>();

        // adding the characters of the input string into the tape
        for (int i = 0; i < inputString.length(); i++) {
            tape.add(Character.toString(inputString.charAt(i)));
        }

        int tapeHead = 0;
        int stepCount = 1;
        int maxSteps = 1000; // Set your desired threshold here

        // start from the first state
        State currentState = tm.getStates().get(0);

        while (stepCount <= maxSteps) {
            System.out.println("----Step " + stepCount + ":");

            if (tapeHead >= tape.size()) {
                // add a "_" to the end of the tape
                tape.add("_");
            } else if (tapeHead < 0) {
                // shift all to the right and add a "_" at the start of the tape
                tape.add(0, "_");
                // set tape head to 0 again because ArrayLists don't have negative indices
                tapeHead = 0;
            }

            System.out.println("Current State: " + currentState.getName());
            System.out.println("Tape: " + printTape(tapeHead, tape));

            String currentInput = tape.get(tapeHead);

            // Searching for possible transitions within current state considering the current input
            int transitionFoundIndex = -1;
            for (int i = 0; i < currentState.getTransitions().size(); i++) {
                if (currentState.getTransitions().get(i).getRule().getReadCharacter().equals(currentInput)) {
                    transitionFoundIndex = i;
                    break;
                }
            }

            // Check if the machine halts or not
            if (transitionFoundIndex == -1 && !currentState.isFinal()) {
                System.out.println("Machine halted. Current state isn't final. Input Rejected!");
                break;
            }

            if (transitionFoundIndex == -1 && currentState.isFinal()) {
                System.out.println("Machine halted. Current state is final. Input Accepted!");
                break;
            }

            // if transition found index isn't -1
            Transition transition = currentState.getTransitions().get(transitionFoundIndex);

            // Write into the tape
            tape.set(tapeHead, transition.getRule().getWriteCharacter());

            System.out.println("We read \"" + currentInput + "\" and write \"" + transition.getRule().getWriteCharacter() +
                    "\" instead of that, and then the tape head will go " + transition.getRule().getTapeDirection() + ".");
            System.out.println("The next state is " + transition.getNextState() + ".\n");

            // Moving the head of the tape
            if (transition.getRule().getTapeDirection() == Direction.Right) {
                tapeHead++;
            } else {
                tapeHead--;
            }

            // Changing the current state to the next state
            // after q0, we don't necessarily go to q1, so we have to check!
            currentState = getStateByName(tm, transition.getNextState());
            stepCount++;
        }

        if (stepCount > maxSteps) {
            System.out.println("Machine halted. Exceeded maximum steps. Input Rejected!");
        }
    }

    // Formatting the tape to write into the console
    public static String printTape(int tapeHead, List<String> tape) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tape.size(); i++) {
            if (i == tapeHead) {
                output.append("<").append(tape.get(i)).append("> ");
            } else {
                output.append(tape.get(i)).append(" ");
            }
        }
        return output.toString();
    }

    public static State getStateByName(TuringMachine tm, String name) {
        for (State state : tm.getStates()) {
            if (state.getName().equals(name)) {
                return state;
            }
        }
        return null;
    }
}
