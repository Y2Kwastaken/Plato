package sh.miles.plato;

import org.jspecify.annotations.NullMarked;
import sh.miles.plato.recipe.Measurement;
import sh.miles.plato.ui.pane.AbstractPane;
import sh.miles.plato.ui.pane.MealListPane;
import sh.miles.plato.util.collection.Registry;

import java.util.List;
import java.util.Map;

/**
 * All registries
 */
@NullMarked
public final class Registries {

    private Registries() {
        throw new UnsupportedOperationException("Can not create instance of utility class Registries");
    }

    public static final Registry<String, Measurement> MEASUREMENT = new Registry<>();
    public static final Registry<String, AbstractPane> PANE = new Registry<>(() -> List.of(
            new MealListPane()
    ));


}
