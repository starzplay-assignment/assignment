package com.starzplay.media;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.starzplay.media.MediaApplication;
import org.starzplay.media.controller.MediaController;
import org.starzplay.media.service.MediaService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MediaController.class, secure = false)
@ContextConfiguration(classes = MediaApplication.class)
public class MediaControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MediaService mediaService;
	
	@Test
	public void retrieveCensoredMedia() throws Exception {
		   HttpUriRequest request = new HttpGet( "http://localhost:8080/media?filter=censoring&level=Censored" );
		 
		   HttpResponse response = HttpClientBuilder.create().build().execute( request );
		   
           String result = EntityUtils.toString(response.getEntity());
           JSONObject jObject = new JSONObject(result);
        
           JSONArray responseJson = (JSONArray) jObject.get("response");
           assertTrue(responseJson.length() == 	1);
		 
	}
	
	
	@Test
	public void retrieveUncensoredMedia() throws Exception {
		   HttpUriRequest request = new HttpGet( "http://localhost:8080/media?filter=censoring&level=Uncensored" );
		 
		   HttpResponse response = HttpClientBuilder.create().build().execute( request );
		   
           String result = EntityUtils.toString(response.getEntity());
           JSONObject jObject = new JSONObject(result);
        
           JSONArray responseJson = (JSONArray) jObject.get("response");
           assertTrue(responseJson.length() == 	1);
		 
	}

}
