package cz.muni.fi.pa165.dominatingspecies.facade;

import cz.muni.fi.pa165.dominatingspecies.dto.AnimalBriefDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.AnimalDetailDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.AnimalEnvironmentDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.AnimalNewDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.EnvironmentDTO;
import cz.muni.fi.pa165.dominatingspecies.service.config.DominatingSpeciesServiceConfig;
import java.util.Collection;
import javax.inject.Inject;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ivan Kralik
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DominatingSpeciesServiceConfig.class)
@Transactional
public class EnvironmentFacadeImplIntegrationTest {

    @Inject
    @InjectMocks
    private EnvironmentFacade facade;
    
    @Inject
    @InjectMocks
    private AnimalFacade animalFacade;
    
    @Test
    public void testFindAllEnvironments() {
        assertEquals(0, facade.findAllEnvironments().size());
        
        EnvironmentDTO freshWater = createEnvironment("Sladká voda", 10);
        EnvironmentDTO mud = createEnvironment("Bahno", 10);
        
        freshWater.setId(facade.createEnvironment(freshWater));
        mud.setId(facade.createEnvironment(mud));
        
        Collection<EnvironmentDTO> environments = facade.findAllEnvironments();
        
        assertTrue(environments.contains(freshWater));
        assertTrue(environments.contains(mud));
    }

    @Test
    public void testFindEnvironment() {
        assertNull(facade.findEnvironment(987l));
        
        EnvironmentDTO freshWater = createEnvironment("Sladká voda", 10);
        freshWater.setId(facade.createEnvironment(freshWater));
        
        assertEquals(facade.findEnvironment(freshWater.getId()), freshWater);
    }

    @Test
    public void testCreateEnvironment() {
        EnvironmentDTO freshWater = createEnvironment("Sladká voda", 10);
        
        freshWater.setId(facade.createEnvironment(freshWater));
        
        EnvironmentDTO result = facade.findEnvironment(freshWater.getId());
        
        assertEquals(freshWater, result);
        assertEnvironmentDataEquals(freshWater, result);
    }

    @Test
    public void testUpdateEnvironment() {
        EnvironmentDTO freshWater = createEnvironment("Sladká voda", 10);
        
        freshWater.setId(facade.createEnvironment(freshWater));
        
        EnvironmentDTO result = facade.findEnvironment(freshWater.getId());
        
        assertEquals(freshWater, result);
        assertEnvironmentDataEquals(freshWater, result);
        
        freshWater.setName("Slaná voda");
        freshWater.setMaxAnimalCount(20l);
        
        facade.updateEnvironment(freshWater);
        
        assertEnvironmentDataEquals(freshWater, facade.findEnvironment(freshWater.getId()));
    }

    @Test
    public void testDeleteEnvironment() {
        EnvironmentDTO freshWater = createEnvironment("Sladká voda", 10);
        
        freshWater.setId(facade.createEnvironment(freshWater));
        
        assertEquals(freshWater, facade.findEnvironment(freshWater.getId()));
        
        facade.deleteEnvironment(freshWater.getId());
        
        assertNull(facade.findEnvironment(freshWater.getId()));
    }

    @Test
    public void testAddAnimalEnvironment() {
        AnimalNewDTO fish = createNewAnimal("Ryba", "Ryby");
        long createdId = animalFacade.createAnimal(fish);
        assertAnimalDataEquals(fish, animalFacade.findAnimalDetail(createdId));
        
        EnvironmentDTO freshWater = createEnvironment("Sladká voda", 10);
        freshWater.setId(facade.createEnvironment(freshWater));
        assertEnvironmentDataEquals(freshWater, facade.findEnvironment(freshWater.getId()));
        
        assertEquals(0, facade.findAnimalsInEnvironment(freshWater.getId()).size());
        assertEquals(0, facade.findEnvironmentsForAnimal(createdId).size());
        
        AnimalEnvironmentDTO fishFreshWater = createAnimalEnvironment(animalFacade.findAnimalBrief(createdId), freshWater, 1);
        fishFreshWater.setId(facade.addAnimalEnvironment(fishFreshWater));
        
        Collection<AnimalDetailDTO> animalsInFreshWater = facade.findAnimalsInEnvironment(freshWater.getId());
        Collection<EnvironmentDTO> fishEnvironments = facade.findEnvironmentsForAnimal(createdId);
        
        assertEquals(1, animalsInFreshWater.size());
        assertEquals(1, fishEnvironments.size());
        assertTrue(fishEnvironments.contains(freshWater));
    }

