package cz.muni.fi.pa165.dominatingspecies.service;

import cz.muni.fi.pa165.dominatingspecies.entity.Animal;
import cz.muni.fi.pa165.dominatingspecies.entity.Environment;
import java.util.Collection;

/**
 * @author Ivan Kralik
 */
public interface EnvironmentService {
    
    /**
     * Creates record for specified environment
     * 
     * @param environment The environment to create
     * @throws IllegalArgumentException If parameter is null
     */
    public void create(Environment environment);
    
    /**
     * Finds and returns record for specified environment identified
     * 
     * @param id The environment identified
     * 
     * @return The record for specified identifier
     */
    public Environment findById(Long id);
    
    /**
     * Finds and returns all environment records
     * 
     * @return All environment records
     */
    public Collection<Environment> findAll();
    
    /**
     * Updates record for specified environment
     * 
     * @param environment The environment to update
     * @throws IllegalArgumentException If parameter is null
     */
    public void update(Environment environment);
    
    /**
     * Removes record for specified environment
     * 
     * @param environment The environment to remove
     * @throws IllegalArgumentException If parameter is null
     */
    public void remove(Environment environment);
    
    /**
     * Finds and returns environments for specified animal
     * 
     * @param animal The animal to find environments for
     * 
     * @return Environments for specified animal
     * @throws IllegalArgumentException If parameter is null
     */
    public Collection<Environment> findEnvironmentsForAnimal(Animal animal);
    
    /**
     * Finds and returns animals for specified environment
     * 
     * @param environment The environment to find animals for
     * 
     * @return Animals for specified environment
     * @throws IllegalArgumentException If parameter is null
     */
    public Collection<Animal> findAnimalsForEnvironment(Environment environment);
}
