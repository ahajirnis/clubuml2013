package controller.compare;
import java.util.ArrayList;

public class ComparerProvider {
	
	private static ComparerProvider instance = null;
	private ArrayList<String> comparerLookUpTable = new ArrayList<String>();
	
	private ComparerProvider(){		
		comparerLookUpTable.add("classDiagram");
		comparerLookUpTable.add("sequenceDiagram");
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
			if(diagramType.equals("classDiagram"))
			{
				comparer = new ClassDiagramComparer();
			}
			else if(diagramType.equals("sequenceDiagram"))
			{
				comparer = new SequenceDiagramComparer();
			}
		}
		return comparer;
	}

}