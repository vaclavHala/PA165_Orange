package cz.muni.fi.pa165.dominatingspecies.config;

import cz.muni.fi.pa165.dominatingspecies.dto.EnvironmentDTO;
import cz.muni.fi.pa165.dominatingspecies.entity.Environment;
import cz.muni.fi.pa165.dominatingspecies.service.config.DominatingSpeciesServiceConfig;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DominatingSpeciesServiceConfig.class)
@ComponentScan(basePackages = "cz.muni.fi.pa165.dominatingspecies.service")
public class DominatingSpeciesApiConfig {
    
    @Bean
    public Mapper dozer() {
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.addMapping(new CustomBeanMappingBuilder());
        
        return dozer;
    }

    public class CustomBeanMappingBuilder extends BeanMappingBuilder {

        @Override
        protected void configure() {
            mapping(Environment.class, EnvironmentDTO.class);
        }
    }
}
