package sh.miles.plato.util.collection;

import org.jspecify.annotations.NullMarked;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Represents a generic registry
 *
 * @param <K> the key value
 * @param <V> the value
 */
@NullMarked
public final class Registry<K, V extends Registry.Key<K>> implements Iterable<V> {

    private final Map<K, V> registry;

    /**
     * Creates a registry with the supplied values
     *
     * @param valueSupplier the value supplier
     */
    public Registry(final Supplier<Collection<V>> valueSupplier) {
        this.registry = valueSupplier.get().stream().collect(Collectors.toMap(
                Key::getKey,
                (v) -> v
        ));
    }

    /**
     * Creates a standard registry with the option of being concurrent
     *
     * @param concurrent whether or not use a concurrent map
     */
    public Registry(boolean concurrent) {
        if (concurrent) {
            this.registry = new ConcurrentHashMap<>();
        } else {
            this.registry = new HashMap<>();
        }
    }

    /**
     * Creates a standard registry
     */
    public Registry() {
        this.registry = new HashMap<>();
    }

    /**
     * Attempts to get a value from the registry with the given key
     *
     * @param key the lookup key of the entry
     * @return the optional result
     */
    public Optional<V> get(final K key) {
        return Optional.ofNullable(this.registry.get(key));
    }

    /**
     * Attempts to register the given value registry
     *
     * @param value the value to register
     * @throws IllegalArgumentException thrown if the registered key already exists
     */
    public void register(final V value) throws IllegalArgumentException {
        final K key = value.getKey();
        if (this.registry.containsKey(key)) {
            throw new IllegalArgumentException("The given key %s already exists within this registry".formatted(key));
        }

        registry.put(key, value);
    }

    /**
     * Attempts to get a random element from within the registry
     *
     * @return the random element, or an empty optional if this registry has no elements
     * @throws IllegalStateException if a random number violates the bounds specified, this should not be expected
     */
    public Optional<V> random(Random random) throws IllegalStateException {
        if (this.registry.isEmpty()) {
            return Optional.empty();
        }

        final int targetIndex = random.nextInt(0, this.registry.size());
        int index = 0;
        for (final V value : registry.values()) {
            if (targetIndex == index) {
                return Optional.of(value);
            }
            index++;
        }

        throw new IllegalStateException("An unknown state occurred in which the generated targetIndex is larger than the value list");
    }

    @Override
    public Iterator<V> iterator() {
        return this.registry.values().iterator();
    }

    /**
     * A Key to be applied to registry entries
     *
     * @param <K>
     */
    @NullMarked
    public interface Key<K> {
        /**
         * Gets the key
         *
         * @return the key
         */
        K getKey();
    }
}
