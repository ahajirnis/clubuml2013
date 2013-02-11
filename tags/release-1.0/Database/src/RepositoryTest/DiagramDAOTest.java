
package RepositoryTest;

import Domain.Diagram;
import Repository.DiagramDAO;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author yangchen
 */
public class DiagramDAOTest {

    @Test
    public void testAddDiagram() {
        Diagram diagram = new Diagram();
        diagram.setDiagramName("diagram1");
        diagram.setCreatedTime("2012-12-01 00:00:00");
        diagram.setOwerId(2);
        boolean result = DiagramDAO.addDiagram(diagram);
        assertTrue(result);
    }

    @Test
    public void testGetDiagramList() {
        assertTrue(DiagramDAO.getDiagramList().size() > 0);
    }

    @Test
    public void testGetDiagram() {
        String name = DiagramDAO.getDiagram(11).getDiagramName();
        assertTrue(name.equals("class diagram 1"));
    }

    @Test
    public void testUpdateDiagram() {
        Diagram diagram = DiagramDAO.getDiagram(11);
        diagram.setDiagramName("test diagram 1");
        assertTrue(DiagramDAO.updateDiagram(diagram));
    }

    @Test
    public void testDeleteDiagram() {
        Diagram diagram = DiagramDAO.getDiagram(12);
        if (diagram == null) {
            assertFalse(DiagramDAO.deleteDiagram(diagram));
        } else {
            assertTrue(DiagramDAO.deleteDiagram(diagram));
        }
    }
}
