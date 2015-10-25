package cz.muni.fi.pa165.dominatingspecies.dao;

import cz.muni.fi.pa165.dominatingspecies.entity.AnimalEnvironment;
import java.util.List;

/**
 * @author Ivan Kralik
 */
public interface AnimalEnvironmentDao {
    
    public void create(AnimalEnvironment environment);
    
    public AnimalEnvironment findById(Long id);
    
    public List<AnimalEnvironment> findAll();
    
    public void remove(AnimalEnvironment environment);
}
