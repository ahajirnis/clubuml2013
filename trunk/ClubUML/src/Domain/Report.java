package Domain;

import java.util.Date;

public class Report {
	private int reportId;
	private int diagramA_Id;
	private int diagramB_Id;
	private Date compareTime;
	private String reportFilePath;

	public int getReportId() {
		return reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	public int getDiagramA_Id() {
		return diagramA_Id;
	}

	public void setDiagramA_Id(int diagramA_Id) {
		this.diagramA_Id = diagramA_Id;
	}

	public int getDiagramB_Id() {
		return diagramB_Id;
	}

	public void setDiagramB_Id(int diagramB_Id) {
		this.diagramB_Id = diagramB_Id;
	}

	public Date getCompareTime() {
		return compareTime;
	}

	public void setCompareTime(Date compareTime) {
		this.compareTime = compareTime;
	}

	public String getReportFilePath() {
		return reportFilePath;
	}

	public void setReportFilePath(String reportFilePath) {
		this.reportFilePath = reportFilePath;
	}

}
