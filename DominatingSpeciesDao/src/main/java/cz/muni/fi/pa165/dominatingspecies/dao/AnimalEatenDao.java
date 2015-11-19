package cz.muni.fi.pa165.dominatingspecies.dao;

import cz.muni.fi.pa165.dominatingspecies.entity.AnimalEaten;
import java.util.List;

/**
 * Basic operations for work with AnimalEaten instance and persistence layer
 * @author Daniel Minarik
 */
public interface AnimalEatenDao {
    /**
     * Creates new instance of AnimalEaten
     * @param animalEaten Instance of AnimalEaten
     */
    public void create(AnimalEaten animalEaten);
    
    /**
     * Returns list of all AnimalEaten
     * @return List List of AnimalEaten
     */
    public List<AnimalEaten> findAll();

    /**
     * Finds AnimalEaten by Id
     * @param id Id of an animal
     * @return AnimalEaten Instance of AnimalEaten
     */
    public AnimalEaten findById(Long id);
    
    /**
     * Removes AnimalEaten
     * @param animalEaten Instance of AnimalEaten
     */
    public void remove(AnimalEaten animalEaten);
    
    /**
     * Updates record for AnimalEaten in persistent storage
     * @param animal
     */
    public void update(AnimalEaten animal);

}
