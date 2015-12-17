package cz.muni.fi.pa165.dominatingspecies.sampledata;

import cz.muni.fi.pa165.dominatingspecies.entity.Animal;
import cz.muni.fi.pa165.dominatingspecies.entity.AnimalEaten;
import cz.muni.fi.pa165.dominatingspecies.entity.AnimalEnvironment;
import cz.muni.fi.pa165.dominatingspecies.entity.Environment;
import cz.muni.fi.pa165.dominatingspecies.service.AnimalEatenService;
import cz.muni.fi.pa165.dominatingspecies.service.AnimalEnvironmentService;
import cz.muni.fi.pa165.dominatingspecies.service.AnimalService;
import cz.muni.fi.pa165.dominatingspecies.service.EnvironmentService;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ivan Kralik
 */
@Named
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    @Inject
    private EnvironmentService environmentService;

    @Inject
    private AnimalService animalService;

    @Inject
    private AnimalEnvironmentService aeService;

    @Inject
    private AnimalEatenService animalEatenService;

    @Override
    public void loadData() {
        Animal cat = createAnimal("Mačka domáca", "Mačky", 10d, 1d);
        Animal mouse = createAnimal("Myš domová", "Myši", 100d, 1d);
        Animal hamster = createAnimal("Škrečok", "Hlodavce", 45d, 6d);
        Animal rabbit = createAnimal("Zajac obyčajný", "Hlodavce", 21d, 3d);
        Animal camel = createAnimal("Ťava dvojhrbá", "Prežúvavce", 4d, 2d);

        Environment home = createEnvironment("Domácnosti", "Domy, statky", 10l);
        Environment field = createEnvironment("Polia", "Obhospodarované polia", 100l);
        Environment forest = createEnvironment("Lesy", "Ihlicnate lesy", 40l);
        Environment desert = createEnvironment("Púšte", "Kamenisté púšte", 23l);
        Environment mountains = createEnvironment("Hory", "Vysoké hory", 190l);

        AnimalEnvironment catHome = createAnimalEnvironment(cat, home, 1);
        AnimalEnvironment hamsterHome = createAnimalEnvironment(hamster, home, 1);
        AnimalEnvironment rabbitHome = createAnimalEnvironment(rabbit, home, 1);
        AnimalEnvironment camelHome = createAnimalEnvironment(camel, home, 1);
        AnimalEnvironment mouseHome = createAnimalEnvironment(mouse, home, 0.4);
        AnimalEnvironment mouseField = createAnimalEnvironment(mouse, field, 0.6);
        AnimalEnvironment mouseForest = createAnimalEnvironment(mouse, forest, 0.2);
        AnimalEnvironment mouseDesert = createAnimalEnvironment(mouse, desert, 0.9);
        AnimalEnvironment mouseMountains = createAnimalEnvironment(mouse, mountains, 0.8);

        AnimalEaten catMouse = createAnimalEaten(cat, mouse, 10.0);
    }

    private Animal createAnimal(String name, String species, double reproductionRate, double foodNeeded) {
        Animal animal = new Animal();
        animal.setName(name);
        animal.setSpecies(species);
        animal.setReproductionRate(reproductionRate);
        animal.setFoodNeeded(foodNeeded);

        animalService.create(animal);

        return animal;
    }

    private Environment createEnvironment(String name, String description, long maxAnimalCount) {
        Environment environment = new Environment();
        environment.setName(name);
        environment.setDescription(description);
        environment.setMaxAnimalCount(maxAnimalCount);

        environmentService.create(environment);

        return environment;
    }

    private AnimalEnvironment createAnimalEnvironment(Animal animal, Environment environment, double percentage) {
        AnimalEnvironment animalEnvironment = new AnimalEnvironment();
        animalEnvironment.setAnimal(animal);
        animalEnvironment.setEnvironment(environment);
        animalEnvironment.setPercentage(percentage);

        aeService.create(animalEnvironment);

        return animalEnvironment;
    }

    private AnimalEaten createAnimalEaten(Animal predator, Animal prey, Double animalCount) {
        AnimalEaten eaten = new AnimalEaten();
        eaten.setPredator(predator);
        eaten.setPrey(prey);
        eaten.setAnimalCount(animalCount);

        animalEatenService.createAnimalEaten(eaten);

        return eaten;
    }
}
