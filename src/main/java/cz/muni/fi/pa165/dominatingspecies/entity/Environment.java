package cz.muni.fi.pa165.dominatingspecies.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Ivan Kralik
 */
@Entity
public class Environment implements Serializable {
    
    @Id
    @GeneratedValue
    @Column
    private Long id;
    
    @Column
    private String name;
    
    @Column
    private String description;
    
    @Column
    private Long maxAnimalConut;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getMaxAnimalConut() {
        return maxAnimalConut;
    }

    public void setMaxAnimalConut(Long maxAnimalConut) {
        this.maxAnimalConut = maxAnimalConut;
    }
}
