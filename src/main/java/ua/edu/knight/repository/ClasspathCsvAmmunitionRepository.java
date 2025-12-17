package ua.edu.knight.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import ua.edu.knight.model.ammunition.Ammunition;

public final class ClasspathCsvAmmunitionRepository implements AmmunitionRepository {

    private final String resourceName;
    private final AmmunitionFactory factory;

    public ClasspathCsvAmmunitionRepository(String resourceName) {
        this(resourceName, new AmmunitionFactory());
    }

    ClasspathCsvAmmunitionRepository(String resourceName, AmmunitionFactory factory) {
        this.resourceName = validateResourceName(resourceName);
        this.factory = Objects.requireNonNull(factory, "factory must not be null");
    }

    @Override
    public List<Ammunition> loadAll() {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(resourceName);
        if (stream == null) {
            throw new IllegalStateException("Resource not found: " + resourceName);
        }

        List<Ammunition> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            String header = reader.readLine(); // skip header
            if (header == null) {
                return result;
            }

            String line;
            while ((line = reader.readLine()) != null) {
                String trimmed = line.trim();
                if (trimmed.isEmpty() || trimmed.startsWith("#")) {
                    continue;
                }
                result.add(factory.fromCsvLine(trimmed));
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read resource: " + resourceName, e);
        }
        return result;
    }

    private static String validateResourceName(String value) {
        Objects.requireNonNull(value, "resourceName must not be null");
        String trimmed = value.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("resourceName must not be blank");
        }
        return trimmed;
    }
}
