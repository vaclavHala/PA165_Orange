package cz.muni.fi.pa165.dominatingspecies.service;

import cz.muni.fi.pa165.dominatingspecies.dao.AnimalDao;
import cz.muni.fi.pa165.dominatingspecies.entity.Animal;
import cz.muni.fi.pa165.dominatingspecies.service.config.DominatingSpeciesServiceConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author hala
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DominatingSpeciesServiceConfig.class)

public class AnimalServiceTest {

    @Mock
    private AnimalDao animalDao;

    @Autowired
    @InjectMocks
    private AnimalService animalService;

    private Animal cat;
    private Animal dog;
    private Animal fish;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        cat = new Animal("Cat", "Cats");
        dog = new Animal("Dog", "Dogs");
        fish = new Animal("Fish", "Fish");
    }

}
