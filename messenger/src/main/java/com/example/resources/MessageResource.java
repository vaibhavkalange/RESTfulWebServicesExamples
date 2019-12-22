package com.example.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.example.model.Message;
import com.example.services.MessageService;

@Path("/messages")
@Produces(value={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {

	private MessageService service = new MessageService();
	
	@GET
	public List<Message> getMessages(@QueryParam("year")int year,
									@QueryParam("start")int start,
									@QueryParam("size")int size){
		if(year >0 ){
			return service.getMessagesByYear(year);
		}
		if(start>=0 && size>0){
			return service.getMessageByPagination(start, size);
		}
		return service.getAllMessages();
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessageById(@PathParam("messageId")Long id){
		return service.getMessage(id);
	}
	
	/*@POST
	public Message addMessage(Message message){
		return service.addMessage(message);
	}*/
	
	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo){
		Message newMessage = service.addMessage(message);
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		System.out.println("URI : "+uri);
		// set the response code to 201(CREATED) and also return URI of new resource as header
		return Response.created(uri).entity(newMessage).build();
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId")Long id, Message message){
		message.setId(id);
		return service.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId")Long id){
		service.removeMessage(id);
	}
}
