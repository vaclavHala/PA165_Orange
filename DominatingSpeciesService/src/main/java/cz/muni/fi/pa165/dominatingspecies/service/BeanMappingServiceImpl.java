package cz.muni.fi.pa165.dominatingspecies.service;

import javax.inject.Inject;
import org.dozer.Mapper;

/**
 *
 * @author Petr
 */
public class BeanMappingServiceImpl implements BeanMappingService{

    @Inject
    private Mapper mapper;
    
    @Override
    public <T> T map(Object u, Class<T> mapToClass) {
        return mapper.map(u, mapToClass);
    }
    
}
