package cz.muni.fi.pa165.dominatingspecies.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;

@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

}
