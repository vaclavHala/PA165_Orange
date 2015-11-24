package cz.muni.fi.pa165.dominatingspecies.service;

import cz.muni.fi.pa165.dominatingspecies.dao.AnimalEnvironmentDao;
import cz.muni.fi.pa165.dominatingspecies.dao.EnvironmentDao;
import cz.muni.fi.pa165.dominatingspecies.entity.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import cz.muni.fi.pa165.dominatingspecies.entity.Environment;
import cz.muni.fi.pa165.dominatingspecies.entity.AnimalEnvironment;
import cz.muni.fi.pa165.dominatingspecies.service.config.DominatingSpeciesServiceConfig;
import java.util.ArrayList;
import java.util.Collection;
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
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * @author Daniel Minarik
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DominatingSpeciesServiceConfig.class)
public class EnvironmentServiceImplTest {

    @Mock
    private EnvironmentDao environmentDao;

    @Mock
    private AnimalEnvironmentDao animalEnvironmentDao;

    @Autowired
    @InjectMocks
    private EnvironmentService environmentService;

    @Autowired
    @InjectMocks
    private AnimalEnvironmentService animalEnvironmentService;

    @Autowired
    @InjectMocks
    private AnimalService animalService;

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testCreateEnvironment() {
        Environment testEnv = new Environment();
        testEnv.setName("Les");
        testEnv.setDescription("Les");
        testEnv.setMaxAnimalCount(10L);
        environmentService.create(testEnv);
        Environment testEnv2 = new Environment();
        testEnv2.setName("Pust");
        testEnv2.setDescription("Pust");
        testEnv2.setMaxAnimalCount(19L);
        environmentService.create(testEnv2);
        
        verify(environmentDao).persist(testEnv);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNullEnvironment() {
        environmentService.create(null);
        verify(environmentDao, never()).persist(null);
    }
    
    @Test
    public void testRemoveEnvironment() {
        Environment testEnv = new Environment();
        testEnv.setName("Les");
        testEnv.setDescription("Les");
        testEnv.setMaxAnimalCount(10L);
        environmentService.create(testEnv);
        Environment testEnv2 = new Environment();
        testEnv2.setName("Pust");
        testEnv2.setDescription("Pust");
        testEnv2.setMaxAnimalCount(19L);
        environmentService.create(testEnv2);

        Collection<Environment> envs = new ArrayList<>();
        envs.add(testEnv);
        envs.add(testEnv2);

        doReturn(envs).when(environmentDao).listAll();

        Collection<Environment> ret = environmentService.findAll();
        assertNotNull(ret);
        assertEquals(envs.size(), ret.size());

        environmentService.remove(testEnv);
        verify(environmentDao).delete(testEnv);

        Collection<Environment> envs2 = new ArrayList<>();
        envs2.add(testEnv2);
        
        doReturn(envs2).when(environmentDao).listAll();

        Collection<Environment> ret2 = environmentService.findAll();
        assertNotNull(ret2);
        assertEquals(envs2.size(), ret2.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNullEnvironment() {
        environmentService.remove(null);
        verify(environmentDao, never()).delete(null);
    }
    
    @Test
    public void testUpdateEnvironment() {
        Environment testEnv = new Environment();
        testEnv.setName("Les");
        testEnv.setDescription("Les");
        testEnv.setMaxAnimalCount(10L);
        environmentService.create(testEnv);
        testEnv.setName("Lesy");
        environmentService.update(testEnv);
        
        verify(environmentDao).update(testEnv);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNullEnvironment() {
        environmentService.update(null);
        verify(environmentDao, never()).update(null);
    }
    
    @Test
    public void testFindById() {
        Environment testEnv = new Environment();
        testEnv.setName("Les");
        testEnv.setDescription("Les");
        testEnv.setMaxAnimalCount(10L);
        environmentService.create(testEnv);

        doReturn(testEnv).when(environmentDao).findById(1L);
        
        Environment returnedEnv = environmentService.findById(1L);
        verify(environmentDao).findById(1L);
        
        assertNotNull(returnedEnv);
        assertEquals(testEnv, returnedEnv);
    }

    @Test
    public void testFindByNonExistingId() {
        Environment testEnv = new Environment();
        testEnv.setName("Les");
        testEnv.setDescription("Les");
        testEnv.setMaxAnimalCount(10L);
        environmentService.create(testEnv);

        Long realId = 1L;
        Long nonExistingId = 456L;
        doReturn(testEnv).when(environmentDao).findById(realId);
        
        Environment returnedEnv = environmentService.findById(nonExistingId);
        verify(environmentDao).findById(nonExistingId);
        
        assertNull(returnedEnv);
    }

    @Test
    public void testFindAll() {
        Environment testEnv = new Environment();
        testEnv.setName("Les");
        testEnv.setDescription("Les");
        testEnv.setMaxAnimalCount(10L);
        environmentService.create(testEnv);
        Environment testEnv2 = new Environment();
        testEnv2.setName("Pust");
        testEnv2.setDescription("Pust");
        testEnv2.setMaxAnimalCount(19L);
        environmentService.create(testEnv2);
        Environment testEnv3 = new Environment();
        testEnv3.setName("Step");
        testEnv3.setDescription("Step");
        testEnv3.setMaxAnimalCount(4L);
        environmentService.create(testEnv3);

        Collection<Environment> envs = new ArrayList<>();
        envs.add(testEnv);
        envs.add(testEnv2);
        envs.add(testEnv3);
                
        doReturn(envs).when(environmentDao).listAll();
        
        Collection<Environment> ret = environmentService.findAll();
        assertNotNull(ret);
        assertEquals(envs, ret);
        assertTrue(ret.contains(testEnv));
    }
    
    @Test
    public void testFindAllWhenEmpty() {
        Collection<Environment> envs = new ArrayList<>();
                
        doReturn(envs).when(environmentDao).listAll();
        
        Collection<Environment> ret = environmentService.findAll();
        assertNotNull(ret);
        assertEquals(envs, ret);
    }

    @Test
    public void testFindEnvironmentsForAnimal() {
        Animal animal = new Animal("Zajac", "Hlodavec");
        animalService.create(animal);
        Environment environment = new Environment();
        environment.setName("Lesy");
        environment.setDescription("popis");
        environment.setMaxAnimalCount(56L);
        environmentService.create(environment);
        Environment environment2 = new Environment();
        environment2.setName("Luky");
        environment2.setDescription("popis");
        environment2.setMaxAnimalCount(20L);
        environmentService.create(environment2);

        Collection<Environment> envs = new ArrayList<>();
        envs.add(environment);
        envs.add(environment2);

        AnimalEnvironment animalEnv = new AnimalEnvironment(animal, environment);
        animalEnvironmentService.create(animalEnv);
        AnimalEnvironment animalEnv2 = new AnimalEnvironment(animal, environment2);
        animalEnvironmentService.create(animalEnv2);

        Collection<AnimalEnvironment> anEnvs = new ArrayList<>();
        anEnvs.add(animalEnv);
        anEnvs.add(animalEnv2);

        doReturn(anEnvs).when(animalEnvironmentDao).findByAnimal(animal);
        
        Collection<Environment> ret = environmentService.findEnvironmentsForAnimal(animal);
        assertNotNull(ret);
        assertEquals(envs, ret);
    }

    @Test
    public void testFindEnvironmentsForUnusedAnimal() {
        Animal animal = new Animal("Zajac", "Hlodavec");
        animalService.create(animal);
        Animal unusedAnimal = new Animal("Kralik", "Hlodavec");
        animalService.create(unusedAnimal);
        Environment environment = new Environment();
        environment.setName("Lesy");
        environment.setDescription("popis");
        environment.setMaxAnimalCount(56L);
        environmentService.create(environment);
        Environment environment2 = new Environment();
        environment2.setName("Luky");
        environment2.setDescription("popis");
        environment2.setMaxAnimalCount(20L);
        environmentService.create(environment2);

        AnimalEnvironment animalEnv = new AnimalEnvironment(animal, environment);
        animalEnvironmentService.create(animalEnv);
        AnimalEnvironment animalEnv2 = new AnimalEnvironment(animal, environment2);
        animalEnvironmentService.create(animalEnv2);

        Collection<AnimalEnvironment> anEnvs = new ArrayList<>();

        doReturn(anEnvs).when(animalEnvironmentDao).findByAnimal(unusedAnimal);
        
        Collection<Environment> ret = environmentService.findEnvironmentsForAnimal(unusedAnimal);
        assertNotNull(ret);
        assertEquals(ret.size(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindEnvironmentsForNullAnimal() {
        environmentService.findEnvironmentsForAnimal(null);
        verify(animalEnvironmentDao, never()).findByAnimal(null);
    }

    @Test
    public void testFindAnimalsForEnvironment() {
        Animal animal = new Animal("Zajac", "Hlodavec");
        animalService.create(animal);
        Animal animal2 = new Animal("Kralik", "Hlodavec");
        animalService.create(animal2);
        
        Environment environment = new Environment();
        environment.setName("Lesy");
        environment.setDescription("popis");
        environment.setMaxAnimalCount(56L);
        environmentService.create(environment);

        Collection<Animal> animals = new ArrayList<>();
        animals.add(animal);
        animals.add(animal2);

        AnimalEnvironment animalEnv = new AnimalEnvironment(animal, environment);
        animalEnvironmentService.create(animalEnv);
        AnimalEnvironment animalEnv2 = new AnimalEnvironment(animal2, environment);
        animalEnvironmentService.create(animalEnv2);

        Collection<AnimalEnvironment> anEnvs = new ArrayList<>();
        anEnvs.add(animalEnv);
        anEnvs.add(animalEnv2);

        doReturn(anEnvs).when(animalEnvironmentDao).findByEnvironment(environment);
        
        Collection<Animal> ret = environmentService.findAnimalsForEnvironment(environment);
        assertNotNull(ret);
        assertEquals(animals, ret);
    }

    @Test
    public void testFindAnimalsForUnusedEnvironment() {
        Animal animal = new Animal("Zajac", "Hlodavec");
        animalService.create(animal);
        Animal animal2 = new Animal("Kralik", "Hlodavec");
        animalService.create(animal2);
        
        Environment environment = new Environment();
        environment.setName("Lesy");
        environment.setDescription("popis");
        environment.setMaxAnimalCount(56L);
        environmentService.create(environment);
        Environment unusedEnv = new Environment();
        unusedEnv.setName("Luky");
        unusedEnv.setDescription("popis");
        unusedEnv.setMaxAnimalCount(20L);
        environmentService.create(unusedEnv);

        AnimalEnvironment animalEnv = new AnimalEnvironment(animal, environment);
        animalEnvironmentService.create(animalEnv);
        AnimalEnvironment animalEnv2 = new AnimalEnvironment(animal2, environment);
        animalEnvironmentService.create(animalEnv2);

        Collection<AnimalEnvironment> anEnvs = new ArrayList<>();

        doReturn(anEnvs).when(animalEnvironmentDao).findByEnvironment(unusedEnv);
        
        Collection<Animal> ret = environmentService.findAnimalsForEnvironment(unusedEnv);
        assertNotNull(ret);
        assertEquals(ret.size(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindAnimalsForNullEnvironment() {
        environmentService.findAnimalsForEnvironment(null);
        verify(animalEnvironmentDao, never()).findByEnvironment(null);
    }

}
