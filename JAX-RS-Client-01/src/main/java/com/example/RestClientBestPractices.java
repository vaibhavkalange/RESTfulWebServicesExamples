package com.example;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.example.model.Message;

public class RestClientBestPractices {

	public static void main(String[] args) {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget webTarget = client.target("http://localhost:8080/messenger/webapi");
		WebTarget messageTarget = webTarget.path("messages");
		WebTarget singleMessageTarget = messageTarget.path("{messageId}");
		
		Message message1 = singleMessageTarget.resolveTemplate("messageId", 1)
								.request(MediaType.APPLICATION_JSON)
								.get(Message.class);
		Message message2 = singleMessageTarget.resolveTemplate("messageId", 2)
								.request(MediaType.APPLICATION_JSON)
								.get(Message.class);
		
		System.out.println(message1.getMessage());
		System.out.println(message2.getMessage());
	}

}
