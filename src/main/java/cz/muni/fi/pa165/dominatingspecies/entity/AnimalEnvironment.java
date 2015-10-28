package cz.muni.fi.pa165.dominatingspecies.entity;

import javax.persistence.*;

/**
 * Created by Petr Domkař on 25. 10. 2015.
 */

/**
 * Entity representing relationship between Animal and Environment.
 * Property percentage determines, how many environment animal needed.
 * @author Petr Domkař
 */
@Entity
public class AnimalEnvironment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(nullable = false)
    private Animal animal;

    @OneToOne
    @JoinColumn(nullable = false)
    private Environment environment;

    private double percentage;

    public AnimalEnvironment(Animal animal, Environment environment) {
        this.animal = animal;
        this.environment = environment;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (!(o instanceof AnimalEnvironment))
            return false;

        AnimalEnvironment animalEnv = (AnimalEnvironment) o;

        if (this.animal == null) {
            if (animalEnv.getAnimal() != null)
                return false;
        } else if (!this.animal.equals(animalEnv.getAnimal())) {
            return false;
        }
        if (this.environment == null) {
            if (animalEnv.getEnvironment() != null)
                return false;
        } else if (!this.environment.equals(animalEnv.getEnvironment())) {
            return false;
        }

        return true;
    }


    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + ((animal == null) ? 0 : animal.hashCode());
        result = 31 * result + ((environment == null) ? 0 : environment.hashCode());
        return result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
