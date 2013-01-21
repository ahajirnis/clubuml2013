package domain;

public class Project {

	private int projectId;
	private String projectName;
	private String description;
	private byte achived = 0;// Default

	
	public Project(String projectName) {
		this.projectName = projectName;
	}

	public Project(int projectId, String projectName, String description,
			byte achived) { 
		this.projectId = projectId;
		this.projectName = projectName;
		this.description = description;
		this.achived = achived;
	}
	
	public int getProjectId() {
		return projectId;
	}
	
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte isAchived() {
		return achived;
	}

	public void setAchived(byte achived) {
		this.achived = achived;
	}

}
