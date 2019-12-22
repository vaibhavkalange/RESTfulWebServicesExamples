package com.example.filter;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

@Provider
public class SecurityFilter implements ContainerRequestFilter {

	/*	In this example we are using base64 encoding for authorization headers.
		The base64 encodes the user name and password with the below format.
		e.g. User Name = user
		 	 Password = user12
		 
		 The base64 creates the single string of the user name and password which is separated by colon (:)
		 e.g. user:user12 ==> Basic dXNlcjp1c2VyMTI=
		 
		 While decoding the base64 string we will have to first remove the prefix Basic from the header value.
		 After decoding the string will have to tokeninze the decoded string by colon separated to get the user name and password.
	*/	
	private static final String AUTHORIZATION_HEADER_KEY="Authorization"; 
	private static final String AUTHORIZATION_HEADER_PREFIX="Basic ";
	private static final String URL_PATTERN="secure";
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		if(requestContext.getUriInfo().getPath().contains(URL_PATTERN)){
			List<String> authHeaders = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
			if(authHeaders!=null && !authHeaders.isEmpty()){
				String authToken = authHeaders.get(0);
				authToken=authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
				String decodeAsString = Base64.decodeAsString(authToken);
				StringTokenizer stringTokenizer = new StringTokenizer(decodeAsString, ":");
				String userName = stringTokenizer.nextToken();
				String password = stringTokenizer.nextToken();
				
				if("user".equals(userName) && "password".equals(password)){
					return;
				}
			}
			Response response = Response.status(Response.Status.UNAUTHORIZED)
					.entity("User cannot access resource...!")
					.build();
			
			requestContext.abortWith(response);
		}
	}

}
