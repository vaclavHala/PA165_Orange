package cz.muni.fi.pa165.dominatingspecies.dao;

import cz.muni.fi.pa165.dominatingspecies.entity.AnimalEnvironment;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

/**
 * @author Ivan Kralik
 */
@Repository
public class AnimalEnvironmentDaoImpl implements AnimalEnvironmentDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void create(AnimalEnvironment environment) throws DataAccessException {
        em.persist(environment);
    }

    @Override
    public AnimalEnvironment findById(Long id) throws DataAccessException {
        return em.find(AnimalEnvironment.class, id);
    }

    @Override
    public List<AnimalEnvironment> findAll() throws DataAccessException {
        return em.createQuery("from AnimalEnvironment ae", AnimalEnvironment.class).getResultList();
    }

    @Override
    public void remove(AnimalEnvironment environment) throws DataAccessException {
        if (!em.contains(environment)) {
            throw new DataRetrievalFailureException("Entity " + environment + " does not exist in the persistent storage");
        }
        
        em.remove(environment);
    }

    @Override
    public void update(AnimalEnvironment environment) throws DataAccessException {
        em.merge(environment);
    }
}
