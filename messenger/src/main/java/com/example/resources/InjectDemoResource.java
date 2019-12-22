package com.example.resources;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/inject")
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

	@GET
	public String getMessage(@HeaderParam("Content-Type")String header,
							@MatrixParam("start")int start,
							@MatrixParam("size")int size,
							@CookieParam("name")String name){
		return "Header is "+ header 
				+" Start="+start
				+" Size="+size
				+" name="+name;
	}
	
	@GET
	@Path("testURI")
	public String getContextDemo(@Context UriInfo uriInfo, @Context HttpHeaders headers){
		String s = "URI : "+uriInfo.getAbsolutePath()
			+ " Cookies : " + headers.getCookies();
		return s;
	}
}
