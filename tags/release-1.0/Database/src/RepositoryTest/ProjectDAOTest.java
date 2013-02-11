package RepositoryTest;
/**
 * @author
 * Joanne Zhuo
 */
import static org.junit.Assert.*;

import org.junit.Test;

import Repository.ProjectDAO;

public class ProjectDAOTest {

	@Test
	public void testGetProject() {
		int id = ProjectDAO.getProjectId();
		assertTrue(id != -1);
	}

	@Test
	public void testRemoveProject() {
		assertTrue(ProjectDAO.removeProject());
	}

}
