package ua.edu.knight.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import ua.edu.knight.model.ammunition.Ammunition;

public final class Knight {

    private final String name;
    private final List<Ammunition> equipment;

    public Knight(String name) {
        this.name = validateName(name);
        this.equipment = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void equip(Ammunition item) {
        Objects.requireNonNull(item, "item must not be null");
        equipment.add(item);
    }

    public void equipAll(List<Ammunition> items) {
        Objects.requireNonNull(items, "items must not be null");
        for (Ammunition item : items) {
            equip(item);
        }
    }

    public List<Ammunition> getEquipment() {
        return Collections.unmodifiableList(equipment);
    }

    public double getTotalCostGold() {
        double sum = 0.0;
        for (Ammunition item : equipment) {
            sum += item.getPriceGold();
        }
        return sum;
    }

    public List<Ammunition> getEquipmentSortedByWeight() {
        List<Ammunition> sorted = new ArrayList<>(equipment);
        sorted.sort(Comparator.comparingDouble(Ammunition::getWeightKg));
        return Collections.unmodifiableList(sorted);
    }

    public List<Ammunition> findByPriceRange(double minInclusive, double maxInclusive) {
        if (minInclusive > maxInclusive) {
            throw new IllegalArgumentException("minInclusive must be <= maxInclusive");
        }

        List<Ammunition> result = new ArrayList<>();
        for (Ammunition item : equipment) {
            double price = item.getPriceGold();
            if (price >= minInclusive && price <= maxInclusive) {
                result.add(item);
            }
        }
        return Collections.unmodifiableList(result);
    }

    private static String validateName(String value) {
        Objects.requireNonNull(value, "name must not be null");
        String trimmed = value.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        return trimmed;
    }
}
