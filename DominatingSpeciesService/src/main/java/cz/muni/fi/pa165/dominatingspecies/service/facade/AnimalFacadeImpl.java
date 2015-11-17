package cz.muni.fi.pa165.dominatingspecies.service.facade;

import cz.muni.fi.pa165.dominatingspecies.dto.AnimalBriefDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.AnimalDetailDTO;
import cz.muni.fi.pa165.dominatingspecies.entity.Animal;
import cz.muni.fi.pa165.dominatingspecies.facade.AnimalFacade;
import cz.muni.fi.pa165.dominatingspecies.service.AnimalEatenService;
import cz.muni.fi.pa165.dominatingspecies.service.AnimalService;
import cz.muni.fi.pa165.dominatingspecies.service.BeanMappingService;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;



public class AnimalFacadeImpl implements AnimalFacade{
    @Inject
    private AnimalService animalService;
    
    @Inject
    private AnimalEatenService animalEatenService;
    
    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public long createAnimal(AnimalBriefDTO newAnimal) {
        Animal animal = new Animal();
        animal.setName(newAnimal.getName());
        animal.setSpecies(newAnimal.getSpecies());
        animalService.create(animal);
        return animal.getId();
    }

    @Override
    public AnimalDetailDTO findAnimal(long animalId) {
        return beanMappingService.map(animalService.findById(animalId), AnimalDetailDTO.class);
    }

    @Override
    public void updateAnimal(AnimalDetailDTO updatedAnimal) {
        Animal anim = animalService.findById(updatedAnimal.getId());
        anim.setName(updatedAnimal.getName());
        anim.setSpecies(updatedAnimal.getSpecies());
        anim.setReproductionRate(updatedAnimal.getRepreductionRate());
        anim.setFoodNeeded(updatedAnimal.getFoodNeeded());
        animalService.update(anim);
    }

    @Override
    public void deleteAnimal(long animalId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AnimalBriefDTO> findAllAnimals() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AnimalBriefDTO> findPredatorsOf(long animalId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AnimalBriefDTO> findPreyOf(long animalId) {
        List<AnimalBriefDTO> prey = new ArrayList<>();
        Animal a = animalService.findById(animalId);
        prey.addAll(beanMappingService.map(animalEatenService.findPreyOf(a), AnimalBriefDTO.class));        
        return prey;
    }

}
