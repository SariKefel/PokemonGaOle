public class CatchMechanism {
    
    public static boolean attemptCatch(Pokemon pokemon, Pokeball ball) {
        // Improved catch formula with higher base rate
        double baseRate = 0.6; // Increased from 0.3 to 0.6 (doubled the base rate)
        double ballModifier = ball.getModifier();
        
        // Calculate catch rate with improved formula
        double catchRate;
        if (pokemon.isFainted()) {
            // Fainted Pokémon are easier to catch (like in real Pokémon games)
            catchRate = ((3.0 * pokemon.getMaxHP() - 2.0 * pokemon.getCurrentHP()) 
                        * baseRate * ballModifier * 1.5) / (3.0 * pokemon.getMaxHP());
        } else {
            // Active Pokémon use normal formula
            catchRate = ((3.0 * pokemon.getMaxHP() - 2.0 * pokemon.getCurrentHP()) 
                        * baseRate * ballModifier) / (3.0 * pokemon.getMaxHP());
        }
        
        // Ensure catch rate is between 0.2 and 0.95 (except for Master Ball)
        if (ball != Pokeball.MASTERBALL) {
            catchRate = Math.max(0.2, Math.min(0.95, catchRate));
        }
        
        System.out.println("\n🎯 Throwing " + ball.getName() + "...");
        Utils.sleep(500);
        
        // Spinning animation
        System.out.print("⚪ ");
        for (int i = 0; i < 3; i++) {
            Utils.sleep(300);
            System.out.print("⚪ ");
        }
        System.out.println();
        
        // Wiggle animation
        System.out.print("Wiggle");
        for (int i = 0; i < 3; i++) {
            Utils.sleep(200);
            System.out.print("...");
        }
        System.out.println();
        
        boolean caught = Math.random() < catchRate;
        
        if (caught) {
            System.out.println("...and click! ✨");
            Utils.sleep(500);
            System.out.println("🎉 Gotcha! " + pokemon.getName() + " was caught!");
        } else {
            System.out.println("Oh no! It escaped! 💨");
        }
        
        return caught;
    }
    
    public static Pokeball getRandomPokeball() {
        double rand = Math.random();
        
        // Improved distribution for better balls
        if (rand < 0.5) {
            return Pokeball.POKEBALL;
        } else if (rand < 0.8) {
            return Pokeball.GREATBALL;
        } else if (rand < 0.95) {
            return Pokeball.ULTRABALL;
        } else {
            return Pokeball.MASTERBALL;
        }
    }
} 