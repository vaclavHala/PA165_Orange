package cz.muni.fi.pa165.dominatingspecies.dao;

import cz.muni.fi.pa165.dominatingspecies.entity.Animal;
import cz.muni.fi.pa165.dominatingspecies.entity.AnimalEnvironment;
import cz.muni.fi.pa165.dominatingspecies.entity.Environment;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
    public void create(AnimalEnvironment ae) throws DataAccessException {
        AnimalEnvironment aeExisting = findByIdAnimalEnvironment(ae.getAnimal().getId(),
                                                                 ae.getEnvironment().getId());
        if (aeExisting == null) {
            em.persist(ae);
        }
    }

    @Override
    public AnimalEnvironment findById(Long id) throws DataAccessException {
        return em.find(AnimalEnvironment.class, id);
    }

    @Override
    public AnimalEnvironment findByIdAnimalEnvironment(long animalId, long envId) throws DataAccessException {
        try {
            return em.createQuery("SELECT ae FROM AnimalEnvironment ae "
                    + "WHERE ae.animal.id=:animal and ae.environment.id=:enviro", AnimalEnvironment.class)
                    .setParameter("animal", animalId)
                    .setParameter("enviro", envId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Collection<AnimalEnvironment> findByAnimalId(long animalId) throws DataAccessException {
        try {
            return em.createQuery("SELECT ae FROM AnimalEnvironment ae "
                    + "WHERE ae.animal.id=:env", AnimalEnvironment.class)
                    .setParameter("env", animalId)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    @Override
    public Collection<AnimalEnvironment> findByEnvironmentId(long envId) throws DataAccessException {
        try {
            return em.createQuery("SELECT ae FROM AnimalEnvironment ae "
                    + "WHERE ae.environment.id=:animal", AnimalEnvironment.class)
                    .setParameter("animal", envId)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
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

    @Override
    public List<AnimalEnvironment> findByAnimal(Animal animal) {
        return em.createQuery("from AnimalEnvironment ae where ae.animal = :animal", AnimalEnvironment.class).setParameter("animal", animal).getResultList();
    }

    @Override
    public List<AnimalEnvironment> findByEnvironment(Environment environment) {
        return em.createQuery("from AnimalEnvironment ae where ae.environment = :environment", AnimalEnvironment.class).setParameter("environment", environment).getResultList();
    }
}
