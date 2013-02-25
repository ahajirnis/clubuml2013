package controller.compare;
import java.util.ArrayList;

public class ComparerProvider {
	
	private static ComparerProvider instance = null;
	private ArrayList<String> comparerLookUpTable = new ArrayList<String>();
	
	
	private ComparerProvider(){		
		comparerLookUpTable.add("xmiClassDiagram");
		comparerLookUpTable.add("ecoreClassDiagram");
		comparerLookUpTable.add("xmiSequenceDiagram");
	}
	
	
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