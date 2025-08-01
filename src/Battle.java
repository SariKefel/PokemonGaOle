import java.util.List;
import java.util.ArrayList;

public class Battle {
    private List<Pokemon> playerTeam;
    private List<Pokemon> wildPokemons;
    
    public Battle(List<Pokemon> playerTeam, List<Pokemon> wildPokemons) {
        this.playerTeam = playerTeam;
        this.wildPokemons = wildPokemons;
    }
    
    public void start() {
        Utils.printHeader("BATTLE START!");
        System.out.println("Your team: " + getTeamNames(playerTeam));
        System.out.println("Wild Pokémon: " + getTeamNames(wildPokemons));
        Utils.pause();
        
        // Battle continues until all wild Pokémon or all player Pokémon are defeated
        while (!allWildPokemonFainted() && !allPlayerPokemonFainted()) {
            // Display current status
            displayBattleStatus();
            
            // Player's turn
            handlePlayerTurn();
            
            // Check if all wild Pokémon are defeated
            if (allWildPokemonFainted()) {
                System.out.println("All wild Pokémon have been defeated!");
                break;
            }
            
            Utils.sleep(1000);
            
            // Wild Pokémon's turn
            handleWildPokemonTurn();
            
            // Check if all player Pokémon are defeated
            if (allPlayerPokemonFainted()) {
                System.out.println("All your Pokémon have fainted! Battle lost!");
                break;
            }
            
            Utils.sleep(1000);
        }
        
        System.out.println("\n🎉 Battle completed!");
    }
    
    private void handlePlayerTurn() {
        System.out.println("\n=== YOUR TURN ===");
        
        // Get active player Pokémon
        Pokemon playerPokemon = getActivePlayerPokemon();
        if (playerPokemon == null) {
            System.out.println("No active Pokémon found!");
            return;
        }
        
        System.out.println("Active Pokémon: " + playerPokemon.getDisplayName());
        
        // Choose move
        Move chosenMove = chooseMove(playerPokemon);
        
        // Choose target
        Pokemon target = chooseTarget();
        if (target == null) {
            System.out.println("No valid target found!");
            return;
        }
        
        // Execute attack
        int damage = calculateDamage(playerPokemon, target, chosenMove);
        target.takeDamage(damage);
        
        System.out.println(playerPokemon.getDisplayName() + " used " + chosenMove.getName() + " on " + target.getDisplayName() + "!");
        System.out.println("It's " + getEffectivenessText(chosenMove.getType(), target.getType()) + "!");
        System.out.println(target.getDisplayName() + " took " + damage + " damage!");
        
        if (target.isFainted()) {
            System.out.println(target.getDisplayName() + " fainted!");
        }
    }
    
    private void handleWildPokemonTurn() {
        System.out.println("\n=== WILD POKÉMON TURN ===");
        
        // Each surviving wild Pokémon attacks
        for (Pokemon wild : wildPokemons) {
            if (wild.isFainted()) continue;
            
            Pokemon playerTarget = getActivePlayerPokemon();
            if (playerTarget == null) {
                System.out.println("No player Pokémon to attack!");
                return;
            }
            
            // Wild Pokémon uses first move
            Move wildMove = wild.getMoves().get(0);
            int damage = calculateDamage(wild, playerTarget, wildMove);
            playerTarget.takeDamage(damage);
            
            System.out.println(wild.getDisplayName() + " used " + wildMove.getName() + " on " + playerTarget.getDisplayName() + "!");
            System.out.println("It's " + getEffectivenessText(wildMove.getType(), playerTarget.getType()) + "!");
            System.out.println(playerTarget.getDisplayName() + " took " + damage + " damage!");
            
            if (playerTarget.isFainted()) {
                System.out.println(playerTarget.getDisplayName() + " fainted!");
                
                // Try to switch to another Pokémon
                Pokemon nextPokemon = getActivePlayerPokemon();
                if (nextPokemon != null) {
                    System.out.println("Go! " + nextPokemon.getDisplayName() + "!");
                } else {
                    System.out.println("All your Pokémon have fainted!");
                    break;
                }
            }
        }
    }
    
    private Pokemon chooseTarget() {
        List<Pokemon> aliveWildPokemon = new ArrayList<>();
        for (Pokemon wild : wildPokemons) {
            if (!wild.isFainted()) {
                aliveWildPokemon.add(wild);
            }
        }
        
        if (aliveWildPokemon.isEmpty()) {
            return null;
        }
        
        if (aliveWildPokemon.size() == 1) {
            return aliveWildPokemon.get(0);
        }
        
        System.out.println("Choose your target:");
        for (int i = 0; i < aliveWildPokemon.size(); i++) {
            Pokemon wild = aliveWildPokemon.get(i);
            System.out.println((i + 1) + ". " + wild.getDisplayName() + " (" + wild.getType() + 
                             ") - HP: " + wild.getCurrentHP() + "/" + wild.getMaxHP());
        }
        
        int choice = Utils.getIntInput(1, aliveWildPokemon.size());
        return aliveWildPokemon.get(choice - 1);
    }
    
    public boolean allWildPokemonFainted() {
        for (Pokemon wild : wildPokemons) {
            if (!wild.isFainted()) {
                return false;
            }
        }
        return true;
    }
    
    public boolean allPlayerPokemonFainted() {
        for (Pokemon pokemon : playerTeam) {
            if (!pokemon.isFainted()) {
                return false;
            }
        }
        return true;
    }
    
