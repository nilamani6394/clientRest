package com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.main.Modeldata.Page;



@Controller
@RequestMapping("/")
public class PageController {
	
	  @Autowired
	    private RestTemplate rest;

	    @GetMapping
	    public String home(Model model) {
	        
	        Page page = rest.getForObject("http://localhost:8080/pages/home", Page.class);

	        model.addAttribute("page", page);

	        return "page";
	    }

	    @GetMapping("/{slug}")
	    public String page(@PathVariable String slug, Model model) {
	        
	        try {

	            Page page = rest.getForObject("http://localhost:8080/pages/{slug}", Page.class, slug);
	            model.addAttribute("page", page);
	        }
	        catch(final HttpClientErrorException e) {
	            return "redirect:/";
	        }

	        return "page";
	    }

}
