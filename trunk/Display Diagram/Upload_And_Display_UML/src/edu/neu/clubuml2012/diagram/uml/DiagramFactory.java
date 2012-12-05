/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package neu.kai.uml;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 *
 * @author Kai
 */
public class DiagramFactory {
    
    private static String absolutePath;
    private static String libPath;
    private String ecoreFileName;
    private String javaFileName;
    private String dotFileName;
    private String pngFileName;
    private ArrayList<EObject> pkgs;  
    private BufferedWriter out;
    String command;
    File javaFile;
    private String FontColor[] = {"red", "black", "blue", "red", "yellow" , "black"};
    private String FillColor[] = {"pink", "orange", "LightYellow", "PaleGreen", "White", "LightGray"};
    private int pkgIndex = 0;
    private final int MAX_NUM_FONT_COLORS = 6;
    
    public static void main(String[] args) {
        
        //parse args
        if (args.length <=2)
        {
            System.out.println("usage:  CreatePng PathAndNameToEcorefile newname pathToLib");
            System.out.println("        where PathAndNameToEcorefile is the ecore source file");
            System.out.println("        and newname is the new file name");
            System.out.println("        and pathToLib is the UMLGRAPH library");
            return;
        }
        

        DiagramFactory obj = new DiagramFactory(args[0],args[1], args[2]);
        obj.process();
    }
    public DiagramFactory(String absolutePath, String newName, String libPath){
        DiagramFactory.absolutePath = absolutePath;
        DiagramFactory.libPath = libPath;
        ecoreFileName = newName;
        javaFileName = newName  + ".java";
        dotFileName = newName + ".dot";
        pngFileName = newName + ".png"; 
    }

    public String process() {
        try {
            String ecoreFilePath = absolutePath + ecoreFileName;
            EcoreParser parserObj = new EcoreParser(ecoreFilePath);
            pkgs = parserObj.getList();
        } catch (IOException e) {
            System.out.println("Error in getting Eobject");
            e.printStackTrace();
        }

        createJavaFile();
        createPngFile();
        
        return command;
    }

    public void createJavaFile() {

        //Create Stream Writer
        try {
            javaFile = new File(absolutePath + javaFileName);
            FileWriter fstream = new FileWriter(javaFile);
            out = new BufferedWriter(fstream);
            
            // write a header for the whole file
            out.write("import java.util.Date;\n");
            out.write("import java.util.*; \n");
            out.write("import java.io.*; \n");
            
            out.write("\n");
            out.write("/**\n");
            out.write(" * @opt edgecolor \"yellow\"\n");
            out.write(" * @opt nodefontcolor \"red\"\n");
            out.write(" * @opt nodefillcolor \"#a0a0a0\"\n");
            out.write(" * @hidden\n");
            out.write(" */ \n");
            
            pkgIndex = 0;
            
            //for each package
            for (int k = 0; k < pkgs.size(); k++) {
                readPkgForClasses(pkgs.get(k));          
                
            }
            out.close();
        } catch (Exception e) {
            try {
                out.close();
            } catch (Exception e2) {
                System.out.println("Error closing the file");
            }
            System.out.println("Got an error creating the file");
        }
    }

