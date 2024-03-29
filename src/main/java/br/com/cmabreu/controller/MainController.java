package br.com.cmabreu.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cmabreu.services.GenesisMonService;
import br.com.cmabreu.services.TokenInfo;

@Controller
public class MainController {
	
	@Autowired
	GenesisMonService service;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model, HttpSession session ) {
		return "index";
	}	

	@RequestMapping(value = "/crash", method = RequestMethod.GET)
	public String crash(Model model, HttpSession session ) {
		return "crash";
	}	

	@RequestMapping(value = "/tokendetails", method = RequestMethod.GET)
	public String tokendetails(Model model, HttpSession session ) {
		return "tokendetails";
	}	

	@RequestMapping(value = "/monitor", method = RequestMethod.GET)
	public String monitor(Model model, HttpSession session ) {
		return "monitor";
	}	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String root( Model model ) {
		return "index";
	}	
	
	@RequestMapping(value = "/tokens", method = RequestMethod.GET)
	public @ResponseBody List<TokenInfo> tokens() {
		return service.getTokens();
	}

	@RequestMapping(value = "/savetoken", method = RequestMethod.GET)
	public String saveToken( @RequestParam(value="address",required=true) String address ) {
		System.out.println( address );
		return "Ok";
	}
	
	
}
