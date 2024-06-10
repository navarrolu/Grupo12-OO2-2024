package com.unla.grupo12OO22024.controllers;

import org.modelmapper.ModelMapper;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo12OO22024.models.PedidoModel;
import com.unla.ghsicilianotfi.dtos.DegreeDTO;
import com.unla.grupo12OO22024.entities.Pedido;
import com.unla.grupo12OO22024.helpers.ViewRouteHelper;
import com.unla.grupo12OO22024.services.implementation.PedidoService;

import jakarta.validation.Valid;

@Controller
//@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/pedido")
public class PedidoController {

	private PedidoService pedidoService;
	
	private ModelMapper modelMapper = new ModelMapper();

	public PedidoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PEDIDO_INDEX);
		mAV.addObject("pedidos", pedidoService.getAll());
		return mAV;
	}

	@GetMapping("/new")
	public ModelAndView create(Model model) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PEDIDO_NEW);
		mAV.addObject("pedido", new PedidoModel());
		return mAV;
	}
	
	@PostMapping("/")
	public RedirectView create(@ModelAttribute("pedido") PedidoModel pedidoModel) {
		pedidoService.insertOrUpdate(modelMapper.map(pedidoModel, Pedido.class));
		return new RedirectView(ViewRouteHelper.PEDIDO_ROOT);
	}
	
	@PostMapping("/new")
	public ModelAndView newdegree(@Valid @ModelAttribute("degree") DegreeDTO degree, BindingResult bindingResult) {
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
