package cz.muni.fi.pa165.dominatingspecies.dao;

import cz.muni.fi.pa165.dominatingspecies.entity.AnimalEaten;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

/**
 * @author Daniel Minarik
 */
@Repository
public class AnimalEatenDaoImpl implements AnimalEatenDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(AnimalEaten animalEaten) throws DataAccessException {
        AnimalEaten aeByPredPrey = findByAnimalsInvolved(animalEaten.getPredator().getId(), animalEaten.getPrey().getId());
        if (aeByPredPrey == null) {
            em.persist(animalEaten);
        }
    }

    @Override
    public List<AnimalEaten> findAll() throws DataAccessException {
        return em.createQuery("SELECT a FROM AnimalEaten a", AnimalEaten.class).getResultList();
    }

    @Override
    public AnimalEaten findById(long id) throws DataAccessException {
        return em.find(AnimalEaten.class, id);
    }

    @Override
    public void remove(AnimalEaten animalEaten) throws DataAccessException {
        if (!em.contains(animalEaten)) {
            throw new DataRetrievalFailureException("Entity " + animalEaten + " does not exist in the persistent storage");
        }
        em.remove(animalEaten);
    }

    @Override
    public void update(AnimalEaten animalEaten) throws DataAccessException {
        AnimalEaten ae = em.getReference(AnimalEaten.class, animalEaten.getId());
        ae.setAnimalCount(animalEaten.getAnimalCount());
    }

    @Override
    public AnimalEaten findByAnimalsInvolved(long predatorId, long preyId) {
        try {
            return em.createQuery("SELECT ae FROM AnimalEaten ae "
                    + "WHERE ae.predator.id = :predator AND ae.prey.id = :prey", AnimalEaten.class)
                    .setParameter("predator", predatorId)
                    .setParameter("prey", preyId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
