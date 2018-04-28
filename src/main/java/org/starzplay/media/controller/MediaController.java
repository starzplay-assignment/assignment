package org.starzplay.media.controller;

import javax.json.Json;
import javax.json.JsonObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.starzplay.media.service.MediaService;

/**
 * Rest Controller to filter and return filtered media based on request parameters
 *
 */
@RestController
@RequestMapping
public class MediaController {

	private static final Logger logger = LoggerFactory.getLogger(MediaController.class);
	
    @Autowired
    MediaService mediaService;


    @GetMapping("/media")
    public ResponseEntity<?> getMedia(@RequestParam(value="filter", required=true) String filter,
            @RequestParam(value="level", required=true) String level) {
    	
    	logger.info("filter="+filter +",level="+level);
    	
		JsonObject result = null;
		try {
			result = mediaService.filterMedia(filter, level);
		} catch (Exception e) {
			result = Json.createObjectBuilder().add("ERROR:", e.getMessage()).build();
		}
		
		return new ResponseEntity<JsonObject>(result, HttpStatus.OK);
    }

}
