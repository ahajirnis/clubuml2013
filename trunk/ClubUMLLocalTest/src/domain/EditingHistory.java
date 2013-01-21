package domain;

import java.sql.Date;

public class EditingHistory {
	private int editingHistoryId;
	private int diagramId;
	private int userId;
	private String EditingTime;

    public EditingHistory(){
    }

    public EditingHistory(int editId, int diagId, int userId, String editTime) {
	this.editingHistoryId = editId;
	this.diagramId = diagId;
	this.userId = userId;
	this.EditingTime = editTime;
    }

	public int getEditingHistoryId() {
		return editingHistoryId;
	}

	public void setEditingHistoryId(int editingHistoryId) {
		this.editingHistoryId = editingHistoryId;
	}

	public int getDiagramId() {
		return diagramId;
	}

	public void setDiagramId(int diagramId) {
		this.diagramId = diagramId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEditingTime() {
		return EditingTime;
	}

	public void setEditingTime(String editingTime) {
		this.EditingTime = editingTime;
	}
}
