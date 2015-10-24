package cz.muni.fi.pa165.dominatingspecies;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Ivan Kralik
 */
public class Main {
    
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DominatingSpeciesConfig.class);
    }
}
