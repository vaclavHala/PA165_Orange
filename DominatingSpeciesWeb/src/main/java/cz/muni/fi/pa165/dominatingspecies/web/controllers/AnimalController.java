package cz.muni.fi.pa165.dominatingspecies.web.controllers;

import cz.muni.fi.pa165.dominatingspecies.dto.AnimalNewDTO;
import cz.muni.fi.pa165.dominatingspecies.facade.AnimalFacade;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/animal")
public class AnimalController {

    @Inject
    private AnimalFacade animalFacade;

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
        redirectAttributes.addFlashAttribute("alert_success", "Animal " + id + " was created");
        return "redirect:/animal/";
    }

}
