package RepositoryTest;
/**
 * @author
 * Joanne Zhuo
 */
import static org.junit.Assert.*;

import org.junit.Test;

import Domain.User;
import Repository.UserDAO;

public class UserDAOTest {

	@Test
	public void testAddUser() {
		User user1 = new User("aaa", "bbb", "zhuo.q@husky.neu.edu", "hungry?", "yes");
		boolean result = UserDAO.addUser(user1);
		assertTrue(result);
	}

	@Test
	public void testGetUser() {
		User user1 = UserDAO.getUser("joanne", "1202");
		assertEquals(user1.getEmail(),"zhuo.q@husky.neu.edu");
	}

	@Test
	public void testRemoveUser() {
		User user1 = UserDAO.getUser("joanne", "1202");
		if (user1 == null)
			assertFalse(UserDAO.removeUser(user1));
		else
			assertTrue(UserDAO.removeUser(user1));
	}

	@Test
	public void testUpdateUser() {
		User user1 = UserDAO.getUser("joanne", "1202");
		user1.setSecurityAnswer("No");
		assertTrue(UserDAO.updateUser(user1));
		
	}

}
