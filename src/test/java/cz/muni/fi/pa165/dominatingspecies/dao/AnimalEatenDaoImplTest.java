package cz.muni.fi.pa165.dominatingspecies.dao;

/**
 * Created by Petr Domkař on 28. 10. 2015.
 */

import cz.muni.fi.pa165.dominatingspecies.DominatingSpeciesConfig;
import cz.muni.fi.pa165.dominatingspecies.entity.Animal;
import cz.muni.fi.pa165.dominatingspecies.entity.AnimalEaten;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.annotations.BeforeMethod;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * @author Petr Domkař
 */

@Transactional
@ContextConfiguration(classes = DominatingSpeciesConfig.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class AnimalEatenDaoImplTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    public EntityManager em;

    private Animal a1;
    private Animal a2;
    private Animal a3;
    private Animal a4;

    private AnimalEaten ae1;
    private AnimalEaten ae2;
    private AnimalEaten ae3;


    @BeforeMethod
    public void createEntities() {
        a1 = new Animal("Slon", "Savec");
        a2 = new Animal("Veverka", "Savec");
        a3 = new Animal("Štika", "Ryba");
        a4 = new Animal("Nosorožec", "Savec");
        ae1 = new AnimalEaten(a1, a2);
    }



}
