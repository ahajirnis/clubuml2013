package domain;

import java.sql.Date;

/**
 * Information class that contains all the features of one comment
 * @ doc author	Dong Guo
 */

public class Comment {

    private int commentId;
    private String content;
    private int reportId;
    private String commentTime;
    private int userId;
    private String userName;

	/**
	 * Constructor to initialize necessary class members
	 *
	 * @param commentId
	 * 			The ID of the comment
	 * @param content
	 * 			The content of the comment
	 * @param commentTime
	 * 			The time that this comment was created
	 * @param diagramId
	 * 			The ID of the diagram that this comment belongs to
	 * @param userId
	 * 			The ID od the user that made this comment
	 */
    public Comment(int commentId, String content, String commentTime, int reportId, int userId) {
        this.commentId = commentId;
        this.content = content;
        this.commentTime = commentTime;
        this.reportId = reportId;
        this.userId = userId;
    }
    
	/**
	 * Constructor to initialize necessary class members
	 *
	 * @param commentId
	 * 			The ID of the comment
	 * @param content
	 * 			The content of the comment
	 * @param commentTime
	 * 			The time that this comment was created
	 * @param diagramId
	 * 			The ID of the diagram that this comment belongs to
	 * @param userId
	 * 			The ID od the user that made this comment
	 * @param userName
	 * 			The name of the user that made this comment
	 */
    public Comment(int commentId, int userId, String content, String commentTime, int diagramId,  String userName) {
        this.commentId = commentId;
        this.content = content;
        this.commentTime = commentTime;
        this.reportId = reportId;
        this.userId = userId;
        this.userName = userName;
    }
    
	/**
	 * Default constructor
	 */
    public Comment(){
    }
    
	/**
	 * Gete the userName
	 * 
	 * @return userName String
	 */    
    public String getUserName() {
        return userName;
    }

	/**
	 * Set userName
	 * 
	 * @param userName
	 * 			The name of the user that made this comment
	 */
    public void setUserName(String userName) {
        this.userName = userName;
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
	 * 			The ID of the user that made this comment
	 */
    public void setUserId(int userId) {
        this.userId = userId;
    }

	/**
	 * Gete commentId
	 * 
	 * @return commentId int
	 */
    public int getCommentId() {
        return commentId;
    }
    
	/**
	 * Set commentId
	 * 
	 * @param commentId
	 * 			The ID of the comment
	 */
    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

	/**
	 * Gete the content
	 * 
	 * @return content String
	 */
    public String getContent() {
        return content;
    }

	/**
	 * Set content
	 * 
	 * @param content
	 * 			The content of the comment
	 */
    public void setContent(String content) {
        this.content = content;
    }

	/**
	 * Gete the commentTime
	 * 
	 * @return commentTime String
	 */
    public String getCommentTime() {
        return commentTime;
    }

	/**
	 * Set commentTime
	 * 
	 * @param commentTime
	 * 			The time that this comment was created
	 */
    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

	/**
	 * Gete the diagramId
	 * 
	 * @return diagramId int
	 */
    public int getReportId() {
        return reportId;
    }

	/**
	 * Set diagramId
	 * 
	 * @param diagramId
	 * 			The ID of the diagram that this comment belongs to
	 */
    public void setReportId(int reportId) {
        this.reportId = reportId;
    }
}