    //This method is recursive
    public void readPkgForClasses(EObject obj) {
        try {
            //determine if this is a pkg
            if (obj instanceof EPackage) {
                // get contents of pkg
                //increment pkgIndex for colors
                pkgIndex = (pkgIndex + 1) % MAX_NUM_FONT_COLORS;
                out.write("//Name of Ecore Package = " + ((ENamedElement) obj).getName()  + "\n\n");
                EList<EObject> objList = obj.eContents();
               for (int k = 0; k < objList.size(); k++) {
                   readPkgForClasses(objList.get(k));

                }
             } else {       //if not a pkg must be a class since underneath a package  
                EClass cls = (EClass) obj;
                out.write("\n");
                out.write("/**\n");
                //define colors for the class
                out.write(" * @opt nodefontcolor " + "\"" + FontColor[pkgIndex] + "\""  + "\n");
                out.write(" * @opt nodefillcolor " + "\"" + FillColor[pkgIndex] + "\""  + "\n");              
                //this is where we would write what this contains
                EList<EReference>  refList = cls.getEReferences();
                for (int j = 0; j < refList.size(); j++) {
                   EReference ref = refList.get(j);
                   
                   if (ref.isContainment()) {   
                       out.write (" * @composed 1 ");

                       if (ref.getName() == null) {
                           out.write(" - ");  
                       } else {
                           out.write(" ");
                           out.write(ref.getName());
                           out.write(" ");
                       }
                       //This is the wrong upper bound, I need the bound at the other end
                       if (ref.getLowerBound() == 0){
                           out.write("0");
                       } else {
                           out.write(ref.getLowerBound());
                       }
                       //if there is a range of values
                       if (ref.getLowerBound() != ref.getUpperBound()) {//
                           out.write("..");
                           if (ref.getUpperBound() == -1) {
                               out.write ("*");
                           } else {
                               out.write(ref.getUpperBound());
                           }
                       }
                       out.write( " " + ref.getEReferenceType().getName() + "\n");   
                   
                   } else {
                       out.write (" * @has ");
                       if (ref.getLowerBound() == -1) {
                           out.write ("1..*");
                       } else if (ref.getLowerBound() == 0){
                           out.write("0");
                       } else if (ref.getLowerBound() == 1){
                           out.write("1");
                       } else {
                           out.write(ref.getLowerBound());
                       }
                       if (ref.getName() == null) {
                           out.write(" - ");  
                       } else {
                           out.write(" ");
                           out.write("\"" + ref.getName() + "\"");
                           out.write(" ");
                       }
                       //This might be the wrong upper bound, I need the bound at the other end
                       if (ref.getLowerBound() == 0){
                           out.write("0");
                       } else if (ref.getLowerBound() == 1){
                           out.write("1");
                       } else {
                           out.write(ref.getLowerBound());
                       }
                       //if there is a range of values
                       if (ref.getLowerBound() != ref.getUpperBound()) {//
                           out.write("..");
                           if (ref.getUpperBound() == -1) {
                               out.write ("*");
                           } else {
                               out.write(ref.getUpperBound());
                           }
                       }
                       out.write( " " + ref.getEReferenceType().getName() + "\n");               
                   }
                }
                out.write(" */ \n");

                out.write("class " + cls.getName());

                //check if this extends any other classes
                EList<EClass> superClsList = cls.getEAllSuperTypes();
                for (int j = 0; j < superClsList.size(); j++) {
                    EClass otherCls = superClsList.get(j);
                    out.write(" extends " + otherCls.getName());
                }
                out.write(" {\n");

                //for each variable in the class
                EList<EAttribute> attr = cls.getEAllAttributes();
                for (int j = 0; j < attr.size(); j++) {
                    out.write("    " + attr.get(j).getEAttributeType().getInstanceClassName()
                            + " " + attr.get(j).getName() + ";\n");
                }

                //get operation objects into a list
                EList<EOperation> ope = cls.getEAllOperations();
                for (int j = 0; j < ope.size(); j++) {
                    out.write("    " + ope.get(j).getEType().getInstanceTypeName()     + " " + ope.get(j).getName() + ";\n");
                }
                out.write("}\n");
            }
       // } 
        } catch (Exception e) {
            System.out.println("Got an writing to file" + e); 
        }
    }
    
    
    public void createPngFile() {
        try {
            String umlGraphPath = libPath + "UmlGraph.jar";
            String command1 = "java" + " -jar " + umlGraphPath + " -all -private "+ absolutePath + javaFileName +
                    " -output " +  absolutePath + dotFileName;
            Runtime.getRuntime().exec(command1);
            try {
                // have to wait before go on, so that the shell has time to create the dot file
                Thread.sleep(1000L);
            } catch (Exception e) {
            }
            
            //Graphviz_Path: C:\Program Files (x86)\Graphviz 2.28\bin
            String command2[] = {"dot", "-Tpng", "-o", absolutePath + pngFileName,
                    absolutePath + dotFileName};
            Runtime.getRuntime().exec(command2);
            command = command2.toString();
            try {
                Thread.sleep(2000L);
            } catch (Exception e) {
            }
        } catch (Exception e) {
            System.out.println("Error in creating the png file");
            e.printStackTrace();
        }
    }
    
    public static void copyFile( File from, File to ) throws IOException {
        
        // Attempt to delete it
        if(to.exists()){
            boolean success = to.delete();
            if (!success) {
                throw new IllegalArgumentException("Delete: deletion failed");      
            }
         }
        Files.copy( from.toPath(), to.toPath() );
    }
}//end class
