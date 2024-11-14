package sh.miles.plato.recipe;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import sh.miles.plato.Registries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents a recipe
 *
 * @param uuid         the recipe uuid
 * @param name         the name of the recipe
 * @param ingredients  the recipe ingredients
 * @param instructions the recipe instructions
 */
@NullMarked
public record Recipe(UUID uuid, String name, List<Ingredient> ingredients, List<Instruction> instructions) {

    public Recipe {
        ingredients = List.copyOf(ingredients);
        instructions = List.copyOf(instructions);
    }

    /**
     * Creates a copy of this recipe with a different name
     *
     * @param name the name
     * @return the recipe
     */
    public Recipe withName(String name) {
        return new Recipe(this.uuid, name, this.ingredients, this.instructions);
    }

    /**
     * Creates a copy of this recipe with a different set of ingredients
     *
     * @param ingredients the ingredients
     * @return the recipe
     */
    public Recipe withIngredients(List<Ingredient> ingredients) {
        return new Recipe(this.uuid, this.name, ingredients, this.instructions);
    }

    /**
     * Creates a copy of this recipe with a different set of instructions
     *
     * @param instructions the instructions
     * @return the recipe
     */
    public Recipe withInstructions(List<Instruction> instructions) {
        return new Recipe(this.uuid, this.name, this.ingredients, instructions);
    }

    /**
     * Creates a copy of this recipe and transforms it into a builder that can be mutated in any way.
     *
     * @param operation the builder operation
     * @return the recipe
     */
    public Recipe withChanges(Consumer<Builder> operation) {
        final Builder builder = new Builder(this.uuid).name(this.name).ingredients(this.ingredients).instructions(this.instructions);
        operation.accept(builder);
        return builder.build();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof final Recipe recipe)) return false;
        return Objects.equals(name, recipe.name) && Objects.equals(instructions, recipe.instructions) && Objects.equals(ingredients, recipe.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ingredients, instructions);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", ingredients=" + ingredients +
                ", instructions=" + instructions +
                '}';
    }

    @NullMarked
    public static class Builder {
        private final UUID uuid;
        @Nullable
        private String name;
        private List<Ingredient> ingredients = new ArrayList<>();
        private List<Instruction> instructions = new ArrayList<>();

        private Builder() {
            this.uuid = UUID.randomUUID();
        }

        private Builder(UUID uuid) {
            this.uuid = uuid;
        }

        /**
         * Sets the recipe name
         *
         * @param name the name
         * @return this builder
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Adds recipe ingredients
         *
         * @param ingredients the recipe ingredients
         * @return this builder
         */
        public Builder ingredients(Ingredient... ingredients) {
            this.ingredients.addAll(Arrays.asList(ingredients));
            return this;
        }

        /**
         * Adds recipe ingredients
         *
         * @param ingredients the recipe ingredients
         * @return this builder
         */
        public Builder ingredients(Collection<Ingredient> ingredients) {
            this.ingredients.addAll(ingredients);
            return this;
        }

        /**
         * Streams ingredients so operations can be done over the ingredients
         *
         * @param streamOperation the stream operation
         * @return this builder
         */
        public Builder streamIngredients(UnaryOperator<Stream<Ingredient>> streamOperation) {
            this.ingredients = streamOperation.apply(this.ingredients.stream()).collect(Collectors.toList());
            return this;
        }

        /**
         * Adds recipe instructions
         *
         * @param instructions the recipe instructions
         * @return this builder
         */
        public Builder instructions(Instruction... instructions) {
            this.instructions.addAll(Arrays.asList(instructions));
            return this;
        }

        /**
         * Adds recipe instructions
         *
         * @param instructions the recipe instructions
         * @return this builder
         */
        public Builder instructions(Collection<Instruction> instructions) {
            this.instructions.addAll(instructions);
            return this;
        }

        /**
         * Streams instructions so operations can be done over the instructions
         *
         * @param streamOperation the stream operation
         * @return this builder
         */
        public Builder streamInstructions(UnaryOperator<Stream<Instruction>> streamOperation) {
            this.instructions = streamOperation.apply(this.instructions.stream()).collect(Collectors.toList());
            return this;
        }

        /**
         * Clears all recipe ingredients
         *
         * @return this builder
         */
        public Builder clearIngredients() {
            this.ingredients.clear();
            return this;
        }

        /**
         * Clears all recipe instructions
         *
         * @return this builder
         */
        public Builder clearInstructions() {
            this.instructions.clear();
            return this;
        }

        /**
         * Creates the recipe from the builder
         *
         * @return the recipe created
         * @throws IllegalArgumentException thrown if a name is not set via the {@link Builder#name(String)} method
         */
        public Recipe build() throws IllegalArgumentException {
            if (name == null) {
                throw new IllegalArgumentException("A Name must be provided to create a recipe");
            }

            return new Recipe(this.uuid, this.name, this.ingredients, this.instructions);
        }

    }

    /**
     * Creates a new recipe builder
     *
     * @return the created recipe builder
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Creates a random recipe
     *
     * @return the random recipe
     */
    public static Recipe generateRandom() {
        final Random random = ThreadLocalRandom.current();
        return new Recipe(
                UUID.randomUUID(),
                random.ints(25, 97, 122).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString(),
                random.ints(random.nextInt(1, 11), 0, 5).mapToObj((i) ->
                        new Ingredient(
                                random.ints(random.nextInt(5, 25), 97, 122).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString(),
                                new int[]{i, 1}, Registries.MEASUREMENT.random(random).orElseThrow())).toList(),
                random.ints(random.nextInt(0, 5), 0, 1).mapToObj((i) -> random.ints(random.nextInt(100, 150), 97, 122).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString()
                ).map(Instruction::new).toList()
        );
    }
}
