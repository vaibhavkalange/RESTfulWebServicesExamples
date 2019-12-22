package com.example.resource;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("test")
@Singleton
public class MyResource {

	private int count;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello(){
		count++;
		return "This method was called "+count+" times....!";
	}
}
