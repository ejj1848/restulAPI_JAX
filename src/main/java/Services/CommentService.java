package Services;

import Database.DatabaseClass;
import Model.Comment;
import Model.ErrorMessage;
import Model.Message;
import com.sun.xml.internal.ws.api.message.Packet;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ericjohn1 on 11/8/2016.
 */
public class CommentService {

    private Map<Long, Message> messages = DatabaseClass.getMessages();

    public List<Comment> getAllComments(long messageId){
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        return new ArrayList<Comment>(comments.values());
    }

    public Comment getComment(long messageId, long commentId){
        ErrorMessage errorMessage = new ErrorMessage("Not Found" , 404, "www.google.com");
        Response response = Response.status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .build();

        Message message = messages.get(messageId);
        if(message == null){
            throw new NotFoundException(response);
        }

        Map<Long, Comment> comments = messages.get(messageId).getComments();
        Comment comment = comments.get(commentId);
            if(comment == null){
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }

        return comment;
    }

    public Comment addComment(long messageId, Comment comment){
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        comment.setId(comments.size()+1);
        comments.put(comment.getId(), comment);
        return comment;
    }
    public Comment updateComment(long messageId, Comment comment){
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        if(comment.getId() <= 0 ){
            return null;
        }
        comments.put(comment.getId(), comment);
        return comment;
    }
    public Comment removeComment(long messageId, long commentId){
        Map<Long,Comment> comments = messages.get(messageId).getComments();
        return comments.remove(commentId);
    }
}
