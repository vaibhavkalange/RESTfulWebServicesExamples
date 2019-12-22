package com.example.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("secure")
public class SecuredRresource {

	@GET
	@Path("message")
	@Produces(MediaType.TEXT_PLAIN)
	public String secureMethod(){
		return "This API needs login...!";
	}
}
