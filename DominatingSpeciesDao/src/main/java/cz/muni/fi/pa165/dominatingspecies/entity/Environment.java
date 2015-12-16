package cz.muni.fi.pa165.dominatingspecies.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.OneToMany;

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
    private Long maxAnimalCount;
    
    @OneToMany(cascade = {CascadeType.REMOVE}, mappedBy = "environment")
    private Set<AnimalEnvironment> animalEnvironments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getMaxAnimalCount() {
        return maxAnimalCount;
    }

    public void setMaxAnimalCount(Long maxAnimalCount) {
        this.maxAnimalCount = maxAnimalCount;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        
        return prime + ((getId() == null) ? 0 : getId().hashCode());
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
        
        return getId() != null && other.getId() != null && getId().equals(other.getId());
    }
}
