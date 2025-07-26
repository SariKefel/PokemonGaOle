import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Manages loading and saving of trainer data.
 * This class simulates a persistence layer. In a real application,
 * methods here would interact with files or a database.
 */
public class GameDataManager {
    // A map to store all trainer data, keyed by the trainer's name (lowercase).
    // This acts as our in-memory "database".
    private static final Map<String, Trainer> trainerDatabase = new HashMap<>();

    /**
     * Saves or updates a trainer's data in the database.
     * @param trainer The trainer object to save.
     */
    public static void saveTrainer(Trainer trainer) {
        if (trainer != null) {
            trainerDatabase.put(trainer.getName().toLowerCase(), trainer);
            System.out.println("[System: Trainer " + trainer.getName() + "'s data has been saved.]");
        }
    }

    /**
     * Retrieves a trainer's data from the database by name.
     * @param name The name of the trainer to find.
     * @return An Optional containing the Trainer if found, otherwise an empty Optional.
     */
    public static Optional<Trainer> getTrainer(String name) {
        return Optional.ofNullable(trainerDatabase.get(name.toLowerCase()));
    }
}
