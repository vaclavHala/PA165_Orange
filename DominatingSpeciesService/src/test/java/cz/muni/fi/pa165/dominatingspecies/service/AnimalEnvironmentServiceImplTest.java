package cz.muni.fi.pa165.dominatingspecies.service;

import cz.muni.fi.pa165.dominatingspecies.dao.AnimalEnvironmentDao;
import cz.muni.fi.pa165.dominatingspecies.entity.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import cz.muni.fi.pa165.dominatingspecies.entity.AnimalEnvironment;
import cz.muni.fi.pa165.dominatingspecies.entity.Environment;
import cz.muni.fi.pa165.dominatingspecies.service.config.DominatingSpeciesServiceConfig;
import java.util.ArrayList;
import java.util.Collection;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doReturn;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Daniel Minarik
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DominatingSpeciesServiceConfig.class)
public class AnimalEnvironmentServiceImplTest {

    @Mock
    private AnimalEnvironmentDao animalEnvironmentDao;

    @Autowired
    @InjectMocks
    private AnimalEnvironmentService animalEnvironmentService;
    
    private Animal animal;
    private Animal animal2;
    private Animal animal3;
    private Environment environment;
    private Environment environment2;
    private Environment environment3;
    
    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
        animal = new Animal("Zajac", "Hlodavec");
        animal2 = new Animal("Mys", "Hlodavec");
        animal3 = new Animal("Kapor", "Ryba");
        environment = new Environment();
        environment.setName("Lesy");
        environment.setDescription("popis");
        environment.setMaxAnimalCount(56L);
        environment2 = new Environment();
        environment2.setName("Hory");
        environment2.setDescription("popis");
        environment2.setMaxAnimalCount(10L);
        environment3 = new Environment();
        environment3.setName("Pust");
        environment3.setDescription("popis");
        environment3.setMaxAnimalCount(99L);
    }
    
    @Test
    public void testCreateAnimalEnvironment() {
        AnimalEnvironment testAnimalEnv = new AnimalEnvironment(animal, environment);
        testAnimalEnv.setPercentage(35);
        animalEnvironmentService.create(testAnimalEnv);
        
        verify(animalEnvironmentDao).create(testAnimalEnv);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNullAnimalEnvironment() {
        animalEnvironmentService.create(null);
        verify(animalEnvironmentDao, never()).create(null);
    }

    @Test
    public void testRemoveAnimalEnvironment() {
        AnimalEnvironment testAnimalEnv = new AnimalEnvironment(animal, environment);
        testAnimalEnv.setPercentage(35);
        animalEnvironmentService.create(testAnimalEnv);
        AnimalEnvironment testAnimalEnv2 = new AnimalEnvironment(animal2, environment2);
        testAnimalEnv.setPercentage(5);
        animalEnvironmentService.create(testAnimalEnv2);

        Collection<AnimalEnvironment> envs = new ArrayList<>();
        envs.add(testAnimalEnv);
        envs.add(testAnimalEnv2);

        doReturn(envs).when(animalEnvironmentDao).findAll();

        Collection<AnimalEnvironment> ret = animalEnvironmentService.findAll();
        assertNotNull(ret);
        assertEquals(envs.size(), ret.size());

        animalEnvironmentService.remove(testAnimalEnv);
        verify(animalEnvironmentDao).remove(testAnimalEnv);

        Collection<AnimalEnvironment> envs2 = new ArrayList<>();
        envs2.add(testAnimalEnv2);
        
        doReturn(envs2).when(animalEnvironmentDao).findAll();

        Collection<AnimalEnvironment> ret2 = animalEnvironmentService.findAll();
        assertNotNull(ret2);
        assertEquals(envs2.size(), ret2.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNullAnimalEnvironment() {
        animalEnvironmentService.remove(null);
        verify(animalEnvironmentDao, never()).remove(null);
    }

    @Test
    public void testUpdateAnimalEnvironment() {
        AnimalEnvironment testAnimalEnv = new AnimalEnvironment(animal, environment);
        testAnimalEnv.setPercentage(35);
        animalEnvironmentService.update(testAnimalEnv);
        
        verify(animalEnvironmentDao).update(testAnimalEnv);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNullAnimalEnvironment() {
        animalEnvironmentService.update(null);
        verify(animalEnvironmentDao, never()).update(null);
    }

    @Test
    public void testFindById() {
        AnimalEnvironment testAnimalEnv = new AnimalEnvironment(animal, environment);
        testAnimalEnv.setPercentage(35);
        animalEnvironmentService.create(testAnimalEnv);

        doReturn(testAnimalEnv).when(animalEnvironmentDao).findById(1L);
        
        AnimalEnvironment returnedEnv = animalEnvironmentService.findById(1L);
        verify(animalEnvironmentDao).findById(1L);
        
        assertNotNull(returnedEnv);
        assertEquals(testAnimalEnv, returnedEnv);
    }

    @Test
    public void testFindByNonExistingId() {
        AnimalEnvironment testAnimalEnv = new AnimalEnvironment(animal, environment);
        testAnimalEnv.setPercentage(35);
        animalEnvironmentService.create(testAnimalEnv);

        Long realId = 1L;
        Long nonExistingId = 456L;
        doReturn(testAnimalEnv).when(animalEnvironmentDao).findById(realId);
        
        AnimalEnvironment returnedEnv = animalEnvironmentService.findById(nonExistingId);
        verify(animalEnvironmentDao).findById(nonExistingId);
        
        assertNull(returnedEnv);
    }

    @Test
    public void testFindAll() {
        AnimalEnvironment testAnimalEnv = new AnimalEnvironment(animal, environment);
        testAnimalEnv.setPercentage(35);
        animalEnvironmentService.create(testAnimalEnv);
        AnimalEnvironment testAnimalEnv2 = new AnimalEnvironment(animal2, environment2);
        testAnimalEnv2.setPercentage(90);
        animalEnvironmentService.create(testAnimalEnv2);
        AnimalEnvironment testAnimalEnv3 = new AnimalEnvironment(animal3, environment3);
        testAnimalEnv3.setPercentage(5);
        animalEnvironmentService.create(testAnimalEnv3);

        Collection<AnimalEnvironment> envs = new ArrayList<>();
        envs.add(testAnimalEnv);
        envs.add(testAnimalEnv2);
        envs.add(testAnimalEnv3);
                
        doReturn(envs).when(animalEnvironmentDao).findAll();
        
        Collection<AnimalEnvironment> ret = animalEnvironmentService.findAll();
        assertNotNull(ret);
        assertEquals(envs, ret);
    }

    @Test
    public void testFindAllWhenEmpty() {
        Collection<AnimalEnvironment> envs = new ArrayList<>();
                
        doReturn(envs).when(animalEnvironmentDao).findAll();
        
        Collection<AnimalEnvironment> ret = animalEnvironmentService.findAll();
        assertNotNull(ret);
        assertEquals(envs, ret);
    }
}
