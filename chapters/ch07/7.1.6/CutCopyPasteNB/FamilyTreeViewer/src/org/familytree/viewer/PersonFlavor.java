/**
 *
 * @author chris.esposito@gmail.com
 */
package org.familytree.viewer;

import java.awt.datatransfer.DataFlavor;

/** 
 * Custom data flavor for this example
 */
public class PersonFlavor extends DataFlavor
{
    public static final DataFlavor PERSON_FLAVOR = new PersonFlavor();

    public PersonFlavor()
    {
        super(Person.class, "Person");
    }
}
