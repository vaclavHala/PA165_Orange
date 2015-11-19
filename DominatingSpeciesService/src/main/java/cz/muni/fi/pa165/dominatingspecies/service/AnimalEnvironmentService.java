package cz.muni.fi.pa165.dominatingspecies.service;

import cz.muni.fi.pa165.dominatingspecies.entity.AnimalEnvironment;
import java.util.Collection;

/**
 * Interface for AnimalEnviroment service
 * @author Daniel Minarik
 */
public interface AnimalEnvironmentService {
    public void create(AnimalEnvironment animalEnvironment);
    public AnimalEnvironment findById(long id);
    public Collection<AnimalEnvironment> findAll();
    public void remove(AnimalEnvironment animalEnvironment);
    public void update(AnimalEnvironment animalEnvironment);
}
