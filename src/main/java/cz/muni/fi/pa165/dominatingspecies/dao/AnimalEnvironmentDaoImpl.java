package cz.muni.fi.pa165.dominatingspecies.dao;

import cz.muni.fi.pa165.dominatingspecies.entity.AnimalEnvironment;
import java.util.List;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ivan Kralik
 */
@Named
@Repository
@Transactional
public class AnimalEnvironmentDaoImpl implements AnimalEnvironmentDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void create(AnimalEnvironment environment) {
        em.persist(environment);
    }

    @Override
    public AnimalEnvironment findById(Long id) {
        return em.find(AnimalEnvironment.class, id);
    }

    @Override
    public List<AnimalEnvironment> findAll() {
        return em.createQuery("from AnimalEnvironment ae", AnimalEnvironment.class).getResultList();
    }

    @Override
    public void remove(AnimalEnvironment environment) {
        em.remove(environment);
    }
}
