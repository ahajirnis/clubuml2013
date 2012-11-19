package Domain;
/**
 * @author
 * Joanne Zhuo
 */
public class EditingHistory {
	private int editingHistoryId;
	private int diagramId;
	private int userId;
	private String EditingTime;
	
	public EditingHistory(int diagramId, int userId) { 
		this.diagramId = diagramId;
		this.userId = userId;
	}
	public EditingHistory(int editingHistoryId, int diagramId, int userId,
			String editingTime) { 
		this.editingHistoryId = editingHistoryId;
		this.diagramId = diagramId;
		this.userId = userId;
		EditingTime = editingTime;
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
		EditingTime = editingTime;
	}
}
