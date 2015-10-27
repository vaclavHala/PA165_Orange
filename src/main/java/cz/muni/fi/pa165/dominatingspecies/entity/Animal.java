package cz.muni.fi.pa165.dominatingspecies.entity;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    
    @NotNull
    private double foodNeeded;
    
    @NotNull
    private double reproductionRate;

    @OneToMany(mappedBy="animalsEaten")
    private Set<AnimalEaten> animalsEaten;

    @OneToMany(mappedBy="animalEnvironments")
    private Set<AnimalEnvironment> animalEnvironments;

    public Animal() {
    }

    public Animal(Long id, String name, String species, double foodNeeded, double reproductionRate) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.foodNeeded = foodNeeded;
        this.reproductionRate = reproductionRate;
        this.animalsEaten = new HashSet<>();
        this.animalEnvironments = new HashSet<>();
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

    public void addAnimalEaten(AnimalEaten animalEaten) {
        this.animalsEaten.add(animalEaten);
    }
    
    public Set<AnimalEaten> getAnimalsEaten() {
        return Collections.unmodifiableSet(animalsEaten);
    }

    public void addAnimalEnviroment(AnimalEnvironment animalEnvironment) {
        this.animalEnvironments.add(animalEnvironment);
    }
    
    public Set<AnimalEnvironment> getAnimalEnviroments() {
        return Collections.unmodifiableSet(animalEnvironments);
    }
}
