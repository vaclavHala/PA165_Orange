package cz.muni.fi.pa165.dominatingspecies.entity;

import javax.persistence.*;

/**
 * Created by Petr on 25. 10. 2015.
 */

@Entity
public class AnimalEnvironment {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @OneToOne
    @JoinColumn(nullable = false)
    private Animal animal;

    @Column
    @OneToOne
    @JoinColumn(nullable = false)
    private Environment environment;

    @Column
    private float percentage;


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

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}
