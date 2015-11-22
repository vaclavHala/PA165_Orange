package cz.muni.fi.pa165.dominatingspecies.dto;

import java.util.Collection;
import java.util.Collections;

public class AnimalDetailDTO {

    private long id;
    private String name;
    private String species;
    private long foodNeeded;
    private double repreductionRate;
    private Collection<AnimalEatenDTO> prey;
    private Collection<AnimalEatenDTO> predators;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public long getFoodNeeded() {
        return foodNeeded;
    }

    public void setFoodNeeded(long foodNeeded) {
        this.foodNeeded = foodNeeded;
    }

    public double getRepreductionRate() {
        return repreductionRate;
    }

    public void setRepreductionRate(double repreductionRate) {
        this.repreductionRate = repreductionRate;
    }

    public Collection<AnimalEatenDTO> getPrey() {
        if (prey == null) {
            prey = Collections.emptyList();
        }
        return prey;
    }

    public void setPrey(Collection<AnimalEatenDTO> prey) {
        this.prey = prey;
    }

    public Collection<AnimalEatenDTO> getPredators() {
        if (predators == null) {
            predators = Collections.emptyList();
        }
        return predators;
    }

    public void setPredators(Collection<AnimalEatenDTO> predators) {
        this.predators = predators;
    }
}