    private Pokemon getActivePlayerPokemon() {
        for (Pokemon pokemon : playerTeam) {
            if (!pokemon.isFainted()) {
                return pokemon;
            }
        }
        return null;
    }
    
    private void displayBattleStatus() {
        System.out.println("\n=== BATTLE STATUS ===");
        
        System.out.println("Your Pokémon:");
        for (Pokemon pokemon : playerTeam) {
            String status = pokemon.isFainted() ? "FAINTED" : "Active";
            System.out.println("  " + pokemon.getDisplayName() + " - HP: " + pokemon.getCurrentHP() + "/" + pokemon.getMaxHP() + " (" + status + ")");
            if (!pokemon.isFainted()) {
                System.out.println("    " + pokemon.getHealthBar());
            }
        }
        
        System.out.println("\nWild Pokémon:");
        for (Pokemon wild : wildPokemons) {
            String status = wild.isFainted() ? "FAINTED" : "Active";
            System.out.println("  " + wild.getDisplayName() + " - HP: " + wild.getCurrentHP() + "/" + wild.getMaxHP() + " (" + status + ")");
            if (!wild.isFainted()) {
                System.out.println("    " + wild.getHealthBar());
            }
        }
    }
    
    private Move chooseMove(Pokemon pokemon) {
        List<Move> moves = pokemon.getMoves();
        System.out.println("Choose a move:");
        for (int i = 0; i < moves.size(); i++) {
            Move move = moves.get(i);
            System.out.println((i + 1) + ". " + move.getName() + " (Type: " + move.getType() + ", Power: " + move.getPower() + ")");
        }
        int choice = Utils.getIntInput(1, moves.size());
        return moves.get(choice - 1);
    }

    public int calculateDamage(Pokemon attacker, Pokemon defender, Move move) {
        double effectiveness = TypeChart.getEffectiveness(move.getType(), defender.getType());
        int damage = (int) (attacker.getAttack() * move.getPower() * effectiveness / 100);
        return Math.max(1, damage);
    }
    
    private String getEffectivenessText(PokemonType attackerType, PokemonType defenderType) {
        double effectiveness = TypeChart.getEffectiveness(attackerType, defenderType);
        
        if (effectiveness >= 2.0) {
            return "super effective!";
        } else if (effectiveness >= 1.5) {
            return "very effective!";
        } else if (effectiveness <= 0.5) {
            return "not very effective...";
        } else if (effectiveness == 0.0) {
            return "completely ineffective!";
        } else {
            return "effective";
        }
    }
    
    private String getTeamNames(List<Pokemon> team) {
        StringBuilder names = new StringBuilder();
        for (int i = 0; i < team.size(); i++) {
            if (i > 0) names.append(", ");
            names.append(team.get(i).getName());
        }
        return names.toString();
    }
    
    public void resolveCatchPhase(Player player, List<Pokemon> catchable) {
        // Only allow catching if player won (all wild Pokémon fainted)
        if (!allWildPokemonFainted()) {
            System.out.println("You lost the battle! No catching allowed.");
            return;
        }
        
        if (catchable.isEmpty()) {
            System.out.println("No Pokémon available for catching.");
            return;
        }
        
        System.out.println("\n=== CATCH PHASE ===");
        System.out.println("🎉 Victory! You can catch one of the defeated Pokémon!");
        
        // Show all defeated wild Pokémon as catchable options
        System.out.println("Choose a Pokémon to catch:");
        for (int i = 0; i < catchable.size(); i++) {
            Pokemon pokemon = catchable.get(i);
            String status = pokemon.isFainted() ? "FAINTED" : "Active";
            System.out.println((i + 1) + ". " + pokemon.getDisplayName() + " (" + pokemon.getType() + 
                             ") - HP: " + pokemon.getCurrentHP() + "/" + pokemon.getMaxHP() + " (" + status + ")");
        }
        
        System.out.println("0. Skip catching");
        
        int choice = Utils.getIntInput(0, catchable.size());
        if (choice == 0) {
            System.out.println("You chose not to catch any Pokémon.");
            return;
        }
        
        Pokemon target = catchable.get(choice - 1);
        System.out.println("You chose to catch " + target.getDisplayName() + "!");
        
        // Roll for Pokéball with animation
        Pokeball ball = rollForPokeball();
        
        boolean caught = CatchMechanism.attemptCatch(target, ball);
        if (caught) {
            GaOleDisk newDisk = new GaOleDisk(target, false);
            player.addDisk(newDisk);
            System.out.println(target.getDisplayName() + " was added to your Ga-Olé Disk collection!");
        }
    }
    
    private Pokeball rollForPokeball() {
        System.out.println("\n🎲 Rolling for Pokéball...");
        Utils.sleep(500);
        
        // Animated rolling effect
        String[] balls = {"⚪", "🔴", "🔵", "🟣"};
        for (int i = 0; i < 10; i++) {
            System.out.print("\rRolling: " + balls[i % balls.length]);
            Utils.sleep(200);
        }
        System.out.println();
        
        // Determine which ball based on probability
        double rand = Math.random();
        Pokeball selectedBall;
        
        if (rand < 0.6) {
            selectedBall = Pokeball.POKEBALL;
        } else if (rand < 0.85) {
            selectedBall = Pokeball.GREATBALL;
        } else if (rand < 0.95) {
            selectedBall = Pokeball.ULTRABALL;
        } else {
            selectedBall = Pokeball.MASTERBALL;
        }
        
        System.out.println("🎯 You got a " + selectedBall.getName() + "!");
        Utils.sleep(1000);
        
        return selectedBall;
    }
} 