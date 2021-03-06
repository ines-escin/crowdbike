package com.software.project.service.rest.resource;

import javax.persistence.StoredProcedureParameter;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.software.project.entities.BikePosition;
import com.software.project.entities.MsgResponse;
import com.software.project.entities.Ocorrencia;
import com.software.project.service.OcorrenciaServiceRestBO;
import com.software.project.util.ApplicationContextProvider;


public class BikeResource extends ServerResource{

	private OcorrenciaServiceRestBO ocorrenciaServiceRestBO;
    

//	private PositionEventHandler bikeRequestEventHandler;
	
	@Get
	public synchronized Representation getMessage() throws JSONException
	{
		Gson gson = new Gson();

		String imei = getAttribute("imei");
		double lat = Double.valueOf(getAttribute("latitude"));
		double lng = Double.valueOf(getAttribute("longitude"));
		
	/*	bikeRequestEventHandler = (PositionEventHandler)ApplicationContextProvider.getBean("positionRequestEventHandler");
		bikeRequestEventHandler.handleBikePosition(new BikePosition(imei,String.valueOf(lat), String.valueOf(lng)));
	*/
		ocorrenciaServiceRestBO = (OcorrenciaServiceRestBO)ApplicationContextProvider.getBean("ocorrenciaServiceRestBO");
		MsgResponse msgResponse = ocorrenciaServiceRestBO.getAlertByLatLng(lat, lng, 0.11);
		
		//String o = gson.toJson(msgResponse);
		//return new JsonRepresentation(o);
		String o = gson.toJson(msgResponse);
		return new JsonRepresentation(o);
		//return "ok";
	}
	
	
	@Post
	public synchronized Representation createEntity(final String representation) throws JSONException 
	{
		Gson gson = new Gson();
		BikePosition msg = gson.fromJson(representation, BikePosition.class);

		String imei = msg.getImei();
		double lat = Double.valueOf(msg.getLat());
		double lng = Double.valueOf(msg.getLng());
		
	 /*	bikeRequestEventHandler = (PositionEventHandler)ApplicationContextProvider.getBean("positionRequestEventHandler");
	    bikeRequestEventHandler.handleBikePosition(new BikePosition(imei,String.valueOf(lat), String.valueOf(lng)));
		*/
		ocorrenciaServiceRestBO = (OcorrenciaServiceRestBO)ApplicationContextProvider.getBean("ocorrenciaServiceRestBO");
		MsgResponse msgResponse = ocorrenciaServiceRestBO.getAlertByLatLng(lat, lng, 10.1);// 0.03 = 30 metros -> distancia em km
		
		String o = gson.toJson(msgResponse);
		return new JsonRepresentation(o);
	}

	/*	private String ObterIdDispositivo(final JSONObject obj, final String tipoDispositivo) throws JSONException {
		
		String idDisp = null;
	
		if (tipoDispositivo == TipoDispositivo.CELULAR.toString()) {
			idDisp = obj.getString("imei");
		}
		else if (tipoDispositivo== TipoDispositivo.LOCAL_WEB.toString()) {
			idDisp = obj.getString("email");
		}
		else if (tipoDispositivo == TipoDispositivo.PDA.toString())
		{
		   idDisp = obj.getString("serialnumber");
		}
		else if(tipoDispositivo == TipoDispositivo.TABLET.toString())
		{
			idDisp = obj.getString("serialNumber2");			
		}
		return idDisp;
		
		return null;
	}*/

}
