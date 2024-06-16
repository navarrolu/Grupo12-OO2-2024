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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo12OO22024.entities.Lote;
import com.unla.grupo12OO22024.helpers.ViewRouteHelper;
import com.unla.grupo12OO22024.models.LoteModel;
import com.unla.grupo12OO22024.models.ProductoModel;
import com.unla.grupo12OO22024.services.implementation.LoteService;
import com.unla.grupo12OO22024.services.implementation.ProductoService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/lote")
public class LoteController {

    @Autowired
    @Qualifier("loteService")
    private LoteService loteService;
    @Autowired
    private ProductoService productoService;

    @GetMapping("/nuevolote")
    public String ingresarLotes(Model model) {
        model.addAttribute("lote", new LoteModel());
        return ViewRouteHelper.LOTE_NUEVO;
    }
    

    @PostMapping("/new")
        public ModelAndView registerLote(@Valid @ModelAttribute("lote") LoteModel lote, 
                                     @RequestParam("producto") String producto, 
                                     BindingResult result){
        ModelAndView mV = new ModelAndView();
        if (result.hasErrors()) {
            mV.setViewName(ViewRouteHelper.LOTE_NUEVO);
            //TODO mandar mensaje de error
        } else {
            ProductoModel producto1 = productoService.traerPorNombre(producto);
            System.out.printf("%s", producto1);
            if (producto1 != null) {
                producto1.setStock(producto1.getStock() + lote.getCantidad());
                productoService.insertOrUpdate(producto1);
                loteService.saveLote(lote);
                mV.addObject("productos", productoService.getAll());
                mV.addObject("lote", new Lote());
                mV.setViewName(ViewRouteHelper.INDEX); 
            } else {
                //TODO mandar mensaje de error
                // mV.addObject("errorMessage", "Producto no encontrado");
                // mV.setViewName(ViewRouteHelper.LOTE_NUEVO);
            }
        }
        return mV;
    }


}
