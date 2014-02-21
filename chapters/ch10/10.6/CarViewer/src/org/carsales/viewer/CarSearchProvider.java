package org.carsales.viewer;

import java.beans.PropertyVetoException;
import javax.swing.SwingUtilities;
import org.netbeans.spi.quicksearch.SearchProvider;
import org.netbeans.spi.quicksearch.SearchRequest;
import org.netbeans.spi.quicksearch.SearchResponse;
import org.openide.explorer.ExplorerManager;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

public class CarSearchProvider implements SearchProvider {

    @Override
    public void evaluate(final SearchRequest request, final SearchResponse response) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TopComponent tc = WindowManager.getDefault().findTopComponent("CarViewerTopComponent");
                final ExplorerManager em = ((ExplorerManager.Provider) tc).getExplorerManager();
                Node root = em.getRootContext();
                for (final Node node : root.getChildren().getNodes()) {
                    Car car = node.getLookup().lookup(Car.class);
                    if (accept(car, request)) {
                        response.addResult(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    em.setSelectedNodes(new Node[]{node});
                                } catch (PropertyVetoException ex) {
                                    Exceptions.printStackTrace(ex);
                                }
                            }
                        }, car.getBrand());
                    }
                }
            }
            private boolean accept(Car car, SearchRequest request) {
                return car != null && car.getBrand().contains(request.getText());
            }
        });
    }
}
