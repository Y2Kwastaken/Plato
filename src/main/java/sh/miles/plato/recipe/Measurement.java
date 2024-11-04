package sh.miles.plato.recipe;

import org.jspecify.annotations.NullMarked;
import sh.miles.plato.util.collection.Registry;

/**
 * Represents a measurement that can be found within a recipe
 */
@NullMarked
public record Measurement(String name, String shortName, String pluralization) implements Registry.Key<String> {
    @Override
    public String getKey() {
        return this.name;
    }
}
