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

    //Maximiliano
    //metodo get que devulve un formulario con para la carga o actualizacion
    //de un producto
    @GetMapping("/form")
    public String producuto(Model model){
        model.addAttribute("producto", new ProductoModel());
        return ViewRouteHelper.PRODUCTO_FORM;
    }


    //Maximiliano
    //metodo POST recibe un productoModel y realiza el alta en base de datos
    @PostMapping("/new")
    public ModelAndView newProducto(@Valid @ModelAttribute("producto") ProductoModel producto, BindingResult bindingResult){
        ModelAndView mV = new ModelAndView();
        if(bindingResult.hasErrors()){
            mV.setViewName(ViewRouteHelper.PRODUCTO_FORM); // en caso de error en entradas vuelve a la vista de form y muetra errores
        } else {

            productoService.insertOrUpdate(producto);
            mV.addObject("productos", productoService.getAll());
            mV.addObject("producto", new ProductoModel());
            mV.setViewName(ViewRouteHelper.PRODUCTO_INDEX);
        }
        return  mV;
    }

    //Maximiliano
    //metodo GET devulve un vista con la lista de prductos traide de base de datos
    @GetMapping("/productos")
    public ModelAndView index( ){
        ModelAndView mV = new ModelAndView( ViewRouteHelper.PRODUCTO_INDEX);
        mV.addObject("productos", productoService.getAll()); // traer lista de productos
        mV.addObject("producto", new ProductoModel());// agrega lista a la vista
        return  mV;
    }


    //Maximiliano
    //metodo GET devuelve un producto con un id especifico
	@GetMapping("/{id_producto}")
	public ModelAndView getById(@PathVariable long id_producto) {
		ProductoModel productoModel = productoService.getById(id_producto);
		ModelAndView mV = new ModelAndView();
		mV.addObject("producto", productoModel);
		mV.setViewName(ViewRouteHelper.PRODUCTO_NEW);
		return mV;
	}


    //Maximiliano
    //metodo GET elimina un pruducto de la base de datos
    // toma un id_producto como parametro para realizar la consulta
    @GetMapping ("/delete/{id_producto}")
    public RedirectView removeProducto( @PathVariable Long id_producto){

        productoService.remove(id_producto); // realiza consulta a DB
        RedirectView rV = new RedirectView();
        rV.setUrl("/producto/productos"); // redirecciona a vista de productos
        return rV;
    }

    //Maximiliano
    //metodo GET devulve una vista form con datos cargados
    @GetMapping ("/form/{id_producto}")
    public ModelAndView formEdit( @PathVariable Long id_producto, Model model){

        ProductoModel p = productoService.getById(id_producto); // busca producto por id
        ModelAndView mV = new ModelAndView();
        mV.addObject("producto", p); // agrega el objeto a la vista
        mV.setViewName( ViewRouteHelper.PRODUCTO_FORM);
        return  mV;
    }

    //Maximiliano
    //metodo GET recibe un id como parametro
    // busca el objeto
    //realiza update
    @GetMapping ("/edit/{id_producto}")
    public ModelAndView editProducto( @PathVariable Long id_producto, Model model){

        ProductoModel p = productoService.getById(id_producto);

        ModelAndView mV = new ModelAndView();
        mV.addObject("producto", p);
        mV.setViewName( ViewRouteHelper.PRODUCTO_FORM); //
        return  mV;
    }

    //Maximiliano
    //metodo GET devulve una vista de informes
    @GetMapping("/informes")
    public ModelAndView getInformeAll( ){

        ModelAndView mV = new ModelAndView( ViewRouteHelper.PRODUCTO_INFORMES);
        mV.addObject("productos", productoService.getAll());
        mV.addObject("producto", new ProductoModel());
        return  mV;
    }

    //Maximiliano
    //metodo GET devulve una vista de informes de productos en stock bajo
    @GetMapping("/informes/lowStock")
    public ModelAndView getInformeLowCost( ){

        ModelAndView mV = new ModelAndView( ViewRouteHelper.PRODUCTO_INFORMES);
        mV.addObject("productos", productoService.findAllLowStock());
        mV.addObject("producto", new ProductoModel());
        return  mV;
    }

    //Maximiliano
    //metodo GET devulve una vista de informes de productos en stock cercanos al stock minimo
    @GetMapping("/informes/closeToLowStock")
    public ModelAndView getInformeCloseToLowCost( ){

        ModelAndView mV = new ModelAndView( ViewRouteHelper.PRODUCTO_INFORMES);
        mV.addObject("productos", productoService.findAllCloseToLowStock());
        mV.addObject("producto", new ProductoModel());
        return  mV;
    }

}
