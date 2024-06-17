package com.unla.grupo12OO22024.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo12OO22024.entities.Producto;
import com.unla.grupo12OO22024.helpers.ViewRouteHelper;
import com.unla.grupo12OO22024.models.VentaModel;
import com.unla.grupo12OO22024.services.implementation.VentaService;
import com.unla.grupo12OO22024.services.implementation.ProductoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/venta")
public class VentaController {

	@Qualifier("ventaService")
	private VentaService ventaService;

	@Qualifier("productoService")
	private ProductoService productoService;

	// private ModelMapper modelMapper = new ModelMapper();

	public VentaController(VentaService ventaService, ProductoService productoService) {
		this.ventaService = ventaService;
		this.productoService = productoService;
	}

	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.VENTA_INDEX);
		List<Producto> productos = productoService.getAll();
		mAV.addObject("productos", productos);
		mAV.addObject("venta", new VentaModel());
		return mAV;
	}

	@GetMapping("/form")
	public String pedido(Model model) {
		List<Producto> productos = productoService.getAll();
		model.addAttribute("productos", productos);
		model.addAttribute("venta", new VentaModel());
		return ViewRouteHelper.VENTA_FORM;
	}

	@PostMapping("/new")
	public ModelAndView newVenta(@Valid @ModelAttribute("venta") VentaModel venta, BindingResult bindingResult) {
		ModelAndView mV = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mV.setViewName(ViewRouteHelper.VENTA_FORM);
		} else {
			ventaService.insertOrUpdate(venta);
			mV.addObject("ventas", ventaService.getAll());
			mV.addObject("venta", new VentaModel());
			mV.setViewName(ViewRouteHelper.VENTA_INDEX);
		}
		return mV;
	}

	@GetMapping("/usuario/{usuarioId}")
	public ModelAndView getVentasByUsuario(@PathVariable int usuarioId) {
		ModelAndView mV = new ModelAndView();
		List<VentaModel> ventas = ventaService.findByUsuario(usuarioId);
		mV.addObject("ventas", ventas);
		mV.setViewName(ViewRouteHelper.VENTA_USUARIO); // Asegúrate de tener esta vista configurada
		return mV;
	}
}