package cz.muni.fi.pa165.dominatingspecies.dto;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class AnimalDetailDTO {

    private long id;
    private String name;
    private String species;
    private Long foodNeeded;
    private Double repreductionRate;
    private Collection<AnimalEatenDTO> prey;
    private Collection<AnimalEatenDTO> predators;

    public AnimalDetailDTO() {
    }

    public AnimalDetailDTO(long id, String name, String species) {
        this.id = id;
        this.name = name;
        this.species = species;
    }

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

    public Long getFoodNeeded() {
        return foodNeeded;
    }

    public void setFoodNeeded(long foodNeeded) {
        this.foodNeeded = foodNeeded;
    }

    public Double getRepreductionRate() {
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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AnimalDetailDTO)) {
            return false;
        }
        AnimalDetailDTO other = (AnimalDetailDTO) obj;
        return this.name.equals(other.name)
            && this.species.equals(other.species);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, species);
    }

}
