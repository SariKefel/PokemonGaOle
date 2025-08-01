import java.io.Serializable;

public class GaOleDisk implements Serializable {
    private Pokemon pokemon;
    private boolean isRental;
    
    public GaOleDisk(Pokemon pokemon, boolean isRental) {
        this.pokemon = pokemon;
        this.isRental = isRental;
    }
    
    // Getters
    public Pokemon getPokemon() { return pokemon; }
    public boolean isRental() { return isRental; }
    
    @Override
    public String toString() {
        String rentalStatus = isRental ? " (Rental)" : "";
        return pokemon.getName() + " - " + pokemon.getType() + rentalStatus;
    }
} 