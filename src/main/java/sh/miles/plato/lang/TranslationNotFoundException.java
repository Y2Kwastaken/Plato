package sh.miles.plato.lang;

import java.util.Locale;

public class TranslationNotFoundException extends RuntimeException {
    public TranslationNotFoundException(String key, Locale locale) {
        super("No value can be found for the given translation key %s within the translation bundle %s".formatted(key, locale.getDisplayName()));
    }
}
