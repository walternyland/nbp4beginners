package org.carsales.viewer;

import java.beans.IntrospectionException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.MIMEResolver;
import org.openide.loaders.DataNode;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.nodes.BeanNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.xml.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@MIMEResolver.NamespaceRegistration(
        displayName = "Car",
        mimeType = "text/x-car+xml",
        elementName = "car")
@DataObject.Registration(
        mimeType = "text/x-car+xml",
        iconBase = "org/carsales/viewer/car.png",
        position = 100)
public class CarDataObject extends MultiDataObject {

    public CarDataObject(FileObject pf, MultiFileLoader loader)
            throws DataObjectExistsException, IOException {
        super(pf, loader);
    }

    @Override
    protected Node createNodeDelegate() {
        return new DataNode(this, Children.create(new CarLineChildFactory(), true));
    }

    private class CarLineChildFactory extends ChildFactory<org.w3c.dom.Node> {

        @Override
        protected boolean createKeys(List<org.w3c.dom.Node> list) {
            InputStream is = null;
            try {
                is = getPrimaryFile().getInputStream();
                Document doc = XMLUtil.parse(
                        new InputSource(is), true, true, null, null);
                NodeList nodeList = doc.getElementsByTagName("car");
                NodeList childNodes = nodeList.item(0).getChildNodes();
                for (int i = 0; i < childNodes.getLength(); i++) {
                    org.w3c.dom.Node item = childNodes.item(i);
                    if (!item.getNodeName().equals("#text")) {
                        list.add(item);
                    }
                }
            } catch (FileNotFoundException ex) {
                Exceptions.printStackTrace(ex);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            } catch (SAXException ex) {
                Exceptions.printStackTrace(ex);
            } finally {
                try {
                    is.close();
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
            return true;
        }

        @Override
        protected Node createNodeForKey(org.w3c.dom.Node key) {
            BeanNode node = null;
            try {
                node = new BeanNode(key);
                node.setDisplayName(key.getTextContent());
            } catch (IntrospectionException ex) {
                Exceptions.printStackTrace(ex);
            }
            return node;
        }
    }

}
