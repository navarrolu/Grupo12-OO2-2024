package com.unla.grupo12OO22024.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo12OO22024.helpers.ViewRouteHelper;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("/index")
    public String index(){
        return ViewRouteHelper.INDEX;
    }

    @GetMapping("/hello")
    public ModelAndView helloParams1(@RequestParam(name = "name", required = false, defaultValue = "null") String name){
            ModelAndView mV = new ModelAndView(ViewRouteHelper.HELLO);
            mV.addObject("name", name);
            return mV;
    }

    @GetMapping("/hello/{name}")
    public ModelAndView helloParams2(@PathVariable("name") String name){
            ModelAndView mV = new ModelAndView(ViewRouteHelper.HELLO);
            mV.addObject("name", name);
            return mV;
    }

   @GetMapping("/login")
    public String login(){
        return ViewRouteHelper.LOGIN;
    }

    @GetMapping("/login2")
    public String login2(){
        return ViewRouteHelper.LOGIN2;
    }
    
}
