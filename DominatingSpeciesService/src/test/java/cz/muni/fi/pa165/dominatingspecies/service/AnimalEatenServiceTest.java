package cz.muni.fi.pa165.dominatingspecies.service;

import cz.muni.fi.pa165.dominatingspecies.dao.AnimalEatenDao;
import cz.muni.fi.pa165.dominatingspecies.entity.Animal;
import cz.muni.fi.pa165.dominatingspecies.entity.AnimalEaten;
import static java.util.Arrays.asList;
import java.util.Collection;
import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.api.iterable.Extractor;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class AnimalEatenServiceTest {

    @Mock
    private AnimalEatenDao animalEatenDao;

    @InjectMocks
    private AnimalEatenServiceImpl animalEatenService;

    private ArgumentCaptor<AnimalEaten> captor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.captor = new ArgumentCaptor<>();
    }

    @Test
    public void testCreateValid() {
        animalEatenService.createAnimalEaten(new AnimalEaten(existingCat(), existingFish()));
        verify(animalEatenDao, times(1)).create(captor.capture());
        assertThat(captor.getValue().getPredator().getId()).isEqualTo(existingCat().getId());
        assertThat(captor.getValue().getPrey().getId()).isEqualTo(existingFish().getId());
    }

    @Test(expected = NullPointerException.class)
    public void testCreateNullAnimalEaten() {
        animalEatenService.createAnimalEaten(null);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateNullPreadtor() {
        animalEatenService.createAnimalEaten(new AnimalEaten(null, existingFish()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreatePredatorNullId() {
        Animal newCat = new Animal("Cat", "Cats");
        animalEatenService.createAnimalEaten(new AnimalEaten(newCat, existingFish()));
    }

    @Test(expected = NullPointerException.class)
    public void testCreateNullPrey() {
        animalEatenService.createAnimalEaten(new AnimalEaten(existingCat(), null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreatePreyNullId() {
        Animal newFish = new Animal("Fish", "Fish");
        animalEatenService.createAnimalEaten(new AnimalEaten(existingCat(), newFish));
    }

    @Test
    public void testFindKnown() {
        when(animalEatenDao.findById(11L)).thenReturn(new AnimalEaten(existingCat(), existingFish()));
        AnimalEaten ae = animalEatenService.findById(11);
        assertThat(ae.getPredator()).isEqualTo(existingCat());
        assertThat(ae.getPrey()).isEqualTo(existingFish());
    }

    @Test
    public void testFindUnknown() {
        assertThat(animalEatenService.findById(10L)).isNull();
    }

    @Test
    @Ignore
    public void testUpdateValid() {
//        AnimalEaten ae = new AnimalEaten(existingCat(), existingFish());
//        ae.setId(10L);
//        animalEatenService.update(ae);
//        verify(animalEatenDao, times(1)).update(captor.capture());
//        assertThat(captor.getValue().getId()).isEqualTo(ae.getId());
//        assertThat(captor.getValue().getPredator().getId()).isEqualTo(existingCat().getId());
//        assertThat(captor.getValue().getPrey().getId()).isEqualTo(existingFish().getId());
    }

    @Test
    public void testDeleteValid() {
        AnimalEaten ae = new AnimalEaten(existingCat(), existingFish());
        ae.setId(10L);
        animalEatenService.remove(ae);
        verify(animalEatenDao, times(1)).remove(captor.capture());
        assertThat(captor.getValue().getId()).isEqualTo(ae.getId());
        assertThat(captor.getValue().getPredator().getId()).isEqualTo(existingCat().getId());
        assertThat(captor.getValue().getPrey().getId()).isEqualTo(existingFish().getId());
    }

    @Test
    public void testFindPredators() {
        when(animalEatenDao.findAll()).thenReturn(asList(
                new AnimalEaten(existingCat(), existingFish()),
                new AnimalEaten(existingCat(), existingHorse()),
                new AnimalEaten(existingFish(), existingHorse()),
                new AnimalEaten(existingFish(), existingFish())
        ));

        Collection<AnimalEaten> preys = animalEatenService.findPredatorsOf(existingFish());
        assertThat(preys).hasSize(2);
        assertThat(preys).containsOnly(new AnimalEaten(existingCat(), existingFish()), new AnimalEaten(existingFish(), existingFish()));

        /* assertThat(animalEatenService.findPredatorsOf(existingFish()))
         * .extracting("id")
         * .containsOnly(existingCat().getId(),
         * existingFish().getId()); */
    }

    @Test
    public void testAnimaleItselfIsPreyWhenListPredators() {
        when(animalEatenDao.findAll()).thenReturn(asList(
                new AnimalEaten(existingCat(), existingFish()),
                new AnimalEaten(existingCat(), existingHorse()),
                new AnimalEaten(existingFish(), existingHorse()),
                new AnimalEaten(existingFish(), existingFish())
        ));
        assertThat(animalEatenService.findPredatorsOf(existingFish()))
                .extracting(new Extractor<AnimalEaten, Long>() {
                    public Long extract(AnimalEaten f) {
                        return f.getPredator().getId();
                    }
                })
                .containsOnly(existingCat().getId(),
                              existingFish().getId());
    }

    @Test
    public void testFindPrey() {
        AnimalEaten aeCF = new AnimalEaten(existingCat(), existingFish());
        AnimalEaten aeCH = new AnimalEaten(existingCat(), existingHorse());
        AnimalEaten aeFH = new AnimalEaten(existingFish(), existingHorse());
        AnimalEaten aeFF = new AnimalEaten(existingFish(), existingFish());
        when(animalEatenDao.findAll()).thenReturn(asList(
                aeCF, aeCH, aeFH, aeFF
        ));

        Collection<AnimalEaten> preys = animalEatenService.findPreyOf(existingCat());
        assertThat(preys).hasSize(2);
        assertThat(preys).containsOnly(aeCF, aeCH);
    }

    private Animal existingCat() {
        Animal cat = new Animal("Cat", "Cats");
        cat.setId(1L);
        return cat;
    }

    private Animal existingFish() {
        Animal fish = new Animal("Fish", "Fish");
        fish.setId(2L);
        return fish;
    }

    private Animal existingHorse() {
        Animal horse = new Animal("Horse", "Horses");
        horse.setId(3L);
        return horse;
    }

}
