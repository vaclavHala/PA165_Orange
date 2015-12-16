package cz.muni.fi.pa165.dominatingspecies.dao;

import cz.muni.fi.pa165.dominatingspecies.entity.Animal;
import cz.muni.fi.pa165.dominatingspecies.entity.AnimalEnvironment;
import cz.muni.fi.pa165.dominatingspecies.entity.Environment;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 * @author Ivan Kralik
 */
public interface AnimalEnvironmentDao {

    /**
     * Creates animal environment record in persistent storage
     *
     * @param environment The animal environment
     * @throws DataAccessException If id is set in animal environment or persistent storage error
     */
    public void create(AnimalEnvironment environment);

    /**
     * Retrieves animal environment record with specified identifier from persistent storage
     *
     * @param id Identifier (primary key) of the record
     * @return Animal environment with specified id or null, if no such exists
     * @throws DataAccessException On persistent storage error
     */
    public AnimalEnvironment findById(Long id);

    /**
     * Retrieves animal environment record with specified animal and environment
     *
     * @return Animal environment with specified id or null, if no such exists
     * @throws DataAccessException On persistent storage error
     */
    public AnimalEnvironment findByIdAnimalEnvironment(long animalId, long envId);

    /**
     * Retrieves all animal environment records from persistent storage
     *
     * @return List of all animal environments
     * @throws DataAccessException On persistent storage error
     */
    public List<AnimalEnvironment> findAll();

    /**
     * Removes record for specified animal environment from persistent storage
     *
     * @param environment Animal environment to remove
     * @throws DataAccessException If animal environment is not in persistent storage or persistent storage error
     */
    public void remove(AnimalEnvironment environment);

    /**
     * Updates record for specified animal environment in persistent storage
     *
     * @param environment Animal environment to update
     * @throws DataAccessException On persistent storage error
     */
    public void update(AnimalEnvironment environment);

    /**
     * Finds records for specified animal
     *
     * @param animal The animal to find records for
     *
     * @return Animal environments for the animal
     */
    public List<AnimalEnvironment> findByAnimal(Animal animal);

    /**
     * Finds records for specified environment
     *
     * @param environment The environment to find records for
     *
     * @return Animal environments for specified environment
     */
    public List<AnimalEnvironment> findByEnvironment(Environment environment);
}
