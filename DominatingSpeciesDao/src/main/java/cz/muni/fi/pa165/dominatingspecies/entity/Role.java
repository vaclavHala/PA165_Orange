package cz.muni.fi.pa165.dominatingspecies.entity;

import java.util.Set;
import javax.persistence.*;

/**
 * Accessed by sql directly in auth,
 * this is here only so that db tables get generated
 * @author hala
 */
@Entity
public class Role {

    @Id
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "role_user",
               joinColumns = @JoinColumn(name = "fk_role"),
               inverseJoinColumns = @JoinColumn(name = "fk_user"))
    private Set<User> user;

}
