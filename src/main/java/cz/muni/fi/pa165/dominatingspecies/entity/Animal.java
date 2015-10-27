package cz.muni.fi.pa165.dominatingspecies.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * @author Daniel Minarik
 */

@Entity
public class Animal implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String species;
    
    private double foodNeeded;
    
    private double reproductionRate;

    public Animal() {
    }

    public Animal(Long id, String name, String species) {
        this.id = id;
        this.name = name;
        this.species = species;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Animal)) {
            return false;
        }
        Animal other = (Animal) obj;
        return ((id == other.getId()) && 
                (name.equals(other.getName())) && 
                (species.equals(other.getSpecies())) && 
                (foodNeeded == other.getFoodNeeded()) &&
                (reproductionRate == other.getReproductionRate()));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public double getFoodNeeded() {
        return foodNeeded;
    }

    public double getReproductionRate() {
        return reproductionRate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setFoodNeeded(double foodNeeded) {
        this.foodNeeded = foodNeeded;
    }

    public void setReproductionRate(double reproductionRate) {
        this.reproductionRate = reproductionRate;
    }
}
