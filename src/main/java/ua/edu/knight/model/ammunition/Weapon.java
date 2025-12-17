package ua.edu.knight.model.ammunition;

public final class Weapon extends Ammunition {

    private final int damage;

    public Weapon(String name, double weightKg, double priceGold, int damage) {
        super(name, weightKg, priceGold);
        this.damage = validateDamage(damage);
    }

    @Override
    public AmmunitionCategory getCategory() {
        return AmmunitionCategory.WEAPON;
    }

    public int getDamage() {
        return damage;
    }

    private static int validateDamage(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("damage must be > 0");
        }
        return value;
    }
}
