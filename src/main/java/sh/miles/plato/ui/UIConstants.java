package sh.miles.plato.ui;

import java.awt.Color;
import java.awt.Dimension;

public final class UIConstants {

    private UIConstants() {
        throw new UnsupportedOperationException("Can not create instance of utility class UIConstants");
    }

    public static final Dimension PREFERRED_SIZE = new Dimension(800, 600);

    public static final float RECIPE_TITLE_SIZE = 24.0f;
    public static final float RECIPE_SECTION_TITLE_SIZE = 16.0f;

    public static final Color RECIPE_TITLE_COLOR = new Color(206, 202, 210);
}
