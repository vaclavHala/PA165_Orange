package cz.muni.fi.pa165.dominatingspecies.dao;

/**
 * Created by Petr Domkař on 28. 10. 2015.
 */

import cz.muni.fi.pa165.dominatingspecies.entity.Animal;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Interface for define operation with Animal instance in persistent layer
 * @author Petr Domkař
 */
public interface AnimalDao {
    /**
     * Create new instance of Animal in persistent layer
     * @param animal Animal to be create in persistent layer
     * @throws DataAccessException If id is already set of Animal or any data-access related exception occurs
     */
    public void create(Animal animal) throws DataAccessException;

    /**
     * Returns List of stored Animal
     * @return List of Animal
     * @throws DataAccessException If any data-access related exception occurs
     */
    public List<Animal> findAll() throws DataAccessException;

    /**
     * Return Animal which has required id
     * @param id Long number identifying specific Animal
     * @return Animal with required id
     * @throws DataAccessException If any data-access related exception occurs
     */
    public Animal getById(Long id) throws DataAccessException;

    /**
     * Remove specified Animal from persistent layer
     * @param animal Instance of Animal with a set id which is supposed to be remove from persistent layer
     * @throws DataAccessException If is not set id of Animal or any data-access related exception occurs
     */
    public void remove(Animal animal) throws DataAccessException;
    
    /**
     * Updates record for specified animal in persistent storage
     * 
     * @param animal to update
     * @throws DataAccessException 
     */
    public void update(Animal animal) throws DataAccessException;
}
