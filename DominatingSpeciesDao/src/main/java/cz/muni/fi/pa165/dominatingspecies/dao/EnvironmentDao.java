package cz.muni.fi.pa165.dominatingspecies.dao;

import cz.muni.fi.pa165.dominatingspecies.entity.Environment;
import java.util.Collection;
import org.springframework.dao.DataAccessException;

/**
 * Handles storage and retrieval of Environment instances to and from persistent storage
 * @author Vaclav Hala 422551
 */
public interface EnvironmentDao {

    /**
     * Persist instance of Environment
     * @param enviro Environment to be persisted
     * @throws DataAccessException if id is already set for enviro
     *                             or any data-access related exception occurs
     */
    void persist(Environment enviro) throws DataAccessException;

    /**
     * Given id, tries to retrieve Environment with such id
     * @return Environment with given id or null of no such Environment exists
     * @throws DataAccessException if any data-access related exception occurs
     */
    Environment findById(Long id) throws DataAccessException;

    /**
     * Remove enviro from persistent storage
     * @param enviro Environment to be removed from persistent storage, must have id set
     * @throws DataAccessException if enviro does not have id set
     *                             or any data-access related exception occurs
     */
    void delete(Environment enviro) throws DataAccessException;
    
    /**
     * Updates enviro in persistent storage
     * @param enviro Environment to be updated in persistent storage, must have id set
     * @throws DataAccessException if enviro does not have id set
     *                             or any data-access related exception occurs
     */
    void update(Environment enviro) throws DataAccessException;

    /**
     * List all persisted Environments, order is not defined
     * @return retrieved Environments
     * @throws DataAccessException if any data-access related exception occurs
     */
    Collection<Environment> listAll() throws DataAccessException;

    /*
     * In future will contain methods which use AnimalEnvironmentDao to provide methods
     * such as possibleAnimalsLivingIn(Environment), mostCommonAnimalLivingIn(Environment) etc.
     */
}
