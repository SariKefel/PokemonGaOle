import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {
    private String username;
    private List<GaOleDisk> gaOleDisks;
    private List<GaOleDisk> newlyObtainedDisks;
    private int medals;
    
    public Player(String username) {
        this.username = username;
        this.gaOleDisks = new ArrayList<>();
        this.newlyObtainedDisks = new ArrayList<>();
        this.medals = 0;
    }
    
    // Getters
    public String getUsername() { return username; }
    public List<GaOleDisk> getGaOleDisks() { return gaOleDisks; }
    public List<GaOleDisk> getNewlyObtainedDisks() { return newlyObtainedDisks; }
    public int getMedals() { return medals; }
    
    // Methods
    public void addDisk(GaOleDisk disk) {
        gaOleDisks.add(disk);
        newlyObtainedDisks.add(disk);
    }
    
    public void addMedal() {
        medals++;
    }
    
    public void clearNewlyObtainedDisks() {
        newlyObtainedDisks.clear();
    }
    
    public Pokemon selectPokemon() {
        if (gaOleDisks.isEmpty()) {
            return null;
        }
        
        System.out.println("\n=== Select Your Pokémon ===");
        for (int i = 0; i < gaOleDisks.size(); i++) {
            Pokemon pokemon = gaOleDisks.get(i).getPokemon();
            System.out.println((i + 1) + ". " + pokemon.getDisplayName() + " (" + pokemon.getType() + 
                             ") - HP: " + pokemon.getCurrentHP() + "/" + pokemon.getMaxHP());
        }
        
        int choice = Utils.getIntInput(1, gaOleDisks.size());
        Pokemon selected = gaOleDisks.get(choice - 1).getPokemon();
        
        if (selected.isFainted()) {
            System.out.println("This Pokémon has fainted! Please select another.");
            return selectPokemon();
        }
        
        return selected;
    }
    
    public List<Pokemon> selectBattleTeam(int count) {
        List<Pokemon> team = new ArrayList<>();
        List<Pokemon> availablePokemon = new ArrayList<>();
        
        // Create a list of available Pokémon (non-fainted)
        for (GaOleDisk disk : gaOleDisks) {
            Pokemon pokemon = disk.getPokemon();
            if (!pokemon.isFainted()) {
                availablePokemon.add(pokemon);
            }
        }
        
        if (availablePokemon.size() < count) {
            System.out.println("You don't have enough healthy Pokémon for a battle team!");
            return team;
        }
        
        System.out.println("\n=== Select Your Battle Team (" + count + " Pokémon) ===");
        
        for (int i = 0; i < count; i++) {
            System.out.println("\nSelect Pokémon " + (i + 1) + ":");
            
            // Display available Pokémon
            System.out.println("Available Pokémon:");
            for (int j = 0; j < availablePokemon.size(); j++) {
                Pokemon pokemon = availablePokemon.get(j);
                System.out.println((j + 1) + ". " + pokemon.getDisplayName() + " (" + pokemon.getType() + 
                                 ") - HP: " + pokemon.getCurrentHP() + "/" + pokemon.getMaxHP());
            }
            
            int choice = Utils.getIntInput(1, availablePokemon.size());
            Pokemon selected = availablePokemon.get(choice - 1);
            
            team.add(selected);
            availablePokemon.remove(choice - 1); // Remove selected Pokémon from available list
            System.out.println("Added " + selected.getName() + " to your team!");
        }
        
        return team;
    }
    
    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(username + ".ser"))) {
            oos.writeObject(this);
            System.out.println("Game saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving game: " + e.getMessage());
        }
    }
    
    public static Player loadFromFile(String username) {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(username + ".ser"))) {
            Player player = (Player) ois.readObject();
            System.out.println("Welcome back, " + player.getUsername() + "!");
            return player;
        } catch (FileNotFoundException e) {
            System.out.println("No saved game found for " + username + ". Creating new profile...");
            return createNewPlayer(username);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading game: " + e.getMessage());
            System.out.println("Creating new profile...");
            return createNewPlayer(username);
        }
    }
    
    private static Player createNewPlayer(String username) {
        Player player = new Player(username);
        
        // Give new player a rental Pikachu with fixed level
        Move thunderShock = new Move("Thunder Shock", 40, PokemonType.ELECTRIC);
        Move quickAttack = new Move("Quick Attack", 40, PokemonType.NORMAL);
        Pokemon pikachu = new Pokemon("Pikachu", PokemonType.ELECTRIC, 35, 55, List.of(thunderShock, quickAttack), false, 5);
        GaOleDisk pikachuDisk = new GaOleDisk(pikachu, true);
        player.addDisk(pikachuDisk);
        
        System.out.println("You received a rental Pikachu!");
        return player;
    }
    
    public void displayDisks() {
        System.out.println("\n=== Your Ga-Olé Disk Collection ===");
        if (gaOleDisks.isEmpty()) {
            System.out.println("You don't have any disks yet!");
            return;
        }
        
        for (int i = 0; i < gaOleDisks.size(); i++) {
            GaOleDisk disk = gaOleDisks.get(i);
            Pokemon pokemon = disk.getPokemon();
            String rentalStatus = disk.isRental() ? " (Rental)" : "";
            System.out.println((i + 1) + ". " + pokemon.getDisplayName() + " - " + 
                             pokemon.getType() + " - HP: " + pokemon.getCurrentHP() + 
                             "/" + pokemon.getMaxHP() + rentalStatus);
        }
        
        System.out.println("\nTotal Disks: " + gaOleDisks.size());
        System.out.println("Medals: " + medals);
    }
} 