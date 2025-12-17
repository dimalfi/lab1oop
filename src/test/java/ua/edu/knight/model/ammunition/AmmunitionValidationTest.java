package ua.edu.knight.model.ammunition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

final class AmmunitionValidationTest {

    @Test
    void weaponShouldValidateFields() {
        Weapon weapon = new Weapon("Sword", 2.0, 10.0, 5);
        assertEquals("Sword", weapon.getName());
        assertEquals(2.0, weapon.getWeightKg(), 0.0001);
        assertEquals(10.0, weapon.getPriceGold(), 0.0001);
        assertEquals(5, weapon.getDamage());
        assertEquals(AmmunitionCategory.WEAPON, weapon.getCategory());
    }

    @Test
    void defensiveShouldValidateDefense() {
        Helmet helmet = new Helmet("Helmet", 1.0, 20.0, 3);
        assertEquals(3, helmet.getDefense());
        assertEquals(AmmunitionCategory.DEFENSIVE, helmet.getCategory());
    }

    @Test
    void shouldRejectInvalidValues() {
        assertThrows(IllegalArgumentException.class, () -> new Weapon("Sword", 0.0, 10.0, 5));
        assertThrows(IllegalArgumentException.class, () -> new Weapon("Sword", 1.0, -1.0, 5));
        assertThrows(IllegalArgumentException.class, () -> new Weapon("Sword", 1.0, 1.0, 0));
        assertThrows(IllegalArgumentException.class, () -> new Helmet("Helmet", 1.0, 1.0, 0));
    }
}
