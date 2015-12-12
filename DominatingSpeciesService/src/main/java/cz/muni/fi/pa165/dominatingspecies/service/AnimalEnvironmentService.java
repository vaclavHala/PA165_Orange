package cz.muni.fi.pa165.dominatingspecies.service;

import cz.muni.fi.pa165.dominatingspecies.entity.Animal;
import cz.muni.fi.pa165.dominatingspecies.entity.AnimalEnvironment;
import java.util.Collection;

/**
 * Interface for AnimalEnviroment service
 * @author Daniel Minarik
 */
public interface AnimalEnvironmentService {

    /**
     * Creates record for specified animalEnvironment
     * @param animalEnvironment The environment to create
     * @throws IllegalArgumentException If parameter is null
     */
    public void create(AnimalEnvironment animalEnvironment);

    /**
     * Returns animalEnvironment for id
     * @param id Id of AnimalEnvironment
     */
    public AnimalEnvironment findById(long id);

    /**
     * Returns all AnimalEnvironments from storage
     */
    public Collection<AnimalEnvironment> findAll();

    /**
     * Removes specified animalEnvironment
     * @param animalEnvironment The environment to remove
     * @throws IllegalArgumentException If parameter is null
     */
    public void remove(AnimalEnvironment animalEnvironment);

    /**
     * Remove all AnimalEatens for animal
     * @param animal
     */
    public void removeAllFor(Animal animal);

    /**
     * Updates specified animalEnvironment
     * @param animalEnvironment The environment to update
     * @throws IllegalArgumentException If parameter is null
     */
    public void update(AnimalEnvironment animalEnvironment);
}
