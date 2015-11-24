package cz.muni.fi.pa165.dominatingspecies.service;

import cz.muni.fi.pa165.dominatingspecies.entity.Animal;
import java.util.Collection;

public interface AnimalService {

    /**
     * Create new Animal
     * @param animal must not have id set
     */
    public void create(Animal animal);

    /**
     * Find animal by id
     * @param id must be positive
     */
    public Animal findById(long id);

    /**
     * Find all known animals
     * @return
     */
    public Collection<Animal> findAll();

    /**
     * Update info about animal
     * @param animal must have id set
     */
    public void update(Animal animal);

    /**
     * Remove animal
     * @param animal must have id set
     */
    public void remove(Animal animal);
}
