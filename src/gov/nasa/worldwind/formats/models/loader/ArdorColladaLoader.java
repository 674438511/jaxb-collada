/**
 * Copyright (c) 2008-2010 Ardor Labs, Inc.
 *
 * This file is part of Ardor3D.
 *
 * Ardor3D is free software: you can redistribute it and/or modify it 
 * under the terms of its license which may be found in the accompanying
 * LICENSE file or at <http://www.ardor3d.com/LICENSE>.
 */

package gov.nasa.worldwind.formats.models.loader;

import java.io.File;
import java.net.URL;

import com.ardor3d.extension.model.collada.jdom.ColladaImporter;
import com.ardor3d.extension.model.collada.jdom.data.ColladaStorage;
import com.ardor3d.scenegraph.Node;
import com.ardor3d.util.resource.ResourceLocatorTool;
import com.ardor3d.util.resource.SimpleResourceLocator;

/**
 * Simplest example of loading a Collada model.
 */

public class ArdorColladaLoader {
    
    
    public static Node loadColladaModel(String modelFileStr) throws Exception {
    	
        final Node root = new Node( "rootNode" );

        String modelDirStr = modelFileStr.substring(0, modelFileStr.lastIndexOf(File.separator));
        String modelNameStr = modelFileStr.substring(modelFileStr.lastIndexOf(File.separator)+1);

        File modelDir = new File(modelDirStr);
        modelDirStr = modelDir.getAbsolutePath();
        
        ColladaImporter importer = new ColladaImporter();

        SimpleResourceLocator modelLocator = new SimpleResourceLocator(new URL("file:" + modelDirStr));
        SimpleResourceLocator textureLocator = new SimpleResourceLocator(new URL("file:" + modelDirStr));
        importer.setModelLocator(modelLocator);
        importer.setTextureLocator(textureLocator);

        ResourceLocatorTool.addResourceLocator(ResourceLocatorTool.TYPE_MODEL, modelLocator);
        ResourceLocatorTool.addResourceLocator(ResourceLocatorTool.TYPE_TEXTURE, textureLocator);
        
        ColladaStorage storage =  importer.load(modelNameStr);
        root.attachChild(storage.getScene());

        root.updateGeometricState(0);
        return root;
    }
}