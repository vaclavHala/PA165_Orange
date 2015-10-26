package cz.muni.fi.pa165.dominatingspecies.dao;

import cz.muni.fi.pa165.dominatingspecies.entity.AnimalEaten;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Daniel Minarik
 */

@Repository
public class AnimalEatenDaoImpl implements AnimalEatenDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(AnimalEaten animalEaten) {
        em.persist(animalEaten);
    }

    @Override
    public List<AnimalEaten> findAll() {
        return em.createQuery("SELECT a FROM AnimalEaten a", AnimalEaten.class).getResultList();
    }

    @Override
    public AnimalEaten findById(Long id) {
        return em.find(AnimalEaten.class, id);
    }

    @Override
    public void remove(AnimalEaten animalEaten) {
        em.remove(animalEaten);
    }

    @Override
    public void update(AnimalEaten animalEaten) {
        em.merge(animalEaten);
    }
}
