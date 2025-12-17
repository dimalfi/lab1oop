package ua.edu.knight.repository;

import java.util.Locale;
import java.util.Objects;
import ua.edu.knight.model.ammunition.Ammunition;
import ua.edu.knight.model.ammunition.Armor;
import ua.edu.knight.model.ammunition.Boots;
import ua.edu.knight.model.ammunition.Helmet;
import ua.edu.knight.model.ammunition.Shield;
import ua.edu.knight.model.ammunition.Weapon;

final class AmmunitionFactory {

    Ammunition fromCsvLine(String csvLine) {
        Objects.requireNonNull(csvLine, "csvLine must not be null");

        // Expected:
        // TYPE,NAME,WEIGHT_KG,PRICE_GOLD,PARAM
        String[] parts = csvLine.split(",", -1);
        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid CSV record (expected 5 fields): " + csvLine);
        }

        String type = parts[0].trim().toUpperCase(Locale.ROOT);
        String name = parts[1].trim();
        double weightKg = parseDouble(parts[2].trim(), "WEIGHT_KG");
        double priceGold = parseDouble(parts[3].trim(), "PRICE_GOLD");
        int param = parseInt(parts[4].trim(), "PARAM");

        switch (type) {
            case "WEAPON":
                return new Weapon(name, weightKg, priceGold, param);
            case "ARMOR":
                return new Armor(name, weightKg, priceGold, param);
            case "HELMET":
                return new Helmet(name, weightKg, priceGold, param);
            case "SHIELD":
                return new Shield(name, weightKg, priceGold, param);
            case "BOOTS":
                return new Boots(name, weightKg, priceGold, param);
            default:
                throw new IllegalArgumentException("Unknown TYPE: " + type);
        }
    }

    private static double parseDouble(String value, String fieldName) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid " + fieldName + ": " + value, ex);
        }
    }

    private static int parseInt(String value, String fieldName) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid " + fieldName + ": " + value, ex);
        }
    }
}
