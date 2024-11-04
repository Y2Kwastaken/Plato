package sh.miles.plato.recipe;

import org.jspecify.annotations.NullMarked;

import java.util.Arrays;
import java.util.Objects;

/**
 * Represents an ingredient within a recipe
 *
 * @param quantity    the quantity of the ingredient
 * @param measurement the measurement of the ingredient
 */
@NullMarked
public record Ingredient(int[] quantity, Measurement measurement) {

    public Ingredient {
        if (quantity.length != 2) {
            throw new IllegalArgumentException("Malformed fraction must have 2 entries [numerator, denominator]");
        }

        if (quantity[1] < 1) {
            throw new ArithmeticException("Numbers less than zero are not allowed in quantity denominators");
        }
    }

    /**
     * Turns this ingredients quantity into a fraction string
     * <p>
     * if the quantity is 5/1 an output would be "5". if the quantity was 5/3 the output would be "5/3"
     *
     * @return the quantity fraction as a string
     */
    public String quantityAsFractionString() {
        final int denominator = quantity[1];
        if (denominator <= 1) {
            return quantity[0] + "";
        } else {
            return quantity[0] + "/" + quantity[1];
        }
    }

    /**
     * Creates a string from the given ingredient as a more easy to read string
     *
     * @param spacing   the spacing between quantity and measurement
     * @param shortName whether to use the short name of the measurement
     * @return the kindly formatted string
     */
    public String asKindString(String spacing, boolean shortName) {
        return quantityAsFractionString() + spacing + (shortName ? measurement.shortName() : measurement.name());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof final Ingredient that)) return false;
        return Objects.deepEquals(quantity, that.quantity) && Objects.equals(measurement, that.measurement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(quantity), measurement);
    }

    @Override
    public String toString() {
        return "Ingredient{" + "quantity=" + Arrays.toString(quantity) + ", measurement=" + measurement + '}';
    }
}
