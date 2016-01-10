package cz.muni.fi.pa165.dominatingspecies.web.controllers;

import cz.muni.fi.pa165.dominatingspecies.dto.*;
import cz.muni.fi.pa165.dominatingspecies.facade.AnimalFacade;
import cz.muni.fi.pa165.dominatingspecies.facade.EnvironmentFacade;
import cz.muni.fi.pa165.dominatingspecies.web.config.DominatingSpeciesSecurityConfig;
import static java.lang.String.format;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Provides web access to Animals and their data and associations
 */
@Controller
@RequestMapping("/animal")
public class AnimalController {

    private final static Pattern EXTRACT_CHECK_ID = Pattern.compile("check_([\\d.]+)");

    @Inject
    private AnimalFacade animalFacade;

    @Inject
    private EnvironmentFacade environmentFacade;

    /**
     * List all known animals, brief info about each
     */
    @RequestMapping(value = "/", method = GET)
    public String list(Model model) {
        model.addAttribute("animals", animalFacade.findAllAnimals());
        return "animal/list";
    }

    /**
     * Create new Animal from its basic data
     */
    @RolesAllowed(DominatingSpeciesSecurityConfig.ROLE_ADMIN)
    @RequestMapping(value = "/", method = POST)
    public String create(@Valid @ModelAttribute("newAnimal") AnimalNewDTO newAnimal,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                model.addAttribute(fe.getField() + "_error_message", fe.getDefaultMessage());
            }
            model.addAttribute("animals", animalFacade.findAllAnimals());
            return "animal/list";
        }
        long id = this.animalFacade.createAnimal(newAnimal);
        redirectAttributes.addFlashAttribute("alert_success",
                                             formatAnimal(id, newAnimal.getName(), newAnimal.getSpecies()) + " was created");
        return "redirect:/animal/";
    }

    /**
     * List all known details and associations of one Animal
     */
    @RequestMapping(value = "/{id}", method = GET)
    public String detail(@PathVariable long id,
                         Model model) {
        AnimalDetailDTO animal = this.animalFacade.findAnimalDetail(id);
        List<AnimalBriefDTO> allAnimals = this.animalFacade.findAllAnimals();
        model.addAttribute("animal", animal);
        List<AnimalBriefDTO> addablePrey = new ArrayList<>(allAnimals);
        for (AnimalEatenDTO ae : animal.getPrey()) {
            addablePrey.remove(ae.getPrey());
        }
        List<AnimalBriefDTO> addablePredators = new ArrayList<>(allAnimals);
        for (AnimalEatenDTO ae : animal.getPredators()) {
            addablePredators.remove(ae.getPredator());
        }
        model.addAttribute("addablePrey", addablePrey);
        model.addAttribute("addablePredators", addablePredators);
        // Environments stuff
        model.addAttribute("aes", environmentFacade.findAeByAnimalId(id));
        Collection<EnvironmentDTO> allEnvironments = environmentFacade.findAllEnvironments();
        List<EnvironmentDTO> addableEnvs = new ArrayList<>(allEnvironments);
        for (EnvironmentDTO e : environmentFacade.findEnvironmentsForAnimal(id)) {
            addableEnvs.remove(e);
        }
        model.addAttribute("addableEnvs", addableEnvs);

        return "animal/detail";
    }

    /**
     * Delete one animal and all its associations
     */
    @RolesAllowed(DominatingSpeciesSecurityConfig.ROLE_ADMIN)
    @RequestMapping(value = "/{id}/delete", method = POST)
    public String delete(@PathVariable long id,
                         RedirectAttributes redirectAttributes) {
        AnimalBriefDTO deletedAnimal = this.animalFacade.findAnimalBrief(id);
        this.animalFacade.deleteAnimal(id);
        redirectAttributes.addFlashAttribute("alert_success",
                                             formatAnimal(deletedAnimal) + " was deleted");
        return "redirect:/animal/";
    }

    /**
     * Update info about one Animals characteristics
     */
    @RequestMapping(value = "/{id}/characteristics", method = POST)
    public String update(@PathVariable long id,
                         @Valid AnimalDetailUpdateCharacteristicsDTO updated,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            return sendPageWithErrorIfInvalidInput(id, bindingResult, model);
        }

        AnimalDetailDTO animal = this.animalFacade.findAnimalDetail(id);
        animal.setFoodNeeded(updated.getFoodNeeded());
        animal.setReproductionRate(updated.getReproductionRate());
        this.animalFacade.updateAnimal(animal);
        return format("redirect:/animal/%s#characteristics", id);
    }

    /**
     * Update info about one Animals identity
     */
    @RolesAllowed(DominatingSpeciesSecurityConfig.ROLE_ADMIN)
    @RequestMapping(value = "/{id}/identity", method = POST)
    public String update(@PathVariable long id,
                         @Valid AnimalDetailUpdateIdentificationDTO updated,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            return sendPageWithErrorIfInvalidInput(id, bindingResult, model);
        }

        AnimalDetailDTO animal = this.animalFacade.findAnimalDetail(id);
        animal.setName(updated.getName());
        animal.setSpecies(updated.getSpecies());
        this.animalFacade.updateAnimal(animal);
        return format("redirect:/animal/%s#characteristics", id);
    }

    private String sendPageWithErrorIfInvalidInput(@PathVariable long id,
                                                   BindingResult bindingResult,
                                                   Model model) {
        for (FieldError fe : bindingResult.getFieldErrors()) {
            model.addAttribute(fe.getField() + "_error", true);
            model.addAttribute(fe.getField() + "_error_message", fe.getDefaultMessage());
        }

        AnimalDetailDTO animal = this.animalFacade.findAnimalDetail(id);
        List<AnimalBriefDTO> allAnimals = this.animalFacade.findAllAnimals();
        model.addAttribute("animal", animal);
        List<AnimalBriefDTO> addablePrey = new ArrayList<>(allAnimals);
        for (AnimalEatenDTO ae : animal.getPrey()) {
            addablePrey.remove(ae.getPrey());
        }
        List<AnimalBriefDTO> addablePredators = new ArrayList<>(allAnimals);
        for (AnimalEatenDTO ae : animal.getPredators()) {
            addablePredators.remove(ae.getPredator());
        }
        model.addAttribute("addablePrey", addablePrey);
        model.addAttribute("addablePredators", addablePredators);
        // Environments stuff
        model.addAttribute("aes", environmentFacade.findAeByAnimalId(id));
        Collection<EnvironmentDTO> allEnvironments = environmentFacade.findAllEnvironments();
        List<EnvironmentDTO> addableEnvs = new ArrayList<>(allEnvironments);
        for (EnvironmentDTO e : environmentFacade.findEnvironmentsForAnimal(id)) {
            addableEnvs.remove(e);
        }
        model.addAttribute("addableEnvs", addableEnvs);

        return "animal/detail";
    }

    /**
     * Add record that animal 'id' eats animal 'prey'
     */
    @RequestMapping(value = "/{id}/prey", method = POST)
    public String addPrey(@PathVariable long id,
                          Long prey,
                          Model model) {
        if (prey != null) {
            this.animalFacade.createAnimalEaten(id, prey);
        }
        return format("redirect:/animal/%s#food", id);
    }

    /**
     * Add record that animal 'id' is eaten by animal 'predator'
     */
    @RequestMapping(value = "/{id}/predator", method = POST)
    public String addPredator(@PathVariable long id,
                              Long predator,
                              Model model) {
        if (predator != null) {
            this.animalFacade.createAnimalEaten(predator, id);
        }
        return format("redirect:/animal/%s#food", id);
    }

    /**
     * Update the 'eats' record with extra data
     */
    @RequestMapping(value = "/{id}/eaten/update", method = POST)
    public String updateAnimalEaten(@PathVariable long id,
                                    HttpServletRequest request) {
        for (long aeId : this.selectedIds(request.getParameterNames())) {
            String newCount = request.getParameter("count_" + aeId);
            Double newCountDouble = null;
            try {
                newCountDouble = Double.valueOf(newCount);
            } catch (NumberFormatException | NullPointerException e) {
                //ignored
            }
            if (newCountDouble != null) {
                this.animalFacade.updateAnimalEaten(aeId, newCountDouble);
            } else if (newCount == null || newCount.isEmpty()) {
                this.animalFacade.updateAnimalEaten(aeId, null);
            }
            //the case of gibberish entered is ignored, that is keep original value
        }
        return format("redirect:/animal/%s#food", id);
    }

    /**
     * Delete the record about one animal eating another
     */
    @RequestMapping(value = "/{id}/eaten/delete", method = POST)
    public String deleteAnimalEaten(@PathVariable long id,
                                    HttpServletRequest request) {
        for (long aeId : this.selectedIds(request.getParameterNames())) {
            {
                this.animalFacade.deleteAnimalEaten(aeId);
            }
        }
        return format("redirect:/animal/%s#food", id);
    }

    private List<Integer> selectedIds(Enumeration<String> paramNames) {
        List<Integer> ids = new ArrayList<>();
        while (paramNames.hasMoreElements()) {
            String name = paramNames.nextElement();
            Matcher m = EXTRACT_CHECK_ID.matcher(name);
            if (m.matches()) {
                ids.add(Integer.valueOf(m.group(1)));
            }
        }
        return ids;
    }

    private String formatAnimal(AnimalBriefDTO existingAnimal) {
        return formatAnimal(existingAnimal.getId(),
                            existingAnimal.getName(),
                            existingAnimal.getSpecies());
    }

    private String formatAnimal(long id, String name, String species) {
        return format("Animal #%s (%s of %s)", id, name, species);
    }

}
