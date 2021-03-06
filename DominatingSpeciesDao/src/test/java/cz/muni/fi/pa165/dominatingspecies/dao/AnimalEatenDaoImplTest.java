package cz.muni.fi.pa165.dominatingspecies.dao;

/**
 * Created by Petr Domkař on 28. 10. 2015.
 */
import cz.muni.fi.pa165.dominatingspecies.DominatingSpeciesConfig;
import cz.muni.fi.pa165.dominatingspecies.entity.Animal;
import cz.muni.fi.pa165.dominatingspecies.entity.AnimalEaten;
import java.util.List;
import javax.inject.Inject;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Petr Domkař
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DominatingSpeciesConfig.class})
public class AnimalEatenDaoImplTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Inject
    private AnimalDao animalDao;
    @Inject
    private AnimalEatenDao animalEatenDao;

    Animal a1;
    Animal a2;
    Animal a3;

    @Test
    public void testCreateAnimalEatenWithoutFillRequiredData() {
        Animal a1 = new Animal("Slon", "Savec");
        animalDao.create(a1);
        AnimalEaten ae1 = new AnimalEaten(a1, null);
        expectedException.expect(NullPointerException.class);
        animalEatenDao.create(ae1);
    }

    @Test
    public void testCreateAnimalEaten() {
        createAnimal();

        AnimalEaten ae1 = new AnimalEaten(a1, a2);
        assertTrue(animalEatenDao.findAll().isEmpty());
        animalEatenDao.create(ae1);
        assertEquals(animalEatenDao.findAll().size(), 1);
        assertEquals(ae1, animalEatenDao.findById(ae1.getId()));
    }

    @Test
    public void testInitFindAllEmpty() {
        assertTrue(animalEatenDao.findAll().isEmpty());
    }

    @Test
    public void testFindAllAnimalEaten() {
        createAnimal();

        AnimalEaten ae1 = new AnimalEaten(a1, a2);
        AnimalEaten ae2 = new AnimalEaten(a2, a3);
        animalEatenDao.create(ae1);
        animalEatenDao.create(ae2);

        List<AnimalEaten> animalEatenList = animalEatenDao.findAll();

        assertEquals(animalEatenList.size(), 2);
        assertTrue(animalEatenList.contains(ae1));
        assertTrue(animalEatenList.contains(ae2));
    }

    @Test
    public void testFindByNotExistId() {
        assertEquals(null, animalEatenDao.findById(50L));
    }

    @Test
    public void testFindById() {
        createAnimal();

        AnimalEaten ae1 = new AnimalEaten(a1, a2);
        AnimalEaten ae2 = new AnimalEaten(a2, a3);
        animalEatenDao.create(ae1);
        animalEatenDao.create(ae2);

        AnimalEaten animalEaten = animalEatenDao.findById(ae1.getId());
        assertEquals(animalEaten, ae1);
    }

    @Test
    public void testRemoveNotExistingAnimalEaten() {
        createAnimal();

        AnimalEaten ae1 = new AnimalEaten(a1, a2);
        expectedException.expect(DataAccessException.class);
        animalEatenDao.remove(ae1);
    }

    @Test
    public void testRemoveAnimalEaten() {
        createAnimal();

        AnimalEaten ae1 = new AnimalEaten(a1, a2);
        AnimalEaten ae2 = new AnimalEaten(a2, a3);
        animalEatenDao.create(ae1);
        animalEatenDao.create(ae2);

        assertEquals(animalEatenDao.findAll().size(), 2);
        animalEatenDao.remove(ae1);
        assertEquals(animalEatenDao.findAll().size(), 1);
        assertEquals(animalEatenDao.findAll().get(0), ae2);
        animalEatenDao.remove(ae2);
        assertEquals(animalEatenDao.findAll().size(), 0);
    }

    private void createAnimal() {
        a1 = new Animal("Slon", "Savec");
        a2 = new Animal("Štika", "Ryba");
        a3 = new Animal("Veverka", "Savec");
        animalDao.create(a1);
        animalDao.create(a2);
        animalDao.create(a3);
    }
}
