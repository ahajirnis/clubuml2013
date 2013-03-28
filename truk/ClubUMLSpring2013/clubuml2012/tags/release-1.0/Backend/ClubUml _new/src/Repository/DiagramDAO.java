package Repository;

/**
 *
 * @author
 * yangchen
 */

import Domain.Diagram;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.Display;

public class DiagramDAO {

    public static boolean addDiagram(Diagram diagram) {
        ResultSet rs;
        try {
            Connection conn = DbManager.getConnection();
            String sql = "INSERT INTO diagram (diagramName , createdTime , inEdition , owner_Id , filePath) VALUES (?,?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, diagram.getDiagramName());
            pstmt.setString(2, diagram.getCreatedTime());
            pstmt.setBoolean(3, diagram.isInEdition());
            pstmt.setInt(4, diagram.getOwerId());
            pstmt.setString(5, diagram.getFilePath());

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
    
    public static ArrayList<Diagram> getDiagramList()
    {
        ArrayList<Diagram> diagrams = new ArrayList<Diagram>();
         try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/clubuml2", "root", ""); 
            Statement stmnt = con.createStatement();
            String sql = "SELECT * FROM diagram ORDER BY createdTime DESC";
            
            ResultSet rs = stmnt.executeQuery(sql);
       
            while(rs.next())
            {
                Diagram diagram = new Diagram(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), 
                        true, Integer.parseInt(rs.getString(5)), rs.getString(6));
                
                diagrams.add(diagram);             
            }
            
            
            if (rs != null)
            {
               rs.close();
            } 
            if (stmnt != null)
            {
               stmnt.close();
            }
            if (con != null)
            {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
        }
       return diagrams;
    }

    public static ArrayList<Diagram> getDiagramList(int project_Id) {
        ArrayList<Diagram> searchResult = new ArrayList<Diagram>();
        try {
            Connection conn = DbManager.getConnection();
            String sql = "SELECT * FROM diagram WHERE project_Id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, project_Id);

            ResultSet rs = pstmt.executeQuery();

            //Initiate a list to get all returned report objects and set attributes
            while (rs.next()) {
                Diagram diagram = new Diagram();
                diagram.setDiagramId(rs.getInt("diagram_Id"));
                diagram.setDiagramName(rs.getString("diagramName"));
                diagram.setCreatedTime(rs.getString("createdTime"));
                diagram.setInEdition(rs.getBoolean("inEdition"));
                diagram.setOwerId(rs.getInt("owner_Id"));
                diagram.setFilePath("filePath");
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
    
    public static Diagram getDiagram (int diagram_Id) {
        try {
            Connection conn = DbManager.getConnection();
            String sql = "SELECT * FROM diagram WHERE diagram_Id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, diagram_Id);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (!rs.next()) {
                return null;
            }
            
            Diagram dia = new Diagram();
            dia.setDiagramId(rs.getInt("diagram_Id"));
            dia.setDiagramName(rs.getString("diagramName"));
            dia.setCreatedTime(rs.getString("createdTime"));
            dia.setInEdition(rs.getBoolean("inEdition"));
            dia.setOwerId(rs.getInt("owner_Id"));
            dia.setFilePath(rs.getString("filePath"));
            
            pstmt.close();
            conn.close();
            return dia;
        } catch (SQLException ex) {            
            Logger.getLogger(DiagramDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean updateDiagram(Diagram diagram) {
        try {
            Connection conn = DbManager.getConnection();
            String sql = "UPDATE diagram SET diagramName = ? , createdTime = ? , inEdition = ? , filePath = ? WHERE diagram_Id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, diagram.getDiagramName());
            pstmt.setString(2, diagram.getCreatedTime());
            pstmt.setBoolean(3, diagram.isInEdition());
            pstmt.setString(4, diagram.getFilePath());
            pstmt.setInt(5, diagram.getDiagramId());
            
            pstmt.executeUpdate();
            
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DiagramDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static boolean deleteDiagram (Diagram diagram) {
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
