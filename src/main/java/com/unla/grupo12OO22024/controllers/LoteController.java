/*package com.unla.grupo12OO22024.controllers;

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


@Controller
@RequestMapping("/lote")
public class LoteController {

    @Autowired
    @Qualifier("loteService")
    private LoteService loteService;


    @GetMapping("/nuevolote")
    public String showLoteForm(Model model){
        model.addAttribute("lote", new Lote());
        return ViewRouteHelper.LOTE_NUEVO;
    }
    //TODO: Crear lote service
    //TODO: Crerar modelo lote

    @PostMapping("/nuevolote")
        public ModelAndView registerLote(@ModelAttribute("lote") Lote lote, 
                                     @RequestParam("producto") String producto, 
                                     BindingResult result){
        ModelAndView mV = new ModelAndView();
        if (result.hasErrors()) {
            //mV.setViewName(ViewRouteHelper.USER_REGISTER);
            //TODO mandar mensaje de error
        } else {
            loteService.saveLote(lote, producto);
            //TODO no quiero guardar un nuevo registro de producto cada vez que guardo un lote
            //sino cambiar el stock del producto. chequear
            //mandar un set a stock desde el ingreso del lote
            mV.addObject("lote", new Lote());
            mV.setViewName(ViewRouteHelper.INDEX);
        }
        return mV;
    }


}
*/