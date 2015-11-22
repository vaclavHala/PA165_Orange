package cz.muni.fi.pa165.dominatingspecies.entity;

import java.util.Objects;
import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;
import javax.validation.constraints.NotNull;

/**
 * Entity representing one animal eating another animal, predator eats prey.
 * Observations about approximate count of animals eaten per month is represented by the animalCount
 * @author Vaclav Hala 422551
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"fk_predator_id", "fk_prey_id"}))
public class AnimalEaten {

    @Id
    @Column(name = "animal_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private double animalCount;

    @OneToOne
    @NotNull
    @JoinColumn(name = "fk_predator_id")
    private Animal predator;

    @OneToOne
    @NotNull
    @JoinColumn(name = "fk_prey_id")
    private Animal prey;

    public AnimalEaten() {
    }

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

    public Long getId() {
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
