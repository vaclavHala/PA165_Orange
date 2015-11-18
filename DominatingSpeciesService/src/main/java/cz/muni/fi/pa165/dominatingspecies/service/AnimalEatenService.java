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
     * Remove animalEaton
     * @param animalEaten 
     */
    public void remove(AnimalEaten animalEaten);
    
    /**
     * Update animalEaton
     * @param animalEaten 
     */
    public void update(AnimalEaten animalEaten);
    
    /**
     * Find All animals which are predator of animal
     * @param animal
     * @return 
     */
    public Collection<Animal> findPredatorOf(Animal animal);
    
    /**
     * Find All animals which are prey of animal
     * @param animal
     * @return 
     */
    public Collection<Animal> findPreyOf(Animal animal);
}
