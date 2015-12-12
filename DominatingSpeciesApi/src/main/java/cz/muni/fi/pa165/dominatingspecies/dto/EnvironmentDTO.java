package cz.muni.fi.pa165.dominatingspecies.dto;

import java.util.Objects;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Ivan Kralik
 */
public class EnvironmentDTO {
    
    private Long id;
    
    @NotNull
    @Size(min = 1, max = 255)
    private String name;
    
    private String description;
    
    @NotNull
    @Min(1)
    private Long maxAnimalCount;

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
        return 89 * 7 + Objects.hashCode(this.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null) {
            return false;
        }
        
        if (!(obj instanceof EnvironmentDTO)) {
            return false;
        }
        
        EnvironmentDTO other = (EnvironmentDTO) obj;
        
        return getId() != null && other.getId() != null && getId().equals(other.getId());
    }
    
    
}
