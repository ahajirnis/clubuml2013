package Domain;

public class Comment {

    private int commentId;
    private String content;
    private String commentTime;
    private int diagramId;
    private int userId;
    private String userName;
    
    public Comment(int commentId, String content, String commentTime, int diagramId, int userId) {
        this.commentId = commentId;
        this.content = content;
        this.commentTime = commentTime;
        this.diagramId = diagramId;
        this.userId = userId;
    }
    
    public Comment(int commentId, int userId, String content, String commentTime, int diagramId,  String userName) {
        this.commentId = commentId;
        this.content = content;
        this.commentTime = commentTime;
        this.diagramId = diagramId;
        this.userId = userId;
        this.userName = userName;
    }
    
    public Comment(int userId, String content, String commentTime, int diagramId) {
        this.content = content;
        this.commentTime = commentTime;
        this.diagramId = diagramId;
        this.userId = userId;
    }
    
    public Comment()
    {}
    
    

    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public int getDiagramId() {
        return diagramId;
    }

    public void setDiagramId(int diagramId) {
        this.diagramId = diagramId;
    }
}
