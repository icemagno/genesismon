package br.com.cmabreu.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cmabreu.services.TokenInfo;
import br.com.cmabreu.services.GenesisMonService;

@Controller
public class MainController {
	@Value("${terena.midas.location}")
	private String midasLocation;  	
	
	@Autowired
	GenesisMonService service;
	
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

	@RequestMapping(value = "/tokens", method = RequestMethod.GET)
	public @ResponseBody List<TokenInfo> tokens() {
		return service.getTokens();
	}
	
}
