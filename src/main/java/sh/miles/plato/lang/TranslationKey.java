package sh.miles.plato.lang;

import org.jspecify.annotations.NullMarked;

/**
 * Represents a key that can be translated within resource bundles
 */
@NullMarked
public final class TranslationKey {

    private final String key;

    TranslationKey(final String key) {
        this.key = key;
    }

    /**
     * Delegates to {@link Translations#getTranslation(String)}, which uses the currently selected resource bundle to
     * get the translation for this key.
     *
     * @return the string
     */
    public String get() {
        return Translations.getInstance().getTranslation(this.key);
    }

    /**
     * Delegates to {@link Translations#getTranslationArray(String)}, which uses the currently selected resource bundle
     * to get the translation for this key.
     *
     * @return the string array
     */
    public String[] getArray() {
        return Translations.getInstance().getTranslationArray(this.key);
    }

    /**
     * Gets the string translation key of this translation key instance
     *
     * @return the key as a string
     */
    public String getKey() {
        return key;
    }
}
