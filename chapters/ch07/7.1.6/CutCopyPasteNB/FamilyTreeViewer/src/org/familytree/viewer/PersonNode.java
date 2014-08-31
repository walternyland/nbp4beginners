/**
 *
 * @author chris.esposito@gmail.com
 */
package org.familytree.viewer;

import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.util.List;
import javax.swing.Action;
import org.openide.actions.CopyAction;
import org.openide.actions.CutAction;
import org.openide.actions.DeleteAction;
import org.openide.actions.MoveDownAction;
import org.openide.actions.MoveUpAction;
import org.openide.actions.PasteAction;
import org.openide.actions.RenameAction;
import org.openide.awt.StatusDisplayer;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.nodes.Index;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.datatransfer.ExTransferable;
import org.openide.util.datatransfer.PasteType;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
 * UI Node for displaying / manipulating domain (Person) objects
 * This and the child node factory class (PersonFactory, in this example)
 * are the trickiest ones to get right.
 */
public class PersonNode extends BeanNode
{
    /**
     * ChangeSupport-enabled list of Person children
     */
    private final PersonList model;
    private Person payload;

    @SuppressWarnings("unchecked")
    private PersonNode(Person c, Children children, InstanceContent instContent)
        throws IntrospectionException
    {
        super(c, children, new AbstractLookup(instContent));
        instContent.add(c);
        model = c.getKids();
        /* this code is for move up / move down support
         * since PersonNode have it's own lookup, we cant use getCookieSet()
         * instead, we need to add an instance of Index.Support() to the 
         * node's lookup
         * */      
        instContent.add(new Index.Support()
        {
            @Override
            public Node[] getNodes()
            {
                return getChildren().getNodes(true);
            }

            @Override
            public int getNodesCount()
            {
                return getNodes().length;
            }

            @Override
            public void reorder(int[] perm)
            {
                PersonNode selectedNode = PersonTopComponent.getSelectedNode();

                PersonNode parentNode = (PersonNode) selectedNode.getParentNode();
                PersonList parentModel = parentNode.getModel();
                parentModel.reorder(perm);
            }
        });
    }

    @SuppressWarnings("unchecked")
    public PersonNode(Person bean, Children children)
        throws IntrospectionException
    {
        this(bean, children, new InstanceContent());
        payload = bean;
    }

    @Override
    protected void createPasteTypes(Transferable t, List<PasteType> s)
    {
        super.createPasteTypes(t, s);
        PasteType p = getDropType(t, 0, 0);
        if (p != null)
        {
            s.add(p);
        }
    }

    /**
     * Can this node be copied?
     * @return true if node can be copied, false otherwise
     */
    @Override
    public boolean canCopy()
    {
        return true;
    }
    
    /**
     * Can this node be cut?
     * @return true if node can be cut, false otherwise
     */
    @Override
    public boolean canCut()
    {
        return false;
    }

    /**
     * can this node be deleted?
     * @return true if node can be deleted; if so, calls destroy()
     */
    @Override
    public boolean canDestroy()
    {
        return true;
    }

    @Override
    public Transferable clipboardCopy() throws IOException
    {
        Transferable deflt = super.clipboardCopy();
        ExTransferable added = ExTransferable.create(deflt);
        added.put(new ExTransferable.Single(PersonFlavor.PERSON_FLAVOR)
        {
            @Override
            protected Person getData()
            {
                return getLookup().lookup(Person.class);
            }
        });

        return added;
    }

    @Override
    public Transferable clipboardCut() throws IOException
    {
        Transferable deflt = super.clipboardCut();
        ExTransferable added = ExTransferable.create(deflt);
        added.put(new ExTransferable.Single(PersonFlavor.PERSON_FLAVOR)
        {
            @Override
            protected Person getData()
            {
                return getLookup().lookup(Person.class);
            }
        });

        return added;
    }

    /*
     * remove this node from parent node's list of child nodes by 
     * removing the contained Person object from it's parent's list 
     * of child Persons
     */
    @Override
    public void destroy() throws IOException
    {
        PersonNode parentNode = (PersonNode) this.getParentNode();
        PersonList parentModel = parentNode.getModel();
        Person c = getLookup().lookup(Person.class);
        parentModel.remove(c);
    }

    /**
     * 
     * @param context
     * @return an array of Actions available from the right-click menu
     */
    @Override
    public Action[] getActions(boolean context)
    {
        return new Action[]
        {
            RenameAction.get(RenameAction.class),
            CopyAction.get(CopyAction.class),
            DeleteAction.get(DeleteAction.class),
            MoveUpAction.get(MoveUpAction.class),
            MoveDownAction.get(MoveDownAction.class),
            PasteAction.get(PasteAction.class), CutAction.get(CutAction.class)
        };
    }

    @Override
    public String getDisplayName()
    {
        return getLookup().lookup(Person.class).getName();
    }

    /**
     * called when node is renamed
     * @param newName - new name to set internally and show in UI
     */
    @Override
    public void setName(String newName)
    {
        Person c = getLookup().lookup(Person.class);

        if (c != null)
        {
            String oldName = c.getName();
            c.setName(newName);
            fireNameChange(oldName, newName);
            fireDisplayNameChange(oldName, newName);
        }
    }

    /**
     * top-level method that handles drop / paste application-level logic
     * @param t the dragged domain object (Person in this case)
     * @param action one of the DND Constants
     * @param index  index where the drop took place
     * @return PasteType
     */
    @Override
    public PasteType getDropType(final Transferable t, final int action, int index)
    {
        if (t.isDataFlavorSupported(PersonFlavor.PERSON_FLAVOR))
        {
            return new PasteType()
            {
                @Override
                public Transferable paste() throws IOException
                {
                    try
                    {
                        /* dropping one on another first selects the dropped-on node, which
                         * puts it in the lookup so it's available ot be found in the line below.
                         * If the drop is accepted, the dragged node (or a copy of it) is then 
                         * selected
                         */                    
                        Person droppedOnPerson = getLookup().lookup(Person.class);
                        Person draggedPerson = (Person) t.getTransferData(PersonFlavor.PERSON_FLAVOR);
                        
                        //optional ui feedback
                        String droppedOnName = droppedOnPerson.getName();
                        String draggedName = draggedPerson.getName();
                        String message;
                        if (Person.makeDropDecision(draggedPerson, droppedOnPerson) == true)
                        {
                            message = draggedName + " is now descended from " + droppedOnName;
                            droppedOnPerson.getKids().add(draggedPerson);
                        }
                        else
                        {
                            message = droppedOnName + " can't be a child of " + draggedName;
                        }

                        /*
                         * final Node node = NodeTransfer.node(t,
                         * NodeTransfer.DND_COPY_OR_MOVE ); if (node != null) {
                         * //node.destroy(); }
                            *
                         */
                        StatusDisplayer.getDefault().setStatusText(message, 15);
                    }
                    catch (UnsupportedFlavorException ex)
                    {
                        Exceptions.printStackTrace(ex);
                    }

                    return null;
                }
            };
        }
        else
        {
            StatusDisplayer.getDefault().setStatusText(" ", 10);
            return null;
        }
    }

    /**
     * @return the model
     */
    public PersonList getModel()
    {
        return model;
    }

    /**
     * @return the payload
     */
    public Person getPayload()
    {
        return payload;
    }

    /**
     * @param payload the payload to set
     */
    public void setPayload(Person payload)
    {
        this.payload = payload;
    }
}
