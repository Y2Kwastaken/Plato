package sh.miles.plato;

import com.formdev.flatlaf.FlatDarculaLaf;
import sh.miles.plato.lang.Translations;
import sh.miles.plato.ui.Frame;

import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        Translations.setup(Locale.US); // en_US is not the only language support others in the future
        FlatDarculaLaf.setup();
        Frame.start();
    }

}
