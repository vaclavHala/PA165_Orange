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
     * Finds and returns environment for specified identifier
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
     * Updates data for specified AnimalEnvironment in system
     * 
     * @param AnimalEnvironment New data for AnimalEnvironment
     */
    public void updateAnimalEnvironment(AnimalEnvironmentDTO ae);
    
    /**
     * Removes environment with specified identifier from system
     * 
     * @param id Identifier of the environment
     */
    public void deleteEnvironment(long id);
    
    /**
     * Removes animal environment with specified identifier from system
     * 
     * @param animalId Identifier of the animal
     * @param envId Identifier of the environment
     */
    public void deleteAnimalEnvironment(long animalId, long envId);

    /**
     * Assigns specified environment to specified animal with specified data
     * 
     * @param animalEnvironment The animal, environment and assignment data
     * 
     * @return Identifier of created animal environment
     */
    public long addAnimalEnvironment(AnimalEnvironmentDTO animalEnvironment);
    
    /**
     * Removes animal environment record for specified identifier
     * 
     * @param id Identifier of animal environment
     */
    public void removeAnimalEnvironment(long id);
    
    /**
     * Finds and returns AnimalEnvironment for specified identifier
     * 
     * @param id Identifier of AnimalEnvironment
     * @return The AnimalEnvironment
     */
    public AnimalEnvironmentDTO findAeById(long id);

    /**
     * Finds and returns AnimalEnvironment for specified identifier
     * 
     * @param animalId Identifier of animal
     * @return The AnimalEnvironment
     */
    public Collection<AnimalEnvironmentDTO> findAeByAnimalId(long animalId);

    /**
     * Finds and returns AnimalEnvironment for specified identifier
     * 
     * @param envId Identifier of environment
     * @return The AnimalEnvironment
     */
    public Collection<AnimalEnvironmentDTO> findAeByEnvironmentId(long envId);

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
