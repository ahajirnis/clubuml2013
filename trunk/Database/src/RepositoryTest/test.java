package RepositoryTest;

import Domain.Project;
import Domain.User;
import Repository.ProjectDAO;
import Repository.UserDAO;

public class test {

	 
	public static void main(String[] args) { 
		
		//Project project = new Project("test");
		//ProjectDAO.addProject(project);
		Project project = ProjectDAO.getProject("test");
		User user1 = new User("joanne", "1202", "zhuo.q@husky.neu.edu", "hungry?", "yes", project.getProjectId());
		UserDAO.addUser(user1); 
		
		//System.out.println(project.getProjectId());
		User user = UserDAO.getUser("joanne", "1202");
		System.out.println(user.getEmail());
		user.setEmail("Joanne1202@gmail.com");
		UserDAO.updateUser(user);
	}

}
