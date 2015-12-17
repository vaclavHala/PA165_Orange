package cz.muni.fi.pa165.dominatingspecies.dto;

import java.util.Objects;

/**
 *
 * @author Petr
 */
public class AnimalEatenDTO {

    private Long id;
    private Double animalCount;
    private AnimalBriefDTO predator;
    private AnimalBriefDTO prey;

    public AnimalEatenDTO() {
    }

    public AnimalEatenDTO(long id, AnimalBriefDTO predator, AnimalBriefDTO prey) {
        this.id = id;
        this.predator = predator;
        this.prey = prey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnimalBriefDTO getPredator() {
        return predator;
    }

    public void setPredator(AnimalBriefDTO predator) {
        this.predator = predator;
    }

    public AnimalBriefDTO getPrey() {
        return prey;
    }

    public void setPrey(AnimalBriefDTO prey) {
        this.prey = prey;
    }

    public Double getAnimalCount() {
        return animalCount;
    }

    public void setAnimalCount(Double animalCount) {
        this.animalCount = animalCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(predator, prey);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AnimalEatenDTO)) {
            return false;
        }
        AnimalEatenDTO other = (AnimalEatenDTO) obj;
        return other.getPredator().equals(this.getPredator())
                && other.getPrey().equals(this.getPrey());
    }

}
