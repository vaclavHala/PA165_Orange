package cz.muni.fi.pa165.dominatingspecies.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AnimalDetailUpdateIdentificationDTO {

    @NotNull
    @Size(min = 1, max = 255)
    private String name;
    @NotNull
    @Size(min = 1, max = 255)
    private String species;

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
