package domain;

/**
 * Information class that contains all the features of one report
 * @ doc author	Dong Guo
 */

public class Report {

    private int reportId;
    private int diagramA_Id;
    private int diagramB_Id;
    private String compareTime;
    private String reportFilePath;

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
    public int getDiagramA_Id() {
        return diagramA_Id;
    }

	/**
	 * Set diagramA_Id
	 * 
	 * @param diagramA_Id
	 * 			The ID of diagram-A
	 */
    public void setDiagramA_Id(int diagramA_Id) {
        this.diagramA_Id = diagramA_Id;
    }

	/**
	 * Gete the diagramB_Id
	 * 
	 * @return diagramB_Id int
	 */
    public int getDiagramB_Id() {
        return diagramB_Id;
    }

	/**
	 * Set diagramB_Id
	 * 
	 * @param diagramB_Id
	 * 			The ID of diagram-B
	 */
    public void setDiagramB_Id(int diagramB_Id) {
        this.diagramB_Id = diagramB_Id;
    }

	/**
	 * Gete the compareTime
	 * 
	 * @return compareTime String
	 */
    public String getCompareTime() {
        return compareTime;
    }

	/**
	 * Set compareTime
	 * 
	 * @param compareTime
	 * 			The time that this compare happened
	 */
    public void setCompareTime(String compareTime) {
        this.compareTime = compareTime;
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
