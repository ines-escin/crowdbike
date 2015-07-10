package com.ocurrence.test;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;


public class TestRetrieveOcurrence {

	@Test
	public void testGetById() throws JSONException {
		String uri = "http://148.6.80.19:1026/v1/contextEntities/358";
		int responseCode = 0;
		String result = "";  
		String line = "";
		HttpClient client;

		try {
			client = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(uri);
		    httpget.setHeader("Accept", "application/json");		
		    httpget.setHeader("Content-type", "application/json");
		    HttpResponse response;
			BufferedReader rd;

			int executeCount = 0;
			do {
				executeCount++;
				response = client.execute(httpget);
				responseCode = response.getStatusLine().getStatusCode();						
			} while (executeCount < 5 && responseCode == 408);
			      rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			while ((line = rd.readLine()) != null){
				result += line.trim();
			}	      
		} catch (Exception e) {
			responseCode = 408;
			e.printStackTrace();
		}

	   String s = "{\"contextElement\" : {\"type\" : \"Ocurrence\","
	   		+ "\"isPattern\" : \"false\",\"id\" : \"358\","
	   		+ "\"attributes\" : [{\"name\" : \"dataOcorrencia\","
	   		+ "\"type\" : \"String\","
	   		+ "\"value\" : \"09/07/2015 13:54:50\"},"
	   		+ "{\"name\" : \"endereco\","
	   		+ "\"type\" : \"String\","
	   		+ "\"value\" : \"Endereco de minha casa\"},"
	   		+ "{\"name\" : \"lat\",\"type\" : \"String\","
	   		+ "\"value\" : \"40.418889\"},"
	   		+ "{\"name\" : \"lng\","
	   		+ "\"type\" : \"String\","
	   		+ "\"value\" : \"-3.691944\"},"
	   		+ "{\"name\" : \"title\","
	   		+ "\"type\" : \"String\","
	   		+ "\"value\" : \"Ocurrence\"},"
	   		+ "{\"name\" : \"userId\","
	   		+ "\"type\" : \"String\","
	   		+ "\"value\" : \"1\"}]},"
	   		+ "\"statusCode\" : {\"code\" : \"200\","
	   		+ "\"reasonPhrase\" : \"OK\"}}";
	   
	   
	JSONAssert.assertEquals(s , result, false);
	}

}
