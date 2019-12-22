package com.example;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.example.model.Message;

public class RestClientPostDemo {

	public static void main(String[] args) {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget webTarget = client.target("http://localhost:8080/messenger/webapi");
		WebTarget messageTarget = webTarget.path("messages");
		
		Message message = new Message(4,"My Created Message From JAX-RS Client", "Ankita");
		
		Response postResponse = messageTarget.request()
									.post(Entity.json(message));
		if(postResponse.getStatus() != 201){
			System.out.println("Error");
		}
		System.out.println(postResponse.readEntity(Message.class).getMessage());
	}

}
