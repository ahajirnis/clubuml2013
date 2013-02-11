package domain;

/**
 * Information class that contains all the features of one editing record
 * @ doc author	Dong Guo
 */

public class EditingHistory {
	private int editingHistoryId;
	private int diagramId;
	private int userId;
	private String EditingTime;

	/**
	 * Constructor to initialize necessary class members
	 *
	 * @param editingHistoryId
	 * 			The ID of the editing record
	 * @param diagramId
	 * 			The ID of the diagram was edited
	 * @param userId
	 * 			The ID of the user that did this edit			
	 * @param EditingTime
	 * 			The time that this edit happened
	 */
    public EditingHistory(int editId, int diagId, int userId, String editTime) {
	this.editingHistoryId = editId;
	this.diagramId = diagId;
	this.userId = userId;
	this.EditingTime = editTime;
    }
    
	/**
	 * Default constructor
	 */
    public EditingHistory(){
    }
    
	/**
	 * Gete the editingHistoryId
	 * 
	 * @return editingHistoryId int
	 */
	public int getEditingHistoryId() {
		return editingHistoryId;
	}

	/**
	 * Set editingHistoryId
	 * 
	 * @param editingHistoryId
	 * 			The ID of the editing record
	 */
	public void setEditingHistoryId(int editingHistoryId) {
		this.editingHistoryId = editingHistoryId;
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
	 * Set diagramId
	 * 
	 * @param diagramId
	 * 			The ID of the diagram was edited
	 */
	public void setDiagramId(int diagramId) {
		this.diagramId = diagramId;
	}

	/**
	 * Gete the userId
	 * 
	 * @return userId int
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Set userId
	 * 
	 * @param userId
	 * 			The ID of the user that did this edit
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * Gete the EditingTime
	 * 
	 * @return EditingTime String
	 */
	public String getEditingTime() {
		return EditingTime;
	}

	/**
	 * Set EditingTime
	 * 
	 * @param EditingTime
	 * 			The time that this edit happened
	 */
	public void setEditingTime(String editingTime) {
		this.EditingTime = editingTime;
	}
}
