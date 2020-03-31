public class Opponent {

    private final String opponentType;
    private final String[] validPickableTypes;

    /**
     * Constructor
     */
    public Opponent(String opponentType, String[] validPickableTypes){
        this.opponentType = opponentType;
        this.validPickableTypes = validPickableTypes;
    }

    /**
     * Get the opponent type
     */
    public String getOpponentType() {
        return opponentType;
    }

    /**
     * Get the valid pickable types
     */
    public String[] getValidPickableTypes() {
        return validPickableTypes;
    }
}
