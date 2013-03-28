package compareAlgorithm;
import java.io.IOException;
import java.util.ArrayList;
import org.eclipse.emf.ecore.EObject;
/**
 * Test class for invoking the algorithm
 * @author Shoeb Shaikh
 *
 */
public class Test {

	public static void main(String[] args) throws Exception
	{
		//Change the path as per thr file location
		final String firstDiagram = "C:\\eclipse-jee-juno-win32\\Workspace\\ParsingProject-v2\\DefaultName1.ecore";
		final String secondDiagram = "C:\\eclipse-jee-juno-win32\\Workspace\\ParsingProject-v2\\DefaultName.ecore";
		final String reportPath = "C:\\reports\\";
		try {
			CompareDiagrams compareDigs = new CompareDiagrams(firstDiagram, secondDiagram, reportPath);
			String reportFile = compareDigs.process();
			System.out.println("Report is saved to : " + reportFile);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
