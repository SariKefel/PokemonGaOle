import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Stage {
    private String name;
    private List<Pokemon> wildPokemonPool;
    private List<Pokemon> rarePokemonPool;
    
    public Stage(String name) {
        this.name = name;
        this.wildPokemonPool = new ArrayList<>();
        this.rarePokemonPool = new ArrayList<>();
        initializePokemonPools();
    }
    
    private void initializePokemonPools() {
        switch (name.toLowerCase()) {
            case "forest":
                // Common Pokémon in forest
                wildPokemonPool.add(new Pokemon("Caterpie", PokemonType.BUG, 45, 30, 
                    List.of(new Move("Tackle", 40, PokemonType.NORMAL), new Move("String Shot", 0, PokemonType.BUG)), true));
                wildPokemonPool.add(new Pokemon("Weedle", PokemonType.BUG, 40, 35, 
                    List.of(new Move("Poison Sting", 15, PokemonType.POISON), new Move("Bug Bite", 30, PokemonType.BUG)), true));
                wildPokemonPool.add(new Pokemon("Pidgey", PokemonType.FLYING, 40, 45, 
                    List.of(new Move("Gust", 40, PokemonType.FLYING), new Move("Quick Attack", 40, PokemonType.NORMAL)), true));
                wildPokemonPool.add(new Pokemon("Rattata", PokemonType.NORMAL, 30, 56, 
                    List.of(new Move("Quick Attack", 40, PokemonType.NORMAL), new Move("Bite", 60, PokemonType.DARK)), true));
                wildPokemonPool.add(new Pokemon("Spearow", PokemonType.FLYING, 40, 60, 
                    List.of(new Move("Peck", 35, PokemonType.FLYING), new Move("Fury Attack", 15, PokemonType.NORMAL)), true));
                wildPokemonPool.add(new Pokemon("Ekans", PokemonType.POISON, 35, 60, 
                    List.of(new Move("Poison Sting", 15, PokemonType.POISON), new Move("Bite", 60, PokemonType.DARK)), true));
                wildPokemonPool.add(new Pokemon("Oddish", PokemonType.GRASS, 45, 50, 
                    List.of(new Move("Absorb", 20, PokemonType.GRASS), new Move("Acid", 40, PokemonType.POISON)), true));
                wildPokemonPool.add(new Pokemon("Bellsprout", PokemonType.GRASS, 50, 75, 
                    List.of(new Move("Vine Whip", 35, PokemonType.GRASS), new Move("Acid", 40, PokemonType.POISON)), true));
                
                // Rare Pokémon in forest
                rarePokemonPool.add(new Pokemon("Butterfree", PokemonType.BUG, 60, 45, 
                    List.of(new Move("Confusion", 50, PokemonType.PSYCHIC), new Move("Gust", 40, PokemonType.FLYING)), true));
                rarePokemonPool.add(new Pokemon("Pidgeotto", PokemonType.FLYING, 63, 60, 
                    List.of(new Move("Wing Attack", 60, PokemonType.FLYING), new Move("Quick Attack", 40, PokemonType.NORMAL)), true));
                rarePokemonPool.add(new Pokemon("Fearow", PokemonType.FLYING, 65, 90, 
                    List.of(new Move("Drill Peck", 80, PokemonType.FLYING), new Move("Fury Attack", 15, PokemonType.NORMAL)), true));
                rarePokemonPool.add(new Pokemon("Arbok", PokemonType.POISON, 60, 85, 
                    List.of(new Move("Poison Fang", 50, PokemonType.POISON), new Move("Crunch", 80, PokemonType.DARK)), true));
                rarePokemonPool.add(new Pokemon("Gloom", PokemonType.GRASS, 60, 65, 
                    List.of(new Move("Razor Leaf", 55, PokemonType.GRASS), new Move("Acid", 40, PokemonType.POISON)), true));
                break;
                
            case "volcano":
                // Common Pokémon in volcano
                wildPokemonPool.add(new Pokemon("Growlithe", PokemonType.FIRE, 55, 70, 
                    List.of(new Move("Ember", 40, PokemonType.FIRE), new Move("Bite", 60, PokemonType.DARK)), true));
                wildPokemonPool.add(new Pokemon("Ponyta", PokemonType.FIRE, 50, 85, 
                    List.of(new Move("Tackle", 40, PokemonType.NORMAL), new Move("Ember", 40, PokemonType.FIRE)), true));
                wildPokemonPool.add(new Pokemon("Geodude", PokemonType.ROCK, 40, 80, 
                    List.of(new Move("Rock Throw", 50, PokemonType.ROCK), new Move("Tackle", 40, PokemonType.NORMAL)), true));
                wildPokemonPool.add(new Pokemon("Sandshrew", PokemonType.GROUND, 50, 75, 
                    List.of(new Move("Scratch", 40, PokemonType.NORMAL), new Move("Sand Attack", 0, PokemonType.GROUND)), true));
                wildPokemonPool.add(new Pokemon("Vulpix", PokemonType.FIRE, 38, 41, 
                    List.of(new Move("Ember", 40, PokemonType.FIRE), new Move("Quick Attack", 40, PokemonType.NORMAL)), true));
                wildPokemonPool.add(new Pokemon("Diglett", PokemonType.GROUND, 10, 55, 
                    List.of(new Move("Scratch", 40, PokemonType.NORMAL), new Move("Sand Attack", 0, PokemonType.GROUND)), true));
                wildPokemonPool.add(new Pokemon("Machop", PokemonType.FIGHTING, 70, 80, 
                    List.of(new Move("Karate Chop", 50, PokemonType.FIGHTING), new Move("Low Kick", 50, PokemonType.FIGHTING)), true));
                
                // Rare Pokémon in volcano
                rarePokemonPool.add(new Pokemon("Arcanine", PokemonType.FIRE, 90, 110, 
                    List.of(new Move("Fire Fang", 65, PokemonType.FIRE), new Move("Crunch", 80, PokemonType.DARK)), true));
                rarePokemonPool.add(new Pokemon("Rapidash", PokemonType.FIRE, 65, 100, 
                    List.of(new Move("Flame Wheel", 60, PokemonType.FIRE), new Move("Stomp", 65, PokemonType.NORMAL)), true));
                rarePokemonPool.add(new Pokemon("Graveler", PokemonType.ROCK, 55, 95, 
                    List.of(new Move("Rock Throw", 50, PokemonType.ROCK), new Move("Magnitude", 30, PokemonType.GROUND)), true));
                rarePokemonPool.add(new Pokemon("Sandslash", PokemonType.GROUND, 75, 100, 
                    List.of(new Move("Slash", 70, PokemonType.NORMAL), new Move("Dig", 80, PokemonType.GROUND)), true));
                rarePokemonPool.add(new Pokemon("Ninetales", PokemonType.FIRE, 73, 76, 
                    List.of(new Move("Flamethrower", 90, PokemonType.FIRE), new Move("Quick Attack", 40, PokemonType.NORMAL)), true));
                rarePokemonPool.add(new Pokemon("Dugtrio", PokemonType.GROUND, 35, 80, 
                    List.of(new Move("Slash", 70, PokemonType.NORMAL), new Move("Dig", 80, PokemonType.GROUND)), true));
                rarePokemonPool.add(new Pokemon("Machoke", PokemonType.FIGHTING, 80, 100, 
                    List.of(new Move("Dynamic Punch", 100, PokemonType.FIGHTING), new Move("Low Kick", 50, PokemonType.FIGHTING)), true));
                break;
                
            case "ocean":
                // Common Pokémon in ocean
                wildPokemonPool.add(new Pokemon("Magikarp", PokemonType.WATER, 20, 10, 
                    List.of(new Move("Splash", 0, PokemonType.NORMAL), new Move("Tackle", 40, PokemonType.NORMAL)), true));
                wildPokemonPool.add(new Pokemon("Tentacool", PokemonType.WATER, 40, 40, 
                    List.of(new Move("Bubble", 40, PokemonType.WATER), new Move("Poison Sting", 15, PokemonType.POISON)), true));
                wildPokemonPool.add(new Pokemon("Staryu", PokemonType.WATER, 30, 45, 
                    List.of(new Move("Water Gun", 40, PokemonType.WATER), new Move("Rapid Spin", 20, PokemonType.NORMAL)), true));
                wildPokemonPool.add(new Pokemon("Shellder", PokemonType.WATER, 30, 65, 
                    List.of(new Move("Tackle", 40, PokemonType.NORMAL), new Move("Water Gun", 40, PokemonType.WATER)), true));
                wildPokemonPool.add(new Pokemon("Krabby", PokemonType.WATER, 30, 105, 
                    List.of(new Move("Bubble", 40, PokemonType.WATER), new Move("Vice Grip", 55, PokemonType.NORMAL)), true));
                wildPokemonPool.add(new Pokemon("Horsea", PokemonType.WATER, 30, 40, 
                    List.of(new Move("Bubble", 40, PokemonType.WATER), new Move("Smokescreen", 0, PokemonType.NORMAL)), true));
                wildPokemonPool.add(new Pokemon("Goldeen", PokemonType.WATER, 45, 67, 
                    List.of(new Move("Peck", 35, PokemonType.FLYING), new Move("Water Gun", 40, PokemonType.WATER)), true));
                
                // Rare Pokémon in ocean
                rarePokemonPool.add(new Pokemon("Gyarados", PokemonType.WATER, 95, 125, 
                    List.of(new Move("Hydro Pump", 110, PokemonType.WATER), new Move("Bite", 60, PokemonType.DARK)), true));
                rarePokemonPool.add(new Pokemon("Starmie", PokemonType.WATER, 60, 75, 
                    List.of(new Move("Psychic", 90, PokemonType.PSYCHIC), new Move("Water Gun", 40, PokemonType.WATER)), true));
                rarePokemonPool.add(new Pokemon("Tentacruel", PokemonType.WATER, 80, 70, 
                    List.of(new Move("Hydro Pump", 110, PokemonType.WATER), new Move("Poison Jab", 80, PokemonType.POISON)), true));
                rarePokemonPool.add(new Pokemon("Cloyster", PokemonType.WATER, 50, 95, 
                    List.of(new Move("Ice Beam", 90, PokemonType.ICE), new Move("Water Gun", 40, PokemonType.WATER)), true));
                rarePokemonPool.add(new Pokemon("Kingler", PokemonType.WATER, 55, 130, 
                    List.of(new Move("Crabhammer", 90, PokemonType.WATER), new Move("Vice Grip", 55, PokemonType.NORMAL)), true));
                rarePokemonPool.add(new Pokemon("Seadra", PokemonType.WATER, 55, 65, 
                    List.of(new Move("Hydro Pump", 110, PokemonType.WATER), new Move("Dragon Rage", 40, PokemonType.DRAGON)), true));
                rarePokemonPool.add(new Pokemon("Seaking", PokemonType.WATER, 80, 92, 
                    List.of(new Move("Horn Drill", 0, PokemonType.NORMAL), new Move("Water Gun", 40, PokemonType.WATER)), true));
                break;
                
            case "cave":
                // Common Pokémon in cave
                wildPokemonPool.add(new Pokemon("Zubat", PokemonType.POISON, 40, 45, 
                    List.of(new Move("Bite", 60, PokemonType.DARK), new Move("Wing Attack", 60, PokemonType.FLYING)), true));
                wildPokemonPool.add(new Pokemon("Geodude", PokemonType.ROCK, 40, 80, 
                    List.of(new Move("Rock Throw", 50, PokemonType.ROCK), new Move("Tackle", 40, PokemonType.NORMAL)), true));
                wildPokemonPool.add(new Pokemon("Machop", PokemonType.FIGHTING, 70, 80, 
                    List.of(new Move("Karate Chop", 50, PokemonType.FIGHTING), new Move("Low Kick", 50, PokemonType.FIGHTING)), true));
                wildPokemonPool.add(new Pokemon("Onix", PokemonType.ROCK, 35, 45, 
                    List.of(new Move("Rock Throw", 50, PokemonType.ROCK), new Move("Bind", 15, PokemonType.NORMAL)), true));
                wildPokemonPool.add(new Pokemon("Cubone", PokemonType.GROUND, 50, 50, 
                    List.of(new Move("Bone Club", 65, PokemonType.GROUND), new Move("Headbutt", 70, PokemonType.NORMAL)), true));
                wildPokemonPool.add(new Pokemon("Rhyhorn", PokemonType.GROUND, 80, 85, 
                    List.of(new Move("Horn Attack", 65, PokemonType.NORMAL), new Move("Stomp", 65, PokemonType.NORMAL)), true));
                wildPokemonPool.add(new Pokemon("Kangaskhan", PokemonType.NORMAL, 105, 95, 
                    List.of(new Move("Comet Punch", 18, PokemonType.NORMAL), new Move("Bite", 60, PokemonType.DARK)), true));
                
                // Rare Pokémon in cave
                rarePokemonPool.add(new Pokemon("Golbat", PokemonType.POISON, 75, 80, 
                    List.of(new Move("Wing Attack", 60, PokemonType.FLYING), new Move("Bite", 60, PokemonType.DARK)), true));
                rarePokemonPool.add(new Pokemon("Graveler", PokemonType.ROCK, 55, 95, 
                    List.of(new Move("Rock Throw", 50, PokemonType.ROCK), new Move("Magnitude", 30, PokemonType.GROUND)), true));
                rarePokemonPool.add(new Pokemon("Machoke", PokemonType.FIGHTING, 80, 100, 
                    List.of(new Move("Dynamic Punch", 100, PokemonType.FIGHTING), new Move("Low Kick", 50, PokemonType.FIGHTING)), true));
                rarePokemonPool.add(new Pokemon("Steelix", PokemonType.STEEL, 75, 85, 
                    List.of(new Move("Iron Tail", 100, PokemonType.STEEL), new Move("Rock Throw", 50, PokemonType.ROCK)), true));
                rarePokemonPool.add(new Pokemon("Marowak", PokemonType.GROUND, 60, 80, 
                    List.of(new Move("Bone Rush", 25, PokemonType.GROUND), new Move("Headbutt", 70, PokemonType.NORMAL)), true));
                rarePokemonPool.add(new Pokemon("Rhydon", PokemonType.GROUND, 105, 130, 
                    List.of(new Move("Horn Drill", 0, PokemonType.NORMAL), new Move("Stomp", 65, PokemonType.NORMAL)), true));
                break;
                
            case "legendary":
                // Legendary Pokémon
                rarePokemonPool.add(new Pokemon("Mewtwo", PokemonType.LEGENDARY, 106, 110, 
                    List.of(new Move("Psychic", 90, PokemonType.PSYCHIC), new Move("Swift", 60, PokemonType.NORMAL)), true));
                rarePokemonPool.add(new Pokemon("Articuno", PokemonType.LEGENDARY, 90, 85, 
                    List.of(new Move("Ice Beam", 90, PokemonType.ICE), new Move("Hurricane", 110, PokemonType.FLYING)), true));
                rarePokemonPool.add(new Pokemon("Zapdos", PokemonType.LEGENDARY, 90, 90, 
                    List.of(new Move("Thunder", 110, PokemonType.ELECTRIC), new Move("Drill Peck", 80, PokemonType.FLYING)), true));
                rarePokemonPool.add(new Pokemon("Moltres", PokemonType.LEGENDARY, 90, 100, 
                    List.of(new Move("Fire Blast", 110, PokemonType.FIRE), new Move("Air Slash", 75, PokemonType.FLYING)), true));
                rarePokemonPool.add(new Pokemon("Mew", PokemonType.LEGENDARY, 100, 100, 
                    List.of(new Move("Psychic", 90, PokemonType.PSYCHIC), new Move("Transform", 0, PokemonType.NORMAL)), true));
                rarePokemonPool.add(new Pokemon("Lugia", PokemonType.LEGENDARY, 106, 90, 
                    List.of(new Move("Aeroblast", 100, PokemonType.FLYING), new Move("Hydro Pump", 110, PokemonType.WATER)), true));
                rarePokemonPool.add(new Pokemon("Ho-Oh", PokemonType.LEGENDARY, 106, 110, 
                    List.of(new Move("Sacred Fire", 100, PokemonType.FIRE), new Move("Hurricane", 110, PokemonType.FLYING)), true));
                rarePokemonPool.add(new Pokemon("Celebi", PokemonType.LEGENDARY, 100, 100, 
                    List.of(new Move("Psychic", 90, PokemonType.PSYCHIC), new Move("Giga Drain", 75, PokemonType.GRASS)), true));
                break;
                
            default:
                // Default Pokémon for unknown stages
                wildPokemonPool.add(new Pokemon("Rattata", PokemonType.NORMAL, 30, 56, 
                    List.of(new Move("Tackle", 40, PokemonType.NORMAL), new Move("Bite", 60, PokemonType.DARK)), true));
                wildPokemonPool.add(new Pokemon("Pidgey", PokemonType.FLYING, 40, 45, 
                    List.of(new Move("Gust", 40, PokemonType.FLYING), new Move("Quick Attack", 40, PokemonType.NORMAL)), true));
        }
    }
    
    public List<Pokemon> getRandomWilds(int count) {
        List<Pokemon> selected = new ArrayList<>();
        Random random = new Random();
        
        for (int i = 0; i < count; i++) {
            Pokemon selectedPokemon = null;
            
            // Try to select a Pokémon based on probability
            if (random.nextDouble() < 0.2 && !rarePokemonPool.isEmpty()) {
                // 20% chance for rare Pokémon
                selectedPokemon = rarePokemonPool.get(random.nextInt(rarePokemonPool.size()));
            } else if (!wildPokemonPool.isEmpty()) {
                // 80% chance for common Pokémon
                selectedPokemon = wildPokemonPool.get(random.nextInt(wildPokemonPool.size()));
            } else if (!rarePokemonPool.isEmpty()) {
                // If no common Pokémon available, use rare Pokémon
                selectedPokemon = rarePokemonPool.get(random.nextInt(rarePokemonPool.size()));
            }
            
            // If we found a Pokémon, add it to the selection
            if (selectedPokemon != null) {
                selected.add(clonePokemon(selectedPokemon));
            }
        }
        
        // If we still don't have enough Pokémon, fill with random rare Pokémon
        while (selected.size() < count && !rarePokemonPool.isEmpty()) {
            Pokemon randomRare = rarePokemonPool.get(random.nextInt(rarePokemonPool.size()));
            selected.add(clonePokemon(randomRare));
        }
        
        // If we still don't have enough, add some default Pokémon
        while (selected.size() < count) {
            Pokemon defaultPokemon = new Pokemon("Rattata", PokemonType.NORMAL, 30, 56, 
                List.of(new Move("Tackle", 40, PokemonType.NORMAL), new Move("Bite", 60, PokemonType.DARK)), true);
            selected.add(defaultPokemon);
        }
        
        return selected;
    }
    
    private Pokemon clonePokemon(Pokemon original) {
        return new Pokemon(original.getName(), original.getType(), 
                          original.getMaxHP(), original.getAttack(), 
                          original.getMoves(), true, original.getLevel());
    }
    
    public String getName() {
        return name;
    }
} 