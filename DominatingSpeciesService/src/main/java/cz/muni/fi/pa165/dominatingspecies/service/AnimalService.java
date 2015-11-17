
package cz.muni.fi.pa165.dominatingspecies.service;

import cz.muni.fi.pa165.dominatingspecies.entity.Animal;
import java.util.Collection;

public interface AnimalService {
    public void create(Animal animal);
    
    public Animal findById(long id);
    
    public Collection<Animal> findAll();
    
    public void update(Animal animal);
    
    public void remove(Animal animal);
}
