package ua.edu.knight.repository;

import java.util.List;
import ua.edu.knight.model.ammunition.Ammunition;

public interface AmmunitionRepository {

    List<Ammunition> loadAll();
}
