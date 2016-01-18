package cz.muni.fi.pa165.dominatingspeciesrest.controllers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cz.muni.fi.pa165.dominatingspecies.dto.AnimalBriefDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.AnimalDetailDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.AnimalNewDTO;
import cz.muni.fi.pa165.dominatingspecies.facade.AnimalFacade;
import cz.muni.fi.pa165.dominatingspeciesrest.exceptions.ResourceAlreadyExistingException;
import cz.muni.fi.pa165.dominatingspeciesrest.exceptions.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

/**
 * REST Controller for animals
 *
  * @author Petr Domkař
 */
@RestController
@RequestMapping("/animals")
public class AnimalsController {

    final static Logger logger = LoggerFactory.getLogger(AnimalsController.class);

    @Inject
    private AnimalFacade animalFacade;

    /**
     * Get list of Animal curl -i -X GET http://localhost:8080/pa165/rest/animals
     *
     * @return ProductDTO
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<AnimalBriefDTO> getAnimals() {
        logger.debug("rest getAnimals()");
        return animalFacade.findAllAnimals();
    }
    
    /**
     * Get Detail of Animal with id curl -i -X GET http://localhost:8080/pa165/rest/animals/1
     * 
     * @param id identifier for animal
     * @return AnimalDetailDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final AnimalDetailDTO getAnimal(@PathVariable("id") long id) throws ResourceNotFoundException {
        logger.debug("rest getAnimal({})", id);
        AnimalDetailDTO animalDetailDTO = animalFacade.findAnimalDetail(id);
        if(animalDetailDTO != null) {
            return animalDetailDTO;
        } else {
            throw new ResourceNotFoundException();
        }
    }
    
    /**
     * Get brief Detail of Animal with id curl -i -X GET http://localhost:8080/pa165/rest/animals/1/brief
     * 
     * @param id identifier for animal
     * @return AnimalBriefDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}/brief", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final AnimalBriefDTO getBriefAnimal(@PathVariable("id") long id) throws ResourceNotFoundException {
        logger.debug("rest getBriefAnimal({})", id);
        AnimalBriefDTO animalBriefDTO = animalFacade.findAnimalBrief(id);
        if(animalBriefDTO != null) {
            return animalBriefDTO;
        } else {
            throw new ResourceNotFoundException();
        }
    }
    
    /**
     * Delete a Animal with id curl -i -X DELETE http://localhost:8080/pa165/rest/animals/1
     * 
     * @param id identifier for animal
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteAnimal(@PathVariable("id") long id) {
        logger.debug("deleteAnimal({})", id);
        animalFacade.deleteAnimal(id);
    }
    
    /**
     * Create a Animal curl -X POST -i -H "Content-Type: application/json" --data '{"name":"test","species":"test"}'  http://localhost:8080/pa165/rest/animals/create
     * 
     * @param animal AnimalNewDTO with required fields for creation
     * @return the created brief animal
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final AnimalBriefDTO createAnimal(@RequestBody AnimalNewDTO animalNewDTO){
        logger.debug("createAnimal()");
        Long id = animalFacade.createAnimal(animalNewDTO);
        return animalFacade.findAnimalBrief(id);
    }
    
    
    /**
     * UPDATE a Animal curl -X PUT -i -H "Content-Type: application/json" --data '{"id": "1", "name":"test","species":"test","foodNeeded":"100","repreductionRate":"2.2"}' http://localhost:8080/pa165/rest/animals/update
     * 
     * @param animal AnimalDetailDTO with id
     * @return the created detail animal
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final AnimalDetailDTO updateAnimal(@RequestBody AnimalDetailDTO animalDetailDTO){
        logger.debug("updateAnimal()");        
        animalFacade.updateAnimal(animalDetailDTO);
        return animalFacade.findAnimalDetail(animalDetailDTO.getId());
    }
    
    
}
