import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Pokemon implements Serializable {
    private String name;
    private PokemonType type;
    private int maxHP;
    private int currentHP;
    private int attack;
    private List<Move> moves;
    private boolean isWild;
    private int level;
    
    public Pokemon(String name, PokemonType type, int maxHP, int attack, Move move, boolean isWild) {
        this.name = name;
        this.type = type;
        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.attack = attack;
        this.moves = new ArrayList<>();
        this.moves.add(move);
        this.isWild = isWild;
        this.level = generateRandomLevel();
    }
    
    public Pokemon(String name, PokemonType type, int maxHP, int attack, List<Move> moves, boolean isWild) {
        this.name = name;
        this.type = type;
        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.attack = attack;
        this.moves = new ArrayList<>(moves);
        this.isWild = isWild;
        this.level = generateRandomLevel();
    }
    
    public Pokemon(String name, PokemonType type, int maxHP, int attack, List<Move> moves, boolean isWild, int level) {
        this.name = name;
        this.type = type;
        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.attack = attack;
        this.moves = new ArrayList<>(moves);
        this.isWild = isWild;
        this.level = level;
    }
    
    private int generateRandomLevel() {
        Random random = new Random();
        // Generate level between 5 and 50 for wild Pokémon
        return random.nextInt(46) + 5;
    }
    
    // Getters
    public String getName() { return name; }
    public PokemonType getType() { return type; }
    public int getMaxHP() { return maxHP; }
    public int getCurrentHP() { return currentHP; }
    public int getAttack() { return attack; }
    public List<Move> getMoves() { return moves; }
    public void addMove(Move move) { moves.add(move); }
    public void setMoves(List<Move> moves) { this.moves = new ArrayList<>(moves); }
    public boolean isWild() { return isWild; }
    public int getLevel() { return level; }
    
    // Methods
    public void resetHP() {
        currentHP = maxHP;
    }
    
    public void takeDamage(int damage) {
        currentHP = Math.max(0, currentHP - damage);
    }
    
    public boolean isFainted() {
        return currentHP <= 0;
    }
    
    public double getHealthPercentage() {
        return (double) currentHP / maxHP;
    }
    
    public String getHealthBar() {
        int bars = 20;
        int filledBars = (int) (getHealthPercentage() * bars);
        StringBuilder healthBar = new StringBuilder("[");
        
        for (int i = 0; i < bars; i++) {
            if (i < filledBars) {
                healthBar.append("█");
            } else {
                healthBar.append("░");
            }
        }
        healthBar.append("]");
        
        return healthBar.toString();
    }
    
    public String getDisplayName() {
        return name + " Lv." + level;
    }
    
    @Override
    public String toString() {
        return getDisplayName() + " (" + type + ") - HP: " + currentHP + "/" + maxHP + " - Attack: " + attack;
    }
} 