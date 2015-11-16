package cz.muni.fi.pa165.dominatingspecies.dto;

import java.util.Collection;

public class AnimalDetailDTO {

    private long id;
    private String name;
    private String species;
    private long foodNeeded;
    private double repreductionRate;
    private Collection<AnimalBriefDTO> prey;
    private Collection<AnimalBriefDTO> predators;
}
