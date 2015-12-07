package cz.muni.fi.pa165.dominatingspecies.web.config;

import cz.muni.fi.pa165.dominatingspecies.service.config.DominatingSpeciesServiceConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DominatingSepciesWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
            DominatingSpeciesWebConfig.class,
            DominatingSpeciesServiceConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

}
