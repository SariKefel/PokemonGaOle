import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Battle {
    private List<Pokemon> playerPokemon;
    private Pokemon enemyPokemon;
    private Scanner scanner;
    private Random random = new Random();

    public Battle(List<Pokemon> playerPokemon, Pokemon enemyPokemon) {
        this.playerPokemon = playerPokemon;
        this.enemyPokemon = enemyPokemon;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts and manages the battle loop.
     * @return true if the player wins, false otherwise.
     */
    public boolean start() {
        System.out.println("\n--- A wild " + enemyPokemon.getName() + " appears! ---");
        
        while (!playerPokemon.stream().allMatch(Pokemon::isFainted) && !enemyPokemon.isFainted()) {
            playerTurn();
            if (enemyPokemon.isFainted()) {
                System.out.println("The wild " + enemyPokemon.getName() + " fainted!");
                return true; // Player wins
            }

            enemyTurn();
            if (playerPokemon.stream().allMatch(Pokemon::isFainted)) {
                System.out.println("All your Pokémon have fainted!");
                return false; // Player loses
            }
        }
        return false; // Should not be reached, but covers all paths
    }
    
    private void playerTurn() {
        System.out.println("\n--- Your Turn ---");
        Pokemon activePokemon = chooseActivePokemon();
        if (activePokemon == null) return; // All are fainted

        System.out.println(activePokemon.getName() + ", what will you do?");
        System.out.println("1. Attack");
        // In a real game, more options would be here.
        
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 1) {
            int damage = calculateDamage(activePokemon, enemyPokemon);
            enemyPokemon.takeDamage(damage);
            System.out.println(activePokemon.getName() + " attacks! It deals " + damage + " damage.");
            System.out.println("Wild " + enemyPokemon.getName() + " has " + enemyPokemon.getHp() + " HP remaining.");
        }
    }

    private Pokemon chooseActivePokemon() {
        // Simple logic: use the first non-fainted Pokémon
        for (Pokemon p : playerPokemon) {
            if (!p.isFainted()) {
                return p;
            }
        }
        return null; // All fainted
    }

    private void enemyTurn() {
        System.out.println("\n--- Enemy's Turn ---");
        // Simple AI: Enemy always attacks the first player Pokémon that isn't fainted
        Pokemon target = null;
        for(Pokemon p : playerPokemon) {
            if(!p.isFainted()) {
                target = p;
                break;
            }
        }

        if (target != null) {
            int damage = calculateDamage(enemyPokemon, target);
            target.takeDamage(damage);
            System.out.println("Wild " + enemyPokemon.getName() + " attacks! It deals " + damage + " damage to " + target.getName() + ".");
            System.out.println(target.getName() + " has " + target.getHp() + " HP remaining.");
        }
    }

    private int calculateDamage(Pokemon attacker, Pokemon defender) {
        // Damage formula: (Attack * TypeEffectiveness * RandomFactor) - Defense
        double typeEffectiveness = attacker.getType().getEffectiveness(defender.getType());
        double randomFactor = 0.85 + (1.15 - 0.85) * random.nextDouble(); // Fluctuation between 85% and 115%
        int rawDamage = (int) (attacker.getAttack() * typeEffectiveness * randomFactor);
        int finalDamage = Math.max(1, rawDamage - defender.getDefense()); // Always do at least 1 damage
        
        if (typeEffectiveness > 1.0) System.out.println("It's super effective!");
        if (typeEffectiveness < 1.0) System.out.println("It's not very effective...");
        
        return finalDamage;
    }
}