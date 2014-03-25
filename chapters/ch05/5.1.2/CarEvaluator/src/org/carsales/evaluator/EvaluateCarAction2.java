package org.carsales.evaluator;
import org.carsales.api.Car;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.awt.StatusDisplayer;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle.Messages;
import org.openide.util.actions.CookieAction;
@ActionID(id = "org.carsales.evaluator.EvaluateCarAction2", category = "Car")
@ActionRegistration(displayName = "not-used", lazy = false)
@ActionReference(path = "Menu/Car", position = 20)
public final class EvaluateCarAction2 extends CookieAction {
    private Car context;
    @Messages("CTL_EvaluateCar2=Evaluate Car 2")
    public EvaluateCarAction2() {
        setEnabled(false);
    }
    @Override
    protected void performAction(Node[] nodes) {
        StatusDisplayer.getDefault().setStatusText(context.getBrand());
    }
    @Override
    protected boolean enable(Node[] activatedNodes) {
        if (activatedNodes.length > 0) {
            Car car = activatedNodes[0].getLookup().lookup(Car.class);
            if (car != null && car.getBrand().equals("Honda")) {
                context = car;
                return true;
            }
        }
        return false;
    }
    @Override
    protected int mode() {
        return CookieAction.MODE_EXACTLY_ONE;
    }
    @Override
    protected Class<?>[] cookieClasses() {
        return new Class[]{Car.class};
    }
    @Override
    public String getName() {
        return Bundle.CTL_EvaluateCar2();
    }
    @Override
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }
}
