package cz.muni.fi.pa165.dominatingspecies.web.config;

import cz.muni.fi.pa165.dominatingspecies.sampledata.SampleDataLoadingFacade;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "cz.muni.fi.pa165.dominatingspecies")
public class DominatingSpeciesWebConfig extends WebMvcConfigurerAdapter {

    @Inject
    private SampleDataLoadingFacade sampleDataFacade;
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");

    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @PostConstruct
    public void loadData() {
        sampleDataFacade.loadData();
    }
}
