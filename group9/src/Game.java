package group9.src;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private Trainer currentTrainer; // The trainer currently playing the game.
    private Scanner scanner;
    private Random random = new Random();
    private List<BattleCourse> courses = new ArrayList<>();

    public Game() {
        this.scanner = new Scanner(System.in);
        initializeCourses();
    }

    private void initializeCourses() {
        // ... (This method is unchanged)
        BattleCourse beginner = new BattleCourse("Beginner Park", "★1–★2");
        beginner.addEnemyToPool("Pidgey");
        beginner.addEnemyToPool("Rattata");
        beginner.addEnemyToPool("Caterpie");
        courses.add(beginner);

        BattleCourse challenge = new BattleCourse("Challenge Dome", "★3–★4");
        challenge.addEnemyToPool("Pikachu");
        challenge.addEnemyToPool("Bulbasaur");
        challenge.addEnemyToPool("Squirtle");
        challenge.addEnemyToPool("Charmander");
        courses.add(challenge);
        
        BattleCourse legendary = new BattleCourse("Legendary Clash", "★5");
        legendary.addEnemyToPool("Garchomp");
        legendary.addEnemyToPool("Lucario");
        legendary.addEnemyToPool("Mewtwo");
        courses.add(legendary);
    }
    
    /**
     * The main entry point for running the game.
     */
    public void run() {
        System.out.println("=== Pokémon Ga-Olé Console Edition ===");
        loginOrCreateTrainer(); // New login system

        if (currentTrainer == null) {
            System.out.println("Could not start game. Exiting.");
            return;
        }

        boolean isPlaying = true;
        while (isPlaying) {
            showMainMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1": startGaOleBattle(); break;
                case "2": scanNewDisk(); break;
                case "3": viewDiskCollection(); break;
                case "4": showTrainerInfo(); break;
                case "5":
                    GameDataManager.saveTrainer(currentTrainer); // Save on exit
                    System.out.println("\nThanks for playing, " + currentTrainer.getName() + "!");
                    isPlaying = false;
                    break;
                default: System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Handles the player login or new player registration process.
     */
    private void loginOrCreateTrainer() {
        System.out.print("\nEnter your trainer name: ");
        String name = scanner.nextLine();

        Optional<Trainer> trainerData = GameDataManager.getTrainer(name);

        if (trainerData.isPresent()) {
            // Trainer exists, load their data
            this.currentTrainer = trainerData.get();
            System.out.printf("\nWelcome back, %s!\n", currentTrainer.getName());
        } else {
            // This is a new trainer
            System.out.printf("\nWelcome, new trainer %s! Here is your starter disk.\n", name);
            this.currentTrainer = new Trainer(name);
            // Give every new trainer a starter Pokémon
            PokemonDB.getPokemon("Pikachu").ifPresent(p -> this.currentTrainer.addDisk(p));
            GameDataManager.saveTrainer(currentTrainer); // Initial save for the new trainer
        }
    }

    private void showMainMenu() {
        System.out.printf("\n--- Main Menu (Trainer: %s) ---\n", currentTrainer.getName());
        System.out.println("1. Start Ga-Olé Battle");
        System.out.println("2. Scan New Disk");
        System.out.println("3. View Disk Collection");
        System.out.println("4. Trainer Info");
        System.out.println("5. Exit Game");
        System.out.print("> ");
    }
    
    // All other methods (startGaOleBattle, viewDiskCollection, etc.) remain unchanged.
    // They will now automatically use the `this.currentTrainer` object that was
    // loaded or created during the login phase.

    // ... (startGaOleBattle, selectBattleCourse, selectPlayerTeam, handleCapture,
    //      scanNewDisk, viewDiskCollection, showTrainerInfo, getUserChoice methods
    //      are identical to the previous version and are omitted here for brevity)
    private void startGaOleBattle() {
        BattleCourse selectedCourse = selectBattleCourse();
        if (selectedCourse == null) return;
        List<Pokemon> enemyTeam = selectedCourse.generateEnemyTeam();
        
        List<Pokemon> playerTeam = selectPlayerTeam();
        if (playerTeam.isEmpty()) return;

        currentTrainer.healAllPokemon(); 
        Battle battle = new Battle(currentTrainer, playerTeam, enemyTeam);
        boolean playerWon = battle.start();
        
        if (playerWon) {
            handleCapture(enemyTeam, battle.getTotalPlayerDamage());
        }
    }
    private BattleCourse selectBattleCourse() {
        System.out.println("\nChoose Battle Course:");
        for (int i = 0; i < courses.size(); i++) {
            System.out.printf("%d. %-20s | Ranks: %s\n", i + 1, courses.get(i).getName(), courses.get(i).getRankRange());
        }
        int choice = getUserChoice(1, courses.size()) - 1;
        return courses.get(choice);
    }
    private List<Pokemon> selectPlayerTeam() {
        List<Pokemon> team = new ArrayList<>();
        System.out.println("\nSelect two Pokémon disks to use:");
        viewDiskCollection();

        if (currentTrainer.getDiskCollection().size() < 2) {
            System.out.println("You don't have enough disks to form a team of two!");
            return team;
        }

        System.out.println("Enter the number of your first Pokémon:");
        int choice1 = getUserChoice(1, currentTrainer.getDiskCollection().size()) - 1;

        System.out.println("Enter the number of your second Pokémon:");
        int choice2;
        while(true) {
            choice2 = getUserChoice(1, currentTrainer.getDiskCollection().size()) - 1;
            if (choice1 != choice2) break;
            System.out.println("You cannot select the same disk twice.");
        }
        
        team.add(currentTrainer.getDiskCollection().get(choice1));
        team.add(currentTrainer.getDiskCollection().get(choice2));
        System.out.printf("Team selected: %s and %s!\n", team.get(0).getName(), team.get(1).getName());
        return team;
    }
    private void handleCapture(List<Pokemon> defeatedTeam, int damageDealt) {
        System.out.println("\n--- Get Time! ---");
        int baseCatchRate = 20; 
        int performanceBonus = damageDealt / 20;
        int finalCatchRate = Math.min(95, baseCatchRate + performanceBonus);
        Pokemon targetToCatch = defeatedTeam.get(random.nextInt(defeatedTeam.size()));
        System.out.printf("Attempting to catch %s...\n", targetToCatch.getName());
        System.out.printf("Capture Rate: %d%% – Rolling...\n", finalCatchRate);
        System.out.println("🌀🌀🌀🌀🌀");
        if (random.nextInt(100) < finalCatchRate) {
            System.out.printf("You caught %s (%s)! Disk added to collection.\n", targetToCatch.getName(), Trainer.getRankStars(targetToCatch.getRank()));
            currentTrainer.addDisk(targetToCatch);
        } else {
            System.out.println("Oh no! It got away!");
        }
    }
    private void scanNewDisk() {
        System.out.print("Scan Pokémon (e.g. \"Pikachu\", \"Lucario\"): ");
        String name = scanner.nextLine();
        Optional<Pokemon> pokemon = PokemonDB.getPokemon(name);
        pokemon.ifPresentOrElse(p -> currentTrainer.addDisk(p), () -> System.out.println("No Pokémon data found for that name."));
    }
    private void viewDiskCollection() {
        System.out.println("\n--- Your Disk Collection ---");
        List<Pokemon> collection = currentTrainer.getDiskCollection();
        if (collection.isEmpty()) System.out.println("You have no disks.");
        else for (int i = 0; i < collection.size(); i++) System.out.println((i + 1) + ". " + collection.get(i).toString());
    }
    private void showTrainerInfo() {
        System.out.println("\n--- Trainer Info ---");
        System.out.println("Name: " + currentTrainer.getName());
        System.out.printf("Record: %d Wins / %d Losses\n", currentTrainer.getWins(), currentTrainer.getLosses());
        System.out.println("Total Disks Collected: " + currentTrainer.getDiskCollection().size());
        Optional<Pokemon> rarest = currentTrainer.getRarestPokemon();
        rarest.ifPresentOrElse(p -> System.out.printf("Rarest Pokémon: %s (%s)\n", p.getName(), Trainer.getRankStars(p.getRank())),() -> System.out.println("Rarest Pokémon: None"));
    }
    private int getUserChoice(int min, int max) {
        int choice;
        while (true) {
            try {
                System.out.print("> ");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= min && choice <= max) return choice;
            } catch (NumberFormatException e) { }
            System.out.printf("Invalid input. Please enter a number between %d and %d.\n", min, max);
        }
    }
}