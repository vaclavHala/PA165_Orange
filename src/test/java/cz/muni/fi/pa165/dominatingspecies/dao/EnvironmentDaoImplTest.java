package cz.muni.fi.pa165.dominatingspecies.dao;

import cz.muni.fi.pa165.dominatingspecies.DominatingSpeciesConfig;
import cz.muni.fi.pa165.dominatingspecies.entity.Environment;
import java.util.Collection;
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
 * @author Daniel Minarik
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DominatingSpeciesConfig.class})
public class EnvironmentDaoImplTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Inject
    private EnvironmentDao dao;

    @Test
    public void testEnvironmentCreated() {
        Environment e = new Environment();
        e.setName("env1");
        e.setDescription("desc1");
        e.setMaxAnimalConut(10L);
        dao.persist(e);

        assertEquals(e, dao.findById(e.getId()));
    }

    @Test
    public void testFindByWrongId() {
        assertEquals(null, dao.findById(1000L));
    }

    @Test
    public void testCreateWithMissingData() {
        Environment e = new Environment();

        expectedException.expect(DataAccessException.class);
        dao.persist(e);
    }

    @Test
    public void testListAllEmptyOnInit() {
        assertTrue(dao.listAll().isEmpty());
    }

    @Test
    public void testListAll() {
        Environment e1 = new Environment();
        e1.setName("env1");
        e1.setDescription("desc1");
        e1.setMaxAnimalConut(1L);
        Environment e2 = new Environment();
        e2.setName("env2");
        e2.setDescription("desc2");
        e2.setMaxAnimalConut(19L);
        dao.persist(e1);
        dao.persist(e2);

        Collection<Environment> all = dao.listAll();
        assertTrue(all.contains(e1));
        assertTrue(all.contains(e2));
    }

    @Test
    public void testDeleteEnvironment() {
        Environment e1 = new Environment();
        e1.setName("env1");
        e1.setDescription("desc1");
        e1.setMaxAnimalConut(1L);
        dao.persist(e1);
        dao.delete(e1);
        assertTrue(dao.listAll().isEmpty());
    }

    @Test
    public void testDeleteNonExistingEnvironment() {
        Environment e1 = new Environment();
        e1.setName("env1");
        e1.setDescription("desc1");
        e1.setMaxAnimalConut(1L);
        expectedException.expect(DataAccessException.class);
        dao.delete(e1);
    }
}
