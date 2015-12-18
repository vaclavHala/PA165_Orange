package cz.muni.fi.pa165.dominatingspecies.facade;

import cz.muni.fi.pa165.dominatingspecies.dto.AnimalBriefDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.AnimalDetailDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.AnimalEnvironmentDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.EnvironmentDTO;
import cz.muni.fi.pa165.dominatingspecies.entity.Animal;
import cz.muni.fi.pa165.dominatingspecies.entity.AnimalEnvironment;
import cz.muni.fi.pa165.dominatingspecies.entity.Environment;
import cz.muni.fi.pa165.dominatingspecies.service.AnimalEnvironmentService;
import cz.muni.fi.pa165.dominatingspecies.service.AnimalService;
import cz.muni.fi.pa165.dominatingspecies.service.BeanMappingService;
import cz.muni.fi.pa165.dominatingspecies.service.EnvironmentService;
import cz.muni.fi.pa165.dominatingspecies.service.config.DominatingSpeciesServiceConfig;
import cz.muni.fi.pa165.dominatingspecies.service.facade.EnvironmentFacadeImpl;
import java.util.Arrays;
import java.util.Collection;
import javax.inject.Inject;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Ivan Kralik
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DominatingSpeciesServiceConfig.class)
public class EnvironmentFacadeImplTest {

    @Mock
    private EnvironmentService environmentService;

    @Mock
    private AnimalService animalService;

    @Mock
    private AnimalEnvironmentService aeService;

    @Inject
    private BeanMappingService bmService;

    private EnvironmentFacade facade;

