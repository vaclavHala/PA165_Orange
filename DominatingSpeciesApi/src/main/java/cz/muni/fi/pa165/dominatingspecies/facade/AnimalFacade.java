package cz.muni.fi.pa165.dominatingspecies.facade;

import cz.muni.fi.pa165.dominatingspecies.dto.AnimalBriefDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.AnimalDetailDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.AnimalEatenDTO;
import java.util.List;

public interface AnimalFacade {

    /**
     * Create new Animal
     * @param newAnimal
     * @return 
     */
    long createAnimal(AnimalBriefDTO newAnimal);

    /**
     * Find animal by anmailId
     * @param animalId
     * @return 
     */
    AnimalDetailDTO findAnimal(long animalId);

    /**
     * Update data of animal
     * @param updatedAnimal 
     */
    void updateAnimal(AnimalDetailDTO updatedAnimal);

    /**
     * Delete animal
     * @param animalId 
     */
    void deleteAnimal(long animalId);

    /**
     * List all animals
     * @return 
     */
    List<AnimalBriefDTO> findAllAnimals();

    /**
     * List all animals, which animal with animalId eat
     * @param animalId
     * @return 
     */
    List<AnimalBriefDTO> findPredatorsOf(long animalId);

    /**
     * List all animals, which are eaten by animal with animalId
     * @param animalId
     * @return 
     */
    List<AnimalBriefDTO> findPreyOf(long animalId);

    /**
     * Create new AnimalEaten
     * @param animalEatenDTO 
     */
    void createAnimalEaten(AnimalEatenDTO animalEatenDTO);
}
