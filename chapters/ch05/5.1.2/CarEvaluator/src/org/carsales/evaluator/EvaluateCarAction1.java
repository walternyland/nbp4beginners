package org.carsales.evaluator;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import org.carsales.api.Car;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.ContextAwareAction;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;
import org.openide.util.WeakListeners;
@ActionID(id = "org.carsales.evaluator.EvaluateCarAction1", category = "Car")
@ActionRegistration(displayName = "not-used", lazy = false)
@ActionReference(path = "Menu/Car", position = 10)
public class EvaluateCarAction1 extends AbstractAction
        implements ContextAwareAction, LookupListener {
    private final Lookup lkp;
    private final Lookup.Result<Car> result;
    public EvaluateCarAction1() {
        this(Utilities.actionsGlobalContext());
    }
    @Messages("CTL_EvaluateCar1=Evaluate Car 1")
    public EvaluateCarAction1(Lookup lkp) {
        super(Bundle.CTL_EvaluateCar1());
        this.lkp = lkp;
        result = lkp.lookupResult(Car.class);
        result.addLookupListener(
                WeakListeners.create(LookupListener.class, this, result));
        setEnabled(false);
    }
    @Override
    public Action createContextAwareInstance(Lookup lkp) {
        return new EvaluateCarAction1(lkp);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for (Car car : result.allInstances()) {
            //Optionally, check if the property is set to the value you're interested in
            //prior to doing something with the Object.
            JOptionPane.showMessageDialog(null, car.getBrand());
        }
    }
    @Override
    public void resultChanged(LookupEvent le) {
        //Optionally, check if the property is set to the value you're interested in
        //prior to enabling the Action.
        super.setEnabled(result.allInstances().size() > 0);
    }
}