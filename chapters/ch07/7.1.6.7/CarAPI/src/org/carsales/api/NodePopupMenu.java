package org.carsales.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface NodePopupMenu {
    String path() default "Cars/";
    String pathToSubPopupMenus() default "SubPopupMenu/Cars/";
    int position() default Integer.MAX_VALUE;
    String displayName();
}
