package cz.muni.fi.pa165.dominatingspecies.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class AnimalEaten {

    @Id
    @Column(name = "animal_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private double animalCount;

    @JoinColumn(nullable = false)
    @OneToOne
    private Animal predator;

    @JoinColumn(nullable = false)
    @OneToOne
    private Animal prey;

    public AnimalEaten(Animal predator, Animal prey) {
        this.predator = predator;
        this.prey = prey;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AnimalEaten)) {
            return false;
        }
        AnimalEaten other = (AnimalEaten) obj;
        return this.predator.equals(other.getPredator())
            && this.prey.equals(other.getPrey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(predator, prey);
    }

    public long getId() {
        return id;
    }

    /**
     * To be used in tests only
     */
    void setId(long id) {
        this.id = id;
    }

    public double getAnimalCount() {
        return animalCount;
    }

    public void setAnimalCount(double animalCount) {
        this.animalCount = animalCount;
    }

    public Animal getPredator() {
        return predator;
    }

    public void setPredator(Animal predator) {
        this.predator = predator;
    }

    public Animal getPrey() {
        return prey;
    }

    public void setPrey(Animal prey) {
        this.prey = prey;
    }

}
