package com.example.resource;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("test")
public class MyResource {

	@GET
	@Produces("text/shortDate")
	public Date testMethod(){
		return new Date();
	}
}
