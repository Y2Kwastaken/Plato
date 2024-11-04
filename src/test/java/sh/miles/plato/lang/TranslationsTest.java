package sh.miles.plato.lang;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sh.miles.plato.TestingReflection;

import java.util.Locale;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class TranslationsTest {

    public static final String SUCCESSFUL_KEY = "title";
    public static final String FAILURE_KEY = "ThisWillNeverBeATranslationKeyLol" + UUID.randomUUID();

    @BeforeEach
    public void setUp() {
        assertTrue(TestingReflection.setStaticField(Translations.class, "instance", null), "setting Translations instance field fails");
    }

    @Test
    public void testSetup() {
        assertDoesNotThrow(() -> Translations.setup(Locale.US), "Translations setup should not fail with supported locale");
    }

    @Test
    public void testGetInstance() {
        assertThrows(IllegalStateException.class, Translations::getInstance, "IllegalStateException should be thrown if Translations is not instantiated");
        testSetup();
        assertDoesNotThrow(Translations::getInstance, "Exception should not be thrown if Translations is instantiated");
    }

    @Test
    public void testGetTranslation_Success() {
        testSetup();
        assertDoesNotThrow(() -> Translations.getInstance().getTranslation(SUCCESSFUL_KEY), "The key %s should exist within all supported translations".formatted(SUCCESSFUL_KEY));
    }

    @Test
    public void testGetTranslation_Failure() {
        testSetup();
        assertThrows(TranslationNotFoundException.class, () -> Translations.getInstance().getTranslation(FAILURE_KEY), "The key %s should throw a TranslationNotFoundException because it does not exist".formatted(FAILURE_KEY));
    }
}
