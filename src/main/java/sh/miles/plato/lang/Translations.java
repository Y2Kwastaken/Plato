package sh.miles.plato.lang;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Represents a generic simplified access to the translations resource bundle
 */
@NullMarked
public final class Translations {

    @Nullable
    private static Translations instance;

    private final ResourceBundle bundle;

    private Translations(final Locale locale) throws MissingResourceException {
        this.bundle = ResourceBundle.getBundle("lang/Lang", locale);
    }

    public String getTranslation(String key) throws TranslationNotFoundException {
        if (!this.bundle.containsKey(key)) {
            throw new TranslationNotFoundException(key, this.bundle.getLocale());
        }

        return this.bundle.getString(key);
    }

    public String[] getTranslationArray(String key) throws TranslationNotFoundException {
        if (!this.bundle.containsKey(key)) {
            throw new TranslationNotFoundException(key, this.bundle.getLocale());
        }

        return this.bundle.getStringArray(key);
    }

    /**
     * Sets up the translations
     *
     * @return the translations
     */
    public static Translations getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Translations are not setup you must call Translations#setup(Locale) before calling Translations#getInstance()");
        }
        return instance;
    }

    /**
     * Sets up the Translations
     *
     * @param locale the locale to use to setup
     * @throws IllegalStateException thrown if the Translations instance is already created
     */
    public static void setup(Locale locale) throws IllegalStateException {
        if (instance != null) {
            throw new IllegalStateException("Translations are already setup and can not be instantiated twice");
        }

        instance = new Translations(locale);
    }
}
