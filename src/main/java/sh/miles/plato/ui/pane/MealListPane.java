package sh.miles.plato.ui.pane;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import sh.miles.plato.lang.TranslationKeys;
import sh.miles.plato.recipe.Instruction;
import sh.miles.plato.recipe.Recipe;
import sh.miles.plato.ui.UIConstants;
import sh.miles.plato.ui.components.Navbar;
import sh.miles.plato.ui.util.RecipeListCellRenderer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import static java.awt.GridBagConstraints.BOTH;
import static java.awt.GridBagConstraints.NORTH;
import static java.awt.GridBagConstraints.NORTHWEST;

@NullMarked
public class MealListPane extends AbstractPane {

    @Override
    public void init() {
        constraints.anchor = NORTHWEST;
        put(new Navbar(), (c, comp) -> {
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 1.0;
            c.gridwidth = 2;
            c.fill = BOTH;
            comp.setBorder(BorderFactory.createLineBorder(Color.RED));
        });
        final SelectedMealView selectedMealView = new SelectedMealView();
        put(new MealListView(selectedMealView), (c, comp) -> {
            c.gridx = 0;
            c.gridy = 1;
            c.weighty = 1.0;
            c.fill = BOTH;
            comp.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        });
        put(selectedMealView, (c, comp) -> {
            c.gridx = 1;
            c.gridy = 1;
            c.weightx = 1.0;
            c.weighty = 1;
            c.fill = BOTH;
            comp.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        });
    }

    @Override
    public String getKey() {
        return PaneKeys.MEAL_LIST;
    }

    public static class MealListView extends AbstractPane {

        private final SelectedMealView view;

        MealListView(SelectedMealView view) {
            this.view = view;
        }

        @Override
        public void init() {
            constraints.anchor = NORTHWEST;
            put(new JList<>(), (c, comp) -> {
                c.gridx = 0;
                c.gridy = 0;
                c.weightx = 0.25;
                c.weighty = 1.0;
                c.fill = BOTH;
                comp.setCellRenderer(new RecipeListCellRenderer());

                // test
                final List<Recipe> recipes = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    recipes.add(Recipe.generateRandom());
                }
                // test
                comp.setListData(recipes.toArray(Recipe[]::new));
                comp.addListSelectionListener(this::onValueChange);
            });


        }

        @Override
        public String getKey() {
            return null;
        }

        public void onValueChange(final ListSelectionEvent event) {
            final JList<Recipe> list = (JList<Recipe>) event.getSource();
            this.view.selectRecipe(list.getSelectedValue());
        }
    }

    public static class SelectedMealView extends AbstractPane {

        SelectedMealView() {
        }

        @Override
        public void init() {
            selectRecipe(null);
        }

        public void selectRecipe(@Nullable Recipe recipe) {
            removeAll();
            if (recipe == null) {
                System.out.println("Null");
                return;
            }

            constraints.anchor = NORTHWEST;
            constraints.gridx = 0;
            put(new JLabel(recipe.name()), (c, comp) -> {
                c.anchor = NORTH;
                c.gridy = 0;
                c.gridwidth = 2;
                c.ipady = 25;
                c.weightx = 1.0;
                comp.setFont(comp.getFont().deriveFont(UIConstants.RECIPE_TITLE_SIZE));
                comp.setForeground(UIConstants.RECIPE_TITLE_COLOR);
                comp.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
            });

            put(new SimplePane(), (panec, pane) -> {
                panec.anchor = NORTHWEST;
                panec.fill = BOTH;
                panec.gridy = 1;
                panec.weightx = 0.75;
                panec.weighty = 1.0;

                pane.constraints.anchor = NORTHWEST;
                pane.constraints.weightx = 1.0;

                putRecipeLabeledSectionWithScrollPane(pane, TranslationKeys.SECTION_INSTRUCTIONS.get(), recipe, (r) -> r.instructions().iterator(), Instruction::instruction);
            });

            put(new SimplePane(), (panec, pane) -> {
                panec.anchor = NORTHWEST;
                panec.fill = BOTH;
                panec.gridx = 1;
                panec.gridy = 1;
                panec.weightx = 0.25;
                panec.weighty = 1.0;

                pane.constraints.anchor = NORTHWEST;
                pane.constraints.weightx = 1.0;

                putRecipeLabeledSectionWithScrollPane(pane, TranslationKeys.SECTION_INGREDIENTS.get(), recipe, (r) -> r.ingredients().iterator(), (i) -> i.asKindString(" ", true));
            });

            this.revalidate();
            this.repaint();
        }

        @Override
        public String getKey() {
            return null;
        }

        private <T> void putRecipeLabeledSectionWithScrollPane(SimplePane pane, String label, Recipe recipe, Function<Recipe, Iterator<T>> typeIterator, Function<T, String> formatter) {
            final StringBuilder displayString = new StringBuilder();
            final Iterator<T> iterator = typeIterator.apply(recipe);
            while (iterator.hasNext()) {
                displayString.append(formatter.apply(iterator.next()));
                if (iterator.hasNext()) {
                    displayString.append("\n\n");
                }
            }

            pane.put(new JLabel(label), (c, comp) -> {
                if (displayString.isEmpty()) {
                    c.weighty = 1.0;
                }

                comp.setFont(comp.getFont().deriveFont(UIConstants.RECIPE_SECTION_TITLE_SIZE).deriveFont(Font.BOLD));
                comp.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
            });


            pane.put(createViewOnlyScrollPane(displayString.toString()), (c, comp) -> {
                c.gridx = 0;
                c.gridy = 1;
                c.weighty = 1.0;
                c.fill = BOTH;
            });

            pane.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }

        private Component createViewOnlyScrollPane(String text) {
            final JTextArea area = new JTextArea(text);
            final JScrollPane pane = new JScrollPane(area);

            area.setRows(getHeight() / 50);
            area.setColumns(getWidth() / 50);
            area.setLineWrap(true);
            area.setEditable(false);
            area.setBorder(BorderFactory.createLineBorder(Color.RED));

            pane.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
            return pane;
        }
    }
}
