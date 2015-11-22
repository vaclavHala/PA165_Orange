package cz.muni.fi.pa165.dominatingspecies.service;

import javax.inject.Inject;

import cz.muni.fi.pa165.dominatingspecies.dao.AnimalEnvironmentDao;
import cz.muni.fi.pa165.dominatingspecies.entity.AnimalEnvironment;
import java.util.Collection;
import javax.inject.Named;

/**
 * Implementation of the AnimalEnvironment service.
 * @author Daniel Minarik
 */
@Named
public class AnimalEnvironmentServiceImpl implements AnimalEnvironmentService {
    
    @Inject
    private AnimalEnvironmentDao animalEnvironmentDao;

    @Override
    public void create(AnimalEnvironment animalEnvironment) {
        animalEnvironmentDao.create(animalEnvironment);
    }

    @Override
    public AnimalEnvironment findById(long id) {
        return animalEnvironmentDao.findById(id);
    }

    @Override
    public Collection<AnimalEnvironment> findAll() {
        return animalEnvironmentDao.findAll();
    }

    @Override
    public void remove(AnimalEnvironment animalEnvironment) {
        animalEnvironmentDao.remove(animalEnvironment);
    }

    @Override
    public void update(AnimalEnvironment animalEnvironment) {
        animalEnvironmentDao.update(animalEnvironment);
    } 
}
