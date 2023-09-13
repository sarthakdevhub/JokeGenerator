package com.app.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.Service.AiService;

@RestController
@RequestMapping("/bot")
@CrossOrigin(origins = "*")
public class AiController {
	
	@Value("${openai.model}")
	private String model;
	
	@Value("${openai.url}")
	private String url;
	
	@Autowired
	private AiService aiService;

	
	@GetMapping("/joke/{level}")
	public ResponseEntity<String> chat(@RequestParam("prompt") String word,@PathVariable int level) {
		return new ResponseEntity<String>(aiService.createJoke(word,level),HttpStatus.OK);
	}
	
}
