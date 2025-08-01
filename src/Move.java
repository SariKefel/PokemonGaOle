import java.io.Serializable;

public class Move implements Serializable {
    private String name;
    private int power;
    private PokemonType type;
    
    public Move(String name, int power, PokemonType type) {
        this.name = name;
        this.power = power;
        this.type = type;
    }
    
    // Getters
    public String getName() { return name; }
    public int getPower() { return power; }
    public PokemonType getType() { return type; }
    
    @Override
    public String toString() {
        return name + " (" + type + ") - Power: " + power;
    }
} 