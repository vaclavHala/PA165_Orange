package cz.muni.fi.pa165.dominatingspecies.facade;

import cz.muni.fi.pa165.dominatingspecies.dto.*;
import java.util.List;

public interface AnimalFacade {

    List<AnimalBriefDTO> findAllAnimals();

    long createAnimal(AnimalNewDTO newAnimal);

    void deleteAnimal(long animalId);

    AnimalBriefDTO findAnimalBrief(long animalId);

    AnimalDetailDTO findAnimalDetail(long animalId);

    void updateAnimal(AnimalDetailDTO detail);

    long createAnimalEaten(long predatorId, long preyId);

    void updateAnimalEaten(AnimalEatenDTO animalEaten);

    void deleteAnimalEaten(long animalEatenId);
}
