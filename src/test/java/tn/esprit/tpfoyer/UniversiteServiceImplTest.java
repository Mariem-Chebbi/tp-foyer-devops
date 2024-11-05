package tn.esprit.tpfoyer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.UniversiteRepository;
import tn.esprit.tpfoyer.service.UniversiteServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class UniversiteServiceImplTest {

    @Mock
    private UniversiteRepository universiteRepository;

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    private Universite universite;
    private List<Universite> listUniversites;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialisation des objets de test
        universite = new Universite();
        universite.setNomUniversite("Université de Test");
        universite.setAdresse("123 Rue Exemple");

        listUniversites = new ArrayList<Universite>() {{
            add(new Universite(0, "Université 1", "Adresse 1", null));
            add(new Universite(0, "Université 2", "Adresse 2", null));
        }};
    }

    @Test
    public void testRetrieveUniversite() {
        // Arrange
        when(universiteRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(universite));

        // Act
        Universite result = universiteService.retrieveUniversite(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Université de Test", result.getNomUniversite());
        assertEquals("123 Rue Exemple", result.getAdresse());
    }

    @Test
    public void testRetrieveAllUniversites() {
        // Arrange
        when(universiteRepository.findAll()).thenReturn(listUniversites);

        // Act
        List<Universite> result = universiteService.retrieveAllUniversites();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testAddUniversite() {
        // Arrange
        when(universiteRepository.save(universite)).thenReturn(universite);

        // Act
        Universite result = universiteService.addUniversite(universite);

        // Assert
        assertNotNull(result);
        assertEquals("Université de Test", result.getNomUniversite());
        assertEquals("123 Rue Exemple", result.getAdresse());
    }

    @Test
    public void testModifyUniversite() {
        // Arrange
        when(universiteRepository.save(universite)).thenReturn(universite);

        // Act
        Universite result = universiteService.modifyUniversite(universite);

        // Assert
        assertNotNull(result);
        assertEquals("Université de Test", result.getNomUniversite());
        assertEquals("123 Rue Exemple", result.getAdresse());
    }

    @Test
    public void testRemoveUniversite() {
        // Act
        universiteService.removeUniversite(1L);

        // Assert
        Mockito.verify(universiteRepository, Mockito.times(1)).deleteById(1L);
    }
}
