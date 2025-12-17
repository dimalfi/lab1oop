package ua.edu.knight.service;

import java.util.List;
import java.util.Objects;
import ua.edu.knight.model.Knight;
import ua.edu.knight.model.ammunition.Ammunition;
import ua.edu.knight.repository.AmmunitionRepository;

public final class KnightEquipmentService {

    private final AmmunitionRepository repository;

    public KnightEquipmentService(AmmunitionRepository repository) {
        this.repository = Objects.requireNonNull(repository, "repository must not be null");
    }

    public Knight createEquippedKnight(String knightName) {
        Knight knight = new Knight(knightName);
        List<Ammunition> ammunition = repository.loadAll();
        knight.equipAll(ammunition);
        return knight;
    }
}
