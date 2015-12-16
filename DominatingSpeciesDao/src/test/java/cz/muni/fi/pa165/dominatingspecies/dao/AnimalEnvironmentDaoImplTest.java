package cz.muni.fi.pa165.dominatingspecies.dao;

import cz.muni.fi.pa165.dominatingspecies.DominatingSpeciesConfig;
import cz.muni.fi.pa165.dominatingspecies.entity.Animal;
import cz.muni.fi.pa165.dominatingspecies.entity.AnimalEnvironment;
import cz.muni.fi.pa165.dominatingspecies.entity.Environment;
import java.util.Collection;
import javax.inject.Inject;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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
public class AnimalEnvironmentDaoImplTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Inject
    private AnimalEnvironmentDao dao;

    @Inject
    private AnimalDao animalDao;
    @Inject
    private EnvironmentDao enviroDao;

    private Animal dog;
    private Animal cat;
    private Environment enviro;

    @Before
    public void initDb() {
        this.dog = new Animal("dog", "dogs");
        this.cat = new Animal("cat", "cats");
        this.enviro = new Environment();
        this.enviro.setName("house");
        this.enviro.setMaxAnimalCount(100L);
        this.animalDao.create(dog);
        this.animalDao.create(cat);
        this.enviroDao.persist(enviro);
    }

    @Test
    public void testAnimalEnvironmentPersistedByCreate() {
        AnimalEnvironment ae = new AnimalEnvironment();
        ae.setAnimal(dog);
        ae.setEnvironment(enviro);
        dao.create(ae);

        assertEquals(ae, dao.findById(ae.getId()));
    }

    @Test
    public void testFindByIdNotExisting() {
        assertEquals(null, dao.findById(100L));
    }

    @Test
    public void testFindByIdAnimalEnvironment() {

        AnimalEnvironment aeFirst = new AnimalEnvironment();
        aeFirst.setAnimal(dog);
        aeFirst.setEnvironment(enviro);
        dao.create(aeFirst);

        AnimalEnvironment returned = dao.findByIdAnimalEnvironment(dog.getId(), enviro.getId());
        assertEquals(aeFirst, returned);
    }

    @Test
    public void testCreateWithExistingIdThrows() {
        AnimalEnvironment ae = new AnimalEnvironment();
        ae.setAnimal(dog);
        ae.setEnvironment(enviro);
        ae.setId(13);

        expectedException.expect(DataAccessException.class);
        dao.create(ae);
    }

    @Test
    public void testCreateWithMissingRequiredField() {
        AnimalEnvironment ae = new AnimalEnvironment();
        ae.setAnimal(dog);
        ae.setEnvironment(null);

        expectedException.expect(NullPointerException.class);
        dao.create(ae);
    }

    @Test
    public void testIgnoredOnSameAnimaEnvironmentTwice() {
        Animal a = dog;
        Environment e = enviro;
        AnimalEnvironment aeFirst = new AnimalEnvironment(a, e);
        AnimalEnvironment aeSecond = new AnimalEnvironment(a, e);

        dao.create(aeFirst);
        dao.create(aeSecond);
        assertEquals(1, dao.findAll().size());
    }

    @Test
    public void testDbInitiallyEmpty() {
        assertTrue(dao.findAll().isEmpty());
    }

    @Test
    public void testFindAllReturnsAllRecords() {
        AnimalEnvironment aeFirst = new AnimalEnvironment();
        aeFirst.setAnimal(dog);
        aeFirst.setEnvironment(enviro);
        AnimalEnvironment aeSecond = new AnimalEnvironment();
        aeSecond.setAnimal(cat);
        aeSecond.setEnvironment(enviro);
        dao.create(aeFirst);
        dao.create(aeSecond);

        Collection<AnimalEnvironment> returned = dao.findAll();
        assertEquals(2, returned.size());
        assertTrue(returned.contains(aeFirst));
        assertTrue(returned.contains(aeSecond));
    }

    @Test
    public void testDeleteRemovesEntity() {
        AnimalEnvironment ae = new AnimalEnvironment();
        ae.setAnimal(dog);
        ae.setEnvironment(enviro);
        dao.create(ae);
        dao.remove(ae);
        assertTrue(dao.findAll().isEmpty());
    }

    @Test
    public void testDeleteNonExistentEntityFails() {
        AnimalEnvironment aeNotInDb = new AnimalEnvironment();
        aeNotInDb.setId(10L);
        expectedException.expect(DataAccessException.class);
        dao.remove(aeNotInDb);
    }

    @Test
    public void testDeleteEntityWithoutIdFails() {
        AnimalEnvironment aeNotInDb = new AnimalEnvironment();
        expectedException.expect(DataAccessException.class);
        dao.remove(aeNotInDb);
    }
}
