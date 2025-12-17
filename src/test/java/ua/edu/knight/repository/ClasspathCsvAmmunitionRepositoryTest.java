package ua.edu.knight.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;
import ua.edu.knight.model.ammunition.Ammunition;
import ua.edu.knight.model.ammunition.Weapon;

final class ClasspathCsvAmmunitionRepositoryTest {

    @Test
    void shouldLoadAllFromClasspathCsv() {
        AmmunitionRepository repository = new ClasspathCsvAmmunitionRepository("equipment-test.csv");
        List<Ammunition> list = repository.loadAll();

        assertEquals(5, list.size());
        assertEquals("Test Sword", list.get(0).getName());
        assertEquals(Weapon.class, list.get(0).getClass());
    }

    @Test
    void shouldThrowIfResourceMissing() {
        AmmunitionRepository repository = new ClasspathCsvAmmunitionRepository("missing.csv");
        assertThrows(IllegalStateException.class, repository::loadAll);
    }
}
