package cz.muni.fi.pa165.dominatingspecies.service.facade;

import cz.muni.fi.pa165.dominatingspecies.dto.*;
import cz.muni.fi.pa165.dominatingspecies.entity.Animal;
import cz.muni.fi.pa165.dominatingspecies.entity.AnimalEaten;
import cz.muni.fi.pa165.dominatingspecies.facade.AnimalFacade;
import cz.muni.fi.pa165.dominatingspecies.service.AnimalEatenService;
import cz.muni.fi.pa165.dominatingspecies.service.AnimalService;
import cz.muni.fi.pa165.dominatingspecies.service.BeanMappingService;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.transaction.annotation.Transactional;

@Named
@Transactional
public class AnimalFacadeImpl implements AnimalFacade {

    @Inject
    private AnimalService animalService;

    @Inject
    private AnimalEatenService animalEatenService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public long createAnimal(AnimalNewDTO newAnimal) {
        Animal animal = beanMappingService.map(newAnimal, Animal.class);
        animalService.create(animal);
        return animal.getId();
    }

    @Override
    public AnimalBriefDTO findAnimalBrief(long animalId) {
        Animal animal = animalService.findById(animalId);
        return beanMappingService.map(animal, AnimalBriefDTO.class);
    }

    @Override
    public AnimalDetailDTO findAnimalDetail(long animalId) {
        Animal animal = animalService.findById(animalId);
        AnimalDetailDTO detail = beanMappingService.map(animal, AnimalDetailDTO.class);
        Collection<AnimalEaten> predators = animalEatenService.findPredatorsOf(animal);
        detail.setPredators(beanMappingService.map(predators, AnimalEatenDTO.class));
        Collection<AnimalEaten> prey = animalEatenService.findPreyOf(animal);
        detail.setPrey(beanMappingService.map(prey, AnimalEatenDTO.class));
        return detail;
    }

    @Override
    public void updateAnimal(AnimalDetailDTO updatedAnimal) {
        Animal animal = beanMappingService.map(updatedAnimal, Animal.class);
        animalService.update(animal);
    }

    @Override
    public void deleteAnimal(long animalId) {
        Animal animal = animalService.findById(animalId);
        animalService.remove(animal);
        animalEatenService.removeAllFor(animal);
    }

    @Override
    public List<AnimalBriefDTO> findAllAnimals() {
        Collection<Animal> animals = animalService.findAll();
        return beanMappingService.map(animals, AnimalBriefDTO.class);
    }

    @Override
    public long createAnimalEaten(long predatorId, long preyId) {
        AnimalEaten ae = new AnimalEaten(
            animalService.findById(predatorId),
            animalService.findById(preyId));
        animalEatenService.createAnimalEaten(ae);
        return ae.getId();
    }

    @Override
    public void updateAnimalEaten(AnimalEatenDTO animalEaten) {
        animalEatenService.update(beanMappingService.map(animalEaten, AnimalEaten.class));
    }

    @Override
    public void deleteAnimalEaten(long animalEatenId) {
        animalEatenService.remove(animalEatenService.findById(animalEatenId));
    }
}
