package cz.muni.fi.pa165.dominatingspecies.service;


/**
 *
 * @author Petr
 */
public interface BeanMappingService {
    public <T> T map(Object u, Class<T> mapToClass);
}
