package cz.muni.fi.pa165.dominatingspecies.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import org.dozer.Mapper;

/**
 *
 * @author Petr
 */
public class BeanMappingServiceImpl implements BeanMappingService{

    @Inject
    private Mapper mapper;
    @Inject
    private Mapper dozer;
    
    @Override
    public <T> T map(Object u, Class<T> mapToClass) {
        return mapper.map(u, mapToClass);
    }
    
    @Override
    public <T> List<T> map(Collection<?> objects, Class<T> mapToClass) {
        List<T> mCollection = new ArrayList();
        for(Object o : objects) {
            mCollection.add(dozer.map(o, mapToClass));
        }
        return mCollection;
    }
    
}
