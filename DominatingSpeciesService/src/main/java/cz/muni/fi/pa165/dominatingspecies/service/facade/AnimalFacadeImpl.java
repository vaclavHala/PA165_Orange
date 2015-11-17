package cz.muni.fi.pa165.dominatingspecies.service.facade;

import cz.muni.fi.pa165.dominatingspecies.dto.AnimalBriefDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.AnimalDetailDTO;
import cz.muni.fi.pa165.dominatingspecies.entity.Animal;
import cz.muni.fi.pa165.dominatingspecies.facade.AnimalFacade;
import cz.muni.fi.pa165.dominatingspecies.service.AnimalService;
import cz.muni.fi.pa165.dominatingspecies.service.BeanMappingService;
import java.util.List;
import javax.inject.Inject;



public class AnimalFacadeImpl implements AnimalFacade{
    @Inject
    private AnimalService animalService;
    
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
        throw new UnsupportedOperationException("Not supported yet.");
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<AnimalBriefDTO> findPreyOf(long animalId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
