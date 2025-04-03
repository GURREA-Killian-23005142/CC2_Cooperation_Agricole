
import fr.univamu.fr.agricole.Commandes;
import fr.univamu.fr.agricole.CommandesRepositoryInterface;
import fr.univamu.fr.agricole.CommandesResource;
import fr.univamu.fr.agricole.CommandesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommandesRepositoryMariadbTest {

    @Mock
    private CommandesRepositoryInterface repository;

    @InjectMocks
    private CommandesService commandesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAjouterCommande() {
        Commandes commande = new Commandes(1, 22, 34.0, "relais nord", LocalDate.of(2025, 4, 22));
        when(repository.ajouterCommande(commande)).thenReturn(true);

        boolean result = commandesService.ajouterCommande(commande);
        assertTrue(result);
        verify(repository, times(1)).ajouterCommande(commande);
    }

    @Test
    void testSupprimerCommande() {
        when(repository.supprimerCommande(1)).thenReturn(true);
        boolean result = commandesService.supprimerCommande(1);
        assertTrue(result);
        verify(repository, times(1)).supprimerCommande(1);
    }

    @Test
    void testMettreAjourCommande() {
        when(repository.mettreAjourCommande(1, 22, 34.0, "relais nord", LocalDate.of(2025, 4, 22))).thenReturn(true);
        boolean result = commandesService.mettreAjourCommande(1, new Commandes(1, 22, 34.0, "relais nord", LocalDate.of(2025, 4, 22)));
        assertTrue(result);
        verify(repository, times(1)).mettreAjourCommande(eq(1), eq(22), eq(34.0), eq("relais nord"), eq(LocalDate.of(2025, 4, 22)));
    }
}
