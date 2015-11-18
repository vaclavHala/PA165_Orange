package cz.muni.fi.pa165.dominatingspecies.service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DominatingSpeciesServiceConfig.class)
@ComponentScan(basePackages = "cz.muni.fi.pa165.dominatingspecies.service")
public class DominatingSpeciesServiceConfig {
     
}
