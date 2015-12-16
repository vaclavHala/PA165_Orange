package cz.muni.fi.pa165.dominatingspecies.sampledata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import cz.muni.fi.pa165.dominatingspecies.service.config.DominatingSpeciesServiceConfig;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.springframework.context.annotation.Import;

/**
 *
 * @author Petr Domkař
 */
@Configuration
@Import(DominatingSpeciesServiceConfig.class)
@ComponentScan(basePackageClasses = {SampleDataLoadingFacadeImpl.class})
public class DominatingSpeciesSampleDataConfiguration {

    final static Logger log = LoggerFactory.getLogger(DominatingSpeciesSampleDataConfiguration.class);

     @Inject
    private SampleDataLoadingFacade sampleDataFacade;
     
     @PostConstruct
    public void loadData() {
        sampleDataFacade.loadData();
    }
}
