package com.example;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import com.example.model.Message;

public class RestAPIClient {

	public static void main(String[] args) {
		
		Client client = ClientBuilder.newClient();
		/*WebTarget target = client.target("http://localhost:8080/messenger/webapi/messages/1");
		Builder request = target.request();
		Response response = request.get();*/
		
		//Message message = response.readEntity(Message.class);
		
		/*String message = client.target("http://localhost:8080/messenger/webapi/messages/")
								.request(MediaType.APPLICATION_JSON)
								.get(String.class);*/
		
		Message message = client.target("http://localhost:8080/messenger/webapi/messages/1")
								.request(MediaType.APPLICATION_JSON)
								.get(Message.class);
		
		System.out.println(message.getMessage());
	}

}
