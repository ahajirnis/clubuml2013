package domain;

/**
 * Information class that contain all the features of one Diagram
 * @ doc author	Dong Guo
 * 
 * not finished: inEdition. line: 31; 110-117
 * 				 projectId. line: 150
 */

public class Diagram {

    private int diagramId;
    private String diagramName;
    private String createdTime;
    private boolean inEdition;
    private int ownerId;
    private int projectId = 2; // default;
    private String ecoreFilePath;
    

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
	 * 			
	 * @param ownerId
	 * 			The ID of the owner of this diagram
	 * @param ecoreFilePath
	 * 			The filepath of this diagram
	 */
    public Diagram(int diagramId, String diagramName, String createdTime, boolean inEdition,
    		int ownerId, String ecoreFilePath) {
        this.diagramId = diagramId;
        this.diagramName = diagramName;
        this.createdTime = createdTime;
        this.inEdition = inEdition;
        this.ownerId = ownerId;
        this.ecoreFilePath = ecoreFilePath;
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

    
    public boolean isInEdition() {
        return inEdition;
    }

    public void setInEdition(boolean inEdition) {
        this.inEdition = inEdition;
    }

	/**
	 * Gete the ownerId
	 * 
	 * @return ownerId int
	 */
    public int getOwnerId() {
        return ownerId;
    }

	/**
	 * Set ownerId
	 * 
	 * @param ownerId
	 * 			The ID of the owner of this diagram
	 */
    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
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
	 * 			
	 */
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

	/**
	 * Gete the ecoreFilePath
	 * 
	 * @return ecoreFilePath String
	 */
    public String getEcoreFilePath() {
        return ecoreFilePath;
    }
    
	/**
	 * Set ecoreFilePath
	 * 
	 * @param ecoreFilePath
	 * 			The filepath of this diagram
	 */
    public void setEcoreFilePath(String ecoreFilePath) {
        this.ecoreFilePath = ecoreFilePath;
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
