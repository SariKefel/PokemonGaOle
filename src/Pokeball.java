public enum Pokeball {
    POKEBALL(1.0),
    GREATBALL(1.5),
    ULTRABALL(2.0),
    MASTERBALL(255.0);
    
    private final double modifier;
    
    Pokeball(double modifier) {
        this.modifier = modifier;
    }
    
    public double getModifier() {
        return modifier;
    }
    
    public String getName() {
        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }
} 