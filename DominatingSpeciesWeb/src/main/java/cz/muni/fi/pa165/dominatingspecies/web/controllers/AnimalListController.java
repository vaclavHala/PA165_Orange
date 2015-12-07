package cz.muni.fi.pa165.dominatingspecies.web.controllers;

import cz.muni.fi.pa165.dominatingspecies.facade.AnimalFacade;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/animal")
public class AnimalListController {

    @Inject
    private AnimalFacade animalFacade;

    @RequestMapping(value = "/list", method = GET)
    public String list(Model model) {
        model.addAttribute("animals", animalFacade.findAllAnimals());
        return "animal/list";
    }

}
