package com.example.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.example.database.DatabaseClass;
import com.example.exception.DataNotFoundException;
import com.example.model.Message;

public class MessageService {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageService(){
		messages.put(1l, new Message(1l, "Hello World", "Vaibhav"));
		messages.put(2l, new Message(2l, "Hello Jersey", "Vaibhav"));
	}

	public List<Message> getAllMessages(){
		 return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getMessagesByYear(int year){
		
		List<Message> list = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for(Message message : messages.values()){
			 cal.setTime(message.getCreated());
			 if(cal.get(Calendar.YEAR)==year){
				 list.add(message);
			 }
		}
		return list;
	}
	
	public List<Message> getMessageByPagination(int start, int size){
		List<Message> list = new ArrayList<>(messages.values());
		if(start+size > list.size()){
			return new ArrayList<Message>();
		}
		return list.subList(start, start+size);
	}
	
	public Message addMessage(Message message){
		message.setId(messages.size()+1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message getMessage(Long id){
		
		Message message = messages.get(id);
		if(message==null){
			throw new DataNotFoundException("Message with ID "+id+" not found.");
			
			/*
			 * Below is the alternate way to handle the exceptions 
			 * with WebapplicationException class.
			 *
			 ErrorMessage errorMessage = new ErrorMessage(500, "Data not found.");
			 Response response=Response.status(Status.NOT_FOUND)
					.entity(errorMessage)
					.build();
			throw new WebApplicationException(response);*/
		}
		return message;
	}
	
	public Message updateMessage(Message message){
		if(message.getId() <= 0){
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(Long id){
		return messages.remove(id);
	}
}
