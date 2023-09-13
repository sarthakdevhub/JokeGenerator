package com.app.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.Model.AiRequest;
import com.app.Model.AiResponse;

@Service
public class AiService {
	
	@Value("${openai.model}")
	private String model;
	
	@Value("${openai.url}")
	private String url;
	

	
	private String ques="";
	private String ans="";
	
	private HashMap<String, String> result = new HashMap<>();
	
	
	@Autowired
	private RestTemplate template;
	
	public String createJoke(String word,int level) {
		
		String humour = "";
		
		if(level== 1||level==2) {
			humour+="low";
		}
		else if(level > 2 && level < 4) {
			humour+="medium";
		}
		else {
			humour+="advance";
		}
		
		AiRequest request = new AiRequest(model, "Create a joke using "+word+" Keyword with "+humour+" humour level");
		AiResponse response = template.postForObject(url, request, AiResponse.class);
		String question = response.getChoices().get(0).getMessage().getContent();
		ques=question;
		return question;
	}
	
	
}
