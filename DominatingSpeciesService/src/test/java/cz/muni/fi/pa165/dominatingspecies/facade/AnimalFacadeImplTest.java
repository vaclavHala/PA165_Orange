package cz.muni.fi.pa165.dominatingspecies.facade;

import cz.muni.fi.pa165.dominatingspecies.dto.AnimalDetailDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.AnimalEatenDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.AnimalNewDTO;
import cz.muni.fi.pa165.dominatingspecies.service.config.DominatingSpeciesServiceConfig;
import java.util.Collection;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
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
    public void createAnimalTest() {
        long id = animalFacade.createAnimal(newGiraffe());
        assertThat(animalFacade.findAllAnimals()).hasSize(1);
        AnimalDetailDTO newAnimal = animalFacade.findAnimalDetail(id);
        assertThat(newAnimal.getId()).isNotNull();
        assertThat(newAnimal.getName()).isEqualTo(newGiraffe().getName());
        assertThat(newAnimal.getSpecies()).isEqualTo(newGiraffe().getSpecies());
    }

    @Test
    public void deleteAnimalTest() {
        long animalId = animalFacade.createAnimal(newGiraffe());
        animalFacade.deleteAnimal(animalId);
        assertEquals(animalFacade.findAllAnimals().size(), 0);
    }

    @Test
    public void findAllanimalsTest() {
        long giraffeId = animalFacade.createAnimal(newGiraffe());
        long dogId = animalFacade.createAnimal(newDog());

        assertThat(animalFacade.findAllAnimals())
            .extracting("id")
            .containsOnly(giraffeId, dogId);
    }

    @Test
    public void addAnimalEaten() {
        long giraffeId = animalFacade.createAnimal(newGiraffe());
        long dogId = animalFacade.createAnimal(newDog());
        animalFacade.createAnimalEaten(giraffeId, dogId);
        Collection<AnimalEatenDTO> giraffeEats = animalFacade.findAnimalDetail(giraffeId).getPrey();
        assertThat(giraffeEats).hasSize(1);
    }

    @Test
    public void testFindAnimalDetail() {
        long giraffeId = animalFacade.createAnimal(newGiraffe());
        long dogId = animalFacade.createAnimal(newDog());
        animalFacade.createAnimalEaten(giraffeId, dogId);
        AnimalEatenDTO giraffeEats = animalFacade.findAnimalDetail(giraffeId).getPrey()
            .iterator().next();
        assertThat(giraffeEats.getPredator().getId()).isEqualTo(giraffeId);
        assertThat(giraffeEats.getPrey().getId()).isEqualTo(dogId);
    }

    @Test
    public void testDeleteAnimal() {
        long giraffeId = animalFacade.createAnimal(newGiraffe());
        long dogId = animalFacade.createAnimal(newDog());
        animalFacade.createAnimalEaten(giraffeId, dogId);
        animalFacade.deleteAnimal(giraffeId);
        assertThat(animalFacade.findAllAnimals()).hasSize(1);
    }

    @Test
    public void testUpdateAnimal() {
        long giraffeId = animalFacade.createAnimal(newGiraffe());
        fail();
    }

    private AnimalNewDTO newGiraffe() {
        return new AnimalNewDTO("Giraffe", "Mammal");
    }

    private AnimalNewDTO newDog() {
        return new AnimalNewDTO("Dog", "Mammal");
    }

}
