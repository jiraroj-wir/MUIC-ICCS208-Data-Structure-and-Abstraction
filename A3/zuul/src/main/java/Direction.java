/*
 * directions player can travel
 */
public enum Direction {
    NORTH("north"),
    EAST("east"),
    SOUTH("south"),
    WEST("west"),
    UP("up"),
    DOWN("down");

    private String label;

    Direction(String label) { this.label = label; }

    public String label() { return label; }

    @Override
    public String toString() {
        return label;
    }

    public static Direction fromString(String input) {
        if (input == null) {
            return null;
        }

        for (Direction direction : Direction.values()) {
            if (direction.label.equalsIgnoreCase(input)) { // should be case in-sensitive?
                return direction;
            }
        }

        return null;
    }
}
