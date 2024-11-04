package sh.miles.plato;

import org.jspecify.annotations.NullMarked;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

@NullMarked
public final class TestingReflection {

    private TestingReflection() {
        throw new UnsupportedOperationException("can not create instance of utility class TestingReflection");
    }

    /**
     * Sets the field of the given class and name to the given value
     *
     * @param clazz     the class to get the field from
     * @param fieldName the name of the field
     * @param value     the value of the field
     * @return true if successful, otherwise false
     */
    public static boolean setStaticField(final Class<?> clazz, final String fieldName, final Object value) {
        try {
            final Field field = clazz.getDeclaredField(fieldName);
            final int modifiers = field.getModifiers();
            if (Modifier.isPrivate(modifiers)) {
                field.setAccessible(true);
                field.set(null, value);
                field.setAccessible(false);
            } else if (Modifier.isFinal(modifiers)) {
                return false;
            } else {
                field.set(null, value);
            }
            return true;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return false;
        }
    }

}
