package group9.src;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PokemonDB {
    private static final Map<String, Pokemon> pokemonMasterList = new HashMap<>();

    // Static block to initialize the database when the class is loaded
    static {
        // Rank ★1-★2 Pokémon (Beginner Park)
        add("Pidgey", new Pokemon("Pidgey", Type.NORMAL, 1, 80, 25, 20, Skill.NONE));
        add("Rattata", new Pokemon("Rattata", Type.NORMAL, 1, 70, 30, 15, Skill.NONE));
        add("Caterpie", new Pokemon("Caterpie", Type.GRASS, 2, 90, 20, 25, Skill.DEFENSE_UP));

        // Rank ★3-★4 Pokémon (Challenge Dome)
        add("Pikachu", new Pokemon("Pikachu", Type.ELECTRIC, 3, 150, 60, 40, Skill.CRITICAL_BOOST));
        add("Bulbasaur", new Pokemon("Bulbasaur", Type.GRASS, 3, 160, 55, 55, Skill.SUPER_EFFECTIVE_UP));
        add("Squirtle", new Pokemon("Squirtle", Type.WATER, 4, 180, 65, 70, Skill.DEFENSE_UP));
        add("Charmander", new Pokemon("Charmander", Type.FIRE, 4, 170, 75, 50, Skill.CRITICAL_BOOST));

        // Rank ★5 Pokémon (Legendary Clash)
        add("Garchomp", new Pokemon("Garchomp", Type.DRAGON, 5, 250, 130, 95, Skill.CRITICAL_BOOST));
        add("Lucario", new Pokemon("Lucario", Type.FIGHTING, 5, 220, 110, 70, Skill.SUPER_EFFECTIVE_UP));
        add("Mewtwo", new Pokemon("Mewtwo", Type.PSYCHIC, 5, 300, 110, 90, Skill.CRITICAL_BOOST));
    }

    private static void add(String name, Pokemon pokemon) {
        pokemonMasterList.put(name.toLowerCase(), pokemon);
    }

    /**
     * Finds a Pokémon by name (case-insensitive) and returns a new copy.
     * @param name The name of the Pokémon to find.
     * @return An Optional containing a new Pokemon instance if found, otherwise empty.
     */
    public static Optional<Pokemon> getPokemon(String name) {
        Pokemon found = pokemonMasterList.get(name.toLowerCase());
        if (found != null) {
            // Return a new instance to prevent modifying the "master" copy
            return Optional.of(new Pokemon(found.getName(), found.getType(), found.getRank(), found.getMaxHp(), found.getAttack(), found.getDefense(), found.getSkill()));
        }
        return Optional.empty();
    }
}