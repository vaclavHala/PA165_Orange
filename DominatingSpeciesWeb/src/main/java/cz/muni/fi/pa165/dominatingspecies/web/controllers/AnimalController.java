package cz.muni.fi.pa165.dominatingspecies.web.controllers;

import cz.muni.fi.pa165.dominatingspecies.dto.AnimalBriefDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.AnimalDetailDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.AnimalEatenDTO;
import cz.muni.fi.pa165.dominatingspecies.dto.AnimalNewDTO;
import cz.muni.fi.pa165.dominatingspecies.facade.AnimalFacade;
import cz.muni.fi.pa165.dominatingspecies.facade.EnvironmentFacade;
import static java.lang.String.format;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

@Controller
@RequestMapping("/animal")
public class AnimalController {

    private final static Pattern EXTRACT_CHECK_ID = Pattern.compile("check_([\\d.]+)");

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

    @RequestMapping(value = "/{id}/delete", method = POST)
    public String delete(@PathVariable long id,
                         RedirectAttributes redirectAttributes) {
        AnimalBriefDTO deletedAnimal = this.animalFacade.findAnimalBrief(id);
        this.animalFacade.deleteAnimal(id);
        redirectAttributes.addFlashAttribute("alert_success",
                                             formatAnimal(deletedAnimal) + " was deleted");
        return "redirect:/animal/";
    }

    @RequestMapping(value = "/{id}", method = GET)
    public String detail(@PathVariable long id,
                         Model model) {
        AnimalDetailDTO animal = this.animalFacade.findAnimalDetail(id);
        List<AnimalBriefDTO> allAnimals = this.animalFacade.findAllAnimals();
        model.addAttribute("animal", animal);
        model.addAttribute("environments", environmentFacade.findEnvironmentsForAnimal(id));
        model.addAttribute("allEnvironments", environmentFacade.findAllEnvironments());
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

        return "animal/detail";
    }

    @RequestMapping(value = "/{id}/prey", method = POST)
    public String addPrey(@PathVariable long id,
                          Long prey,
                          Model model) {
        if (prey != null) {
            this.animalFacade.createAnimalEaten(id, prey);
        }
        return format("redirect:/animal/%s#food", id);
    }

    @RequestMapping(value = "/{id}/predator", method = POST)
    public String addPredator(@PathVariable long id,
                              Long predator,
                              Model model) {
        if (predator != null) {
            this.animalFacade.createAnimalEaten(predator, id);
        }
        return format("redirect:/animal/%s#food", id);
    }

    @RequestMapping(value = "/{id}/eaten/update", method = POST)
    public String updateAnimalEaten(@PathVariable long id,
                                    HttpServletRequest request) {
        for (long aeId : this.selectedIds(request.getParameterNames())) {
            String newCount = request.getParameter("count_" + aeId);
            if (newCount != null) {
                this.animalFacade.updateAnimalEaten(aeId, Double.valueOf(newCount));
            } else {
                this.animalFacade.updateAnimalEaten(aeId, null);
            }
        }
        return format("redirect:/animal/%s#food", id);
    }

    @RequestMapping(value = "/{id}/eaten/delete", method = POST)
    public String deleteAnimalEaten(@PathVariable long id
    ) {
        //TODO
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
