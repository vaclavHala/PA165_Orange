
package cz.muni.fi.pa165.dominatingspecies.dto;

import java.util.Objects;

/**
 *
 * @author Petr
 */
public class AnimalEatenDTO {
    private Long id;
    private double animalCount;
    private AnimalBriefDTO predator;
    private AnimalBriefDTO prey;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAnimalCount() {
        return animalCount;
    }

    public void setAnimalCount(double animalCount) {
        this.animalCount = animalCount;
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

    @Override
    public int hashCode() {
        return Objects.hash(id, predator, prey);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof AnimalEatenDTO)) {
            return false;
        }
        final AnimalEatenDTO other = (AnimalEatenDTO) obj;
        if (!Objects.equals(this.getId(), other.getId())) {
            return false;
        }
        if (!Objects.equals(this.getPredator(), other.getPredator())) {
            return false;
        }
        if (!Objects.equals(this.getPrey(), other.getPrey())) {
            return false;
        }
        return true;
    }
    
    
}
