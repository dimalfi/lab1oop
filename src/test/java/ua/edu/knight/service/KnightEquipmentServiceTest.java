package ua.edu.knight.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.edu.knight.model.Knight;
import ua.edu.knight.model.ammunition.Ammunition;
import ua.edu.knight.model.ammunition.Weapon;
import ua.edu.knight.repository.AmmunitionRepository;

@ExtendWith(MockitoExtension.class)
final class KnightEquipmentServiceTest {

    @Mock
    private AmmunitionRepository repository;

    @Test
    void shouldEquipKnightWithLoadedAmmunition() {
        List<Ammunition> items = List.of(
                new Weapon("Sword", 3.0, 75.0, 10),
                new Weapon("Dagger", 1.0, 30.0, 6)
        );
        when(repository.loadAll()).thenReturn(items);

        KnightEquipmentService service = new KnightEquipmentService(repository);
        Knight knight = service.createEquippedKnight("Lancelot");

        verify(repository).loadAll();
        assertEquals("Lancelot", knight.getName());
        assertEquals(2, knight.getEquipment().size());
        assertEquals(105.0, knight.getTotalCostGold(), 0.0001);
    }
}
