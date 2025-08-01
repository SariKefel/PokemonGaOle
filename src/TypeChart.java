import java.util.HashMap;
import java.util.Map;

public class TypeChart {
    private static final Map<PokemonType, Map<PokemonType, Double>> effectiveness = new HashMap<>();
    
    static {
        initializeTypeChart();
    }
    
    private static void initializeTypeChart() {
        // Initialize all types with neutral effectiveness (1.0)
        for (PokemonType attacker : PokemonType.values()) {
            effectiveness.put(attacker, new HashMap<>());
            for (PokemonType defender : PokemonType.values()) {
                effectiveness.get(attacker).put(defender, 1.0);
            }
        }
        
        // Fire type effectiveness
        setEffectiveness(PokemonType.FIRE, PokemonType.GRASS, 2.0);
        setEffectiveness(PokemonType.FIRE, PokemonType.ICE, 2.0);
        setEffectiveness(PokemonType.FIRE, PokemonType.BUG, 2.0);
        setEffectiveness(PokemonType.FIRE, PokemonType.STEEL, 2.0);
        setEffectiveness(PokemonType.FIRE, PokemonType.FIRE, 0.5);
        setEffectiveness(PokemonType.FIRE, PokemonType.WATER, 0.5);
        setEffectiveness(PokemonType.FIRE, PokemonType.ROCK, 0.5);
        setEffectiveness(PokemonType.FIRE, PokemonType.DRAGON, 0.5);
        
        // Water type effectiveness
        setEffectiveness(PokemonType.WATER, PokemonType.FIRE, 2.0);
        setEffectiveness(PokemonType.WATER, PokemonType.GROUND, 2.0);
        setEffectiveness(PokemonType.WATER, PokemonType.ROCK, 2.0);
        setEffectiveness(PokemonType.WATER, PokemonType.WATER, 0.5);
        setEffectiveness(PokemonType.WATER, PokemonType.GRASS, 0.5);
        setEffectiveness(PokemonType.WATER, PokemonType.DRAGON, 0.5);
        
        // Grass type effectiveness
        setEffectiveness(PokemonType.GRASS, PokemonType.WATER, 2.0);
        setEffectiveness(PokemonType.GRASS, PokemonType.GROUND, 2.0);
        setEffectiveness(PokemonType.GRASS, PokemonType.ROCK, 2.0);
        setEffectiveness(PokemonType.GRASS, PokemonType.FIRE, 0.5);
        setEffectiveness(PokemonType.GRASS, PokemonType.GRASS, 0.5);
        setEffectiveness(PokemonType.GRASS, PokemonType.POISON, 0.5);
        setEffectiveness(PokemonType.GRASS, PokemonType.FLYING, 0.5);
        setEffectiveness(PokemonType.GRASS, PokemonType.BUG, 0.5);
        setEffectiveness(PokemonType.GRASS, PokemonType.DRAGON, 0.5);
        setEffectiveness(PokemonType.GRASS, PokemonType.STEEL, 0.5);
        
        // Electric type effectiveness
        setEffectiveness(PokemonType.ELECTRIC, PokemonType.WATER, 2.0);
        setEffectiveness(PokemonType.ELECTRIC, PokemonType.FLYING, 2.0);
        setEffectiveness(PokemonType.ELECTRIC, PokemonType.GRASS, 0.5);
        setEffectiveness(PokemonType.ELECTRIC, PokemonType.ELECTRIC, 0.5);
        setEffectiveness(PokemonType.ELECTRIC, PokemonType.DRAGON, 0.5);
        setEffectiveness(PokemonType.ELECTRIC, PokemonType.GROUND, 0.0);
        
        // Ice type effectiveness
        setEffectiveness(PokemonType.ICE, PokemonType.GRASS, 2.0);
        setEffectiveness(PokemonType.ICE, PokemonType.GROUND, 2.0);
        setEffectiveness(PokemonType.ICE, PokemonType.FLYING, 2.0);
        setEffectiveness(PokemonType.ICE, PokemonType.DRAGON, 2.0);
        setEffectiveness(PokemonType.ICE, PokemonType.FIRE, 0.5);
        setEffectiveness(PokemonType.ICE, PokemonType.WATER, 0.5);
        setEffectiveness(PokemonType.ICE, PokemonType.ICE, 0.5);
        setEffectiveness(PokemonType.ICE, PokemonType.STEEL, 0.5);
        
        // Fighting type effectiveness
        setEffectiveness(PokemonType.FIGHTING, PokemonType.NORMAL, 2.0);
        setEffectiveness(PokemonType.FIGHTING, PokemonType.ICE, 2.0);
        setEffectiveness(PokemonType.FIGHTING, PokemonType.ROCK, 2.0);
        setEffectiveness(PokemonType.FIGHTING, PokemonType.STEEL, 2.0);
        setEffectiveness(PokemonType.FIGHTING, PokemonType.DARK, 2.0);
        setEffectiveness(PokemonType.FIGHTING, PokemonType.FLYING, 0.5);
        setEffectiveness(PokemonType.FIGHTING, PokemonType.POISON, 0.5);
        setEffectiveness(PokemonType.FIGHTING, PokemonType.PSYCHIC, 0.5);
        setEffectiveness(PokemonType.FIGHTING, PokemonType.BUG, 0.5);
        setEffectiveness(PokemonType.FIGHTING, PokemonType.FAIRY, 0.5);
        setEffectiveness(PokemonType.FIGHTING, PokemonType.GHOST, 0.0);
        
        // Poison type effectiveness
        setEffectiveness(PokemonType.POISON, PokemonType.GRASS, 2.0);
        setEffectiveness(PokemonType.POISON, PokemonType.FAIRY, 2.0);
        setEffectiveness(PokemonType.POISON, PokemonType.POISON, 0.5);
        setEffectiveness(PokemonType.POISON, PokemonType.GROUND, 0.5);
        setEffectiveness(PokemonType.POISON, PokemonType.ROCK, 0.5);
        setEffectiveness(PokemonType.POISON, PokemonType.GHOST, 0.5);
        setEffectiveness(PokemonType.POISON, PokemonType.STEEL, 0.0);
        
        // Ground type effectiveness
        setEffectiveness(PokemonType.GROUND, PokemonType.FIRE, 2.0);
        setEffectiveness(PokemonType.GROUND, PokemonType.ELECTRIC, 2.0);
        setEffectiveness(PokemonType.GROUND, PokemonType.POISON, 2.0);
        setEffectiveness(PokemonType.GROUND, PokemonType.ROCK, 2.0);
        setEffectiveness(PokemonType.GROUND, PokemonType.STEEL, 2.0);
        setEffectiveness(PokemonType.GROUND, PokemonType.GRASS, 0.5);
        setEffectiveness(PokemonType.GROUND, PokemonType.POISON, 0.5);
        setEffectiveness(PokemonType.GROUND, PokemonType.FLYING, 0.0);
        
        // Flying type effectiveness
        setEffectiveness(PokemonType.FLYING, PokemonType.GRASS, 2.0);
        setEffectiveness(PokemonType.FLYING, PokemonType.FIGHTING, 2.0);
        setEffectiveness(PokemonType.FLYING, PokemonType.BUG, 2.0);
        setEffectiveness(PokemonType.FLYING, PokemonType.ELECTRIC, 0.5);
        setEffectiveness(PokemonType.FLYING, PokemonType.ROCK, 0.5);
        setEffectiveness(PokemonType.FLYING, PokemonType.STEEL, 0.5);
        
        // Psychic type effectiveness
        setEffectiveness(PokemonType.PSYCHIC, PokemonType.FIGHTING, 2.0);
        setEffectiveness(PokemonType.PSYCHIC, PokemonType.POISON, 2.0);
        setEffectiveness(PokemonType.PSYCHIC, PokemonType.PSYCHIC, 0.5);
        setEffectiveness(PokemonType.PSYCHIC, PokemonType.STEEL, 0.5);
        setEffectiveness(PokemonType.PSYCHIC, PokemonType.DARK, 0.0);
        
        // Bug type effectiveness
        setEffectiveness(PokemonType.BUG, PokemonType.GRASS, 2.0);
        setEffectiveness(PokemonType.BUG, PokemonType.PSYCHIC, 2.0);
        setEffectiveness(PokemonType.BUG, PokemonType.DARK, 2.0);
        setEffectiveness(PokemonType.BUG, PokemonType.FIRE, 0.5);
        setEffectiveness(PokemonType.BUG, PokemonType.FIGHTING, 0.5);
        setEffectiveness(PokemonType.BUG, PokemonType.POISON, 0.5);
        setEffectiveness(PokemonType.BUG, PokemonType.FLYING, 0.5);
        setEffectiveness(PokemonType.BUG, PokemonType.GHOST, 0.5);
        setEffectiveness(PokemonType.BUG, PokemonType.STEEL, 0.5);
        setEffectiveness(PokemonType.BUG, PokemonType.FAIRY, 0.5);
        
        // Rock type effectiveness
        setEffectiveness(PokemonType.ROCK, PokemonType.FIRE, 2.0);
        setEffectiveness(PokemonType.ROCK, PokemonType.ICE, 2.0);
        setEffectiveness(PokemonType.ROCK, PokemonType.FLYING, 2.0);
        setEffectiveness(PokemonType.ROCK, PokemonType.BUG, 2.0);
        setEffectiveness(PokemonType.ROCK, PokemonType.FIGHTING, 0.5);
        setEffectiveness(PokemonType.ROCK, PokemonType.GROUND, 0.5);
        setEffectiveness(PokemonType.ROCK, PokemonType.STEEL, 0.5);
        
        // Ghost type effectiveness
        setEffectiveness(PokemonType.GHOST, PokemonType.PSYCHIC, 2.0);
        setEffectiveness(PokemonType.GHOST, PokemonType.GHOST, 2.0);
        setEffectiveness(PokemonType.GHOST, PokemonType.DARK, 0.5);
        setEffectiveness(PokemonType.GHOST, PokemonType.NORMAL, 0.0);
        
        // Dragon type effectiveness
        setEffectiveness(PokemonType.DRAGON, PokemonType.DRAGON, 2.0);
        setEffectiveness(PokemonType.DRAGON, PokemonType.STEEL, 0.5);
        setEffectiveness(PokemonType.DRAGON, PokemonType.FAIRY, 0.0);
        
        // Dark type effectiveness
        setEffectiveness(PokemonType.DARK, PokemonType.PSYCHIC, 2.0);
        setEffectiveness(PokemonType.DARK, PokemonType.GHOST, 2.0);
        setEffectiveness(PokemonType.DARK, PokemonType.FIGHTING, 0.5);
        setEffectiveness(PokemonType.DARK, PokemonType.DARK, 0.5);
        setEffectiveness(PokemonType.DARK, PokemonType.FAIRY, 0.5);
        
        // Steel type effectiveness
        setEffectiveness(PokemonType.STEEL, PokemonType.ICE, 2.0);
        setEffectiveness(PokemonType.STEEL, PokemonType.ROCK, 2.0);
        setEffectiveness(PokemonType.STEEL, PokemonType.FAIRY, 2.0);
        setEffectiveness(PokemonType.STEEL, PokemonType.FIRE, 0.5);
        setEffectiveness(PokemonType.STEEL, PokemonType.WATER, 0.5);
        setEffectiveness(PokemonType.STEEL, PokemonType.ELECTRIC, 0.5);
        setEffectiveness(PokemonType.STEEL, PokemonType.STEEL, 0.5);
        
        // Fairy type effectiveness
        setEffectiveness(PokemonType.FAIRY, PokemonType.FIGHTING, 2.0);
        setEffectiveness(PokemonType.FAIRY, PokemonType.DRAGON, 2.0);
        setEffectiveness(PokemonType.FAIRY, PokemonType.DARK, 2.0);
        setEffectiveness(PokemonType.FAIRY, PokemonType.POISON, 0.5);
        setEffectiveness(PokemonType.FAIRY, PokemonType.STEEL, 0.5);
        setEffectiveness(PokemonType.FAIRY, PokemonType.FIRE, 0.5);
        
        // Legendary type (super effective against most types)
        for (PokemonType defender : PokemonType.values()) {
            if (defender != PokemonType.LEGENDARY) {
                setEffectiveness(PokemonType.LEGENDARY, defender, 1.5);
            }
        }
        setEffectiveness(PokemonType.LEGENDARY, PokemonType.LEGENDARY, 1.0);
    }
    
    private static void setEffectiveness(PokemonType attacker, PokemonType defender, double multiplier) {
        effectiveness.get(attacker).put(defender, multiplier);
    }
    
    public static double getEffectiveness(PokemonType attacker, PokemonType defender) {
        return effectiveness.get(attacker).get(defender);
    }
} 