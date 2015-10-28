package cz.muni.fi.pa165.dominatingspecies.dao;

/**
 * Created by Petr Domkař on 28. 10. 2015.
 */

import cz.muni.fi.pa165.dominatingspecies.entity.Animal;
import java.util.List;

/**
 * Interface for define operation with Animal instance in persistent layer
 * @author Petr Domkař
 */
public interface AnimalDao {
    /**
     * Create new instance of Animal in persistent layer
     * @param animal Animal to be create in persistent layer
     */
    void create(Animal animal);

    /**
     * Returns List of stored Animal
     * @return List of Animal
     */
    List<Animal> findAll();

    /**
     * Return Animal which has required id
     * @param id Long number identifying specific Animal
     * @return Animal with required id
     */
    Animal getById(Long id);

    /**
     * Remove specified Animal from persistent layer
     * @param animal Instance of Animal with a set id which is supposed to be remove from persistent layer
     */
    void remove(Animal animal);
}
