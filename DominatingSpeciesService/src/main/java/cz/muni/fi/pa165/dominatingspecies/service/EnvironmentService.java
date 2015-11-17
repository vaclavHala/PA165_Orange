package cz.muni.fi.pa165.dominatingspecies.service;

import cz.muni.fi.pa165.dominatingspecies.entity.Environment;
import java.util.Collection;

/**
 * @author Ivan Kralik
 */
public interface EnvironmentService {
    
    public void create(Environment environment);
    
    public Environment findById(Long id);
    
    public Collection<Environment> findAll();
    
    public void update(Environment environment);
    
    public void remove(Environment environment);
}
