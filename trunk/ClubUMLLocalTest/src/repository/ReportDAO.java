package repository;

/**
 *
 * @author yangchen
 */
import domain.Report;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportDAO {

    public static boolean addReport(Report report) {
	ResultSet rs;
	try {
	    Connection conn = DbManager.getConnection();
	    String sql = "INSERT INTO report(diagram_A , diagram_B , comparedTime , reportFilePath) VALUES(?,?,NOW(),?);";
	    PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

	    pstmt.setInt(1, report.getDiagramA_Id());
	    pstmt.setInt(2, report.getDiagramB_Id());
	    pstmt.setString(3, report.getReportFilePath());

	    pstmt.executeUpdate();

	    //Get and set the auto-generated PK
	    rs = pstmt.getGeneratedKeys();
	    if (rs.next()) {
		int newId = rs.getInt(1);
		report.setReportId(newId);
	    }
	    rs.close();
	    pstmt.close();
	    conn.close();
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
	return false;
    }

    public static Report getReport(int diagram_A, int diagram_B) {
	try {
	    Connection conn = DbManager.getConnection();
	    String sql = "SELECT * FROM report WHERE diagram_A = ? and diagram_B = ? ORDER BY report_Id DESC;";
	    PreparedStatement pstmt = conn.prepareStatement(sql);

	    pstmt.setInt(1, diagram_A);
	    pstmt.setInt(2, diagram_B);

	    ResultSet rs = pstmt.executeQuery();

	    if (!rs.next()) {
		return null;
	    }

	    //Initiate a report object and set attributes
	    Report report = new Report();
	    report.setReportId(rs.getInt("report_Id"));
	    report.setDiagramA_Id(rs.getInt("diagram_A"));
	    report.setDiagramB_Id(rs.getInt("diagram_B"));
	    report.setCompareTime(rs.getString("comparedTime"));
	    report.setReportFilePath(rs.getString("reportFilePath"));

	    rs.close();
	    pstmt.close();
	    conn.close();
	    return report;
	} catch (SQLException ex) {
	    Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
	return null;
    }

    public static boolean updateReport(Report report) {
	try {
	    Connection conn = DbManager.getConnection();
	    String sql = "UPDATE report SET diagram_A = ? , diagram_B = ? , reportFilePath = ? WHERE report_Id = ?;";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setInt(1, report.getDiagramA_Id());
	    pstmt.setInt(2, report.getDiagramB_Id());
	    pstmt.setString(3, report.getReportFilePath());
	    pstmt.setInt(4, report.getReportId());

	    pstmt.executeUpdate();

	    pstmt.close();
	    conn.close();
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
	return false;
    }

    public static boolean deleteReport(Report report) {
	try {
	    Connection conn = DbManager.getConnection();
	    String sql = "DELETE FROM report WHERE report_Id = ?;";
	    PreparedStatement pstmt = conn.prepareStatement(sql);

	    pstmt.setInt(1, report.getReportId());

	    pstmt.executeUpdate();

	    pstmt.close();
	    conn.close();
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
	return false;
    }
}
