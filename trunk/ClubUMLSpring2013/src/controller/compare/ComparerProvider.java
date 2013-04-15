package controller.compare;
import java.util.ArrayList;

/**
 * ComparerProvider Class, provides suitable comparer based on different diagram types
 * 
 */

public class ComparerProvider {
	
	private static ComparerProvider instance = null;
	private ArrayList<String> comparerLookUpTable = new ArrayList<String>();
	
	/**
	 * Constructor, adding three kinds of diagrams in ArrayList
	 * for future checking
	 */
	private ComparerProvider(){		
		comparerLookUpTable.add("xmiClassDiagram");
		comparerLookUpTable.add("ecoreClassDiagram");
		comparerLookUpTable.add("xmiSequenceDiagram");
	}
	
	/**
	 * Instance provider 
	 * 
	 * @return ComparerProvider 
	 */
	
	public static ComparerProvider getInstance(){
		if(instance == null){
			synchronized(ComparerProvider.class){
				if(instance == null){
					instance = new ComparerProvider();
				}
			}
		}
		return instance;
	}	
	
	/**
	 * @param diagramType
	 * 			The type of input diagram
	 * 
	 * @return comparer
	 * 			The instance of different Comparers
	 */
	
	public ComparerIntf createComparer(String diagramType)
	{
		ComparerIntf comparer = null;
		if (!comparerLookUpTable.contains(diagramType))
		{
			return comparer;
		}
		else
		{
			if(diagramType.equals("xmiClassDiagram"))
			{
				comparer = new XmiClassDiagramComparer();
			}
			else if(diagramType.equals("ecoreClassDiagram"))
			{
				comparer = new EcoreClassDiagramComparer();
			}
			else if(diagramType.equals("xmiSequenceDiagram"))
			{
				comparer = new SequenceDiagramComparer();
			}
		}
		return comparer;
	}

}