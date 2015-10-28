package cz.muni.fi.pa165.dominatingspecies.dao;

import cz.muni.fi.pa165.dominatingspecies.entity.Animal;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * Created by Petr Domkař on 28. 10. 2015.
 */
@Repository
public class AnimalDaoImpl implements AnimalDao {

    @PersistenceContext //DI
    private EntityManager em;
    @Override
    public void create(Animal animal) throws DataAccessException {
        em.persist(animal);
    }

    @Override
    public List<Animal> findAll() throws DataAccessException {
        return em.createQuery("SELECT a FROM Animal a", Animal.class).getResultList();
    }

    @Override
    public Animal getById(Long id) throws DataAccessException {
        return em.find(Animal.class, id);
    }

    @Override
    public void remove(Animal animal) throws DataAccessException {
        em.remove(animal);
    }
}
