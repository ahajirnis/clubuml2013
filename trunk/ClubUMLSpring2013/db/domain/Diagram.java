package domain;

/**
 * Information class that contain all the features of one Diagram
 * @ doc author	Dong Guo
 */

public class Diagram {

    private int diagramId;
    private int merged;
    private int userId;
    private int projectId = 2; // default;
    private String diagramName;
    private String diagramType;
    private String filePath;
    private String fileType;
    private String createdTime;    
    private String notationFilePath;
    private String notationFileName;
    private String diFileName;
    private String diFilepath;
    
	public String getDiagramType() {
		return diagramType;
	}

	public void setDiagramType(String diagramType) {
		this.diagramType = diagramType;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public int getMerged() {
		return merged;
	}

	public void setMerged(int merged) {
		this.merged = merged;
	}

	public String getNotationFilePath() {
		return notationFilePath;
	}

	public void setNotationFilePath(String notationFilePath) {
		this.notationFilePath = notationFilePath;
	}

	public String getNotationFileName() {
		return notationFileName;
	}

	public void setNotationFileName(String notationFileName) {
		this.notationFileName = notationFileName;
	}

	public String getDiFileName() {
		return diFileName;
	}

	public void setDiFileName(String diFileName) {
		this.diFileName = diFileName;
	}

	public String getDiFilepath() {
		return diFilepath;
	}

	public void setDiFilepath(String diFilepath) {
		this.diFilepath = diFilepath;
	}

	/**
	 * Constructor to initialize necessary class members
	 *
	 * @param diagramId
	 * 			The ID of the diagram
	 * @param diagramName
	 * 			The name of the diagram
	 * @param createdTime
	 * 			The time that this diagram was created
	 * @param inEdition
	 * 			Status of is diagram
	 * @param ownerId
	 * 			The ID of the owner of this diagram
	 * @param ecoreFilePath
	 * 			The filepath of this diagram
	 */
    public Diagram(int diagramId, String diagramName, String createdTime, int merged,
    		int userId, String FilePath) {
        this.diagramId = diagramId;
        this.diagramName = diagramName;
        this.createdTime = createdTime;
        this.merged = merged;
        this.userId = userId;
        this.filePath = FilePath;
    }

	/**
	 * Default constructor
	 */
    public Diagram(){
    }

	/**
	 * Gete the createdTime
	 * 
	 * @return createdTime String
	 */
    public String getCreatedTime() {
        return createdTime;
    }
    
	/**
	 * Set createdTime
	 * 
	 * @param createdTime
	 * 			The time that this diagram was created
	 */
    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
    
	/**
	 * Gete the diagramId
	 * 
	 * @return diagramId int
	 */
    public int getDiagramId() {
        return diagramId;
    }

	/**
	 * Set createdTime
	 * 
	 * @param diagramId
	 * 			The ID of this diagram
	 */
    public void setDiagramId(int diagramId) {
        this.diagramId = diagramId;
    }
    
	/**
	 * Gete the diagramName
	 * 
	 * @return diagramName String
	 */
    public String getDiagramName() {
        return diagramName;
    }

	/**
	 * Set diagramName
	 * 
	 * @param diagramName
	 * 			The name of this diagram
	 */    
    public void setDiagramName(String diagramName) {
        this.diagramName = diagramName;
    }

	/**
	 * Gete the inEdition
	 * 
	 * @return inEdition boolean

    public boolean isInEdition() {
        return inEdition;
    }

	/**
	 * Set inEdition
	 * 
	 * @param inEdition
	 * 			Status of is diagram

    public void setInEdition(boolean inEdition) {
        this.inEdition = inEdition;
    }

	/**
	 * Gete the ownerId
	 * 
	 * @return ownerId int
	 */
    public int getUserId() {
        return userId;
    }

	/**
	 * Set ownerId
	 * 
	 * @param ownerId
	 * 			The ID of the owner of this diagram
	 */
    public void setUserId(int userId) {
        this.userId = userId;
    }

	/**
	 * Gete the projectId
	 * 
	 * @return projectId int
	 */
    public int getProjectId() {
        return projectId;
    }

	/**
	 * Set projectId
	 * 
	 * @param projectId
	 * 			The ID of this project
	 */
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

	
    
	/**
	 * Some functions that have not been used yet
	 */
//	public String getImageFilePath() {
//		return imageFilePath;
//	}
//
//	public void setImageFilePath(String imageFilePath) {
//		this.imageFilePath = imageFilePath;
//	}
}
