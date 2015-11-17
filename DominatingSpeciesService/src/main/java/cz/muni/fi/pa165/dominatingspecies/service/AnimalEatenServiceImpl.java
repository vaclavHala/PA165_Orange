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
import javax.inject.Inject;

/**
 *
 * @author Petr
 */
public class AnimalEatenServiceImpl implements AnimalEatenService{
    @Inject
    private AnimalEatenDao animalEatenDao;
    
    @Override
    public Collection<Animal> findPredatorOf(Animal animal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Animal> findPreyOf(Animal animal) {
        List<AnimalEaten> animalsEaten = animalEatenDao.findAll(); //nechat jen ty co predator = animal
        List<Animal> filtered = new ArrayList<>();
        for(AnimalEaten a : animalsEaten) {
            if(!a.getPredator().equals(animal)) {
                filtered.add(a.getPredator());
            }
        }
        return filtered;
    }
    
}
