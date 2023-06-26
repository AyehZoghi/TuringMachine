// Example = a -> b,Right

public class Rule {
    private String readCharacter;
    private String writeCharacter;
    private Direction tapeDirection;

    public Rule(String readCharacter, String writeCharacter, Direction tapeDirection) {
        this.readCharacter = readCharacter;
        this.writeCharacter = writeCharacter;
        this.tapeDirection = tapeDirection;
    }

    public String getReadCharacter() {
        return readCharacter;
    }

    public void setReadCharacter(String readCharacter) {
        this.readCharacter = readCharacter;
    }

    public String getWriteCharacter() {
        return writeCharacter;
    }

    public void setWriteCharacter(String writeCharacter) {
        this.writeCharacter = writeCharacter;
    }

    public Direction getTapeDirection() {
        return tapeDirection;
    }

    public void setTapeDirection(Direction tapeDirection) {
        this.tapeDirection = tapeDirection;
    }
}
