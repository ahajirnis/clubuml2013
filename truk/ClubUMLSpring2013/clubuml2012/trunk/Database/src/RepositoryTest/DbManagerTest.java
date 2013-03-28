package RepositoryTest;
/**
 * @author
 * Joanne Zhuo
 */
import static org.junit.Assert.*;

import org.junit.Test;

import Repository.DbManager;

public class DbManagerTest {

	@Test
	public void testConnect() {
		assertTrue (DbManager.connect());
	}

}
