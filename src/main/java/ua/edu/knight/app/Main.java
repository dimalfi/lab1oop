package ua.edu.knight.app;

import java.util.List;
import ua.edu.knight.model.Knight;
import ua.edu.knight.model.ammunition.Ammunition;
import ua.edu.knight.repository.AmmunitionRepository;
import ua.edu.knight.repository.ClasspathCsvAmmunitionRepository;
import ua.edu.knight.service.KnightEquipmentService;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        AmmunitionRepository repository = new ClasspathCsvAmmunitionRepository("equipment.csv");
        KnightEquipmentService service = new KnightEquipmentService(repository);

        Knight knight = service.createEquippedKnight("Sir Codealot");

        System.out.println("Knight: " + knight.getName());
        System.out.println("Total cost (gold): " + knight.getTotalCostGold());

        List<Ammunition> sorted = knight.getEquipmentSortedByWeight();
        System.out.println("Equipment sorted by weight (kg):");
        for (Ammunition item : sorted) {
            System.out.println(" - " + item.getName() + " | " + item.getWeightKg()
                    + " kg | " + item.getPriceGold() + " gold");
        }

        double min = 30.0;
        double max = 100.0;
        System.out.println("Equipment in price range [" + min + ", " + max + "] gold:");
        for (Ammunition item : knight.findByPriceRange(min, max)) {
            System.out.println(" - " + item.getName() + " (" + item.getPriceGold() + ")");
        }
    }
}
