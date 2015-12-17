package cz.muni.fi.pa165.dominatingspecies.service;

import cz.muni.fi.pa165.dominatingspecies.entity.Animal;
import cz.muni.fi.pa165.dominatingspecies.entity.AnimalEaten;
import java.util.Collection;

/**
 *
 * @author Petr
 */
public interface AnimalEatenService {

    /**
     * Create new animalEaten
     * @param animalEaten
     */
    public void createAnimalEaten(AnimalEaten animalEaten);

    /**
     * find AnimalEaten by id
     * @param animalEatenId
     * @return
     */
    public AnimalEaten findById(long animalEatenId);

    /**
     * Finds AnimalEaten which has the given combination of predator and prey
     * @return the AnimalEaten or null if no such exists
     */
    public AnimalEaten findByAnimalsInvolved(long predatorId, long preyId);

    /**
     * Remove animalEaton
     * @param animalEaten
     */
    public void remove(AnimalEaten animalEaten);

    /**
     * Remove all AnimalEatens for animal
     * @param animal
     */
    public void removeAllFor(Animal animal);

    /**
     * Update animalEaton
     */
    public void updateCount(long animalEatenId, Double newCount);

    /**
     * Find All animals which are predator of animal
     * @param animal
     * @return
     */
    public Collection<AnimalEaten> findPredatorsOf(Animal animal);

    /**
     * Find All animals which are prey of animal
     * @param animal
     * @return
     */
    public Collection<AnimalEaten> findPreyOf(Animal animal);

}
