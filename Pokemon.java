public class Pokemon {
    private String name;
    private Type type;
    private int rank; // Rank from 1 to 5 (★)
    private int hp;
    private int maxHp;
    private int attack;
    private int defense;
    private Skill specialSkill;

    public Pokemon(String name, Type type, int rank, int maxHp, int attack, int defense, Skill skill) {
        this.name = name;
        this.type = type;
        this.rank = rank;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.attack = attack;
        this.defense = defense;
        this.specialSkill = skill;
    }

    // Getters
    public String getName() { return name; }
    public Type getType() { return type; }
    public int getRank() { return rank; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public Skill getSkill() { return specialSkill; }

    public boolean isFainted() {
        return hp <= 0;
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
        }
    }
    
    public void heal() {
        this.hp = this.maxHp;
    }

    @Override
    public String toString() {
        StringBuilder rankStars = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            rankStars.append(i < rank ? "★" : "☆");
        }
        return String.format("%-12s | %s | Type: %-8s | HP: %-3d/%-3d | Atk: %-3d | Def: %-3d",
                name, rankStars.toString(), type, hp, maxHp, attack, defense);
    }
}