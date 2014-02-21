package org.carsales.graph;

import java.io.IOException;
import javax.swing.Action;
import org.netbeans.spi.palette.PaletteActions;
import org.netbeans.spi.palette.PaletteController;
import org.netbeans.spi.palette.PaletteFactory;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;

public class CarPaletteFactory {

    public static PaletteController getCarPalette() {
        PaletteController controller = null;
        try {
            controller = PaletteFactory.createPalette(
                    "Vehicles/Cars",
                    new PaletteActions() {
                        @Override
                        public Action[] getImportActions() {
                            return null;
                        }

                        @Override
                        public Action[] getCustomPaletteActions() {
                            return null;
                        }

                        @Override
                        public Action[] getCustomCategoryActions(Lookup category) {
                            return null;
                        }

                        @Override
                        public Action[] getCustomItemActions(Lookup item) {
                            return null;
                        }

                        @Override
                        public Action getPreferredAction(Lookup item) {
                            return null;
                        }
                    });
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return controller;
    }
}
