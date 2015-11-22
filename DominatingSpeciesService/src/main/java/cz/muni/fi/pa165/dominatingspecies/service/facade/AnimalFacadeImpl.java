package cz.muni.fi.pa165.dominatingspecies.service.facade;

import cz.muni.fi.pa165.dominatingspecies.dto.AnimalBriefDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.AnimalDetailDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.AnimalEatenDTO;
import cz.muni.fi.pa165.dominatingspecies.entity.Animal;
import cz.muni.fi.pa165.dominatingspecies.entity.AnimalEaten;
import cz.muni.fi.pa165.dominatingspecies.facade.AnimalFacade;
import cz.muni.fi.pa165.dominatingspecies.service.AnimalEatenService;
import cz.muni.fi.pa165.dominatingspecies.service.AnimalService;
import cz.muni.fi.pa165.dominatingspecies.service.BeanMappingService;
import java.util.ArrayList;
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
    public long createAnimal(AnimalBriefDTO newAnimal) {
        Animal animal = new Animal();
        animal.setName(newAnimal.getName());
        animal.setSpecies(newAnimal.getSpecies());
        animalService.create(animal);
        return animal.getId();
    }

    @Override
    public AnimalDetailDTO findAnimalDetail(long animalId) {
        return beanMappingService.map(animalService.findById(animalId), AnimalDetailDTO.class);
    }

    @Override
    public AnimalBriefDTO findAnimalBrief(long animalId) {
        System.out.println(animalService.findById(animalId));
        System.out.println(beanMappingService.map(animalService.findById(animalId), AnimalBriefDTO.class));
        return beanMappingService.map(animalService.findById(animalId), AnimalBriefDTO.class);
    }

    @Override
    public void updateAnimal(AnimalDetailDTO updatedAnimal) {
        Animal animal = beanMappingService.map(updatedAnimal, Animal.class);
        animalService.update(animal);
        for (AnimalEatenDTO preyDTO : updatedAnimal.getPrey()) {
            System.out.println("save prey");
            Animal prey = animalService.findById(preyDTO.getOther().getId());
            AnimalEaten ae = new AnimalEaten(animal, prey);
            ae.setAnimalCount(preyDTO.getAnimalCount());
            if (ae.getId() == null) {
                animalEatenService.createAnimalEaten(ae);
            } else {
                animalEatenService.update(ae);
            }
        }
        for (AnimalEatenDTO predatorDTO : updatedAnimal.getPredators()) {
            System.out.println("save predator");
            Animal predator = animalService.findById(predatorDTO.getOther().getId());
            AnimalEaten ae = new AnimalEaten(predator, animal);
            ae.setAnimalCount(predatorDTO.getAnimalCount());

            if (ae.getId() == null) {
                animalEatenService.createAnimalEaten(ae);
            } else {
                animalEatenService.update(ae);
            }
        }
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
    public List<AnimalBriefDTO> findPredatorsOf(long animalId) {
        Animal animal = animalService.findById(animalId);
        Collection<Animal> predators = animalEatenService.findPredatorOf(animal);
        return beanMappingService.map(predators, AnimalBriefDTO.class);
    }

    @Override
    public List<AnimalBriefDTO> findPreyOf(long animalId) {
        List<AnimalBriefDTO> prey = new ArrayList<>();
        Animal a = animalService.findById(animalId);
        prey.addAll(beanMappingService.map(animalEatenService.findPreyOf(a), AnimalBriefDTO.class));
        return prey;
    }
}
