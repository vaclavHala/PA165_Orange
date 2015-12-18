package cz.muni.fi.pa165.dominatingspecies.entity;

import java.io.Serializable;
import java.util.Objects;
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

    private Double foodNeeded;

    private Double reproductionRate;

    public Animal() {
    }

    public Animal(String name, String species) {
        this.name = name;
        this.species = species;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Animal)) {
            return false;
        }

        Animal other = (Animal) obj;

        return this.getId() != null && other.getId() != null && this.getId().equals(other.getId());
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

    public Double getFoodNeeded() {
        return foodNeeded;
    }

    public Double getReproductionRate() {
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

    public void setFoodNeeded(Double foodNeeded) {
        this.foodNeeded = foodNeeded;
    }

    public void setReproductionRate(Double reproductionRate) {
        this.reproductionRate = reproductionRate;
    }

    @Override
    public String toString() {
        return "Animal{"
                + "id=" + id
                + ", name=" + name
                + ", species=" + species
                + ", foodNeeded=" + foodNeeded
                + ", reproductionRate=" + reproductionRate
                + '}';
    }

}
