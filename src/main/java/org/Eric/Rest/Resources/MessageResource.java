package org.Eric.Rest.Resources;

import Beans.MessageFilterBean;
import Model.Message;
import Services.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.stream.events.Comment;
import java.util.List;

/**
 * Created by ericjohn1 on 11/3/2016.
 */
@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class MessageResource {

    MessageService messageService = new MessageService();

    @GET
    public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {
        if (filterBean.getYear() > 0 ){
            return messageService.getAllMessagesByYear(filterBean.getYear());
        }
        if (filterBean.getStart() >= 0 && filterBean.getSize() >= 0){
            return messageService.getAllMessagesPagenated(filterBean.getStart(), filterBean.getSize());
            }
        return messageService.getAllMessages();
    }

    @POST
    public Message addMessage(Message message){
       return messageService.addMessage(message);
    }

    @PUT
    @Path("/{messageId}")
    public Message updateMessage(Message message ,@PathParam("messageId") long id){
        message.setId(id);
        return messageService.updateMessage(message);
    }
    @DELETE
    @Path("/{messageId}")
    public void deleteMessage(@PathParam("messageId") long id){
        messageService.removeMessage(id);
    }


    @GET
    @Path("/{messageId}")
    public Message getMessageById(@PathParam("messageId")long id){
        return messageService.getMessage(id);
    }


    @Path("/{messageId}/comments")
    public CommentsResource getCommentResource(){
        return new CommentsResource();
    }
}
