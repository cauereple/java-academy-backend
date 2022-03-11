package com.example.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.entity.Employee;

@Controller
public class MyController {
	
	@GetMapping(value = "/")
    public String test() {
        return "index";
    }	
	
	//  @GetMapping("/") è un'abbreviazione di @RequestMapping(value = "/", method = RequestMethod.GET)
	@GetMapping("/testmodel")
	// il parametro di tipo Model consente di notificare le variabili model alla view
	public String home(Model model) {
		// aggiungiamo al model la coppia chiave => valore : nomevariabile valore
		model.addAttribute("nome", "Giacomo");
		
		ArrayList<String>list = new ArrayList<>();
		list.add("Pippo");
		list.add("Pluto");
		list.add("Paperino");
		
		// aggiungiamo al model la collezione list
		model.addAttribute("lista", list);
		return "testmodel";
	}
	
	@GetMapping(value = "/employee")
	    public ModelAndView showForm() {
	        return new ModelAndView("employeeHome", "employee", new Employee());
	    }

	@PostMapping(value = "/addEmployee")
	    public String submit(@Valid @ModelAttribute("employee")Employee employee, 
	      BindingResult result, ModelMap model) {
	        if (result.hasErrors()) {
	            return "error";
	        }
	        model.addAttribute("name", employee.getName());
	        model.addAttribute("contactNumber", employee.getContactNumber());
	        model.addAttribute("id", employee.getId());
	        return "employeeView";
	    }	
	

	

}
