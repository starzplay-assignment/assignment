package org.starzplay.media.service;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.starzplay.media.controller.MediaController;
import org.starzplay.media.util.Constants;

@Service
public class MediaServiceImpl implements MediaService{

	private static final Logger logger = LoggerFactory.getLogger(MediaController.class);
	
	@Value("${URL}")
	private String external_url;
	
	/**
	 * Filter media based on parameter "level"
	 */
	@Override
	public JsonObject filterMedia(String filter, String level) throws Exception {
		
		logger.info("filter="+filter +",level="+level);

		URL url = new URL(external_url);

		JsonArrayBuilder jsonArrayBuilder = null;
		
		try (InputStream is = url.openStream(); JsonReader rdr = Json.createReader(is)) {

			JsonObject obj = rdr.readObject();
			JsonArray entries = obj.getJsonArray(Constants.ENTRIES);
		
			jsonArrayBuilder = entries.stream()
					.filter(entry -> entry.asJsonObject().getString(Constants.CONTENT_CLASSIFICATION)
							.equalsIgnoreCase(Constants.CENSORED))
					.map(entry -> entry.asJsonObject().getJsonArray(Constants.MEDIA)).flatMap(List::stream)
					.map(entry -> entry.asJsonObject()).filter(nestedObject -> {
						if (level.equalsIgnoreCase(Constants.CENSORED) && nestedObject.getString(Constants.GUID).endsWith("C"))
							return true;
						else if (level.equalsIgnoreCase(Constants.UNCENSORED) && !nestedObject.getString(Constants.GUID).endsWith("C"))
							return true;
						else
							return false;
					}).collect(Json::createArrayBuilder, JsonArrayBuilder::add, JsonArrayBuilder::add);

		}catch(Exception e) {
			logger.error("ERROR:" + e);
			throw new Exception(e);
		}

		return Json.createObjectBuilder().add("response", jsonArrayBuilder).build();
		
	}

}
