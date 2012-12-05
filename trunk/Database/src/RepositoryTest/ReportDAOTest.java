
package RepositoryTest;

import Domain.Report;
import Repository.ReportDAO;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author yangchen
 */
public class ReportDAOTest {

    @Test
    public void testAddReport() {
        Report report = new Report();
        report.setDiagramA_Id(15);
        report.setDiagramB_Id(14);
        report.setCompareTime("2012-12-01 00:00:00");
        report.setReportFilePath("../../report/report2.pdf");
        boolean result = ReportDAO.addReport(report);
        assertTrue(result);
    }

    @Test
    public void testGetReport() {
        String file = ReportDAO.getReport(15,14).getReportFilePath();
        assertTrue(file.equals("../../report/report2.pdf"));
    }

    @Test
    public void testUpdateReport() {
        Report report = ReportDAO.getReport(15,14);
        report.setCompareTime("2012-12-01 12:12:12");
        assertTrue(ReportDAO.updateReport(report));
    }

    @Test
    public void testDeleteReport() {
        Report report = ReportDAO.getReport(15,14);
        if (report == null) {
            assertFalse(ReportDAO.deleteReport(report));
        } else {
            assertTrue(ReportDAO.deleteReport(report));
        }
    }
}
