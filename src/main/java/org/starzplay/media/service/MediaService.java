package org.starzplay.media.service;

import javax.json.JsonObject;

public interface MediaService {
	
	public JsonObject filterMedia(String filter, String level) throws Exception;

}
