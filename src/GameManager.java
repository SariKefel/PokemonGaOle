import java.util.List;

public class GameManager {
    private Player player;
    private boolean gameRunning;
    
    public GameManager() {
        this.gameRunning = true;
    }
    
    public void start() {
        Utils.printHeader("POKÉMON GA-OLÉ");
        System.out.println("Welcome to the Pokémon Ga-Olé console game!");
        System.out.println("Experience the thrill of collecting Ga-Olé Disks and battling wild Pokémon!");
        Utils.pause();
        
        // Login phase
        login();
        
        // Main game loop
        while (gameRunning) {
            showMainMenu();
        }
        
        System.out.println("Thank you for playing Pokémon Ga-Olé!");
    }
    
    private void login() {
        Utils.clearConsole();
        Utils.printHeader("LOGIN");
        
        String username = Utils.getStringInput("Enter your username: ");
        if (username.trim().isEmpty()) {
            username = "Trainer";
        }
        
        player = Player.loadFromFile(username);
        Utils.pause();
    }
    
    private void showMainMenu() {
        Utils.clearConsole();
        Utils.printHeader("MAIN MENU");
        System.out.println("Welcome, " + player.getUsername() + "!");
        System.out.println("Disks: " + player.getGaOleDisks().size() + " | Medals: " + player.getMedals());
        System.out.println();
        
        System.out.println("[1] Start Battle");
        System.out.println("[2] View My Disks");
        System.out.println("[3] Catch Mode");
        System.out.println("[4] Heal All Pokémon");
        System.out.println("[5] Save Game");
        System.out.println("[6] Exit");
        
        int choice = Utils.getIntInput(1, 6);
        
        switch (choice) {
            case 1:
                startBattleMode();
                break;
            case 2:
                viewDisks();
                break;
            case 3:
                startCatchMode();
                break;
            case 4:
                healAllPokemon();
                break;
            case 5:
                saveGame();
                break;
            case 6:
                exitGame();
                break;
        }
    }
    
    private void startBattleMode() {
        Utils.clearConsole();
        Utils.printHeader("BATTLE MODE");
        
        // Check if player has enough Pokémon
        if (player.getGaOleDisks().size() < 2) {
            System.out.println("You need at least 2 Pokémon to start a battle!");
            System.out.println("Try Catch Mode first to get more Pokémon.");
            Utils.pause();
            return;
        }
        
        // Select stage
        Stage stage = selectStage();
        if (stage == null) return;
        
        // Generate wild Pokémon
        List<Pokemon> wildPokemons = stage.getRandomWilds(2);
        
        // Validate that wild Pokémon were generated
        if (wildPokemons.isEmpty()) {
            System.out.println("Error: No wild Pokémon appeared in this stage!");
            System.out.println("Please try a different stage.");
            Utils.pause();
            return;
        }
        
        System.out.println("Wild Pokémon appeared: " + getPokemonNames(wildPokemons));
        Utils.pause();
        
        // Select player team
        List<Pokemon> playerTeam = player.selectBattleTeam(2);
        if (playerTeam.size() < 2) {
            System.out.println("You need to select 2 Pokémon for battle!");
            Utils.pause();
            return;
        }
        
        // Start battle
        Battle battle = new Battle(playerTeam, wildPokemons);
        battle.start();
        
        // Check battle result and handle catch phase
        boolean playerWon = battle.allWildPokemonFainted();
        boolean playerLost = battle.allPlayerPokemonFainted();
        
        if (playerWon) {
            System.out.println("🏆 Victory! You earned a medal!");
            player.addMedal();
            
            // Post-battle catch phase (only if player won)
            battle.resolveCatchPhase(player, wildPokemons);
        } else if (playerLost) {
            System.out.println("💔 Defeat! Better luck next time!");
        } else {
            System.out.println("🤝 Battle ended in a draw!");
        }
        
        Utils.pause();
    }
    
