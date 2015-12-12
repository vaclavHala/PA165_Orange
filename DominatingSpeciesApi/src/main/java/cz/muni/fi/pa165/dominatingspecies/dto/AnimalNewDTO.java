package cz.muni.fi.pa165.dominatingspecies.dto;

import java.util.Objects;
import javax.validation.constraints.Size;

public class AnimalNewDTO {

    @Size(min = 1, max = 255)
    private String name;

    @Size(min = 1, max = 255)
    private String species;

    public AnimalNewDTO() {
    }

    public AnimalNewDTO(String name, String species) {
        this.name = name;
        this.species = species;
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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AnimalNewDTO)) {
            return false;
        }
        AnimalNewDTO other = (AnimalNewDTO) obj;
        return this.name.equals(other.getName())
                && this.species.equals(other.species);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, species);
    }

    @Override
    public String toString() {
        return "AnimalBriefDTO{"
                + "name=" + name
                + ", species=" + species
                + '}';
    }

}
