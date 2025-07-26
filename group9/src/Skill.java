package group9.src;
public enum Skill {
    NONE("No special skill."),
    CRITICAL_BOOST("Increases critical hit damage."),
    DEFENSE_UP("Slightly increases Defense during battle."),
    SUPER_EFFECTIVE_UP("Boosts the power of super-effective moves.");

    private final String description;

    Skill(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
