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

import com.unla.grupo12OO22024.helpers.ViewRouteHelper;
import com.unla.grupo12OO22024.models.LoteModel;
import com.unla.grupo12OO22024.models.ProductoModel;
import com.unla.grupo12OO22024.services.IProductoService;
import com.unla.grupo12OO22024.services.implementation.LoteService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/lote")
public class LoteController {

    @Autowired
    @Qualifier("loteService")
    private LoteService loteService;

    @Autowired
    private IProductoService productoService;

    //obtener la vista del form
    @GetMapping("/nuevolote")
    public String ingresarLotes(Model model) {
        model.addAttribute("lote", new LoteModel());
        return ViewRouteHelper.LOTE_NUEVO;
    }

    @PostMapping("/new")
    public ModelAndView registerLote(@Valid @ModelAttribute("lote") LoteModel loteModel,
                                     @RequestParam("producto") String productoNombre,
                                     BindingResult result) {
        ModelAndView mV = new ModelAndView();
        if (result.hasErrors()) {
            mV.setViewName(ViewRouteHelper.LOTE_NUEVO);
            mV.addObject("errorMessage", "Error en los datos del lote.");
            return mV;
        } 

        ProductoModel productoModel = productoService.traerPorNombre(productoNombre);
        if (productoModel != null) {
            productoModel.setStock(productoModel.getStock() + loteModel.getCantidad());
            productoService.insertOrUpdate(productoModel);
            loteModel.setProducto(productoService.entityFromModel(productoModel));
            loteService.saveLote(loteModel);
            mV.addObject("productos", productoService.getAll());
            mV.addObject("lote", new LoteModel());
            mV.setViewName(ViewRouteHelper.INDEX);
        } else {
            mV.addObject("errorMessage", "Producto no encontrado.");
            mV.setViewName(ViewRouteHelper.LOTE_NUEVO);
        }

        return mV;
    }

}
