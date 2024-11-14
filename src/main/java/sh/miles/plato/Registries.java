package sh.miles.plato;

import org.jspecify.annotations.NullMarked;
import sh.miles.plato.recipe.Measurement;
import sh.miles.plato.ui.pane.AbstractPane;
import sh.miles.plato.ui.pane.MealListPane;
import sh.miles.plato.util.collection.Registry;

import java.util.List;

import static sh.miles.plato.lang.TranslationKeys.MEASUREMENT_CUP_NAME;
import static sh.miles.plato.lang.TranslationKeys.MEASUREMENT_CUP_PLURAL;
import static sh.miles.plato.lang.TranslationKeys.MEASUREMENT_CUP_SHORT;
import static sh.miles.plato.lang.TranslationKeys.MEASUREMENT_FLUIDOUNCE_NAME;
import static sh.miles.plato.lang.TranslationKeys.MEASUREMENT_FLUIDOUNCE_PLURAL;
import static sh.miles.plato.lang.TranslationKeys.MEASUREMENT_FLUIDOUNCE_SHORT;
import static sh.miles.plato.lang.TranslationKeys.MEASUREMENT_PINT_NAME;
import static sh.miles.plato.lang.TranslationKeys.MEASUREMENT_PINT_PLURAL;
import static sh.miles.plato.lang.TranslationKeys.MEASUREMENT_PINT_SHORT;
import static sh.miles.plato.lang.TranslationKeys.MEASUREMENT_POUND_NAME;
import static sh.miles.plato.lang.TranslationKeys.MEASUREMENT_POUND_PLURAL;
import static sh.miles.plato.lang.TranslationKeys.MEASUREMENT_POUND_SHORT;
import static sh.miles.plato.lang.TranslationKeys.MEASUREMENT_TABLESPOON_NAME;
import static sh.miles.plato.lang.TranslationKeys.MEASUREMENT_TABLESPOON_PLURAL;
import static sh.miles.plato.lang.TranslationKeys.MEASUREMENT_TABLESPOON_SHORT;
import static sh.miles.plato.lang.TranslationKeys.MEASUREMENT_TEASPOON_NAME;
import static sh.miles.plato.lang.TranslationKeys.MEASUREMENT_TEASPOON_PLURAL;
import static sh.miles.plato.lang.TranslationKeys.MEASUREMENT_TEASPOON_SHORT;

/**
 * All registries
 */
@NullMarked
public final class Registries {

    private Registries() {
        throw new UnsupportedOperationException("Can not create instance of utility class Registries");
    }

    public static final Registry<String, Measurement> MEASUREMENT = new Registry<>(() -> List.of(
            new Measurement(MEASUREMENT_POUND_NAME.get(), MEASUREMENT_POUND_SHORT.get(), MEASUREMENT_POUND_PLURAL.get()),
            new Measurement(MEASUREMENT_PINT_NAME.get(), MEASUREMENT_PINT_SHORT.get(), MEASUREMENT_PINT_PLURAL.get()),
            new Measurement(MEASUREMENT_CUP_NAME.get(), MEASUREMENT_CUP_SHORT.get(), MEASUREMENT_CUP_PLURAL.get()),
            new Measurement(MEASUREMENT_TABLESPOON_NAME.get(), MEASUREMENT_TABLESPOON_SHORT.get(), MEASUREMENT_TABLESPOON_PLURAL.get()),
            new Measurement(MEASUREMENT_TEASPOON_NAME.get(), MEASUREMENT_TEASPOON_SHORT.get(), MEASUREMENT_TEASPOON_PLURAL.get()),
            new Measurement(MEASUREMENT_FLUIDOUNCE_NAME.get(), MEASUREMENT_FLUIDOUNCE_SHORT.get(), MEASUREMENT_FLUIDOUNCE_PLURAL.get())
    ));
    public static final Registry<String, AbstractPane> PANE = new Registry<>(() -> List.of(
            new MealListPane()
    ));


}
