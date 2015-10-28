package cz.muni.fi.pa165.dominatingspecies.dao;

import cz.muni.fi.pa165.dominatingspecies.DominatingSpeciesConfig;
import cz.muni.fi.pa165.dominatingspecies.entity.Animal;
import java.util.Collection;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Vaclav Hala 422551
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DominatingSpeciesConfig.class})
public class AnimalDaoImplTest {

    @Inject
    private AnimalDao dao;

    @Test
    public void testCreate() {
        Animal animal = createAnimal("Mačka domáca", "Mačky", 100d, 20d);

        dao.create(animal);
        
        assertEquals(animal, dao.getById(animal.getId()));
    }

    @Test
    public void testFindByIdNotExisting() {
        assertEquals(null, dao.getById(1235647l));
    }

    @Test(expected = DataAccessException.class)
    public void testCreateWithExistingIdException() {
        Animal animal = createAnimal("Mačka domáca", "Mačky", 100d, 20d);
        animal.setId(1l);
        
        dao.create(animal);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testCreateInvalid() {
        Animal animal1 = createAnimal(null, null, 100d, 20d);
        Animal animal2 = createAnimal(null, "Mačky", 100d, 20d);
        Animal animal3 = createAnimal("Mačka domáca", null, 100d, 20d);
        
        dao.create(animal1);
        dao.create(animal2);
        dao.create(animal3);
    }

    @Test
    public void testDbInitiallyEmpty() {
        assertTrue(dao.findAll().isEmpty());
    }

    @Test
    public void testFindAll() {
        Animal cat = createAnimal("Mačka domáca", "Mačky", 100d, 20d);
        Animal dog = createAnimal("Pes", "Psy", 100d, 20d);
        
        dao.create(cat);
        dao.create(dog);

        Collection<Animal> persistentAnimals = dao.findAll();
        
        assertEquals(2, persistentAnimals.size());
        
        assertTrue(persistentAnimals.contains(cat));
        assertTrue(persistentAnimals.contains(dog));
    }

    @Test
    public void testRemove() {
        Animal animal = createAnimal("Mačka domáca", "Mačky", 100d, 20d);

        dao.create(animal);
        
        assertEquals(animal, dao.getById(animal.getId()));
        
        dao.remove(animal);
        
        assertEquals(null, dao.getById(animal.getId()));
        assertTrue(dao.findAll().isEmpty());
    }

    @Test(expected = DataAccessException.class)
    public void testRemoveNonExistentId() {
        Animal animal = createAnimal("Mačka domáca", "Mačky", 100d, 20d);
        animal.setId(123456l);
        
        dao.remove(animal);
    }
    
    private Animal createAnimal(String name, String species, Double foodNeeded, Double reproductionRate) {
        Animal animal = new Animal();
        
        animal.setName(name);
        animal.setFoodNeeded(foodNeeded);
        animal.setReproductionRate(reproductionRate);
        animal.setSpecies(species);
        
        return animal;
    }
}
