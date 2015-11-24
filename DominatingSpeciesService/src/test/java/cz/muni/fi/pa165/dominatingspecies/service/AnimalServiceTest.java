package cz.muni.fi.pa165.dominatingspecies.service;

import cz.muni.fi.pa165.dominatingspecies.dao.AnimalDao;
import cz.muni.fi.pa165.dominatingspecies.entity.Animal;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author hala
 */
public class AnimalServiceTest {

    @Mock
    private AnimalDao animalDao;

    @InjectMocks
    private AnimalServiceImpl animalService;

    private ArgumentCaptor<Animal> captor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.captor = new ArgumentCaptor<>();
    }

    @Test
    public void testCreateValid() {
        animalService.create(newCat());
        verify(animalDao, times(1)).create(captor.capture());
        assertThat(captor.getValue().getName()).isEqualTo(newCat().getName());
        assertThat(captor.getValue().getSpecies()).isEqualTo(newCat().getSpecies());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateSetId() {
        Animal cat = newCat();
        cat.setId(10L);
        animalService.create(cat);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateMissingData() {
        Animal cat = newCat();
        cat.setName(null);
        animalService.create(cat);
    }

    @Test
    public void testFind() {
        Animal cat = newCat();
        cat.setId(1L);
        when(animalDao.getById(1L)).thenReturn(cat);
        assertThat(animalService.findById(1).getId()).isEqualTo(cat.getId());
    }

    @Test
    public void testFindAllAnimals() {
        Animal cat = newCat();
        cat.setId(1L);
        Animal fish = newFish();
        fish.setId(2L);
        when(animalDao.findAll()).thenReturn(asList(cat, fish));
        assertThat(animalService.findAll())
            .extracting("id")
            .containsOnly(1L, 2L);
    }

    @Test
    public void testUpdateValid() {
        Animal cat = newCat();
        cat.setId(1L);
        animalService.update(cat);
        verify(animalDao, times(1)).update(captor.capture());
        assertThat(captor.getValue().getId()).isEqualTo(cat.getId());
        assertThat(captor.getValue().getName()).isEqualTo(cat.getName());
        assertThat(captor.getValue().getSpecies()).isEqualTo(cat.getSpecies());
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateNullId() {
        animalService.update(newFish());
    }

    @Test
    public void testDeleteValid() {
        Animal cat = newCat();
        cat.setId(1L);
        animalService.remove(cat);
        verify(animalDao, times(1)).remove(captor.capture());
        assertThat(captor.getValue().getId()).isEqualTo(cat.getId());
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteNullId() {
        animalService.remove(newFish());
    }

    private Animal newCat() {
        return new Animal("Cat", "Cats");
    }

    private Animal newFish() {
        return new Animal("Fish", "Fish");
    }

}
