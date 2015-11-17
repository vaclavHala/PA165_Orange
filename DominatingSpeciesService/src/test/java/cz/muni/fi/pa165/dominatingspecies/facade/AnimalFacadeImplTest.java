
package cz.muni.fi.pa165.dominatingspecies.facade;

import cz.muni.fi.pa165.dominatingspecies.DominatingSpeciesConfig;
import cz.muni.fi.pa165.dominatingspecies.dto.AnimalBriefDTO;
import javax.inject.Inject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
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
@ContextConfiguration(classes = {DominatingSpeciesConfig.class})
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
     public void findAllanimals() {
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
}
