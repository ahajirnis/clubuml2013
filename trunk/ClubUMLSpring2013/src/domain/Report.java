package domain;

/**
 * Information class that contains all the features of one report
 * @ doc author	Dong Guo
 */

public class Report {

    private int reportId;
    private int diagramA;
    private int diagramB;
    private int mergedDiagram;
    private String time;
    private String reportFileName;
    private String reportFilePath;

	public int getMergedDiagram() {
		return mergedDiagram;
	}

	public void setMergedDiagram(int mergedDiagram) {
		this.mergedDiagram = mergedDiagram;
	}

	public String getReportFileName() {
		return reportFileName;
	}

	public void setReportFileName(String reportFileName) {
		this.reportFileName = reportFileName;
	}

	/**
	 * Gete the reportId
	 * 
	 * @return reportId int
	 */
    public int getReportId() {
        return reportId;
    }

	/**
	 * Set reportId
	 * 
	 * @param reportId
	 * 			The ID of the report
	 */
    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

	/**
	 * Gete the diagramA_Id
	 * 
	 * @return diagramA_Id int
	 */
    public int getDiagramA() {
        return diagramA;
    }

	/**
	 * Set diagramA_Id
	 * 
	 * @param diagramA_Id
	 * 			The ID of diagram-A
	 */
    public void setDiagramA(int diagramA) {
        this.diagramA = diagramA;
    }

	/**
	 * Gete the diagramB_Id
	 * 
	 * @return diagramB_Id int
	 */
    public int getDiagramB() {
        return diagramB;
    }

	/**
	 * Set diagramB_Id
	 * 
	 * @param diagramB_Id
	 * 			The ID of diagram-B
	 */
    public void setDiagramB(int diagramB) {
        this.diagramB = diagramB;
    }

	/**
	 * Gete the compareTime
	 * 
	 * @return compareTime String
	 */
    public String getTime() {
        return time;
    }

	/**
	 * Set compareTime
	 * 
	 * @param compareTime
	 * 			The time that this compare happened
	 */
    public void setTime(String time) {
        this.time = time;
    }
    
	/**
	 * Gete the reportFilePath
	 * 
	 * @return reportFilePath String
	 */
    public String getReportFilePath() {
        return reportFilePath;
    }

	/**
	 * Set reportFilePath
	 * 
	 * @param reportFilePath
	 * 			The filepath of this report
	 */
    public void setReportFilePath(String reportFilePath) {
        this.reportFilePath = reportFilePath;
    }
}