    private ArgumentCaptor<Environment> captor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        facade = new EnvironmentFacadeImpl(environmentService, animalService, aeService, bmService);
        captor = new ArgumentCaptor<>();
    }

    @Test
    public void testFindAllEnvironments() {
        Environment environment = createEnvironment(1l, "Sladká voda", 10l, "Popis sladkej vody");
        Collection<Environment> environments = Arrays.asList(environment);

        when(environmentService.findAll()).thenReturn(environments);

        Collection<EnvironmentDTO> result = facade.findAllEnvironments();

        verify(environmentService, times(1)).findAll();

        Assert.assertEquals(environments.size(), result.size());
        assertEnvironmentCorrectlyMapped(environment, result.toArray(new EnvironmentDTO[result.size()])[0]);
    }

    @Test
    public void testFindEnvironment() {
        Environment environment = createEnvironment(1l, "Sladká voda", 10l, "Popis sladkej vody");

        when(environmentService.findById(1l)).thenReturn(environment);

        Assert.assertNull(facade.findEnvironment(2l));
        assertEnvironmentCorrectlyMapped(environment, facade.findEnvironment(1l));
    }

    @Test
    public void testCreateEnvironment() {
        EnvironmentDTO environment = createEnvironmentDTO(null, "Sladká voda", 10l, "Popis sladkej vody");

        doAnswer(new Answer() {

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((Environment) invocation.getArguments()[0]).setId(1l);
                return null;
            }
        }).when(environmentService).create(any(Environment.class));

        environment.setId(facade.createEnvironment(environment));

        verify(environmentService, times(1)).create(captor.capture());
        assertEnvironmentCorrectlyMapped(captor.getValue(), environment);
    }

    @Test
    public void testDeleteEnvironment() {
        Environment environment = createEnvironment(1l, "Sladká voda", 10l, "Popis sladkej vody");

        when(environmentService.findById(1l)).thenReturn(environment);

        facade.deleteEnvironment(1l);

        verify(environmentService, times(1)).remove(environment);
    }

    @Test
    public void testUpdateEnvironment() {
        EnvironmentDTO environment = createEnvironmentDTO(1l, "Sladká voda", 10l, "Popis sladkej vody");

        facade.updateEnvironment(environment);

        verify(environmentService, times(1)).update(captor.capture());
        assertEnvironmentCorrectlyMapped(captor.getValue(), environment);
    }

    @Test
    public void testAddAnimalEnvironment() {
        AnimalBriefDTO fish = createAnimalDTO(1l, "Ryba", "Ryby");
        EnvironmentDTO environment = createEnvironmentDTO(1l, "Sladká voda", 10l, "Popis sladkej vody");
        AnimalEnvironmentDTO animalEnvironment = createAnimalEnvironmentDTO(fish, environment, 1d);

        facade.addAnimalEnvironment(animalEnvironment);

        verify(aeService, times(1)).create(any(AnimalEnvironment.class));
    }

    @Test
    public void testRemoveAnimalEnvironment() {
        Environment water = createEnvironment(1l, "Sladká voda", 10l, "Popis sladkej vody");
        Animal fish = createAnimal(1l, "Ryba", "Ryby", 1d, 1d);
        AnimalEnvironment fishWater = createAnimalEnvironment(1l, fish, water, 1);

        when(aeService.findById(1l)).thenReturn(fishWater);

        facade.removeAnimalEnvironment(1l);

        verify(aeService, times(1)).remove(fishWater);
    }

    @Test
    public void testFindAnimalsInEnvironment() {
        Environment water = createEnvironment(1l, "Sladká voda", 10l, "Popis sladkej vody");
        Animal fish = createAnimal(1l, "Ryba", "Ryby", 1d, 1d);

        when(environmentService.findById(1l)).thenReturn(water);
        when(environmentService.findAnimalsForEnvironment(water)).thenReturn(Arrays.asList(fish));

        Collection<AnimalBriefDTO> result = facade.findAnimalsInEnvironment(1l);

        assertEquals(1, result.size());
        assertAnimalCorrectlyMapped(fish, result.toArray(new AnimalBriefDTO[result.size()])[0]);
    }

    @Test
    public void testFindEnvironmentsForAnimal() {
        Environment water = createEnvironment(1l, "Sladká voda", 10l, "Popis sladkej vody");
        Animal fish = createAnimal(1l, "Ryba", "Ryby", 1d, 1d);

        when(animalService.findById(1l)).thenReturn(fish);
        when(environmentService.findEnvironmentsForAnimal(fish)).thenReturn(Arrays.asList(water));

        Collection<EnvironmentDTO> result = facade.findEnvironmentsForAnimal(1l);

        assertEquals(1, result.size());
        assertEnvironmentCorrectlyMapped(water, result.toArray(new EnvironmentDTO[1])[0]);
    }

    private Environment createEnvironment(Long id, String name, long maxAnimalCount, String description) {
        Environment environment = new Environment();

        environment.setId(id);
        environment.setName(name);
        environment.setMaxAnimalCount(maxAnimalCount);
        environment.setDescription(description);

        return environment;
    }

    private EnvironmentDTO createEnvironmentDTO(Long id, String name, long maxAnimalCount, String description) {
        EnvironmentDTO environmentDto = new EnvironmentDTO();

        environmentDto.setId(id);
        environmentDto.setName(name);
        environmentDto.setMaxAnimalCount(maxAnimalCount);
        environmentDto.setDescription(description);

        return environmentDto;
    }

    private void assertEnvironmentCorrectlyMapped(Environment expected, EnvironmentDTO actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getMaxAnimalCount(), actual.getMaxAnimalCount());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
    }

    private void assertAnimalCorrectlyMapped(Animal expected, AnimalBriefDTO actual) {
        Assert.assertEquals(expected.getId(), (Long) actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getSpecies(), actual.getSpecies());
    }

    private AnimalBriefDTO createAnimalDTO(Long id, String name, String species) {
        AnimalBriefDTO dto = new AnimalBriefDTO();

        dto.setId(id);
        dto.setName(name);
        dto.setSpecies(species);

        return dto;
    }

    private Animal createAnimal(Long id, String name, String species, double reproductionRate, double foodNeeded) {
        Animal animal = new Animal();

        animal.setId(id);
        animal.setName(name);
        animal.setSpecies(species);
        animal.setReproductionRate(reproductionRate);
        animal.setFoodNeeded(foodNeeded);

        return animal;
    }

    private AnimalEnvironmentDTO createAnimalEnvironmentDTO(AnimalBriefDTO animal, EnvironmentDTO environment, double percentage) {
        AnimalEnvironmentDTO dto = new AnimalEnvironmentDTO();

        dto.setAnimal(animal);
        dto.setEnvironment(environment);
        dto.setPercentage(percentage);

        return dto;
    }

    private AnimalEnvironment createAnimalEnvironment(Long id, Animal animal, Environment environment, double percentage) {
        AnimalEnvironment animalEnvironment = new AnimalEnvironment();

        animalEnvironment.setId(id);
        animalEnvironment.setAnimal(animal);
        animalEnvironment.setEnvironment(environment);
        animalEnvironment.setPercentage(percentage);

        return animalEnvironment;
    }
}
