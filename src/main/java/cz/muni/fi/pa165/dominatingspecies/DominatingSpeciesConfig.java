package cz.muni.fi.pa165.dominatingspecies;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Ivan Kralik
 */
@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableJpaRepositories
@ComponentScan(basePackages = "cz.muni.fi.pa165.dominatingspecies")
public class DominatingSpeciesConfig {
    
}
