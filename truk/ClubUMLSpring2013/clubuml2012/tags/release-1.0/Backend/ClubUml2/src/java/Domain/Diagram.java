package Domain;

public class Diagram {

    private int diagramId;
    private String diagramName;
    private String createdTime;
    private boolean inEdition;
    private int owerId;
    private int projectId = 2; // default;
    private String ecoreFilePath;
    
    public Diagram()
    {}

    public Diagram(int diagramId, String diagramName, String createdTime, boolean inEdition, int owerId, String ecoreFilePath) {
        this.diagramId = diagramId;
        this.diagramName = diagramName;
        this.createdTime = createdTime;
        this.inEdition = inEdition;
        this.owerId = owerId;
        this.ecoreFilePath = ecoreFilePath;
    }
    
    

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public int getDiagramId() {
        return diagramId;
    }

    public void setDiagramId(int diagramId) {
        this.diagramId = diagramId;
    }

    public String getDiagramName() {
        return diagramName;
    }

    public void setDiagramName(String diagramName) {
        this.diagramName = diagramName;
    }

    public boolean isInEdition() {
        return inEdition;
    }

    public void setInEdition(boolean inEdition) {
        this.inEdition = inEdition;
    }

    public int getOwerId() {
        return owerId;
    }

    public void setOwerId(int owerId) {
        this.owerId = owerId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getEcoreFilePath() {
        return ecoreFilePath;
    }

    public void setEcoreFilePath(String ecoreFilePath) {
        this.ecoreFilePath = ecoreFilePath;
    }
//	public String getImageFilePath() {
//		return imageFilePath;
//	}
//
//	public void setImageFilePath(String imageFilePath) {
//		this.imageFilePath = imageFilePath;
//	}
}
