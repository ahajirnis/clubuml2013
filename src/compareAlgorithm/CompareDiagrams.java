package compareAlgorithm;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.File;
import java.util.Date;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * CompareDiagrams class implements an application that simply compares 2 UML
 * diagrams It takes 2 String type parameters which are path of EcoreDiagram
 * files find out differences between these diagrams writes these differences
 * into a pdf file and returns the path of this pdf file
 *
 */
public class CompareDiagrams {

	private String ecorePath1; // Path to the first diagram (E-Core file)
	private String ecorePath2; // Path to the second diagram (E-Core file)
	private String pathToReport; // Path to the directory where the report is
									// generated
	private String DIRECTORY = "~/tmp/clubuml/reports/"; // Default directory
															// for report
															// generation

	/**
	 * Initializes the class fields and creates a report document
	 *
	 * @param firstDiagram
	 *            Path to the first diagram (E-Core file)
	 * @param secondDiagram
	 *            Path to the second diagram (E-Core file)
	 * @param reportPath
	 *            Path to the directory where the report is generated, Defaults
	 *            to "~/tmp/clubuml/reports/" if a empty string is passed
	 * @throws Exception
	 *             mainly when the pdf generation fails
	 */

	public CompareDiagrams(String firstDiagram, String secondDiagram,
			String reportPath) {

		// Populating class members
		this.ecorePath1 = firstDiagram;
		this.ecorePath2 = secondDiagram;
		// Setting appropriate report path
		if (reportPath.equals("")) {
			pathToReport = DIRECTORY + new File(firstDiagram).getName() + "_"
					+ new File(secondDiagram).getName() + ".pdf";
			// pathToReport = DIRECTORY + "first_second.pdf";
		} else {
			pathToReport = reportPath +"/"+ new File(firstDiagram).getName() + "_"
					+ new File(secondDiagram).getName() + ".pdf";
			// this.pathToReport = reportPath + "first_second.pdf";
		}
	}

	/**
	 * Does the comparison of the specified diagrams in the initialize phase
	 *
	 * @return The path where the report has been generated
	 * @throws Exception
	 *             Mainly when the ECore files are not in proper format
	 */
	public String process() throws Exception {
		try {
			// Set up the path and parse the file into ECore packages
			MyParser parser = new MyParser(this.ecorePath1, this.ecorePath2);
			parser.parseModels();

			// Set up the comparison algo
			Algo algoObj = new Algo(parser.getFirstModel(),
					parser.getSecondModel(), pathToReport);

			// Begin comparison
			algoObj.compare();

		} catch (IOException e) {
			throw new Exception(e);
		}
		return pathToReport;
	}
}