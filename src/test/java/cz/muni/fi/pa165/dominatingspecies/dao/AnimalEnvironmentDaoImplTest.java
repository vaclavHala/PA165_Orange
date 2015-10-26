package cz.muni.fi.pa165.dominatingspecies.dao;

import cz.muni.fi.pa165.dominatingspecies.DominatingSpeciesConfig;
import cz.muni.fi.pa165.dominatingspecies.entity.Animal;
import cz.muni.fi.pa165.dominatingspecies.entity.AnimalEnvironment;
import cz.muni.fi.pa165.dominatingspecies.entity.Environment;
import javax.inject.Inject;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DominatingSpeciesConfig.class})
public class AnimalEnvironmentDaoImplTest {

    @Inject
    private AnimalEnvironmentDao dao;

    @Test
    public void testCreate() {
        AnimalEnvironment ae = new AnimalEnvironment();
        Animal a = new Animal();
        Environment e = new Environment();
        ae.setAnimal(new Animal());
        ae.setEnvironment(e);
        dao.create(ae);

        assertEquals(1, dao.findById(ae.getId()));
    }

}
