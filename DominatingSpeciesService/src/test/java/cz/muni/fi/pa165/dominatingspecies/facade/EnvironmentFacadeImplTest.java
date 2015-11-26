package cz.muni.fi.pa165.dominatingspecies.facade;

import cz.muni.fi.pa165.dominatingspecies.dto.EnvironmentDTO;
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
import org.junit.Test;
import org.junit.Before;
import static org.mockito.Mockito.*;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
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
    public void testUpdateEnvironment() {
        EnvironmentDTO environment = createEnvironmentDTO(1l, "Sladká voda", 10l, "Popis sladkej vody");
        
        facade.updateEnvironment(environment);
        
        verify(environmentService, times(1)).update(captor.capture());
        assertEnvironmentCorrectlyMapped(captor.getValue(), environment);
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
}
