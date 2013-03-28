package repository;

/**
 * @author Yidu Liang
 * @author yangchen
 */
import domain.Diagram;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiagramDAO {

    /**
     * Add Diagram into DB (diagram name, created time, in edition, owner Id, file path)
     * 			
     * @param Diagram object
     * 			diagramName, inEdition, ownerId, ecoreFilePath
     * @return true if success; false if fail
     */
    public static boolean addDiagram(Diagram diagram) {
	ResultSet rs;
	try {
	    Connection conn = DbManager.getConnection();
	    //String sql = "INSERT INTO diagram (diagramName , createdTime , inEdition , owner_Id , filePath) VALUES (?,NOW(),?,?,?);";
		//add by Yidu Liang Mar 20 2013
		
		String sql = "insert into diagram (projectId, userId, diagramType, diagramName, filePath, fileType, notationFileName, notationFilePath, diFileName, diFilePath)"+
		"VALUES (?, ?, ?, ?, ?, ? ,?, ?, ?, ?)";
		
		
	    PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

	    //pstmt.setString(1, diagram.getDiagramName());
	    //pstmt.setBoolean(2, diagram.isInEdition());
	    //pstmt.setInt(3, diagram.getOwnerId());
	    //pstmt.setString(4, diagram.getEcoreFilePath());
		
		pstmt.setString(1,diagram.getProjectName()); // this need to be implementing 
		pstmt.setString(2,diagram.getOwnerId());
		pstmt.setString(3,diagram.getdiagramType()); // this need to be implementing 
		pstmt.setString(4,diagram.diagramName());    // this need to be implementing 
		pstmt.setString(5,diagram.getFilePath());    // this need to be implementing 
		pstmt.setString(6,diagram.getfileType());    // this need to be implementing 
		pstmt.setString(7,diagram.getnotationFileName());   // this need to be implementing 
		pstmt.setString(8,diagram.getnotationFilePath());   // this need to be implementing 
		pstmt.setString(9,diagram.getdiFileName());   // this need to be implementing 
		pstmt.setString(10,diagram.diFilePath());     // this need to be implementing 
		
	    pstmt.executeUpdate();

	    //Get and set the auto-generated PK
	    rs = pstmt.getGeneratedKeys();
	    if (rs.next()) {
		int newId = rs.getInt(1);
		diagram.setDiagramId(newId);
	    }

	    rs.close();
	    pstmt.close();
	    conn.close();
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(DiagramDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
	return false;
    }

    /**
     * Get Diagram ArrayList from DB
     * 
     * @param projectId
     * 			The ID of the project
     * @return Diagram ArrayList
     */
    public static ArrayList<Diagram> getDiagramList(int project_Id) {
	ArrayList<Diagram> searchResult = new ArrayList<>();
	try {
	    Connection conn = DbManager.getConnection();
	    String sql = "SELECT * FROM diagram;";
	    PreparedStatement pstmt = conn.prepareStatement(sql);

	    //pstmt.setInt(1, project_Id);

	    ResultSet rs = pstmt.executeQuery();

	    //Initiate a list to get all returned report objects and set attributes
	    /*
		while (rs.next()) {
		Diagram diagram = new Diagram();
		diagram.setDiagramId(rs.getInt("diagram_Id"));
		diagram.setDiagramName(rs.getString("diagramName"));
		diagram.setCreatedTime(rs.getString("createdTime"));
		diagram.setInEdition(rs.getBoolean("inEdition"));
		diagram.setOwnerId(rs.getInt("owner_Id"));
		diagram.setEcoreFilePath(rs.getString("filePath"));
		searchResult.add(diagram);
	    }
		*/
		//add by Yidu Liang Mar22 2013  projectId, userId, diagramType, diagramName, filePath, fileType, notationFileName, notationFilePath, diFileName, diFilePath
		while (rs.next()) {
		Diagram diagram = new Diagram();
		diagram.setDiagramId(rs.getInt("diagramId"));
		diagram.setDiagramProject(rs.getInt("projectId"));
		diagram.setDiagramOwner(rs.getInt("userId"));
		diagram.setDiagramType(rs.getString("diagramType"));
		diagram.setDiagramName(rs.getString("diagramName"));
		diagram.setFilePath(rs.getString("filePath"));
		diagram.setFileType(rs.getString("fileType"));
		diagram.setnotationFilename(rs.getString("notationFileName"));
		diagram.setnotationFilePath(rs.getString("notationFilePath"));
		diagram.setdiFilename(rs.getString("diFileName"));
		diagram.setdiFilePath(rs.getString("diFilePath"));
		
		searchResult.add(diagram);
	    }

	    rs.close();
	    pstmt.close();
	    conn.close();
	    return searchResult;
	} catch (SQLException ex) {
	    Logger.getLogger(DiagramDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
	return null;
    }

    /**
     * Get Diagram from DB
     * 
     * @param diagram_Id
     * 			The ID of the diagram
     * @return Diagram object
     */
    public static Diagram getDiagram(int diagram_Id) {
	try {
	    Connection conn = DbManager.getConnection();
	    String sql = "SELECT * FROM diagram WHERE diagramId = ?;";
	    PreparedStatement pstmt = conn.prepareStatement(sql);

	    pstmt.setInt(1, diagram_Id);

	    ResultSet rs = pstmt.executeQuery();

	    if (!rs.next()) {
		return null;
	    }

	    Diagram dia = new Diagram();
		diagram.setDiagramId(rs.getInt("diagramId"));
		diagram.setDiagramProject(rs.getInt("projectId"));
		diagram.setDiagramOwner(rs.getInt("userId"));
		diagram.setDiagramType(rs.getString("diagramType"));
		diagram.setDiagramName(rs.getString("diagramName"));
		diagram.setFilePath(rs.getString("filePath"));
		diagram.setFileType(rs.getString("fileType"));
		diagram.setnotationFilename(rs.getString("notationFileName"));
		diagram.setnotationFilePath(rs.getString("notationFilePath"));
		diagram.setdiFilename(rs.getString("diFileName"));
		diagram.setdiFilePath(rs.getString("diFilePath"));

	    pstmt.close();
	    conn.close();
	    return dia;
	} catch (SQLException ex) {
	    Logger.getLogger(DiagramDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
	return null;
    }

    /**
     * Update Diagram from DB
     * 
     * @param Diagram object
     * 			projectId, userId, diagramType, diagramName, filePath, fileType, notationFileName, notationFilePath, diFileName, diFilePath
     * @return true if success; false if fail
     */
    public static boolean updateDiagram(Diagram diagram) {
	try {
	    Connection conn = DbManager.getConnection();
	    String sql = "UPDATE diagram SET projectId = ?, userId = ?, diagramType = ?, diagramName = ?, filePath = ?, fileType = ?, notationFileName = ?, notationFilePath= ?, diFileName = ?, diFilePath = ? WHERE diagramId = ?;";
	    PreparedStatement pstmt = conn.prepareStatement(sql);

	    pstmt.setString(1,diagram.getProjectName()); // this need to be implementing 
		pstmt.setString(2,diagram.getOwnerId());
		pstmt.setString(3,diagram.getdiagramType()); // this need to be implementing 
		pstmt.setString(4,diagram.diagramName());    // this need to be implementing 
		pstmt.setString(5,diagram.getFilePath());    // this need to be implementing 
		pstmt.setString(6,diagram.getfileType());    // this need to be implementing 
		pstmt.setString(7,diagram.getnotationFileName());   // this need to be implementing 
		pstmt.setString(8,diagram.getnotationFilePath());   // this need to be implementing 
		pstmt.setString(9,diagram.getdiFileName());   // this need to be implementing 
		pstmt.setString(10,diagram.diFilePath());     // this need to be implementing 

	    pstmt.executeUpdate();

	    pstmt.close();
	    conn.close();
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(DiagramDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
	return false;
    }

    /**
     * Delete Diagram from DB
     * 
     * @param Diagram object
     * @return true if success; false if fail
     */    
    public static boolean deleteDiagram(Diagram diagram) {
	try {
	    Connection conn = DbManager.getConnection();
	    String sql = "DELETE FROM diagram WHERE diagram_Id = ?;";
	    PreparedStatement pstmt = conn.prepareStatement(sql);

	    pstmt.setInt(1, diagram.getDiagramId());

	    pstmt.executeUpdate();

	    pstmt.close();
	    conn.close();
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(DiagramDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
	return false;
    }
}
