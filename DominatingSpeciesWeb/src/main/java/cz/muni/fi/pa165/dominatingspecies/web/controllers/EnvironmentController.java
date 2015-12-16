package cz.muni.fi.pa165.dominatingspecies.web.controllers;

import cz.muni.fi.pa165.dominatingspecies.dto.EnvironmentDTO;
import cz.muni.fi.pa165.dominatingspecies.facade.AnimalFacade;
import cz.muni.fi.pa165.dominatingspecies.facade.EnvironmentFacade;
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

@Controller
@RequestMapping("/environment")
public class EnvironmentController {

    @Inject
    private EnvironmentFacade facade;
    
    @RolesAllowed(DominatingSpeciesSecurityConfig.ROLE_ADMIN)
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String update(@PathVariable long id, @Valid @ModelAttribute("environment") EnvironmentDTO formBean, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            
            return "environment/view";
        }
        
        formBean.setId(id);
        facade.updateEnvironment(formBean);
        redirectAttributes.addFlashAttribute("alert_success", "Environment " + id + " was updated");
        
        return "redirect:" + uriBuilder.path("/environment/{id}").buildAndExpand(id).encode().toUriString();
    }
    
    @RolesAllowed(DominatingSpeciesSecurityConfig.ROLE_ADMIN)
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("newEnvironment") EnvironmentDTO formBean, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                model.addAttribute(fe.getField() + "_error_message", fe.getDefaultMessage());
            }
            
            model.addAttribute("environments", facade.findAllEnvironments());
            
            return "environment/list";
        }
        
        Long id = facade.createEnvironment(formBean);
        
        redirectAttributes.addFlashAttribute("alert_success", "Environment " + id + " was created");
        
        return "redirect:/environment/";
    }
    
    @RolesAllowed(DominatingSpeciesSecurityConfig.ROLE_ADMIN)
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        EnvironmentDTO environment = facade.findEnvironment(id);
        facade.deleteEnvironment(id);
        
        redirectAttributes.addFlashAttribute("alert_success", "Environment \"" + environment.getName() + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/environment/").toUriString();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        model.addAttribute("environment", facade.findEnvironment(id));
        model.addAttribute("animals", facade.findAnimalsInEnvironment(id));
        
        return "environment/view";
    }
    
    @RequestMapping(value = "/", method = GET)
    public String list(Model model) {
        model.addAttribute("environments", facade.findAllEnvironments());
        model.addAttribute("newEnvironment", new EnvironmentDTO());
        
        return "environment/list";
    }
}
