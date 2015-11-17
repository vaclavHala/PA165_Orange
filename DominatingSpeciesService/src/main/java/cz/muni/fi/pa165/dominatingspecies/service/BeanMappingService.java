package cz.muni.fi.pa165.dominatingspecies.service;

import java.util.Collection;
import java.util.List;


/**
 *
 * @author Petr
 */
public interface BeanMappingService {
    public <T> T map(Object u, Class<T> mapToClass);
    public <T> List<T> map(Collection<?> objects, Class<T> mapToClass);
}
