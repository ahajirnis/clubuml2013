package controller.upload;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;

import controller.EcoreParser;

/**
 * Upload processor for Ecore files
 * 
 * @author RD2012
 * 
 */
public class EcoreUploadProcessor extends ClassPngFile {

	private String absolutePath;
	private String libPath;
	private String ecoreFileName;
	private String javaFileName;

	private ArrayList<EObject> pkgs;
	private BufferedWriter out;

	/**
	 * Constructor for Ecore file
	 * 
	 * @param pDestFilePath
	 *            destination path to create new files.
	 * @param pFileName
	 *            Ecore file name. Also used to generate other related file
	 *            names (.dot, .java, .png).
	 * @param pLibPath
	 *            path to the library directory for web application.
	 */
	public EcoreUploadProcessor(String pDestFilePath, String pFileName,
			String pLibPath) {
		this.absolutePath = pDestFilePath;
		this.libPath = pLibPath;
		this.ecoreFileName = pFileName;
		this.javaFileName = pFileName + ".java";
	}

	@Override
	public void process() {
		try {
			String ecoreFilePath = absolutePath + ecoreFileName;
			EcoreParser parserObj = new EcoreParser(ecoreFilePath);
			pkgs = parserObj.getList();
		} catch (IOException e) {
			System.out.println("Error in getting Eobject");
			e.printStackTrace();
		}

		this.createJavaFile();
		this.createPngFile(ecoreFileName, javaFileName, absolutePath, libPath);

	}

	/**
	 * Generates Java file
	 */
	public void createJavaFile() {

		// Create Stream Writer
		try {
			// for each package
			for (int k = 0; k < pkgs.size(); k++) {
				// name java file after first class in pkg
				File javaFile = new File(absolutePath + javaFileName);
				FileWriter fstream = new FileWriter(javaFile);
				out = new BufferedWriter(fstream);

				System.out.println(pkgs.get(k));

				out.write("//Name of Ecore Package = " + pkgs.get(k) + "\n\n");
				out.write("import java.util.Date;\n");
				out.write("import java.util.*; \n");
				out.write("import java.io.*; \n");

				boolean firstClass = true;
				// iterate thru an array list of classes
				EList<EObject> clsList = pkgs.get(0).eContents();
				for (int i = 0; i < clsList.size(); i++) {
					EClass cls = (EClass) clsList.get(i);

					out.write("\n");
					out.write("/**\n");
					// this is where we would write what this contains
					EList<EReference> refList = cls.getEReferences();
					for (int j = 0; j < refList.size(); j++) {
						System.out.println(cls + "\n        " + refList.get(j));
						EReference ref = refList.get(j);
						// if (ref.isContainer()) { // didn't print anything
						if (ref.isContainment()) {
							out.write(" * @composed 1 ");

							if (ref.getName() == null) {
								out.write(" - ");
							} else {
								out.write(" ");
								out.write(ref.getName());
								out.write(" ");
							}
							// This is the wrong upper bound, I need the bound
							// at the other end
							if (ref.getLowerBound() == 0) {
								out.write("0");
							} else {
								out.write(ref.getLowerBound());
							}
							// if there is a range of values
							if (ref.getLowerBound() != ref.getUpperBound()) {//
								out.write("..");
								if (ref.getUpperBound() == -1) {
									out.write("*");
								} else {
									out.write(ref.getUpperBound());
								}
							}
							out.write(" " + ref.getEReferenceType().getName()
									+ "\n");
							System.out.println(ref.getName());

						} else {
							out.write(" * @has 1 ");
							if (ref.getLowerBound() == -1) {
								out.write("1..*");
							} else if (ref.getLowerBound() == 0) {
								out.write("0");
							} else {
								out.write(ref.getLowerBound());
							}
							// if (ref.isUnique())
							if (ref.getName() == null) {
								out.write(" - ");
							} else {
								out.write(" ");
								out.write(ref.getName());
								out.write(" ");
							}
							// This is the wrong upper bound, I need the bound
							// at the other end
							if (ref.getLowerBound() == 0) {
								out.write("0");
							} else if (ref.getLowerBound() == 1) {
								out.write("1");
							} else {
								out.write(ref.getLowerBound());
								// System.out.println (ref.getLowerBound());
							}
							System.out.println(ref.getLowerBound());
							System.out.println(ref.getUpperBound());
							// if there is a range of values
							if (ref.getLowerBound() != ref.getUpperBound()) {//
								out.write("..");
								if (ref.getUpperBound() == -1) {
									out.write("*");
								} else {
									out.write(ref.getUpperBound());
								}
							}
							out.write(" " + ref.getEReferenceType().getName()
									+ "\n");
						}
					}
					out.write(" */ \n");

					if (firstClass) {
						// only first one is public: otherwise get errors in
						// UMLGraph
						out.write("class " + cls.getName());
						firstClass = false;
					} else {
						out.write("class " + cls.getName());
					}
					// check if this extends any other classes
					EList<EClass> superClsList = cls.getEAllSuperTypes();
					for (int j = 0; j < superClsList.size(); j++) {
						EClass otherCls = superClsList.get(j);
						out.write(" extends " + otherCls.getName());
					}
					out.write(" {\n");

					// for each variable in the class
					EList<EAttribute> attr = cls.getEAllAttributes();
					for (int j = 0; j < attr.size(); j++) {
						out.write("    "
								+ attr.get(j).getEAttributeType()
										.getInstanceClassName() + " "
								+ attr.get(j).getName() + ";\n");
					}

					// get operation objects into a list
					EList<EOperation> ope = cls.getEAllOperations();
					for (int j = 0; j < ope.size(); j++) {
						System.out.println("119");
						out.write("    " + ope.get(j).getEType() + " "
								+ ope.get(j).getName() + ";\n");
					}
					out.write("}\n");
				}
				out.close();
				fstream.close();
			}
		} catch (Exception e) {
			try {
				out.close();
			} catch (Exception e2) {
				System.out.println("Error closing the file");
			}
			System.out.println("Got an error creating the file...."
					+ e.getMessage());
		}

		System.out.println("Complete creating java file.");
	}

}
