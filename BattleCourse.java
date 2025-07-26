import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BattleCourse {
    private String name;
    private String rankRange;
    private List<Pokemon> enemyPool;

    public BattleCourse(String name, String rankRange) {
        this.name = name;
        this.rankRange = rankRange;
        this.enemyPool = new ArrayList<>();
    }

    public String getName() { return name; }
    public String getRankRange() { return rankRange; }

    public void addEnemyToPool(String pokemonName) {
        Optional<Pokemon> pokemon = PokemonDB.getPokemon(pokemonName);
        pokemon.ifPresent(p -> this.enemyPool.add(p));
    }

    /**
     * Generates a random team of two distinct enemies from the course's pool.
     * @return A list containing two enemy Pokémon.
     */
    public List<Pokemon> generateEnemyTeam() {
        Collections.shuffle(enemyPool);
        List<Pokemon> team = new ArrayList<>();
        if(enemyPool.size() >= 2) {
            team.add(new Pokemon(enemyPool.get(0).getName(), enemyPool.get(0).getType(), enemyPool.get(0).getRank(), enemyPool.get(0).getMaxHp(), enemyPool.get(0).getAttack(), enemyPool.get(0).getDefense(), enemyPool.get(0).getSkill()));
            team.add(new Pokemon(enemyPool.get(1).getName(), enemyPool.get(1).getType(), enemyPool.get(1).getRank(), enemyPool.get(1).getMaxHp(), enemyPool.get(1).getAttack(), enemyPool.get(1).getDefense(), enemyPool.get(1).getSkill()));
        }
        return team;
    }
}