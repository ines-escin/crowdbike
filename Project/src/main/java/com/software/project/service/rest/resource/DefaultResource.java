package com.software.project.service.rest.resource;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class DefaultResource  extends ServerResource{

	@Get
	public String welcome() {
		
		return "welcome";
	}
}
