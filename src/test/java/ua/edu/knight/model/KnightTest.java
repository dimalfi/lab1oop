package ua.edu.knight.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;
import ua.edu.knight.model.ammunition.Armor;
import ua.edu.knight.model.ammunition.Weapon;

final class KnightTest {

    @Test
    void shouldCalculateTotalCost() {
        Knight knight = new Knight("Arthur");
        knight.equip(new Weapon("Sword", 3.0, 75.0, 10));
        knight.equip(new Armor("Armor", 10.0, 200.0, 30));

        assertEquals(275.0, knight.getTotalCostGold(), 0.0001);
    }

    @Test
    void shouldSortEquipmentByWeightAscending() {
        Knight knight = new Knight("Arthur");
        knight.equip(new Weapon("Heavy", 5.0, 10.0, 1));
        knight.equip(new Weapon("Light", 1.0, 10.0, 1));
        knight.equip(new Weapon("Medium", 3.0, 10.0, 1));

        List<?> sorted = knight.getEquipmentSortedByWeight();
        assertEquals("Light", ((ua.edu.knight.model.ammunition.Ammunition) sorted.get(0)).getName());
        assertEquals("Medium", ((ua.edu.knight.model.ammunition.Ammunition) sorted.get(1)).getName());
        assertEquals("Heavy", ((ua.edu.knight.model.ammunition.Ammunition) sorted.get(2)).getName());
    }

    @Test
    void shouldFindByPriceRangeInclusive() {
        Knight knight = new Knight("Arthur");
        knight.equip(new Weapon("A", 1.0, 10.0, 1));
        knight.equip(new Weapon("B", 1.0, 20.0, 1));
        knight.equip(new Weapon("C", 1.0, 30.0, 1));

        List<?> found = knight.findByPriceRange(10.0, 20.0);
        assertEquals(2, found.size());
        assertEquals("A", ((ua.edu.knight.model.ammunition.Ammunition) found.get(0)).getName());
        assertEquals("B", ((ua.edu.knight.model.ammunition.Ammunition) found.get(1)).getName());
    }

    @Test
    void shouldRejectInvalidPriceRange() {
        Knight knight = new Knight("Arthur");
        assertThrows(IllegalArgumentException.class, () -> knight.findByPriceRange(10.0, 1.0));
    }

    @Test
    void shouldRejectBlankName() {
        assertThrows(IllegalArgumentException.class, () -> new Knight("   "));
    }
}