    @Test
    public void testRemoveAnimalEnvironment() {
        AnimalNewDTO fish = createNewAnimal("Ryba", "Ryby");
        long createdId = animalFacade.createAnimal(fish);
        
        EnvironmentDTO freshWater = createEnvironment("Sladká voda", 10);
        freshWater.setId(facade.createEnvironment(freshWater));
        
        AnimalEnvironmentDTO fishFreshWater = createAnimalEnvironment(animalFacade.findAnimalBrief(createdId), freshWater, 1);
        fishFreshWater.setId(facade.addAnimalEnvironment(fishFreshWater));
        
        Collection<AnimalDetailDTO> animalsInFreshWater = facade.findAnimalsInEnvironment(freshWater.getId());
        Collection<EnvironmentDTO> fishEnvironments = facade.findEnvironmentsForAnimal(createdId);
        
        assertEquals(1, animalsInFreshWater.size());
        assertEquals(1, fishEnvironments.size());
        assertTrue(fishEnvironments.contains(freshWater));
        
        facade.removeAnimalEnvironment(fishFreshWater.getId());
        
        animalsInFreshWater = facade.findAnimalsInEnvironment(freshWater.getId());
        fishEnvironments = facade.findEnvironmentsForAnimal(createdId);
        
        assertEquals(0, animalsInFreshWater.size());
        assertEquals(0, fishEnvironments.size());
    }

    @Test
    public void testFindAnimalsInEnvironment() {
        AnimalNewDTO fish = createNewAnimal("Ryba", "Ryby");
        long createdId = animalFacade.createAnimal(fish);
        
        EnvironmentDTO freshWater = createEnvironment("Sladká voda", 10);
        freshWater.setId(facade.createEnvironment(freshWater));
        
        Collection<AnimalDetailDTO> animalsInFreshWater = facade.findAnimalsInEnvironment(freshWater.getId());
        
        assertEquals(0, animalsInFreshWater.size());
        
        AnimalEnvironmentDTO fishFreshWater = createAnimalEnvironment(animalFacade.findAnimalBrief(createdId), freshWater, 1);
        fishFreshWater.setId(facade.addAnimalEnvironment(fishFreshWater));
        
        animalsInFreshWater = facade.findAnimalsInEnvironment(freshWater.getId());
        
        assertEquals(1, animalsInFreshWater.size());
    }

    @Test
    public void testFindEnvironmentsForAnimal() {
        AnimalNewDTO fish = createNewAnimal("Ryba", "Ryby");
        long createdId = animalFacade.createAnimal(fish);
        
        EnvironmentDTO freshWater = createEnvironment("Sladká voda", 10);
        freshWater.setId(facade.createEnvironment(freshWater));
        
        Collection<EnvironmentDTO> fishEnvironments = facade.findEnvironmentsForAnimal(createdId);
        
        assertEquals(0, fishEnvironments.size());
        
        AnimalEnvironmentDTO fishFreshWater = createAnimalEnvironment(animalFacade.findAnimalBrief(createdId), freshWater, 1);
        fishFreshWater.setId(facade.addAnimalEnvironment(fishFreshWater));
        
        fishEnvironments = facade.findEnvironmentsForAnimal(createdId);
        
        assertEquals(1, fishEnvironments.size());
        assertTrue(fishEnvironments.contains(freshWater));
    }
    
    private EnvironmentDTO createEnvironment(String name, long maxAnimalCount) {
        EnvironmentDTO environment = new EnvironmentDTO();
        
        environment.setName(name);
        environment.setMaxAnimalCount(maxAnimalCount);
        
        return environment;
    }
    
    private AnimalNewDTO createNewAnimal(String name, String species) {
        return new AnimalNewDTO(name, species);
    }
    
    private AnimalEnvironmentDTO createAnimalEnvironment(AnimalBriefDTO animal, EnvironmentDTO environment, double percentage) {
        AnimalEnvironmentDTO animalEnvironment = new AnimalEnvironmentDTO();
        animalEnvironment.setAnimal(animal);
        animalEnvironment.setEnvironment(environment);
        animalEnvironment.setPercentage(percentage);
        
        return animalEnvironment;
    }
    
    private void assertEnvironmentDataEquals(EnvironmentDTO expected, EnvironmentDTO actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getMaxAnimalCount(), actual.getMaxAnimalCount());
    }
    
    private void assertAnimalDataEquals(AnimalNewDTO expected, AnimalDetailDTO actual) {
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getSpecies(), actual.getSpecies());
    }
}
