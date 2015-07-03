package com.software.project.service.rest;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import com.software.project.service.rest.resource.DefaultResource;
import com.software.project.service.rest.resource.BikeResource;
import com.software.project.service.rest.resource.VehicleResource;


public class ServerApp extends Application{
	Router router;
    public ServerApp() {
        super();
    }
    

  public ServerApp(Context context) {
        super(context);
    }

    @Override
    public synchronized Restlet createInboundRoot() {
        Router router = new Router(getContext()); 
        router.attach("/callback/", DefaultResource.class); 
        router.attach("/trace/", BikeResource.class); 
        router.attach("/trace/{imei}/{latitude}/{longitude}/", BikeResource.class); 
        router.attach("/vehicle/", VehicleResource.class);
        return router;
    }

	public Router getrouter() {
		return router;
	}

	public void setrouter(Router router) {
		this.router = router;
	}


	
	
}
