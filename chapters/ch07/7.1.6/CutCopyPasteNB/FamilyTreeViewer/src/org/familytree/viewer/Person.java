/**
 *
 * @author chris.esposito@gmail.com
 */
package org.familytree.viewer;

import java.util.ArrayList;
import java.util.List;

/**
 * Sample domain object; has a list of children
 */
public class Person
{

    /**
     * @return can the first node be dropped on the second?
     */
    public static boolean makeDropDecision(Person man, Person woman)
    {
        boolean result = true;
        if ((man.getName().equalsIgnoreCase("Tom")) && woman.getName().equalsIgnoreCase("Mary"))
        {
            result = false;
        }

        return result;
    }
    private final PersonList kids; // List of children
    private String name;  // display name for the person

    public Person(String name)
    {
        this.name = name;
        kids = new PersonList(new ArrayList<Person>());
    }

    /**
     * @return the list of children
     */
    public PersonList getKids()
    {
        return kids;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the list of children. Some parts of the UI infrastructure want
     * a standard collection instead of a custom class, returned by a list() method
     */
    public List<Person> list()
    {
        return kids.list();
    }

}
