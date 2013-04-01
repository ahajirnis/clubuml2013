package repository;

/**
 * @author Xuesong Meng&Yidu Liang
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

    /**
     * Add Report into DB (diagram A, diagram B, compared Time, report file Path)
     * 			
     * @param Report object
     * 			diagramA_Id, diagramB_Id, reportFilePath
     * @return true if success; false if fail
     */
    public static boolean addReport(Report report) {
	ResultSet rs;
	try {
	    Connection conn = DbManager.getConnection();
	    // Modified by Xuesong Meng, statement is not ready to be used.
	    //String sql = "INSERT INTO report(diagramA , diagramB , mergedDiagram , type , time , reportFilePath , reportFileName) " +
	    //               VALUES(?,?,?,?,NOW(),?,?);";
	    String sql = "INSERT INTO report(diagramA , diagramB , time , reportFilePath) VALUES(?,?,NOW(),?);";
	    PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

	    pstmt.setInt(1, report.getDiagramA());
	    pstmt.setInt(2, report.getDiagramB());
	    pstmt.setString(3, report.getReportFilePath());
        // Need to add new setAttributes...

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

    /**
     * Get Report from DB
     * 
     * @param diagramA int
     * 			The ID of the diagram A
     * @param diagramB int
     * 			The ID of the diagram B
     * @return Report
     */
    public static Report getReport(int diagram_A, int diagram_B) {
	try {
	    Connection conn = DbManager.getConnection();
	    // Modified by Xuesong Meng
	    //String sql = "SELECT * FROM report WHERE diagram_A = ? and diagram_B = ? ORDER BY report_Id DESC;";
	    String sql = "SELECT * FROM report WHERE diagramA = ? and diagramB = ? ORDER BY reportId DESC;";
	    PreparedStatement pstmt = conn.prepareStatement(sql);

	    pstmt.setInt(1, diagram_A);
	    pstmt.setInt(2, diagram_B);

	    ResultSet rs = pstmt.executeQuery();

	    if (!rs.next()) {
		return null;
	    }

	    //Initiate a report object and set attributes
	    Report report = new Report();
	    report.setReportId(rs.getInt("reportId"));
	    report.setDiagramA(rs.getInt("diagramA"));
	    report.setDiagramB(rs.getInt("diagramB"));
	    report.setTime(rs.getString("time"));
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

    /**
     * Update Report from DB
     * 
     * @param Report object
     * 			diagramA_Id, diagramB_Id, reportFilePath, reportId 
     * @return true if success; false if fail
     */
    public static boolean updateReport(Report report) {
	try {
	    Connection conn = DbManager.getConnection();
	    //Modified by Xuesong Meng
	    //String sql = "UPDATE report SET diagram_A = ? , diagram_B = ? , reportFilePath = ? WHERE report_Id = ?;";
	    String sql = "UPDATE report SET diagramA = ? , diagramB = ? , reportFilePath = ? WHERE reportId = ?;";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setInt(1, report.getDiagramA());
	    pstmt.setInt(2, report.getDiagramB());
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

    /**
     * Delete Report from DB
     * 
     * @param Report object
     * @return true if success; false if fail
     */
    public static boolean deleteReport(Report report) {
	try {
	    Connection conn = DbManager.getConnection();
	    // Modified by Xuesong Meng
	    //String sql = "DELETE FROM report WHERE report_Id = ?;";
	    String sql = "DELETE FROM report WHERE reportId = ?;";
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
