package cz.muni.fi.pa165.dominatingspecies.facade;

import cz.muni.fi.pa165.dominatingspecies.dto.*;
import java.util.List;

public interface AnimalFacade {

    /**
     * Finds all animals stored in the system
     * @return list of Animals
     */
    List<AnimalBriefDTO> findAllAnimals();

    /**
     * Create new animal
     * @param newAnimal to be created
     * @return id of the newly created animal
     */
    long createAnimal(AnimalNewDTO newAnimal);

    /**
     * Delete existing animal
     * @param animalId must be positive
     */
    void deleteAnimal(long animalId);

    /**
     * Retrieve brief set of details about animal, suitable for displaying list of animals etc.
     * @param animalId of Animal to find
     * @return brief info about the Animal
     */
    AnimalBriefDTO findAnimalBrief(long animalId);

    /**
     * Retrieve all available info about given Animal
     * @param animalId of Animal to find
     * @return full info about the Animal
     */
    AnimalDetailDTO findAnimalDetail(long animalId);

    /**
     * update info about existing animal
     * @param detail must have id set
     */
    void updateAnimal(AnimalDetailDTO detail);

    /**
     * Create the information that animal eats another.
     * The created instance has no info apart from the two Animals,
     * additional details must be submitted via
     * {@link #updateAnimalEaten(AnimalEatenDTO) updateAnimalEaten()}
     * @param predatorId eats prey
     * @param preyId     is eaten by predator
     * @return id of the created AnimalEaten
     */
    long createAnimalEaten(long predatorId, long preyId);

    /**
     * Update info about one Animal eating another
     * @param animalEaten must have id set
     */
    void updateAnimalEaten(AnimalEatenDTO animalEaten);

    /**
     * Delete info about one Animal eating another
     * @param animalEatenId must have id set
     */
    void deleteAnimalEaten(long animalEatenId);

}
