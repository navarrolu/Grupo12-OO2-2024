package com.unla.grupo12OO22024.controllers;

import java.util.List;

//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo12OO22024.models.PedidoModel;
import com.unla.grupo12OO22024.entities.Producto;
import com.unla.grupo12OO22024.helpers.ViewRouteHelper;
import com.unla.grupo12OO22024.services.implementation.PedidoService;
import com.unla.grupo12OO22024.services.implementation.ProductoService;

import jakarta.validation.Valid;

@Controller
//@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/pedido")
public class PedidoController {

	@Qualifier("pedidoService")
	private PedidoService pedidoService;

	@Qualifier("productoService")
	private ProductoService productoService;

	// private ModelMapper modelMapper = new ModelMapper();

	public PedidoController(PedidoService pedidoService, ProductoService productoService) {
		this.pedidoService = pedidoService;
		this.productoService = productoService;
	}

	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PEDIDO_INDEX);
		mAV.addObject("pedidos", pedidoService.getAll());
		mAV.addObject("pedido", new PedidoModel());
		return mAV;
	}

	@GetMapping("/form")
	public String pedido(Model model) {
		List<Producto> productos = productoService.getAll();
		model.addAttribute("productos", productos);
		model.addAttribute("pedido", new PedidoModel());
		return ViewRouteHelper.PEDIDO_FORM;
	}

	@PostMapping("/new")
	public ModelAndView newpedido(@Valid @ModelAttribute("pedido") PedidoModel pedido, BindingResult bindingResult) {
		ModelAndView mV = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mV.setViewName(ViewRouteHelper.PEDIDO_FORM);
		} else {
			pedidoService.insertOrUpdate(pedido);
			mV.addObject("pedidos", pedidoService.getAll());
			mV.addObject("pedido", new PedidoModel());
			mV.setViewName(ViewRouteHelper.PEDIDO_INDEX);

		}
		return mV;
	}

}
