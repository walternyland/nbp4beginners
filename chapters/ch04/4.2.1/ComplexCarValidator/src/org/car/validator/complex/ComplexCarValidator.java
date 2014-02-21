package org.car.validator.complex;

import org.car.api.CarValidator;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = CarValidator.class)
public class ComplexCarValidator implements CarValidator {

    @Override
    public boolean validate(String brand) {
        // do something more complex here:
        return brand != null;
    }
    
}
