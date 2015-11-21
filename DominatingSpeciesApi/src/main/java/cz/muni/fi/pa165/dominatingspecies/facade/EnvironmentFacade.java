package cz.muni.fi.pa165.dominatingspecies.facade;

import cz.muni.fi.pa165.dominatingspecies.dto.AnimalDetailDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.AnimalEnvironmentDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.EnvironmentDTO;
import java.util.Collection;

public interface EnvironmentFacade {
    
    /**
     * Finds and returns all environments in system
     * 
     * @return The environments
     */
    public Collection<EnvironmentDTO> findAllEnvironments();
    
    /**
     * Finds and returns environment for spevified identifier
     * 
     * @param id Identifier of environment
     * 
     * @return The environment
     */
    public EnvironmentDTO findEnvironment(long id);
    
    /**
     * Creates environment with specified data in system
     * 
     * @param environment Environment data
     * 
     * @return Identifier of created environment
     */
    public long createEnvironment(EnvironmentDTO environment);
    
    /**
     * Updates data for specified environment in system
     * 
     * @param environment New data for environment
     */
    public void updateEnvironment(EnvironmentDTO environment);
    
    /**
     * Removes environment with specified identifier from system
     * 
     * @param id Identifier of the environment
     */
    public void deleteEnvironment(long id);
    
    /**
     * Assigns specified environment to specified animal with specified data
     * 
     * @param animalEnvironment The animal, environment and assignment data
     */
    public void addAnimalEnvironment(AnimalEnvironmentDTO animalEnvironment);
    
    /**
     * Removes specified environment from specified animal
     * 
     * @param animalEnvironment The animal and environment to remove
     */
    public void removeAnimalEnvironment(AnimalEnvironmentDTO animalEnvironment);
    
    /**
     * Finds and returns animals assigned to environment with specified identifier
     * 
     * @param environmentId The identifier of environment
     * 
     * @return Animals assigned to the environment
     */
    public Collection<AnimalDetailDTO> findAnimalsInEnvironment(long environmentId);
    
    /**
     * Finds and returns environments assigned to animal with specified identifier
     * 
     * @param animalId The identified of animal
     * 
     * @return Environments assigned to the animal
     */
    public Collection<EnvironmentDTO> findEnvironmentsForAnimal(long animalId);
}
