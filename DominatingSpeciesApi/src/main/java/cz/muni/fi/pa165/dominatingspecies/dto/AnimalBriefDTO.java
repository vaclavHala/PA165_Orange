package cz.muni.fi.pa165.dominatingspecies.dto;

public class AnimalBriefDTO {

    private long id;
    private String name;
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
