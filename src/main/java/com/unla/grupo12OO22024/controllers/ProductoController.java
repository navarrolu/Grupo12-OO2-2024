package com.unla.grupo12OO22024.controllers;

import com.unla.grupo12OO22024.entities.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo12OO22024.helpers.ViewRouteHelper;
import com.unla.grupo12OO22024.models.ProductoModel;
import com.unla.grupo12OO22024.services.implementation.ProductoService;

import jakarta.validation.Valid;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    @Qualifier("productoService")
    private ProductoService productoService;


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
            //System.out.printf("%s", producto);
            productoService.insertOrUpdate(producto);
            mV.addObject("productos", productoService.getAll());
            mV.addObject("producto", new ProductoModel());
            mV.setViewName(ViewRouteHelper.PRODUCTO_INDEX);
        }
        return  mV;
    }

    @GetMapping("/productos")
    public ModelAndView index( ){
        ModelAndView mV = new ModelAndView( ViewRouteHelper.PRODUCTO_INDEX);
        mV.addObject("productos", productoService.getAll());
        mV.addObject("producto", new ProductoModel());
        return  mV;
    }

	@GetMapping("/{id_producto}")
	public ModelAndView getById(@PathVariable long id_producto) {
		ProductoModel productoModel = productoService.getById(id_producto);
		System.out.printf("%s", productoModel);
		ModelAndView mV = new ModelAndView();
		mV.addObject("producto", productoModel);
		mV.setViewName(ViewRouteHelper.PRODUCTO_NEW);
		return mV;
	}

    @GetMapping ("/delete/{id_producto}")
    public RedirectView removeProducto( @PathVariable Long id_producto){

        productoService.remove(id_producto);
        return new RedirectView("producto/productos");
    }

    @GetMapping ("/edit/{id_producto}")
    public ModelAndView editProducto( @PathVariable Long id_producto, Model model){

        ProductoModel p = productoService.getById(id_producto);
        ModelAndView mV = new ModelAndView();
        mV.addObject("producto", p);
        mV.setViewName( ViewRouteHelper.PRODUCTO_FORM);
        return  mV;
    }

    @GetMapping("/informes")
    public ModelAndView getInformeAll( ){

        ModelAndView mV = new ModelAndView( ViewRouteHelper.PRODUCTO_INFORMES);
        mV.addObject("productos", productoService.getAll());
        mV.addObject("producto", new ProductoModel());
        return  mV;
    }

    @GetMapping("/informes/lowStock")
    public ModelAndView getInformeLowCost( ){

        ModelAndView mV = new ModelAndView( ViewRouteHelper.PRODUCTO_INFORMES);
        mV.addObject("productos", productoService.findAllLowStock());
        mV.addObject("producto", new ProductoModel());
        return  mV;
    }

    @GetMapping("/informes/closeToLowStock")
    public ModelAndView getInformeCloseToLowCost( ){

        ModelAndView mV = new ModelAndView( ViewRouteHelper.PRODUCTO_INFORMES);
        mV.addObject("productos", productoService.findAllCloseToLowStock());
        mV.addObject("producto", new ProductoModel());
        return  mV;
    }

}
