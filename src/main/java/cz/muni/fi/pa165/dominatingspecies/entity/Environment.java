package cz.muni.fi.pa165.dominatingspecies.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Ivan Kralik
 */
@Entity
public class Environment implements Serializable {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    
    @NotNull
    private String name;
    
    private String description;
    
    @NotNull
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
    
    @Override
    public int hashCode() {
        final int prime = 31;
        
        return prime + ((name == null) ? 0 : name.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null) {
            return false;
        }
        
        if (!(obj instanceof Environment)) {
            return false;
        }
        
        Environment other = (Environment) obj;
        
        if (name == null) {
            return other.getName() == null;
        }
        
        return name.equals(other.getName());
    }
}
