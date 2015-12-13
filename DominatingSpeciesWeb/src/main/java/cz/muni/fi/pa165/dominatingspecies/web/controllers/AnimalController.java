package cz.muni.fi.pa165.dominatingspecies.web.controllers;

import cz.muni.fi.pa165.dominatingspecies.dto.AnimalBriefDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.AnimalDetailDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.AnimalNewDTO;
import cz.muni.fi.pa165.dominatingspecies.facade.AnimalFacade;
import cz.muni.fi.pa165.dominatingspecies.facade.EnvironmentFacade;
import static java.lang.String.format;
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
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/animal")
public class AnimalController {

    @Inject
    private AnimalFacade animalFacade;

    @Inject
    private EnvironmentFacade environmentFacade;

    @RequestMapping(value = "/", method = GET)
    public String list(Model model) {
        model.addAttribute("animals", animalFacade.findAllAnimals());
        return "animal/list";
    }

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

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable long id,
                         RedirectAttributes redirectAttributes) {
        AnimalBriefDTO deletedAnimal = this.animalFacade.findAnimalBrief(id);
        this.animalFacade.deleteAnimal(id);
        redirectAttributes.addFlashAttribute("alert_success",
                                             formatAnimal(deletedAnimal) + " was deleted");
        return "redirect:/animal/";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable long id,
                         Model model) {
        AnimalDetailDTO animal = this.animalFacade.findAnimalDetail(id);
        model.addAttribute("animal", animal);
        model.addAttribute("environments", environmentFacade.findEnvironmentsForAnimal(id));
        model.addAttribute("allEnvironments", environmentFacade.findAllEnvironments());
        return "animal/detail";
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
