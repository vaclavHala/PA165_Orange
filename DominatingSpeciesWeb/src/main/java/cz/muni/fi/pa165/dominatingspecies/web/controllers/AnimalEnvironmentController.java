package cz.muni.fi.pa165.dominatingspecies.web.controllers;

import cz.muni.fi.pa165.dominatingspecies.dto.AnimalEnvironmentDTO;
import cz.muni.fi.pa165.dominatingspecies.facade.AnimalFacade;
import cz.muni.fi.pa165.dominatingspecies.facade.EnvironmentFacade;
import static java.lang.String.format;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Controller for managing REST requests for AnimalEnvironments
 * @author Daniel Minarik
 */
@Controller
@RequestMapping("/animalenvironment")
public class AnimalEnvironmentController {

    @Inject
    private EnvironmentFacade environmentFacade;

    @Inject
    private AnimalFacade animalFacade;

    /**
     * Create a new 'lives in' association between Animal and Environment
     */
    @RequestMapping(value = "/{animalId}/envId/addEnvironment", method = RequestMethod.POST)
    public String addEnvironment(@PathVariable long animalId, Long envId, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        if (envId != null) {
            AnimalEnvironmentDTO ae = new AnimalEnvironmentDTO();
            ae.setAnimal(animalFacade.findAnimalBrief(animalId));
            ae.setEnvironment(environmentFacade.findEnvironment(envId));
            environmentFacade.addAnimalEnvironment(ae);
        }
        return format("redirect:/animal/%s#enviro", animalId);
    }

    /**
     * Add Animal to an existing Environment
     */
    @RequestMapping(value = "/animalId/{envId}/addAnimal", method = RequestMethod.POST)
    public String addAnimal(Long animalId, @PathVariable long envId, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        if (animalId != null) {
            AnimalEnvironmentDTO ae = new AnimalEnvironmentDTO();
            ae.setAnimal(animalFacade.findAnimalBrief(animalId));
            ae.setEnvironment(environmentFacade.findEnvironment(envId));
            environmentFacade.addAnimalEnvironment(ae);
        }
        return format("redirect:/environment/%s#animals", envId);
    }

    /**
     * Remove record about animal living in an Environment
     */
    @RequestMapping(value = "/{animalId}/{envId}/remove/{redirectTo}", method = RequestMethod.GET)
    public String remove(@PathVariable long animalId, @PathVariable long envId, @PathVariable String redirectTo, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        environmentFacade.deleteAnimalEnvironment(animalId, envId);
        String ret = "redirect:";
        if (redirectTo.equals("animal")) {
            ret += format("/animal/%s#enviro", animalId);
        }
        if (redirectTo.equals("environment")) {
            ret += format("/environment/%s#animals", envId);
        }
        return ret;
    }

    /**
     * Update record about animal living in an Environment with extra data
     */
    @RequestMapping(value = "/{id}/{aeId}/update/{redirectTo}", method = POST)
    public String updateAnimalEnvironment(@PathVariable long id, @PathVariable long aeId, @PathVariable String redirectTo, HttpServletRequest request) {
        String percentage = request.getParameter("percentage");
        if (percentage != null) {
            AnimalEnvironmentDTO ae = environmentFacade.findAeById(aeId);
            Double percDouble = null;
            try {
                percDouble = Double.valueOf(percentage);
            } catch (NullPointerException | NumberFormatException e) {
                //ignored
            }
            ae.setPercentage(percDouble);
            environmentFacade.updateAnimalEnvironment(ae);
        }
        String ret = "redirect:";
        if (redirectTo.equals("animal")) {
            ret += format("/animal/%s#enviro", id);
        }
        if (redirectTo.equals("environment")) {
            ret += format("/environment/%s#animals", id);
        }
        return ret;
    }

}
