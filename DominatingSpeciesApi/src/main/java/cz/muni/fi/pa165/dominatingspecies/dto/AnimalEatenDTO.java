package cz.muni.fi.pa165.dominatingspecies.dto;

import java.util.Objects;

/**
 *
 * @author Petr
 */
public class AnimalEatenDTO {

    private Long id;
    private Double animalCount;
    private AnimalBriefDTO other;

    public AnimalEatenDTO() {
    }

    public AnimalEatenDTO(double animalCount, AnimalBriefDTO other) {
        this.animalCount = animalCount;
        this.other = other;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnimalBriefDTO getOther() {
        return other;
    }

    public void setOther(AnimalBriefDTO other) {
        this.other = other;
    }

    public Double getAnimalCount() {
        return animalCount;
    }

    public void setAnimalCount(double animalCount) {
        this.animalCount = animalCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(other);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AnimalEatenDTO)) {
            return false;
        }
        return ((AnimalEatenDTO) obj).getOther().equals(this.other);
    }

}
