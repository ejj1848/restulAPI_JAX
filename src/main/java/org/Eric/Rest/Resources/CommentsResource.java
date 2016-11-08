package org.Eric.Rest.Resources;

import Model.Comment;
import Services.CommentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by ericjohn1 on 11/8/2016.
 */

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class CommentsResource {

    private CommentService commentService = new CommentService();

    @GET
    public List<Comment> getAllComments(@PathParam("messageId") long messageId){
        return commentService.getAllComments(messageId);
    }

    @POST
    public Comment addMessage(@PathParam("messageId") long messageId, Comment comment){
        return commentService.addComment(messageId,comment);
    }
    @PUT
    @Path("/{commentId}")
    public Comment updateComment(@PathParam("messageId") long messageId, @PathParam("commentId") long id, Comment comment){
        comment.setId(id);
        return commentService.updateComment(messageId,comment);
    }
    @DELETE
    @Path("/{commentId}")
    public void deleteComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId){
        commentService.removeComment(messageId,commentId);
    }

    @GET
    @Path("/{commentId}")
    public Comment getComment(@PathParam("commentId") long commentId, @PathParam("messageId") long messageId){
        return commentService.getComment(messageId,commentId) ;
    }

}