    private void startCatchMode() {
        Utils.clearConsole();
        Utils.printHeader("CATCH MODE");
        
        System.out.println("Three wild Pokémon have appeared!");
        
        // Generate 3 random wild Pokémon
        Stage randomStage = new Stage("forest"); // Default stage for catch mode
        List<Pokemon> wildPokemons = randomStage.getRandomWilds(3);
        
        // Display available Pokémon
        for (int i = 0; i < wildPokemons.size(); i++) {
            Pokemon pokemon = wildPokemons.get(i);
            System.out.println((i + 1) + ". " + pokemon.getDisplayName() + " (" + pokemon.getType() + 
                             ") - HP: " + pokemon.getMaxHP() + " - Attack: " + pokemon.getAttack());
        }
        
        System.out.println("0. Cancel");
        
        int choice = Utils.getIntInput(0, wildPokemons.size());
        if (choice == 0) {
            System.out.println("Catch mode cancelled.");
            Utils.pause();
            return;
        }
        
        Pokemon target = wildPokemons.get(choice - 1);
        System.out.println("You chose to catch " + target.getDisplayName() + "!");
        
        // Roll for Pokéball with animation (same as battle mode)
        System.out.println("\n🎲 Rolling for Pokéball...");
        Utils.sleep(500);
        
        // Animated rolling effect
        String[] balls = {"⚪", "🔴", "🔵", "🟣"};
        for (int i = 0; i < 10; i++) {
            System.out.print("\rRolling: " + balls[i % balls.length]);
            Utils.sleep(200);
        }
        System.out.println();
        
        // Get random Pokéball with improved distribution
        Pokeball ball = CatchMechanism.getRandomPokeball();
        System.out.println("🎯 You got a " + ball.getName() + "!");
        Utils.sleep(1000);
        
        boolean caught = CatchMechanism.attemptCatch(target, ball);
        if (caught) {
            GaOleDisk newDisk = new GaOleDisk(target, false);
            player.addDisk(newDisk);
            System.out.println(target.getDisplayName() + " was added to your Ga-Olé Disk collection!");
        }
        
        Utils.pause();
    }
    
    private void healAllPokemon() {
        Utils.clearConsole();
        Utils.printHeader("HEAL ALL POKÉMON");
        
        int healedCount = 0;
        for (GaOleDisk disk : player.getGaOleDisks()) {
            Pokemon pokemon = disk.getPokemon();
            if (pokemon.getCurrentHP() < pokemon.getMaxHP()) {
                pokemon.resetHP();
                healedCount++;
            }
        }
        
        if (healedCount > 0) {
            System.out.println("✨ Healed " + healedCount + " Pokémon to full health!");
        } else {
            System.out.println("All your Pokémon are already at full health!");
        }
        
        Utils.pause();
    }
    
    private Stage selectStage() {
        System.out.println("Select a stage:");
        System.out.println("[1] Forest (Bug, Flying, Normal)");
        System.out.println("[2] Volcano (Fire, Rock)");
        System.out.println("[3] Ocean (Water)");
        System.out.println("[4] Cave (Poison, Rock, Fighting)");
        System.out.println("[5] Legendary (Very Rare!)");
        System.out.println("[0] Cancel");
        
        int choice = Utils.getIntInput(0, 5);
        
        switch (choice) {
            case 1: return new Stage("forest");
            case 2: return new Stage("volcano");
            case 3: return new Stage("ocean");
            case 4: return new Stage("cave");
            case 5: return new Stage("legendary");
            default: return null;
        }
    }
    
    private void viewDisks() {
        Utils.clearConsole();
        player.displayDisks();
        Utils.pause();
    }
    
    private void saveGame() {
        player.saveToFile();
        Utils.pause();
    }
    
    private void exitGame() {
        System.out.println("Do you want to save before exiting? (y/n)");
        String response = Utils.getStringInput("").toLowerCase();
        
        if (response.startsWith("y")) {
            player.saveToFile();
        }
        
        gameRunning = false;
    }
    
    private String getPokemonNames(List<Pokemon> pokemons) {
        StringBuilder names = new StringBuilder();
        for (int i = 0; i < pokemons.size(); i++) {
            if (i > 0) names.append(", ");
            names.append(pokemons.get(i).getDisplayName());
        }
        return names.toString();
    }
} 