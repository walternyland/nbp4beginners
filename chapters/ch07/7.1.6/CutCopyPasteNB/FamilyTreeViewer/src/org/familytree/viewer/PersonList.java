/**
 *
 * @author chris.esposito@gmail.com
 */
package org.familytree.viewer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.event.ChangeListener;
import org.openide.util.ChangeSupport;

/**
 * data model of a list of people. Change support allows interested parties (i.e., UI) 
 * to properly regenerate child node lists
 */
public final class PersonList {
    private final ChangeSupport cs = new ChangeSupport(this);
    private final List<Person> children;

    public PersonList(List<Person> kids) {
        this.children = new ArrayList<Person>(kids);
    }

    /**
     * add this person to the child list
     * @param c The person to add
     */
    public void add(Person c) {
        children.add(c);
        cs.fireChange();
    }

    public void addChangeListener(ChangeListener l) {
        cs.addChangeListener(l);
    }

    /**
     * return the list of Persons. 
     * @return this list of children as a collection
     */
    public List<Person> list() {
        return children;
    }

    /**
     * remove this person from the child list
     * @param c The person to remove
     */
    public void remove(Person c) {
        children.remove(c);
        cs.fireChange();
    }

    public void removeChangeListener(ChangeListener l) {
        cs.removeChangeListener(l);
    }

    /**
     * called when move up or move down is invoked from context menu
     * @param perm new order for children
     */
    public void reorder(int[] perm) {
        Person[] reordered = new Person[children.size()];

        for (int i = 0; i < perm.length; i++) {
            int j = perm[i];
            Person c = children.get(i);
            reordered[j] = c;
        }

        children.clear();
        children.addAll(Arrays.asList(reordered));
        cs.fireChange();
    }
}
