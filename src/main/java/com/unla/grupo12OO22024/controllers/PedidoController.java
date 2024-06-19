package com.unla.grupo12OO22024.controllers;

import java.util.List;

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
import com.unla.grupo12OO22024.models.PedidoModel;
import com.unla.grupo12OO22024.services.implementation.PedidoService;
import com.unla.grupo12OO22024.services.implementation.ProductoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

	@Qualifier("pedidoService")
	private PedidoService pedidoService;

	@Qualifier("productoService")
	private ProductoService productoService;

	// Constructor que recibe las instancias de PedidoService y ProductoService
	public PedidoController(PedidoService pedidoService, ProductoService productoService) {
		this.pedidoService = pedidoService;
		this.productoService = productoService;
	}

	// Rocio:
	/*
	 * Método GET para mostrar la vista de índice de pedidos
	 * 
	 * Dentro de este metodo se agregan la lista de pedidos y un nuevo objeto
	 * PedidoModel al modelo,
	 */
	@GetMapping("/pedidos")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PEDIDO_INDEX);
		mAV.addObject("pedidos", pedidoService.getAll());
		mAV.addObject("pedido", new PedidoModel());
		return mAV;
	}

	// Rocio:
	/*
	 * Método GET para mostrar el formulario de pedido
	 * 
	 * En este metodo se obtiene una lista de Productos, la cual se agrega al modelo
	 * al igual que un nuevo objeto PedidoModel y retorna la ruta de la vista del
	 * formulario de pedido.
	 */
	@GetMapping("/form")
	public String pedido(Model model) {
		List<Producto> productos = productoService.getAll();
		model.addAttribute("productos", productos);
		model.addAttribute("pedido", new PedidoModel());
		return ViewRouteHelper.PEDIDO_FORM;
	}

	// Rocio
	/*
	 * Método POST para crear un nuevo pedido
	 * 
	 * Se verifica si hay errores en la validacion, en caso de que haya errores, se
	 * setea la vista del formulario de pedido.
	 * 
	 * En caso de estar correcto, inserta el pedido en la base de datos, agrega la
	 * lista actualizada de pedidos y un nuevo objeto PedidoModel al modelo y por
	 * ultimo redirige a la vista de indice de pedidos
	 *
	 */
	@PostMapping("/new")
	public ModelAndView newpedido(@Valid @ModelAttribute("pedido") PedidoModel pedido, BindingResult bindingResult) {
		ModelAndView mV = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mV.setViewName(ViewRouteHelper.PEDIDO_FORM);
		} else {
			pedidoService.insertPedido(pedido);
			mV.addObject("pedidos", pedidoService.getAll());
			mV.addObject("pedido", new PedidoModel());
			mV.setViewName(ViewRouteHelper.PEDIDO_INDEX);
		}
		return mV;
	}

}
