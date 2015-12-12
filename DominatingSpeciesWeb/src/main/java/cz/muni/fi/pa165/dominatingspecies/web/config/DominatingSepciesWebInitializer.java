package cz.muni.fi.pa165.dominatingspecies.web.config;

import cz.muni.fi.pa165.dominatingspecies.service.config.DominatingSpeciesServiceConfig;
import javax.servlet.Filter;
import org.springframework.web.filter.CharacterEncodingFilter;
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
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("utf-8");
        
        return new Filter[]{encodingFilter};
    }
    
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

}
