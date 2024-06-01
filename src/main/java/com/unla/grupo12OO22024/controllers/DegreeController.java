package com.unla.grupo12OO22024.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo12OO22024.helpers.ViewRouteHelper;
import com.unla.grupo12OO22024.models.DegreeModel;
import com.unla.grupo12OO22024.services.IDegreeService;

@Controller
@RequestMapping("/degrees")
public class DegreeController {
    @Autowired
    @Qualifier("degreeService")
    private IDegreeService degreeService;

    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView mV = new ModelAndView(ViewRouteHelper.DEGREE_INDEX);
        mV.addObject("degrees", degreeService.getAll());
        mV.addObject("degree", new DegreeModel());
        return mV;
    }

    @PostMapping("/")
    public RedirectView create(@ModelAttribute("degree") DegreeModel degreeModel){
        degreeService.insertOrUpdate(degreeModel);
        return new RedirectView(ViewRouteHelper.DEGREE_ROOT);
    }

    @GetMapping("/form")
	public String degree(Model model) {
		model.addAttribute("degree", new DegreeModel());
		return ViewRouteHelper.DEGREE_FORM;
	}

    @PostMapping("/new")
	public ModelAndView newdegree(@ModelAttribute("degree") DegreeModel degree, BindingResult bindingResult) {
		ModelAndView mV = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mV.setViewName(ViewRouteHelper.DEGREE_FORM);
		} else {
			mV.setViewName(ViewRouteHelper.DEGREE_NEW);
			mV.addObject("degree", degree);
		}
		return mV;
	}

    
}
