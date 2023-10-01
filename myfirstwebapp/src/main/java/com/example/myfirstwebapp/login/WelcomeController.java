package com.example.myfirstwebapp.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/*
 * 1.http-request
 * 2.Controller method identified for that request
 * 3.execute logics , return view name
 * 4.referencing application.properties, find proper view (.jsp or somethingelse)
 * 5.and http-response
 * */
/*
 * ModelMap is limited to only one request
 * so if you want to share datas on multiple requests, use session
 * SessionAttributes must be had by all Controllers
 * */

@Controller
@SessionAttributes("name")
public class WelcomeController {
	
	//private Logger logger = LoggerFactory.getLogger(getClass());
	//url query way, take parameter by @RequestParam on the side of Controller
	//and to send from Controller to jsp, use ModelMap.
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String gotoWelcomePage(ModelMap model) {
		model.put("name",getLoggedUsername());
		return "welcome";
	}
	
	private String getLoggedUsername() {
		org.springframework.security.core.Authentication authentication =   SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	
	
}
