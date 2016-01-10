package cz.muni.fi.pa165.dominatingspecies.dto;

import java.util.Objects;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * DTO for entity representing relationship between animal and environment.
 * @author Daniel Minarik
 */
public class AnimalEnvironmentDTO {

    private long id;
    private AnimalBriefDTO animal;
    private EnvironmentDTO environment;
    @Min(0)
    @Max(100)
    private Double percentage;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AnimalBriefDTO getAnimal() {
        return animal;
    }

    public void setAnimal(AnimalBriefDTO animal) {
        this.animal = animal;
    }

    public EnvironmentDTO getEnvironment() {
        return environment;
    }

    public void setEnvironment(EnvironmentDTO environment) {
        this.environment = environment;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof EnvironmentDTO)) {
            return false;
        }
        AnimalEnvironmentDTO other = (AnimalEnvironmentDTO) o;
        return Objects.equals(this.getId(), other.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, animal, environment);
    }

}
