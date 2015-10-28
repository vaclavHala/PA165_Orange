package cz.muni.fi.pa165.dominatingspecies.dao;

import cz.muni.fi.pa165.dominatingspecies.entity.Environment;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

@Repository
public class JpaEnvironmentDao implements EnvironmentDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void persist(Environment enviro) throws DataAccessException {
        em.persist(enviro);
    }

    @Override
    public Environment findById(Long id) throws DataAccessException {
        return em.find(Environment.class, id);
    }

    @Override
    public void delete(Environment enviro) throws DataAccessException {
        if (!em.contains(enviro)) {
            throw new DataRetrievalFailureException("Entity " + enviro + " does not exist in the persistent storage");
        }
        em.remove(enviro);
    }

    @Override
    public Collection<Environment> listAll() throws DataAccessException {
        return em.createQuery("FROM Environment e", Environment.class).getResultList();
    }

}
