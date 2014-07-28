package org.car.api;

import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

public class CentralLookup extends AbstractLookup {

    private InstanceContent content = null;
    private static CentralLookup def = new CentralLookup();

    public CentralLookup(InstanceContent content) {
        super(content);
        this.content = content;
    }

    public CentralLookup() {
        this(new InstanceContent());
    }

    public void add(Object instance) {
        content.add(instance);
    }

    public void remove(Object instance) {
        content.remove(instance);
    }

    public static CentralLookup getDefault() {
        return def;
    }

}
