import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.util.stream.Collectors;

public class Battle {
    private Trainer trainer;
    private List<Pokemon> playerTeam;
    private List<Pokemon> enemyTeam;
    private Scanner scanner;
    private Random random = new Random();
    private int totalPlayerDamage = 0;

    public Battle(Trainer trainer, List<Pokemon> playerTeam, List<Pokemon> enemyTeam) {
        this.trainer = trainer;
        this.playerTeam = playerTeam;
        this.enemyTeam = enemyTeam;
        this.scanner = new Scanner(System.in);
    }
    
    public int getTotalPlayerDamage() {
        return totalPlayerDamage;
    }

    public boolean start() {
        System.out.printf("\n--- Battle Start! %s vs Wild Pokémon! ---\n", trainer.getName());

        while (!isTeamFainted(playerTeam) && !isTeamFainted(enemyTeam)) {
            playerTurn();
            if (isTeamFainted(enemyTeam)) break;
            enemyTurn();
        }

        if (isTeamFainted(enemyTeam)) {
            System.out.println("\n🏆 You defeated the wild Pokémon! 🏆");
            trainer.incrementWins();
            return true;
        } else {
            System.out.println("\nYour team was defeated... Better luck next time.");
            trainer.incrementLosses();
            return false;
        }
    }

    private void playerTurn() {
        System.out.println("\n--- Your Turn ---");
        displayBattleState();

        for (Pokemon attacker : playerTeam) {
            if (attacker.isFainted()) continue;
            if (isTeamFainted(enemyTeam)) break;

            System.out.printf("\nChoose a target for %s:\n", attacker.getName());
            List<Pokemon> availableTargets = getAvailableTargets(enemyTeam);
            for (int i = 0; i < availableTargets.size(); i++) {
                System.out.printf("%d. %s (HP: %d/%d)\n", i + 1, availableTargets.get(i).getName(), availableTargets.get(i).getHp(), availableTargets.get(i).getMaxHp());
            }

            int choice = getUserChoice(1, availableTargets.size()) - 1;
            Pokemon target = availableTargets.get(choice);
            
            attackWithTiming(attacker, target);
        }
    }

    private void enemyTurn() {
        System.out.println("\n--- Enemy's Turn ---");
        for (Pokemon attacker : enemyTeam) {
            if (attacker.isFainted()) continue;
            if (isTeamFainted(playerTeam)) break;

            List<Pokemon> availableTargets = getAvailableTargets(playerTeam);
            Pokemon target = availableTargets.get(random.nextInt(availableTargets.size()));
            
            int damage = calculateDamage(attacker, target, 1.0); // AI gets normal damage
            target.takeDamage(damage);
            System.out.printf("Wild %s attacks %s and deals %d damage!\n", attacker.getName(), target.getName(), damage);
        }
    }

    private void attackWithTiming(Pokemon attacker, Pokemon target) {
        System.out.printf("\n%s is attacking %s!\n", attacker.getName(), target.getName());
        System.out.println("===[   |====     ]=== (Attack Gauge)");
        System.out.print("Type \"GO\" and press Enter for a power shot! > ");
        scanner.nextLine(); // Wait for user to type GO and press enter

        // Simulate timing result
        double timingMultiplier = 0.5 + random.nextDouble() * 1.0; // Random value between 0.5 and 1.5
        String timingResult;
        if (timingMultiplier < 0.8) timingResult = "Oops... ";
        else if (timingMultiplier < 1.3) timingResult = "Good! ";
        else timingResult = "Great Timing! ";

        int damage = calculateDamage(attacker, target, timingMultiplier);
        totalPlayerDamage += damage;
        target.takeDamage(damage);
        System.out.println(timingResult + attacker.getName() + " deals " + damage + " damage!");

        if (target.isFainted()) {
            System.out.println(target.getName() + " fainted!");
        }
    }
    
    private int calculateDamage(Pokemon attacker, Pokemon defender, double timingMultiplier) {
        double typeEffectiveness = attacker.getType().getEffectiveness(defender.getType());
        int baseDamage = (int) (attacker.getAttack() * timingMultiplier * typeEffectiveness);
        int finalDamage = Math.max(1, baseDamage - defender.getDefense());
        
        if (typeEffectiveness > 1.0) System.out.println("It's super effective!");
        if (typeEffectiveness < 1.0) System.out.println("It's not very effective...");

        return finalDamage;
    }

    private int getUserChoice(int min, int max) {
        int choice;
        while (true) {
            try {
                System.out.print("> ");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= min && choice <= max) break;
            } catch (NumberFormatException e) {
                // ignore
            }
            System.out.printf("Invalid input. Please enter a number between %d and %d.\n", min, max);
        }
        return choice;
    }
    
    private void displayBattleState() {
        System.out.println("Your Team:");
        playerTeam.forEach(p -> System.out.printf("  - %-12s HP: %d/%d %s\n", p.getName(), p.getHp(), p.getMaxHp(), p.isFainted() ? "(Fainted)" : ""));
        System.out.println("Enemy Team:");
        enemyTeam.forEach(p -> System.out.printf("  - %-12s HP: %d/%d %s\n", p.getName(), p.getHp(), p.getMaxHp(), p.isFainted() ? "(Fainted)" : ""));
    }

    private boolean isTeamFainted(List<Pokemon> team) {
        return team.stream().allMatch(Pokemon::isFainted);
    }

    private List<Pokemon> getAvailableTargets(List<Pokemon> team) {
        return team.stream().filter(p -> !p.isFainted()).collect(Collectors.toList());
    }
}