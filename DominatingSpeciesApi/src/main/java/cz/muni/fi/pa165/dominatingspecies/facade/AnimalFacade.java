package cz.muni.fi.pa165.dominatingspecies.facade;

import cz.muni.fi.pa165.dominatingspecies.dto.AnimalBriefDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.AnimalDetailDTO;
import java.util.List;

public interface AnimalFacade {

    long createAnimal(AnimalBriefDTO newAnimal);

    AnimalDetailDTO findAnimal(long animalId);

    void updateAnimal(AnimalDetailDTO updatedAnimal);

    void deleteAnimal(long animalId);

    List<AnimalBriefDTO> findAllAnimals();

    List<AnimalBriefDTO> findPredatorsOf(long animalId);

    List<AnimalBriefDTO> findPreyOf(long animalId);

}
