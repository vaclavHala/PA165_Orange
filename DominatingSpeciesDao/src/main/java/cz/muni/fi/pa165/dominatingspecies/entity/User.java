package cz.muni.fi.pa165.dominatingspecies.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Accessed by sql directly in auth,
 * this is here only so that db tables get generated
 * @author hala
 */
@Entity
@Table(name = "usr")
public class User {

    @Id
    private Long id;
    private String username;
    private String password;

}
