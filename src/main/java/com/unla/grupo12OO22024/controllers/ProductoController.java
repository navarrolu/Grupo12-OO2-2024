package com.unla.grupo12OO22024.controllers;

import com.unla.grupo12OO22024.helpers.ViewRouteHelper;
import com.unla.grupo12OO22024.models.ProductoModel;
import com.unla.grupo12OO22024.services.implementation.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    @Qualifier("productoService")
    private ProductoService productoService;

    @GetMapping("/")
    public RedirectView productoList(Model model){
        return new RedirectView("/producto/index");
    }

    @GetMapping("/form")
    public String producuto(Model model){
        model.addAttribute("producto", new ProductoModel());
        return ViewRouteHelper.PRODUCTO_FORM;
    }

    @PostMapping("/new")
    public ModelAndView newProducto(@Valid @ModelAttribute("producto") ProductoModel producto, BindingResult bindingResult){
        ModelAndView mV = new ModelAndView();
        if(bindingResult.hasErrors()){
            mV.setViewName(ViewRouteHelper.PRODUCTO_FORM);
        } else {
            System.out.printf("%s", producto);
            productoService.insertOrUpdate(producto);
            mV.addObject("productos", productoService.getAll());
            mV.addObject("producto", new ProductoModel());
            mV.setViewName(ViewRouteHelper.PRODUCTO_INDEX);
        }
        return  mV;
    }

    @GetMapping("/index")
    public ModelAndView index( ){
        ModelAndView mV = new ModelAndView( ViewRouteHelper.PRODUCTO_INDEX);
        mV.addObject("productos", productoService.getAll());
        mV.addObject("producto", new ProductoModel());
        return  mV;
    }

    @GetMapping("/{id_producto}")
    public ModelAndView getById( @PathVariable long id_producto) {
        ProductoModel productoModel = productoService.getById(id_producto);
        System.out.printf("%s", productoModel);
        ModelAndView mV = new ModelAndView();
        mV.addObject("producto", productoModel);
        mV.setViewName( ViewRouteHelper.PRODUCTO_NEW);
        return  mV;
    }

    @GetMapping("/lowstock")
    public ModelAndView productoLowStock ( ){
        ModelAndView mV = new ModelAndView( ViewRouteHelper.PRODUCTO_INDEX);
        mV.addObject("productos", productoService.getAllLowStock());
        mV.addObject("producto", new ProductoModel());
        return  mV;
    }



}
