package controller.compare;
import java.util.HashMap;

public class ComparerProvider {
	
	private static ComparerProvider instance = null;
	private HashMap<String,ComparerIntf> comparerLookUpTable = new HashMap<String,ComparerIntf>();
	
	private ComparerProvider(){		
		comparerLookUpTable.put("classDiagram", new ClassDiagramComparer());
		comparerLookUpTable.put("sequenceDiagram", new SequenceDiagramComparer());
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
	    return comparerLookUpTable.get(diagramType);
	}

}