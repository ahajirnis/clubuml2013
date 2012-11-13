package Domain;

import java.sql.Date;

public class Comment {
	private int commentId;
	private String content;
	private Date commentTime;
	private int diagramId;

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	public int getDiagramId() {
		return diagramId;
	}

	public void setDiagramId(int diagramId) {
		this.diagramId = diagramId;
	}
}
