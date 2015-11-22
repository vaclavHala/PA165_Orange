package cz.muni.fi.pa165.dominatingspecies.facade;

import cz.muni.fi.pa165.dominatingspecies.dto.AnimalBriefDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.AnimalDetailDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.AnimalEatenDTO;
import cz.muni.fi.pa165.dominatingspecies.service.config.DominatingSpeciesServiceConfig;
import static java.util.Arrays.asList;
import java.util.Collection;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Petr
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DominatingSpeciesServiceConfig.class})
public class AnimalFacadeImplTest {

    @Inject
    private AnimalFacade animalFacade;

    @Test
    public void deleteAnimalTest() {
        long animalId = animalFacade.createAnimal(giraffeDTO());
        assertEquals(animalFacade.findAllAnimals().size(), 1);
        animalFacade.deleteAnimal(animalId);

        assertEquals(animalFacade.findAllAnimals().size(), 0);
    }

    @Test
    public void findAllanimalsTest() {
        animalFacade.createAnimal(giraffeDTO());
        animalFacade.createAnimal(dogDTO());

        assertThat(animalFacade.findAllAnimals())
            .containsExactly(giraffeDTO(), dogDTO());
    }

    @Test
    public void findPredatorsOfTest() {
        long giraffeId = animalFacade.createAnimal(giraffeDTO());
        long dogId = animalFacade.createAnimal(dogDTO());
        System.out.println("all" + animalFacade.findAllAnimals());
        AnimalDetailDTO giraffeDetail = animalFacade.findAnimalDetail(giraffeId);
        Collection<AnimalEatenDTO> giraffeEats = asList(
            new AnimalEatenDTO(10, animalFacade.findAnimalBrief(dogId)),
            new AnimalEatenDTO(2, animalFacade.findAnimalBrief(giraffeId))
        );
        giraffeDetail.setPrey(giraffeEats);
        animalFacade.updateAnimal(giraffeDetail);
        assertThat(animalFacade.findPredatorsOf(giraffeId))
            .containsExactly(animalFacade.findAnimalBrief(giraffeId));
        assertThat(animalFacade.findPredatorsOf(dogId))
            .containsExactly(animalFacade.findAnimalBrief(dogId));
    }

    private AnimalBriefDTO giraffeDTO() {
        return new AnimalBriefDTO("Giraffe", "Mammal");
    }

    private AnimalBriefDTO dogDTO() {
        return new AnimalBriefDTO("Dog", "Mammal");
    }

}
