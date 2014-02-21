package org.carsales.graph;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.carsales.api.Part;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.MIMEResolver;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.nodes.Node;

@MIMEResolver.ExtensionRegistration(
        displayName = "Part",
        mimeType = "text/x-part-palette-item",
        extension = {"part"}
)
@DataObject.Registration(
        mimeType = "text/x-part-palette-item",
        displayName = "Part",
        position = 300
)
public class PartDataObject extends MultiDataObject {

    Part data;

    public PartDataObject(FileObject pf, MultiFileLoader loader)
            throws DataObjectExistsException, IOException {
        super(pf, loader);
        InputStream input = pf.getInputStream();
        Properties props = new Properties();
        props.load(input);
        input.close();
        data = new Part(props);
    }

    @Override
    protected Node createNodeDelegate() {
        return new PartDataNode(this, data);
    }
}
