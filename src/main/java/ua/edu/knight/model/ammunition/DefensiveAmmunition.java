package ua.edu.knight.model.ammunition;

public abstract class DefensiveAmmunition extends Ammunition {

    private final int defense;

    protected DefensiveAmmunition(String name, double weightKg, double priceGold, int defense) {
        super(name, weightKg, priceGold);
        this.defense = validateDefense(defense);
    }

    @Override
    public final AmmunitionCategory getCategory() {
        return AmmunitionCategory.DEFENSIVE;
    }

    public final int getDefense() {
        return defense;
    }

    private static int validateDefense(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("defense must be > 0");
        }
        return value;
    }
}
