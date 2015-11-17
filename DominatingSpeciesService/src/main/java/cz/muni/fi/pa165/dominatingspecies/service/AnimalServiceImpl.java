
package cz.muni.fi.pa165.dominatingspecies.service;

import cz.muni.fi.pa165.dominatingspecies.dao.AnimalDao;
import cz.muni.fi.pa165.dominatingspecies.entity.Animal;
import java.util.Collection;
import javax.inject.Inject;

/**
 *
 * @author Petr
 */
public class AnimalServiceImpl implements AnimalService {
    @Inject
    private AnimalDao animalDao;
            
    @Override
    public void create(Animal animal) {
        animalDao.create(animal);
    }

    @Override
    public Animal findById(long id) {
        return animalDao.getById(id);
    }

    @Override
    public Collection<Animal> findAll() {
        return animalDao.findAll();
    }

    @Override
    public void update(Animal animal) {
        animalDao.update(animal);
    }

    @Override
    public void remove(Animal animal) {
        animalDao.remove(animal);
    }
    
}
