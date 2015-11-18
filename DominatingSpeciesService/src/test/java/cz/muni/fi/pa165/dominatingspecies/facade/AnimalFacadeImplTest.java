
package cz.muni.fi.pa165.dominatingspecies.facade;

import cz.muni.fi.pa165.dominatingspecies.DominatingSpeciesConfig;
import cz.muni.fi.pa165.dominatingspecies.dto.AnimalBriefDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.AnimalEatenDTO;
import cz.muni.fi.pa165.dominatingspecies.service.config.DominatingSpeciesServiceConfig;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
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
         AnimalBriefDTO animalDTO = new AnimalBriefDTO();
         animalDTO.setName("Zirafa");
         animalDTO.setSpecies("Savec");
         long animalId = animalFacade.createAnimal(animalDTO);
         assertEquals(animalFacade.findAllAnimals().size(), 1);
         animalFacade.deleteAnimal(animalId);
         
         assertEquals(animalFacade.findAllAnimals().size(), 0);
     }
     
     @Test
     public void findAllanimalsTest() {
         AnimalBriefDTO animalDTO1 = new AnimalBriefDTO();
         animalDTO1.setName("Zirafa");
         animalDTO1.setSpecies("Savec");
         AnimalBriefDTO animalDTO2 = new AnimalBriefDTO();
         animalDTO2.setName("Pes");
         animalDTO2.setSpecies("Savec");
         animalFacade.createAnimal(animalDTO1);
         animalFacade.createAnimal(animalDTO2);
         
         assertEquals(animalFacade.findAllAnimals().size(), 2);
     }
     
     @Test
     public void findPredatorsOfTest() {
         AnimalBriefDTO animalDTO1 = new AnimalBriefDTO();
         animalDTO1.setName("Zirafa");
         animalDTO1.setSpecies("Savec");
         AnimalBriefDTO animalDTO2 = new AnimalBriefDTO();
         animalDTO2.setName("Pes");
         animalDTO2.setSpecies("Savec");
         animalFacade.createAnimal(animalDTO1);
         animalFacade.createAnimal(animalDTO2);
         
         AnimalEatenDTO animalEatenDTO1 = new AnimalEatenDTO();
         animalEatenDTO1.setPredator(animalDTO1);
         animalEatenDTO1.setPrey(animalDTO2);
         animalFacade.createAnimalEaten(animalEatenDTO1);
         
         AnimalEatenDTO animalEatenDTO2 = new AnimalEatenDTO();
         animalEatenDTO2.setPredator(animalDTO1);
         animalEatenDTO2.setPrey(animalDTO1);
         animalFacade.createAnimalEaten(animalEatenDTO2);
         
         List<AnimalBriefDTO> list = new ArrayList<>();
         list.add(animalDTO1);
         assertEquals(list, animalFacade.findPredatorsOf(animalDTO2.getId()));    
     }
}

