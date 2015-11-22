package cz.muni.fi.pa165.dominatingspecies.dto;

import java.util.Objects;

public class AnimalBriefDTO {

    private Long id;
    private String name;
    private String species;

    public AnimalBriefDTO() {
    }

    public AnimalBriefDTO(String name, String species) {
        this.name = name;
        this.species = species;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AnimalBriefDTO)) {
            return false;
        }
        AnimalBriefDTO other = (AnimalBriefDTO) obj;
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
            + "id=" + id
            + ", name=" + name
            + ", species=" + species
            + '}';
    }

}
