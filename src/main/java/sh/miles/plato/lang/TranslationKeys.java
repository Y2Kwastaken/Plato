package sh.miles.plato.lang;

/**
 * Represents all available translation keys
 */
public final class TranslationKeys {

    private TranslationKeys() {
        throw new UnsupportedOperationException("Can not create instance of TranslationKeys utility class");
    }

    public static final TranslationKey TITLE = new TranslationKey("title");
    public static final TranslationKey NAVBAR_HOME = new TranslationKey("navbar.home");
    public static final TranslationKey SECTION_INGREDIENTS = new TranslationKey("section.ingredients");
    public static final TranslationKey SECTION_INSTRUCTIONS = new TranslationKey("section.instructions");

    public static final TranslationKey MEASUREMENT_POUND_NAME = new TranslationKey("measurement.pound.name");
    public static final TranslationKey MEASUREMENT_POUND_SHORT = new TranslationKey("measurement.pound.short");
    public static final TranslationKey MEASUREMENT_POUND_PLURAL = new TranslationKey("measurement.pound.plural");
    public static final TranslationKey MEASUREMENT_PINT_NAME = new TranslationKey("measurement.pint.name");
    public static final TranslationKey MEASUREMENT_PINT_SHORT = new TranslationKey("measurement.pint.short");
    public static final TranslationKey MEASUREMENT_PINT_PLURAL = new TranslationKey("measurement.pint.plural");
    public static final TranslationKey MEASUREMENT_CUP_NAME = new TranslationKey("measurement.cup.name");
    public static final TranslationKey MEASUREMENT_CUP_SHORT = new TranslationKey("measurement.cup.short");
    public static final TranslationKey MEASUREMENT_CUP_PLURAL = new TranslationKey("measurement.cup.plural");
    public static final TranslationKey MEASUREMENT_TABLESPOON_NAME = new TranslationKey("measurement.tablespoon.name");
    public static final TranslationKey MEASUREMENT_TABLESPOON_SHORT = new TranslationKey("measurement.tablespoon.short");
    public static final TranslationKey MEASUREMENT_TABLESPOON_PLURAL = new TranslationKey("measurement.tablespoon.plural");
    public static final TranslationKey MEASUREMENT_TEASPOON_NAME = new TranslationKey("measurement.teaspoon.name");
    public static final TranslationKey MEASUREMENT_TEASPOON_SHORT = new TranslationKey("measurement.teaspoon.short");
    public static final TranslationKey MEASUREMENT_TEASPOON_PLURAL = new TranslationKey("measurement.teaspoon.plural");
    public static final TranslationKey MEASUREMENT_FLUIDOUNCE_NAME = new TranslationKey("measurement.fluidounce.name");
    public static final TranslationKey MEASUREMENT_FLUIDOUNCE_SHORT = new TranslationKey("measurement.fluidounce.short");
    public static final TranslationKey MEASUREMENT_FLUIDOUNCE_PLURAL = new TranslationKey("measurement.fluidounce.plural");

}
