package org.carsales.graph;

import org.openide.util.lookup.Lookups;
import org.openide.windows.TopComponent;

@TopComponent.Description(
        preferredID = "CarEditorTopComponent",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(
        mode = "editor",
        openAtStartup = true)
public class CarEditorTopComponent extends TopComponent {

    public CarEditorTopComponent() {
        associateLookup(Lookups.singleton(CarPaletteFactory.getCarPalette()));
    }
    
}
