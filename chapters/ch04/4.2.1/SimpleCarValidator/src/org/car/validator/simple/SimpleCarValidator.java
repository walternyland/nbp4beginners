package org.car.validator.simple;

import org.car.api.CarValidator;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = CarValidator.class)
public class SimpleCarValidator implements CarValidator {

    @Override
    public boolean validate(String brand) {
        return brand != null;
    }
    
}
