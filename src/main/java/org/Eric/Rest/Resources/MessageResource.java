package org.Eric.Rest.Resources;

import Beans.MessageFilterBean;
import Model.Message;
import Services.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.stream.events.Comment;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by ericjohn1 on 11/3/2016.
 */
@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML})

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
    public Response addMessage(Message message, @Context UriInfo uriInfo) throws URISyntaxException {
        Message newMessage = messageService.addMessage(message);
        String newId = String.valueOf(newMessage.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri)
                .entity(newMessage)
                .build();
       //return messageService.addMessage(message);
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
    public Message getMessageById(@PathParam("messageId")long id, @Context UriInfo uriInfo){
        Message message = messageService.getMessage(id);
        message.addLink(getUriForSelf(uriInfo, message), "self");
        message.addLink(getUriforProfile(uriInfo, message), "profile");


        return message;
    }
    private String getURIforComment(UriInfo uriInfo, Message message){
        URI uri = uriInfo.getBaseUriBuilder()
                .path(MessageResource.class)
                .path(MessageResource.class, "getCommentResource")
                .path(CommentsResource.class)
                .resolveTemplate("messageId", message.getId())
                .build();
        return uri.toString();
    }

    private String getUriforProfile(@Context UriInfo uriInfo, Message message){
        URI uri= uriInfo.getBaseUriBuilder()
                .path(ProfileResource.class)
                .path(message.getAuthor())
                .build();
                return uri.toString();

    }

    private String getUriForSelf(@Context UriInfo uriInfo, Message message) {
        String uri = uriInfo.getBaseUriBuilder()
                    .path(MessageResource.class)
                    .path(Long.toString(message.getId()))
                    .build()
                    .toString();
        return uri;
    }

    @Path("/{messageId}/comments")
    public CommentsResource getCommentResource(){
        return new CommentsResource();
    }
}
