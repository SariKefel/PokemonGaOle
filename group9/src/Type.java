package group9.src;
public enum Type {
    FIRE, WATER, GRASS, NORMAL, ELECTRIC, PSYCHIC, FIGHTING, DRAGON;

    /**
     * Determines the damage multiplier based on type advantage.
     * @param opponentType The type of the defending Pokémon.
     * @return 1.5 for super-effective, 0.5 for not very effective, 1.0 otherwise.
     */
    public double getEffectiveness(Type opponentType) {
        switch (this) {
            case FIRE:
                return opponentType == GRASS ? 1.5 : (opponentType == WATER ? 0.5 : 1.0);
            case WATER:
                return opponentType == FIRE ? 1.5 : (opponentType == GRASS ? 0.5 : 1.0);
            case GRASS:
                return opponentType == WATER ? 1.5 : (opponentType == FIRE ? 0.5 : 1.0);
            case ELECTRIC:
                return opponentType == WATER ? 1.5 : (opponentType == GRASS ? 0.5 : 1.0);
            case PSYCHIC:
                return opponentType == FIGHTING ? 1.5 : (opponentType == PSYCHIC ? 0.5 : 1.0);
            case FIGHTING:
                return opponentType == NORMAL ? 1.5 : (opponentType == PSYCHIC ? 0.5 : 1.0);
            case DRAGON:
                return opponentType == DRAGON ? 1.5 : (opponentType == FIGHTING ? 0.5 : 1.0);
            case NORMAL:
            default: // NORMAL
                return 1.0;
        }
    }
}