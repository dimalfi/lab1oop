package ua.edu.knight.model.ammunition;

import java.util.Objects;

public abstract class Ammunition {

    private final String name;
    private final double weightKg;
    private final double priceGold;

    protected Ammunition(String name, double weightKg, double priceGold) {
        this.name = validateName(name);
        this.weightKg = validatePositive(weightKg, "weightKg");
        this.priceGold = validateNonNegative(priceGold, "priceGold");
    }

    public final String getName() {
        return name;
    }

    public final double getWeightKg() {
        return weightKg;
    }

    public final double getPriceGold() {
        return priceGold;
    }

    public abstract AmmunitionCategory getCategory();

    private static String validateName(String value) {
        Objects.requireNonNull(value, "name must not be null");
        String trimmed = value.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        return trimmed;
    }

    private static double validatePositive(double value, String field) {
        if (value <= 0.0) {
            throw new IllegalArgumentException(field + " must be > 0");
        }
        return value;
    }

    private static double validateNonNegative(double value, String field) {
        if (value < 0.0) {
            throw new IllegalArgumentException(field + " must be >= 0");
        }
        return value;
    }
}
