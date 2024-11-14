package sh.miles.plato.ui.util;

import sh.miles.plato.recipe.Recipe;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import java.awt.Component;

public class RecipeListCellRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(final JList<?> list, final Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (!(value instanceof Recipe recipe)) {
            return null;
        }

        setText(recipe.name());
        setToolTipText(recipe.uuid().toString());
        return this;
    }
}
