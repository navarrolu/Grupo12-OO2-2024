package com.unla.grupo12OO22024.controllers;

import java.util.List;

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

import com.unla.grupo12OO22024.entities.Pedido;
import com.unla.grupo12OO22024.entities.Producto;
import com.unla.grupo12OO22024.helpers.ViewRouteHelper;
import com.unla.grupo12OO22024.models.LoteModel;
import com.unla.grupo12OO22024.services.implementation.LoteService;
import com.unla.grupo12OO22024.services.implementation.PedidoService;
import com.unla.grupo12OO22024.services.implementation.ProductoService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/lote")
public class LoteController {

    @Autowired
    @Qualifier("loteService")
    private LoteService loteService;

    @Qualifier("productoService")
    private ProductoService productoService;

    @Qualifier("pedidoService")
    private PedidoService pedidoService;


    public LoteController(LoteService loteService, ProductoService productoService, PedidoService pedidoService) {
		this.loteService = loteService;
		this.productoService = productoService;
        this.pedidoService = pedidoService;
	}

    
    //obtener la vista del form para registrar un lote
    @GetMapping("/form")
    public String lote(Model model) {
        //se llama al listado de productos y pedidos
        List<Producto> productos = productoService.getAll();
        List<Pedido> pedidos = pedidoService.getAll();
        model.addAttribute("productos", productos);
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("lote", new LoteModel());
        return ViewRouteHelper.LOTE_FORM;
    }

    //post para registrar un lote
    @PostMapping("/new")
    public ModelAndView registerLote(@Valid @ModelAttribute("lote") LoteModel lote,
                                    BindingResult result) {
        ModelAndView mV = new ModelAndView();
        //comprueba si hay algun error de validacion con el objeto loteModel
        if (result.hasErrors()) {
            mV.addObject("errorMessage", "Error en los datos del lote.");
            mV.setViewName(ViewRouteHelper.LOTE_FORM);
            //si los hay entonces dirijo de nuevo al formulario de registro de lote
        }else{
            //si no hay errores guardo el lote
            loteService.insertOrUpdate(lote);
            mV.addObject("productos", productoService.getClass());
            mV.addObject("pedidos", pedidoService.getClass());
            mV.addObject("lote", new LoteModel());
            mV.setViewName(ViewRouteHelper.ROUTE_INDEX);
        }
        return mV;
    }

    //vista de todos los lotes registrados
    @GetMapping("/lotes")
    public ModelAndView index( ){
        ModelAndView mV = new ModelAndView( ViewRouteHelper.LOTE);
        mV.addObject("lotes", loteService.getAll());
        mV.addObject("lote", new LoteModel());
        return  mV;
    }
    

}
