/**
 *
 * @author chris.esposito@gmail.com
 */

package org.familytree.viewer;

import java.beans.IntrospectionException;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;

/**
 * UI infrastructure class for generating lists of child PersonNodes from a
 * parent PersonNode's list of Person children
 */
class PersonFactory extends ChildFactory.Detachable<Person>
    implements ChangeListener
{

    /** 
     * list of Person children for a node
     */
    private final PersonList model; 

    public PersonFactory(PersonList model)
    {
        this.model = model;
        this.addNotify(); // ask to be notified when the list of children changes
    }

    @Override
    protected boolean createKeys(List<Person> list)
    {
        list.addAll(getModel().list());

        return true;
    }

    @Override
    protected Node createNodeForKey(Person c)
    {
        try
        {
            return new PersonNode(c, Children.create(new PersonFactory(c.getKids()), true));
        }
        catch (IntrospectionException ex)
        {
            Exceptions.printStackTrace(ex);
        }

        return null;
    }

    /**
     * add this class as a listener to changes in the child list
     */
    @Override
    protected void addNotify()
    {
        getModel().addChangeListener(this);
    }

    /**
     * remove this class as a listener to changes in the child list
     */
    @Override
    protected void removeNotify()
    {
        getModel().removeChangeListener(this);
    }

    /**
     * @return the model
     */
    public PersonList getModel()
    {
        return model;
    }

    /**
     * called when we're told list of children has changed
     */
    @Override
    public void stateChanged(ChangeEvent e)
    {
        refresh(true);
    }
}
