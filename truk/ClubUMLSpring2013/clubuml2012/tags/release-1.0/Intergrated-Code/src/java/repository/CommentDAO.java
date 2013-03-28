package repository;

/**
 *
 * @author yangchen
 */
import Domain.Comment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommentDAO {

    public static boolean addComment(Comment comment) {
	ResultSet rs;
	try {
	    Connection conn = DbManager.getConnection();
	    String sql = "INSERT INTO comment(user_Id , content , writtenTime , diagram_Id) VALUES(?,?,NOW(),?);";
	    PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	    pstmt.setInt(1, comment.getUserId());
	    pstmt.setString(2, comment.getContent());
	    pstmt.setInt(3, comment.getDiagramId());

	    pstmt.executeUpdate();

	    //Get and set the auto-generated PK
	    rs = pstmt.getGeneratedKeys();
	    if (rs.next()) {
		int newId = rs.getInt(1);
		comment.setCommentId(newId);
	    }
	    rs.close();
	    pstmt.close();
	    conn.close();

	} catch (SQLException ex) {
	    Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
	return true;
    }

    public static ArrayList<Comment> getComment(int diagram_id) {
	ArrayList<Comment> searchResult = new ArrayList<>();
	try {
	    Connection conn = DbManager.getConnection();
	    String sql = "SELECT * FROM comment where diagram_Id = ? ORDER BY `writtenTime` DESC;";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setInt(1, diagram_id);

	    ResultSet rs = pstmt.executeQuery();

	    //Initiate a list to get all returned comment objects and set attributes
	    while (rs.next()) {
		Comment comt = new Comment();
		comt.setCommentId(rs.getInt("comment_Id"));
		comt.setContent(rs.getString("content"));
		comt.setUserId(rs.getInt("user_Id"));
		comt.setDiagramId(rs.getInt("diagram_Id"));
		comt.setCommentTime(rs.getString("writtenTime"));
		searchResult.add(comt);
	    }

	    rs.close();
	    pstmt.close();
	    conn.close();
	    return searchResult;
	} catch (SQLException ex) {
	    Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
	return null;
    }

    public static boolean updateComment(Comment comment) {
	try {
	    Connection conn = DbManager.getConnection();
	    String sql = "UPDATE comment SET content = ? , writtenTime = ? WHERE comment_Id = ?;";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, comment.getContent());
	    pstmt.setString(2, comment.getCommentTime());
	    pstmt.setInt(3, comment.getCommentId());

	    pstmt.executeUpdate();

	    pstmt.close();
	    conn.close();
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
	return false;
    }

    public static boolean deleteComment(Comment comment) {
	try {
	    Connection conn = DbManager.getConnection();
	    String sql = "DELETE FROM comment WHERE comment_Id = ? ;";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setInt(1, comment.getCommentId());

	    pstmt.executeUpdate();

	    pstmt.close();
	    conn.close();
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
	return false;
    }
}
