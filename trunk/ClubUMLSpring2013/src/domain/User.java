package domain;

/**
 * Information class that contains all the features of one user
 * @ doc author	Dong Guo
 */

public class User {

	private int userId;
	private String userName;
	private String password;
	private String email;
	private String securityQuestion;
	private String securityAnswer;
	//private int projectId;

	/**
	 * Constructor to initialize necessary class members
	 *
	 * @param userName
	 * 			The username of one account
	 * @param password
	 * 			The password of one account
	 */
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	/**
	 * Constructor to initialize necessary class members
	 *
	 * @param userName
	 * 			The username of one account
	 * @param password
	 * 			The password of the account 
	 * @param email
	 * 			The email of the account			
	 * @param securityQuestion
	 * 			The security question of the account
	 * @param securityAnswer
	 * 			The answer of the security question
	 * @param projectId
	 * 			The ID of the project
	 */
	public User( String userName, String password, String email,
			String securityQuestion, String securityAnswer) { 
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
		//this.projectId = projectId;
	}
	
	/**
	 * Constructor to initialize necessary class members
	 * 
	 * @param userId
	 * 			The ID of one account
	 * @param userName
	 * 			The username of the account
	 * @param password
	 * 			The password of the account 
	 * @param email
	 * 			The email of the account			
	 * @param securityQuestion
	 * 			The security question of the account
	 * @param securityAnswer
	 * 			The answer of the security question
	 * @param projectId
	 * 			The ID of the project
	 */
	public User(int userId, String userName, String password, String email,
			String securityQuestion, String securityAnswer) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
		//this.projectId = projectId;
	}

	/**
	 * Gete the userId
	 * 
	 * @return userId int
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Set userId
	 * 
	 * @param userId
	 * 			The ID of one account
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
	 * Gete the userName
	 * 
	 * @return userName String
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Set userName
	 * 
	 * @param userName
	 * 			The username of the account
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gete the password
	 * 
	 * @return password String
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set password
	 * 
	 * @param password
	 * 			The password of the account
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gete the email
	 * 
	 * @return email String
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Set email
	 * 
	 * @param email
	 * 			The email of the account
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Gete the securityQuestion
	 * 
	 * @return securityQuestion Srting
	 */
	public String getSecurityQuestion() {
		return securityQuestion;
	}

	/**
	 * Set securityQuestion
	 * 
	 * @param securityQuestion
	 * 			The security question of the account
	 */
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	/**
	 * Gete the securityAnswer
	 * 
	 * @return securityAnswer Srting
	 */
	public String getSecurityAnswer() {
		return securityAnswer;
	}

	/**
	 * Set securityAnswer
	 * 
	 * @param securityQuestion
	 * 			The answer of the security question
	 */
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	/**
	 * Gete the projectId
	 * 
	 * @return projectId int
	
	public int getProjectId() {
		return projectId;
	}
	
	/**
	 * Set projectId
	 * 
	 * @param projectId
	 * 			The ID of the project

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	*/
}
