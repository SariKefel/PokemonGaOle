package group9.src;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Trainer {
    private String name;
    private List<Pokemon> diskCollection;
    private int wins;
    private int losses;

    public Trainer(String name) {
        this.name = name;
        this.diskCollection = new ArrayList<>();
        this.wins = 0;
        this.losses = 0;
    }

    // Getters and stats modifiers
    public String getName() { return name; }
    public List<Pokemon> getDiskCollection() { return diskCollection; }
    public int getWins() { return wins; }
    public int getLosses() { return losses; }
    public void incrementWins() { this.wins++; }
    public void incrementLosses() { this.losses++; }

    public void addDisk(Pokemon pokemon) {
        diskCollection.add(pokemon);
        System.out.printf("Disk scanned! Added %s (%s) to your collection.\n", pokemon.getName(), getRankStars(pokemon.getRank()));
    }

    public Optional<Pokemon> getRarestPokemon() {
        return diskCollection.stream().max(Comparator.comparingInt(Pokemon::getRank));
    }

    public void healAllPokemon() {
        for (Pokemon p : diskCollection) {
            p.heal();
        }
    }
    
    public static String getRankStars(int rank) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rank; i++) sb.append("★");
        return sb.toString();
    }
}