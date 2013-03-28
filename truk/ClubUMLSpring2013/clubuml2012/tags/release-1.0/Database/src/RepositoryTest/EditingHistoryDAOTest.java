package RepositoryTest;
/**
 * @author
 * Joanne Zhuo
 */
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import Domain.EditingHistory;
import Repository.EditingHistoryDAO;

public class EditingHistoryDAOTest {

	@Test
	public void testAddHistory() {
		EditingHistory h1 = new EditingHistory(1,13);
		assertTrue(EditingHistoryDAO.addHistory(h1));
	}

	@Test
	public void testGetPriorityList() {
		List<EditingHistory> historyList = EditingHistoryDAO.getPriorityList();
		for(EditingHistory h : historyList)
		{
			System.out.println(h.getEditingTime() + h.getUserId());
		}
		assertTrue(historyList != null);
	}

}
