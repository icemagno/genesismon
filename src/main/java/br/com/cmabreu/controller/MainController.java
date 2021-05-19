package br.com.cmabreu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	@Value("${terena.midas.location}")
	private String midasLocation;  	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model, HttpSession session ) {
		model.addAttribute( "midasLocation", midasLocation );
		return "index";
	}	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String root( Model model ) {
		model.addAttribute( "midasLocation", midasLocation);
		return "index";
	}	
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home( Model model ) {
		model.addAttribute( "midasLocation", midasLocation );
		return "index";
	}	

			

}
