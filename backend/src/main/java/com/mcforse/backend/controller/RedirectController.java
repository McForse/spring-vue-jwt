package com.mcforse.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectController {

	private Logger LOG = LoggerFactory.getLogger(this.getClass().getName());

	// Forwards all routes to FrontEnd except: '/', '/index.html', '/api', '/api/**'
	// Required because of 'mode: history' usage in frontend routing
	@RequestMapping("{?:(?:(?!api|static|\\.).)*}/**")
	public String redirectApi() {
		LOG.info("URL entered directly into the Browser, so we need to redirect...");
		return "forward:/";
	}

}
