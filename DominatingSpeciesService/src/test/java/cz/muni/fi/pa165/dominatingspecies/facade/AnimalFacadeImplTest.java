package cz.muni.fi.pa165.dominatingspecies.facade;

import cz.muni.fi.pa165.dominatingspecies.dto.AnimalBriefDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.AnimalDetailDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.AnimalEatenDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.AnimalNewDTO;
import cz.muni.fi.pa165.dominatingspecies.entity.Animal;
import cz.muni.fi.pa165.dominatingspecies.entity.AnimalEaten;
import cz.muni.fi.pa165.dominatingspecies.service.AnimalEatenService;
import cz.muni.fi.pa165.dominatingspecies.service.AnimalEnvironmentService;
import cz.muni.fi.pa165.dominatingspecies.service.AnimalService;
import cz.muni.fi.pa165.dominatingspecies.service.BeanMappingService;
import cz.muni.fi.pa165.dominatingspecies.service.config.DominatingSpeciesServiceConfig;
import cz.muni.fi.pa165.dominatingspecies.service.facade.AnimalFacadeImpl;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.ContextConfiguration;

/**
 *
 * @author Petr
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {DominatingSpeciesServiceConfig.class})
public class AnimalFacadeImplTest {

    @Mock
    private AnimalService animalService;

    @Mock
    private AnimalEatenService animalEatenService;

    @Mock
    private AnimalEnvironmentService animalEnvironmentService;

    @Mock
    private BeanMappingService beanMappingService;

    @InjectMocks
    private AnimalFacade animalFacade
            = new AnimalFacadeImpl();

    private static final long ID_1 = 1L;
    private static final long ID_2 = 2L;
    Animal a1;
    Animal a2;
    AnimalBriefDTO aB1;
    AnimalBriefDTO aB2;
    AnimalNewDTO aNewDTO;
    AnimalEaten ae1;
    AnimalEaten ae2;
    AnimalEatenDTO aeDTO1;
    AnimalEatenDTO aeDTO2;
    AnimalDetailDTO aDetailDTO1;

    List<AnimalEaten> listPredators;
    List<AnimalEaten> listPrey;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        aB1 = new AnimalBriefDTO(ID_1, "žirafa", "savec");
        aB2 = new AnimalBriefDTO(ID_2, "brouk", "savec");

        a1 = new Animal("žirafa", "savec");
        a1.setId(ID_1);
        a2 = new Animal("brouk", "savec");
        a2.setId(ID_2);

        ae1 = new AnimalEaten(a1, a2);
        ae1.setId(ID_1);

        ae2 = new AnimalEaten(a2, a1);
        ae2.setId(ID_1);

        aeDTO1 = new AnimalEatenDTO(ID_1, aB1, aB2);
        aeDTO2 = new AnimalEatenDTO(ID_1, aB2, aB1);

        aNewDTO = new AnimalNewDTO("žirafa", "savec");

        aDetailDTO1 = new AnimalDetailDTO(ID_1, "žirafa", "savec");
        List<AnimalEatenDTO> predators = new ArrayList<>();
        predators.add(aeDTO2);
        aDetailDTO1.setPredators(predators);
        List<AnimalEatenDTO> prey = new ArrayList<>();
        prey.add(aeDTO1);
        aDetailDTO1.setPrey(prey);

        listPredators = new ArrayList<>();
        listPredators.add(ae2);

        listPrey = new ArrayList<>();
        listPrey.add(ae1);
    }

    @Test
    public void createAnimalTest() {
        assertThat(createAnimalOnAnimalFacade())
                .isNotNull()
                .isEqualTo(ID_1);

        verify(beanMappingService).map(aNewDTO, Animal.class);
        verify(animalService).create(a1);
    }

    @Test
    public void findAllanimalsTest() {
        animalFacade.findAllAnimals();
        verify(animalService).findAll();
    }

    @Test
    public void testFindAnimalBrief() {
        when(beanMappingService.map(a1, AnimalBriefDTO.class)).thenReturn(aB1);
        animalFacade.findAnimalBrief(ID_1);
        verify(animalService).findById(ID_1);
    }

    @Test
    public void testFindAnimalDetail() {
        when(animalService.findById(ID_1)).thenReturn(a1);
        when(animalEatenService.findPredatorsOf(a1)).thenReturn(listPredators);
        when(animalEatenService.findPreyOf(a1)).thenReturn(listPrey);
        aDetailDTO1.setPredators(null);
        aDetailDTO1.setPrey(null);
        when(beanMappingService.map(a1, AnimalDetailDTO.class)).thenReturn(aDetailDTO1);

        //Mock pro AEDTO1
        List<AnimalEaten> listAnimalEatens = new ArrayList<>();
        listAnimalEatens.add(ae1);
        List<AnimalEatenDTO> listAnimalEatenDTOs = new ArrayList<>();
        listAnimalEatenDTOs.add(aeDTO1);
        when(beanMappingService.map(listAnimalEatens, AnimalEatenDTO.class)).thenReturn(listAnimalEatenDTOs);
        //Mock pro AEDTO2
        List<AnimalEaten> listAnimalEatens2 = new ArrayList<>();
        listAnimalEatens2.add(ae2);
        List<AnimalEatenDTO> listAnimalEatenDTOs2 = new ArrayList<>();
        listAnimalEatenDTOs2.add(aeDTO2);
        when(beanMappingService.map(listAnimalEatens2, AnimalEatenDTO.class)).thenReturn(listAnimalEatenDTOs2);

        AnimalDetailDTO returnedADDTO = animalFacade.findAnimalDetail(ID_1);
        assertEquals(aDetailDTO1.getName(), returnedADDTO.getName());
        assertEquals(aDetailDTO1.getSpecies(), returnedADDTO.getSpecies());

        assertEquals(listPredators.size(), returnedADDTO.getPredators().size());
        assertEquals(true, returnedADDTO.getPredators().contains(aeDTO2));

        assertEquals(listPrey.size(), returnedADDTO.getPrey().size());
        assertEquals(true, returnedADDTO.getPrey().contains(aeDTO1));
    }

    @Test
    public void testUpdateAnimal() {
        when(beanMappingService.map(aDetailDTO1, Animal.class)).thenReturn(a1);
        animalFacade.updateAnimal(aDetailDTO1);
        verify(beanMappingService).map(aDetailDTO1, Animal.class);
        verify(animalService).update(a1);
    }

    @Test
    public void testDeleteAnimal() {
        when(animalService.findById(ID_1)).thenReturn(a1);
        animalFacade.deleteAnimal(ID_1);
        verify(animalService).remove(a1);
        verify(animalEatenService).removeAllFor(a1);
    }

    @Test
    public void addAnimalEaten() {
        assertThat(createAnimalEatenOnAnimalFacade())
                .isNotNull()
                .isEqualTo(ID_1);
        verify(animalEatenService).createAnimalEaten(ae1);
    }

    @Test
    @Ignore
    public void testUpdateAnimalEaten() {
//        when(beanMappingService.map(aeDTO1, AnimalEaten.class)).thenReturn(ae1);
//        animalFacade.updateAnimalEaten(aeDTO1);
//        verify(animalEatenService).update(ae1);
    }

    @Test
    public void testDeleteAnimalEaten() {
        when(animalEatenService.findById(ID_1)).thenReturn(ae1);
        animalFacade.deleteAnimalEaten(ID_1);
        verify(animalEatenService).remove(ae1);

    }

    private Long createAnimalOnAnimalFacade() {
        doReturn(a1).when(beanMappingService).map(aNewDTO, Animal.class);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((Animal) invocation.getArguments()[0]).setId(ID_1);
                return null;
            }
        }).when(animalService).create(a1);

        return animalFacade.createAnimal(aNewDTO);
    }

    private Long createAnimalEatenOnAnimalFacade() {
        doReturn(ae1).when(beanMappingService).map(aNewDTO, Animal.class);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((AnimalEaten) invocation.getArguments()[0]).setId(ID_1);
                return null;
            }
        }).when(animalEatenService).createAnimalEaten(ae1);
        when(animalService.findById(ID_1)).thenReturn(a1);
        when(animalService.findById(ID_2)).thenReturn(a2);
        return animalFacade.createAnimalEaten(ID_1, ID_2);
    }

}
