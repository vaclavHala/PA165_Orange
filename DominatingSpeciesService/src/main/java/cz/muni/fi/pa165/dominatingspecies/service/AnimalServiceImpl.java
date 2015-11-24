package cz.muni.fi.pa165.dominatingspecies.service;

import cz.muni.fi.pa165.dominatingspecies.dao.AnimalDao;
import cz.muni.fi.pa165.dominatingspecies.entity.Animal;
import java.util.Collection;
import java.util.Objects;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *
 * @author Petr
 */
@Service
public class AnimalServiceImpl implements AnimalService {

    @Inject
    private AnimalDao animalDao;

    @Override
    public void create(Animal animal) {
        if (animal.getId() != null) {
            throw new IllegalArgumentException("Creating animal with set id: " + animal);
        }
        Objects.requireNonNull(animal.getName());
        Objects.requireNonNull(animal.getSpecies());
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
        Objects.requireNonNull(animal.getId());
        animalDao.update(animal);
    }

    @Override
    public void remove(Animal animal) {
        Objects.requireNonNull(animal.getId());
        animalDao.remove(animal);
    }

}
