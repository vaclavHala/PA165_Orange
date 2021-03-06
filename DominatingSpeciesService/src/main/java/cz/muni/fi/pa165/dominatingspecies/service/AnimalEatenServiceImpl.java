/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dominatingspecies.service;

import cz.muni.fi.pa165.dominatingspecies.dao.AnimalEatenDao;
import cz.muni.fi.pa165.dominatingspecies.entity.Animal;
import cz.muni.fi.pa165.dominatingspecies.entity.AnimalEaten;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *
 * @author Petr
 */
@Service
public class AnimalEatenServiceImpl implements AnimalEatenService {

    @Inject
    private AnimalEatenDao animalEatenDao;

    @Override
    public AnimalEaten findById(long animalEatenId) {
        return animalEatenDao.findById(animalEatenId);
    }

    @Override
    public AnimalEaten findByAnimalsInvolved(long predatorId, long preyId) {
        return animalEatenDao.findByAnimalsInvolved(predatorId, preyId);
    }

    @Override
    public Collection<AnimalEaten> findPredatorsOf(Animal animal) {

        //could be done with a JPQL query but if performance is not
        //an issue, this should suffice (would need more methods on dao etc.)
        List<AnimalEaten> predators = new ArrayList<>();
        for (AnimalEaten ae : animalEatenDao.findAll()) {
            if (ae.getPrey().equals(animal)) {
                predators.add(ae);
            }
        }
        return predators;
    }

    @Override
    public Collection<AnimalEaten> findPreyOf(Animal animal) {
        List<AnimalEaten> prey = new ArrayList<>();
        for (AnimalEaten ae : animalEatenDao.findAll()) {
            if (ae.getPredator().equals(animal)) {
                prey.add(ae);
            }
        }
        return prey;
    }

    @Override
    public void createAnimalEaten(AnimalEaten animalEaten) {
        Objects.requireNonNull(animalEaten);
        Objects.requireNonNull(animalEaten.getPredator());
        Objects.requireNonNull(animalEaten.getPrey());
        if (animalEaten.getPredator().getId() == null) {
            throw new IllegalArgumentException("Predator must be persisted first, got: " + animalEaten);
        }
        if (animalEaten.getPrey().getId() == null) {
            throw new IllegalArgumentException("Prey must be persisted first, got: " + animalEaten);
        }
        animalEatenDao.create(animalEaten);
    }

    @Override
    public void updateCount(long animalEatenId, Double newCount) {
        AnimalEaten ae = this.animalEatenDao.findById(animalEatenId);
        if (ae == null) {
            throw new IllegalArgumentException("Cant update non-existing animal with id " + animalEatenId);
        }
        ae.setAnimalCount(newCount);
        animalEatenDao.update(ae);
    }

    @Override
    public void remove(AnimalEaten animalEaten) {
        animalEatenDao.remove(animalEaten);
    }

    @Override
    public void removeAllFor(Animal animal) {
        for (AnimalEaten ae : animalEatenDao.findAll()) {
            if (ae.getPredator().equals(animal)
                    || ae.getPrey().equals(animal)) {
                animalEatenDao.remove(ae);
            }
        }
    }

}
