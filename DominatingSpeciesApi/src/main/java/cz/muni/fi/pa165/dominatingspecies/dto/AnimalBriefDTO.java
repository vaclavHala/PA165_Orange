package cz.muni.fi.pa165.dominatingspecies.dto;

import java.util.Objects;

public class AnimalBriefDTO {

    private long id;
    private String name;
    private String species;

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

}
