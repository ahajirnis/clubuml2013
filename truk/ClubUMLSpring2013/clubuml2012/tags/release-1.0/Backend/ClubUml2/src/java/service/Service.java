/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service;
import Domain.Comment;
import Domain.Diagram;
import controller.Display;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wintor12
 */

/*
 * Service for assisting to manage data from databse.
 */
public class Service{
    
  /*
   * get Diagram list from databse. 
   */  
    public ArrayList<Diagram> getDiagramList()
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
    /*
     * function to get diagram name by id.
     */
    public String getDiagramName(int id)
    {
        Diagram diagram = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/clubuml2", "root", ""); 
            Statement stmnt = con.createStatement();
            String sql = "SELECT * FROM diagram WHERE diagram_Id = '" + id + "'";
            
            ResultSet rs = stmnt.executeQuery(sql);
  
            if(rs.next())
            {
                diagram = new Diagram(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), 
                        true, Integer.parseInt(rs.getString(5)), rs.getString(6));
            
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
        return diagram.getDiagramName();
    }
    /*
     * function to get all comments for diagram by diagram id.
     */
    public ArrayList<Comment> getComments(int id)
    {
        ArrayList<Comment> comments = new ArrayList<Comment>();
         try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/clubuml2", "root", ""); 
            Statement stmnt = con.createStatement();
            String sql = "SELECT * FROM comment WHERE diagram_Id = '" + id + "'";
            
            ResultSet rs = stmnt.executeQuery(sql);
      
            while(rs.next())
            {
                
                Comment comment = new Comment(Integer.parseInt(rs.getString(1)), Integer.parseInt(rs.getString(2)), 
                        rs.getString(3), rs.getString(4), 
                         Integer.parseInt(rs.getString(5)), getUsername(Integer.parseInt(rs.getString(2))));
                
                comments.add(comment);
                
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
       return comments;
    }
    
    
    public String getUsername(int id)
    {
        String name = ""; 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/clubuml2", "root", ""); 
            Statement stmnt = con.createStatement();
            String sql = "SELECT userName FROM user WHERE User_Id = '" + id + "'";
            
            ResultSet rs = stmnt.executeQuery(sql);
             
            
            if(rs.next())
            {
                name = rs.getString(1);
               
                
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
       return name;
    }
    /*
     *  function to stroe comments .
     */
    public void storeComment(int id, String content, String time, int diagramId)
    {
        try {
            
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clubuml2", "root", "");
            Statement stmnt = con.createStatement();

            String sql = "INSERT INTO comment(`user_Id`,`content`,`writtenTime`,`diagram_Id`)"
                            + " VALUES('"+id+"','"+content+"','"+time+"','"+diagramId+"')";
            
            stmnt.execute(sql);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    }
    
}