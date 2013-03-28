package edu.neu.csye.clubuml2012.compareAlgorithm;
import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

/**
 * Class for parsing the EMF ecore files
 * @author Pratham
 *
 */
public class MyParser
{
	private String ecorePath1;
	private String ecorePath2;
	
	private EList<EObject> firstModel;
	private EList<EObject> secondModel;
	
	public MyParser(String path1, String path2) throws IOException
	{
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		this.ecorePath1 = path1;
		this.ecorePath2 = path2;
	}
	/**
	 * Load the file into the ECore list
	 * @throws IOException
	 */
	public void parseModels() throws IOException
    {
       this.setFirstModel(this.loadModel(this.ecorePath1));
       this.setSecondModel(this.loadModel(this.ecorePath2));
//       System.out.println("Begin Comparison....");
//       System.out.println("---------------------------");
    }
	
	private EList<EObject> loadModel(String path) throws IOException
	{
	       ResourceSet resourceSet = new ResourceSetImpl();
	       Resource res = new ResourceImpl();
	       
	       URI uri = URI.createFileURI(path);       
	       res = resourceSet.createResource(uri);
	       res.load(Collections.emptyMap());
	       EList<EObject> objList = (EList<EObject>) res.getContents();
	       EObject pkg = objList.get(0);
	       return pkg.eContents();
	}

	public EList<EObject> getSecondModel() {
		return secondModel;
	}

	public void setSecondModel(EList<EObject> secondModel) {
		this.secondModel = secondModel;
	}

	public EList<EObject> getFirstModel() {
		return firstModel;
	}

	public void setFirstModel(EList<EObject> firstModel) {
		this.firstModel = firstModel;
	}
}
