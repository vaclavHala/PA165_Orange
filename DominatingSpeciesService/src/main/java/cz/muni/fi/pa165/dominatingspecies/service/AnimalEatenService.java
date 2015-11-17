package cz.muni.fi.pa165.dominatingspecies.service;

import cz.muni.fi.pa165.dominatingspecies.entity.Animal;
import java.util.Collection;

/**
 *
 * @author Petr
 */
public interface AnimalEatenService {
    public Collection<Animal> findPredatorOf(Animal animal);
    public Collection<Animal> findPreyOf(Animal animal);
}
