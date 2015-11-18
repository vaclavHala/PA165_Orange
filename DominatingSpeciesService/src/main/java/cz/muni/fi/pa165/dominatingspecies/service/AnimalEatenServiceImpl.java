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
import org.springframework.stereotype.Service;

/**
 *
 * @author Petr
 */
@Service
public class AnimalEatenServiceImpl implements AnimalEatenService{
    @Inject
    private AnimalEatenDao animalEatenDao;
    
    @Override
    public Collection<Animal> findPredatorOf(Animal animal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Animal> findPreyOf(Animal animal) {
        List<AnimalEaten> animalsEaten = animalEatenDao.findAll();
        List<Animal> filtered = new ArrayList<>();
        for(AnimalEaten a : animalsEaten) {
            if(!a.getPredator().equals(animal)) {
                filtered.add(a.getPrey());
            }
        }
        return filtered;
    }
    
    @Override
    public void createAnimalEaten(AnimalEaten animalEaten) {
        animalEatenDao.create(animalEaten);
    }
    
    @Override
    public void update(AnimalEaten animalEaten) {
        
    }
    
    @Override
    public void remove(AnimalEaten animalEaten) {
        animalEatenDao.remove(animalEaten);
    }
    
}
