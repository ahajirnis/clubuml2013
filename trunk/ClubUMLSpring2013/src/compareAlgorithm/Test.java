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
		final String firstDiagram = "F:\\NEU_Files\\CSYE 7945_Software Engineering Project - Spring 2013\\ClubUML\\trunk\\Compare\\Test1.ecore";
		final String secondDiagram = "F:\\NEU_Files\\CSYE 7945_Software Engineering Project - Spring 2013\\ClubUML\\trunk\\Compare\\Test2.ecore";
		final String reportPath = "F:\\NEU_Files\\CSYE 7945_Software Engineering Project - Spring 2013\\report\\";
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
