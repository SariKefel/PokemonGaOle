public class PokemonGaOle {
    public static void main(String[] args) {
        try {
            GameManager game = new GameManager();
            game.start();
        } catch (Exception e) {
            System.out.println("An error occurred while running the game: " + e.getMessage());
            System.out.println("Please try running the game again.");
        }
    }
} 