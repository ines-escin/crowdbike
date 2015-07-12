package com.ocurrence.test;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import com.google.gson.Gson;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;
import com.software.project.entities.Attributes;
import com.software.project.entities.Entity;
import com.software.project.service.adapter.AdapterOcurrence;


public class TestCreateOcurrence {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreateNew() throws JSONException {
		  String result = "";  
			String line = "";
			String id = String.valueOf("358");
		    Entity entity = new Entity();
			List<Attributes> attributes = new ArrayList<Attributes>();
			attributes.add(new Attributes("title", "String", "Ocurrence"));
			attributes.add(new Attributes("lat","String","40.418889"));
			attributes.add(new Attributes("lng","String","-3.691944"));
			attributes.add(new Attributes("endereco", "String", "Endereco de minha casa"));
			attributes.add(new Attributes("dataOcorrencia", "String",AdapterOcurrence.df.format(Calendar.getInstance().getTime()))); 
			attributes.add(new Attributes("userId", "String", "1")); 
			
			entity.setType("Ocurrence");
			entity.setId(id);
			entity.setAttributes(attributes);

			Gson gson;
			String uri = "http://148.6.80.19:1026/v1/contextEntities";
			
			
			int responseCode = 0;

			try {
				HttpClient client = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(uri);
			    httppost.setHeader("Accept", "application/json");
				gson = new Gson();
				StringEntity entityPost = new StringEntity(gson.toJson(entity));
				entityPost.setContentType("application/json");
				
				
				httppost.setEntity(entityPost);

				int executeCount = 0;
				HttpResponse response;
				do {
					executeCount++;
					//Log.v("TENTATIVA", "tentativa número:" + executeCount);

					// Execute HTTP Post Request
					response = client.execute(httppost);
					responseCode = response.getStatusLine().getStatusCode();						

				} while (executeCount < 5 && responseCode == 408);

				BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

				while ((line = rd.readLine()) != null){
					result += line.trim();
				}

			      
			} catch (Exception e) {
				responseCode = 408;
				e.printStackTrace();
				fail("Not yet");
			}
			
			
	    String s = "{ \"type\" : \"Position\",\"isPattern\" : \"false\", " +
	    		"\"id\" : \"358972063059834\",\"contextResponses\" : " +
	    		"[{\"attributes\" : [{\"name\" : \"latitude\",\"type\" : " +
	    		"\"String\",\"value\" : \"\"},{\"name\" : \"longitude\"," +
	    		"\"type\" : \"String\",\"value\" : \"\"}],\"statusCode\" : " +
	    		"{\"code\" : \"200\",\"reasonPhrase\" : \"OK\"}}]}";
	    
	    JSONAssert.assertEquals(s, result, false);
	}


}
