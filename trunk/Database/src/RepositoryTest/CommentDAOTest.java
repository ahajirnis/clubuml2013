
package RepositoryTest;

import Domain.Comment;
import Repository.CommentDAO;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author yangchen
 */
public class CommentDAOTest {

    @Test
    public void testAddComment() {
        Comment comment = new Comment();
        comment.setCommentTime("2012-12-01 10:10:10");
        comment.setContent("very well");
        comment.setDiagramId(16);
        comment.setUserId(2);
        boolean result = CommentDAO.addComment(comment);
        assertTrue(result);
    }
    
    @Test
    public void testGetComment() {
        assertTrue(CommentDAO.getComment(16).size() > 0);
    }

    @Test
    public void testUpdateComment() {
        Comment comment = CommentDAO.getComment(16).get(0);
        comment.setContent("very very well");
        assertTrue(CommentDAO.updateComment(comment));
    }

    @Test
    public void testDeleteComment() {
        Comment comment = CommentDAO.getComment(16).get(0);
        if (comment == null) {
            assertFalse(CommentDAO.deleteComment(comment));
        } else {
            assertTrue(CommentDAO.deleteComment(comment));
        }
    }
}
