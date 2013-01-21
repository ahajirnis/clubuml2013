package domain;

public class User {

	private int userId;
	private String userName;
	private String password;
	private String email;
	private String securityQuestion;
	private String securityAnswer;
	private int projectId;

	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	public User( String userName, String password, String email,
			String securityQuestion, String securityAnswer, int projectId) { 
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
		this.projectId = projectId;

	}
	public User(int userId, String userName, String password, String email,
			String securityQuestion, String securityAnswer, int projectId) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
		this.projectId = projectId;

	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
}
