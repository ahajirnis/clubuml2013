package Domain;
/**
 * @author
 * Joanne Zhuo
 */
public class Diagram {

    private int diagramId;
    private String diagramName;
    private String createdTime;
    private boolean inEdition;
    private int owerId;
    private String filePath;

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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
