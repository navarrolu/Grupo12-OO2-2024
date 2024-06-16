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

import com.unla.grupo12OO22024.entities.Producto;
import com.unla.grupo12OO22024.helpers.ViewRouteHelper;
import com.unla.grupo12OO22024.models.LoteModel;
import com.unla.grupo12OO22024.services.implementation.LoteService;
import com.unla.grupo12OO22024.services.implementation.ProductoService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/lote")
public class LoteController {

    @Autowired
    @Qualifier("loteService")
    private LoteService loteService;

    @Qualifier("productoService")
    @Autowired
    private ProductoService productoService;

    //obtener la vista del form
    @GetMapping("/form")
    public String lote(Model model) {
        List<Producto> productos = productoService.getAll();
        model.addAttribute("productos", productos);
        model.addAttribute("lote", new LoteModel());
        return ViewRouteHelper.LOTE_FORM;
    }

    @PostMapping("/new")
    public ModelAndView registerLote(@Valid @ModelAttribute("lote") LoteModel lote,
                                     BindingResult result) {
        ModelAndView mV = new ModelAndView();
        if (result.hasErrors()) {
            mV.setViewName(ViewRouteHelper.LOTE_FORM);
            mV.addObject("errorMessage", "Error en los datos del lote.");
        }else{
            loteService.insertOrUpdate(lote);
            mV.addObject("lotes", loteService.getClass());
            mV.addObject("lote", new LoteModel());
            mV.setViewName(ViewRouteHelper.INDEX);
        }
        return mV;
    }

    

}
