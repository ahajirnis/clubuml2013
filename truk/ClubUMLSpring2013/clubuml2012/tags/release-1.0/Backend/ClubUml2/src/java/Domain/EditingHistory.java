package Domain;

import java.sql.Date;

public class EditingHistory {
	private int editingHistoryId;
	private int diagramId;
	private int userId;
	private Date EditingTime;

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

	public Date getEditingTime() {
		return EditingTime;
	}

	public void setEditingTime(Date editingTime) {
		EditingTime = editingTime;
	}
}
