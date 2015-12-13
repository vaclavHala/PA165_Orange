package cz.muni.fi.pa165.dominatingspecies.web.controllers;

import cz.muni.fi.pa165.dominatingspecies.dto.AnimalEnvironmentDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.EnvironmentDTO;
import cz.muni.fi.pa165.dominatingspecies.facade.EnvironmentFacade;
import cz.muni.fi.pa165.dominatingspecies.facade.AnimalFacade;
import cz.muni.fi.pa165.dominatingspecies.web.config.DominatingSpeciesSecurityConfig;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
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

    @RequestMapping(value = "/{animalId}/envId/addEnvironment", method = RequestMethod.POST)
    public String addEnvironment(@PathVariable long animalId, long envId, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        AnimalEnvironmentDTO ae = new AnimalEnvironmentDTO();
        ae.setAnimal(animalFacade.findAnimalBrief(animalId));
        ae.setEnvironment(environmentFacade.findEnvironment(envId));
        environmentFacade.addAnimalEnvironment(ae);
        return "redirect:" + uriBuilder.path("/animal/{animalId}").buildAndExpand(animalId).encode().toUriString();
    }

    @RequestMapping(value = "/animalId/{envId}/addAnimal", method = RequestMethod.POST)
    public String addAnimal(long animalId, @PathVariable long envId, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        AnimalEnvironmentDTO ae = new AnimalEnvironmentDTO();
        ae.setAnimal(animalFacade.findAnimalBrief(animalId));
        ae.setEnvironment(environmentFacade.findEnvironment(envId));
        environmentFacade.addAnimalEnvironment(ae);
        return "redirect:" + uriBuilder.path("/environment/{envId}/edit").buildAndExpand(envId).encode().toUriString();
    }

    @RequestMapping(value = "/{animalId}/{envId}/remove/{redirectTo}", method = RequestMethod.GET)
    public String remove(@PathVariable long animalId, @PathVariable long envId, @PathVariable String redirectTo, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        environmentFacade.deleteAnimalEnvironment(animalId, envId);
        String ret = "redirect:";
        if (redirectTo.equals("animal")) {
            ret += uriBuilder.path("/animal/{animalId}").buildAndExpand(animalId).encode().toUriString();
        } if (redirectTo.equals("environment")) {
            ret += uriBuilder.path("/environment/{envId}/edit").buildAndExpand(envId).encode().toUriString();
        }
        return ret;
    }
}
