package cz.muni.fi.pa165.dominatingspecies.service;

import cz.muni.fi.pa165.dominatingspecies.dao.AnimalEnvironmentDao;
import cz.muni.fi.pa165.dominatingspecies.dao.EnvironmentDao;
import cz.muni.fi.pa165.dominatingspecies.entity.Animal;
import cz.muni.fi.pa165.dominatingspecies.entity.AnimalEnvironment;
import cz.muni.fi.pa165.dominatingspecies.entity.Environment;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class EnvironmentServiceImpl implements EnvironmentService {

    @Inject
    private EnvironmentDao dao;
    
    @Inject
    private AnimalEnvironmentDao aeDao;
    
    @Override
    public void create(Environment environment) {
        dao.persist(environment);
    }

    @Override
    public Environment findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public Collection<Environment> findAll() {
        return dao.listAll();
    }

    @Override
    public void update(Environment environment) {
        dao.update(environment);
    }

    @Override
    public void remove(Environment environment) {
        dao.delete(environment);
    }

    @Override
    public Collection<Environment> findEnvironmentsForAnimal(Animal animal) {
        List<AnimalEnvironment> animalEnvironments = aeDao.findByAnimal(animal);
        List<Environment> environments = new ArrayList<>(animalEnvironments.size());
        
        for (AnimalEnvironment ae : animalEnvironments) {
            environments.add(ae.getEnvironment());
        }
        
        return environments;
    }

    @Override
    public Collection<Animal> findAnimalsForEnvironment(Environment environment) {
        List<AnimalEnvironment> animalEnvironments = aeDao.findByEnvironment(environment);
        List<Animal> environments = new ArrayList<>(animalEnvironments.size());
        
        for (AnimalEnvironment ae : animalEnvironments) {
            environments.add(ae.getAnimal());
        }
        
        return environments;
    }
}
