package cz.muni.fi.pa165.dominatingspecies.service;

import cz.muni.fi.pa165.dominatingspecies.dao.EnvironmentDao;
import cz.muni.fi.pa165.dominatingspecies.entity.Environment;
import java.util.Collection;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class EnvironmentServiceImpl implements EnvironmentService {

    @Inject
    private EnvironmentDao dao;
    
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
        // update
    }

    @Override
    public void remove(Environment environment) {
        dao.delete(environment);
    }
    
}
