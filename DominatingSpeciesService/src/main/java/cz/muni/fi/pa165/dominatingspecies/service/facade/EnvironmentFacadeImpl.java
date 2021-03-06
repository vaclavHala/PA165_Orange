package cz.muni.fi.pa165.dominatingspecies.service.facade;

import cz.muni.fi.pa165.dominatingspecies.dto.AnimalBriefDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.AnimalEnvironmentDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.EnvironmentDTO;
import cz.muni.fi.pa165.dominatingspecies.entity.Animal;
import cz.muni.fi.pa165.dominatingspecies.entity.AnimalEnvironment;
import cz.muni.fi.pa165.dominatingspecies.entity.Environment;
import cz.muni.fi.pa165.dominatingspecies.facade.EnvironmentFacade;
import cz.muni.fi.pa165.dominatingspecies.service.AnimalEnvironmentService;
import cz.muni.fi.pa165.dominatingspecies.service.AnimalService;
import cz.muni.fi.pa165.dominatingspecies.service.BeanMappingService;
import cz.muni.fi.pa165.dominatingspecies.service.EnvironmentService;
import java.util.Collection;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ivan Kralik
 */
@Named
@Transactional
public class EnvironmentFacadeImpl implements EnvironmentFacade {

    private final EnvironmentService environmentService;

    private final AnimalService animalService;

    private final AnimalEnvironmentService aeService;

    private final BeanMappingService mappingService;

    @Inject
    public EnvironmentFacadeImpl(EnvironmentService environmentService, AnimalService animalService, AnimalEnvironmentService aeService, BeanMappingService mappingService) {
        this.environmentService = environmentService;
        this.animalService = animalService;
        this.aeService = aeService;
        this.mappingService = mappingService;
    }

    @Override
    public Collection<EnvironmentDTO> findAllEnvironments() {
        return mappingService.map(environmentService.findAll(), EnvironmentDTO.class);
    }

    @Override
    public EnvironmentDTO findEnvironment(long id) {
        Environment environment = environmentService.findById(id);

        if (environment == null) {
            return null;
        }

        return mappingService.map(environment, EnvironmentDTO.class);
    }

    @Override
    public long createEnvironment(EnvironmentDTO environment) {
        Environment entity = mappingService.map(environment, Environment.class);

        environmentService.create(entity);

        return entity.getId();
    }

    @Override
    public void updateEnvironment(EnvironmentDTO environment) {
        Environment entity = mappingService.map(environment, Environment.class);

        environmentService.update(entity);
    }

    @Override
    public void updateAnimalEnvironment(AnimalEnvironmentDTO ae) {
        AnimalEnvironment entity = mappingService.map(ae, AnimalEnvironment.class);
        aeService.update(entity);
    }

    @Override
    public void deleteAnimalEnvironment(long animalId, long envId) {
        AnimalEnvironment entity = aeService.findByIdAnimalEnvironment(animalId, envId);

        aeService.remove(entity);
    }

    @Override
    public void deleteEnvironment(long id) {
        Environment entity = environmentService.findById(id);

        environmentService.remove(entity);
    }

    @Override
    public long addAnimalEnvironment(AnimalEnvironmentDTO animalEnvironment) {
        AnimalEnvironment aeExisting = aeService.findByIdAnimalEnvironment(animalEnvironment.getAnimal().getId(),
                                                                           animalEnvironment.getEnvironment().getId());
        if (aeExisting != null) {
            return aeExisting.getId();
        }
        AnimalEnvironment entity = mappingService.map(animalEnvironment, AnimalEnvironment.class);

        Animal managedAnimal = animalService.findById(animalEnvironment.getAnimal().getId());
        Environment managedEnvironment = environmentService.findById(animalEnvironment.getEnvironment().getId());

        entity.setAnimal(managedAnimal);
        entity.setEnvironment(managedEnvironment);

        aeService.create(entity);

        return entity.getId();
    }

    @Override
    public AnimalEnvironmentDTO findAeById(long id) {
        return mappingService.map(aeService.findById(id), AnimalEnvironmentDTO.class);
    }

    @Override
    public Collection<AnimalEnvironmentDTO> findAeByAnimalId(long animalId) {
        return mappingService.map(aeService.findByAnimalId(animalId), AnimalEnvironmentDTO.class);
    }

    @Override
    public Collection<AnimalEnvironmentDTO> findAeByEnvironmentId(long envId) {
        return mappingService.map(aeService.findByEnvironmentId(envId), AnimalEnvironmentDTO.class);
    }

    @Override
    public void removeAnimalEnvironment(long id) {
        AnimalEnvironment entity = aeService.findById(id);

        aeService.remove(entity);
    }

    @Override
    public Collection<AnimalBriefDTO> findAnimalsInEnvironment(long environmentId) {
        Environment environment = environmentService.findById(environmentId);

        return mappingService.map(environmentService.findAnimalsForEnvironment(environment), AnimalBriefDTO.class);
    }

    @Override
    public Collection<EnvironmentDTO> findEnvironmentsForAnimal(long animalId) {
        Animal animal = animalService.findById(animalId);

        return mappingService.map(environmentService.findEnvironmentsForAnimal(animal), EnvironmentDTO.class);
    }

    public long createAnimalEnvironment(long animalId, long environmentId) {
        AnimalEnvironment aeExisting = aeService.findByIdAnimalEnvironment(animalId, environmentId);
        if (aeExisting != null) {
            return aeExisting.getId();
        }
        AnimalEnvironment ae = new AnimalEnvironment(
                animalService.findById(animalId),
                environmentService.findById(environmentId));
        aeService.create(ae);
        return ae.getId();
    }

}
