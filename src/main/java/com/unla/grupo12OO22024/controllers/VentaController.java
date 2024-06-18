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
import com.unla.grupo12OO22024.entities.Venta;
import com.unla.grupo12OO22024.helpers.ViewRouteHelper;
import com.unla.grupo12OO22024.models.VentaModel;
import com.unla.grupo12OO22024.services.implementation.ProductoService;
import com.unla.grupo12OO22024.services.implementation.UserService;
import com.unla.grupo12OO22024.services.implementation.VentaService;

import jakarta.validation.Valid;

//Indica que esta clase es un controlador de Spring
@Controller
//Mapea las solicitudes que comienzan con "/venta" a este controlador
@RequestMapping("/venta")
public class VentaController {

	// Se utiliza la anotación @Qualifier para inyectar el bean de VentaService
	@Qualifier("ventaService")
	private VentaService ventaService;

	// Se utiliza la anotación @Qualifier para inyectar el bean de ProductoService
	@Qualifier("productoService")
	private ProductoService productoService;

	// Se utiliza la anotación @Qualifier para inyectar el bean de UserService
	@Qualifier("userService")
	private UserService userService;

	// Rocio:
	// Constructor que recibe instancias de los servicios necesarios
	public VentaController(VentaService ventaService, ProductoService productoService, UserService userService) {
		this.ventaService = ventaService;
		this.productoService = productoService;
		this.userService = userService;
	}

	// Rocio:
	/*
	 * Método GET para mostrar la vista de compras
	 * 
	 * Se setea una vista para el metodo, se obtiene una lista de compras realizadas
	 * por el usuario que haya ingresado al sistema, se agregan las ventas y un
	 * nuevo objeto VentaModel al modelo, para al final retornar la vista seteada al
	 * principio.
	 */
	@GetMapping("/compras")
	public ModelAndView compras() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.VENTA_COMPRAS);
		List<Venta> ventas = ventaService.obtenerVentasPorUsuario();
		mAV.addObject("ventas", ventas);
		mAV.addObject("venta", new VentaModel());
		return mAV;
	}

	// Rocio:
	/*
	 * Método GET para mostrar la vista de ventas totales para usuarios con rol
	 * administrador
	 * 
	 * Se obtiene y se agrega al modelo una lista de productos y una lista de ventas
	 * de todos los usuarios, Se agrega un nuevo objeto VentaModel al modelo y se
	 * retorna la vista seteada al inicio.
	 */
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.VENTA_INDEX);
		List<Producto> productos = productoService.getAll();
		List<Venta> ventas = ventaService.getAll();
		mAV.addObject("productos", productos);
		mAV.addObject("ventas", ventas);
		mAV.addObject("venta", new VentaModel());
		return mAV;
	}

	// Rocio:
	/*
	 * Método GET para mostrar el formulario de venta *
	 * 
	 * Se obtiene una lista de productos, la cual se agrega al modelo junto con un
	 * nuevo objeto VentaModel, luego se devuelve la ruta de la vista del formulario
	 */
	@GetMapping("/form")
	public String venta(Model model) {
		List<Producto> productos = productoService.getAll();
		model.addAttribute("productos", productos);
		model.addAttribute("venta", new VentaModel());
		return ViewRouteHelper.VENTA_FORM;
	}

	// Rocio:
	/*
	 * Método POST para crear una nueva venta
	 * 
	 * Se verifica si hay errores en la validacion, en caso de que haya errores, se
	 * setea la vista del formulario de venta.
	 * 
	 * Si no hay errores, se inserta la nueva venta utilizando el service de venta,
	 * se agrega las ventas actualizadas y un nuevo objeto VentaModel al modelo y se
	 * setea la vista de compras.
	 * 
	 * Finalmente se redirige a la vista seteada previamente
	 */
	/*
	 * @PostMapping("/new") public ModelAndView
	 * newVenta(@Valid @ModelAttribute("venta") VentaModel venta, BindingResult
	 * bindingResult) { ModelAndView mV = new ModelAndView(); if
	 * (bindingResult.hasErrors()) { mV.setViewName(ViewRouteHelper.VENTA_FORM); }
	 * else { ventaService.insertVenta(venta); mV.addObject("ventas",
	 * ventaService.obtenerVentasPorUsuario()); mV.addObject("venta", new
	 * VentaModel()); mV.setViewName(ViewRouteHelper.VENTA_COMPRAS); } return mV; }
	 */

	@PostMapping("/new")
	public ModelAndView newVenta(@Valid @ModelAttribute("venta") VentaModel venta, BindingResult bindingResult) {
		ModelAndView mV = new ModelAndView();

		if (bindingResult.hasErrors()) {
			mV.setViewName(ViewRouteHelper.VENTA_FORM);
		}

		try {
			ventaService.insertVenta(venta);
			mV.addObject("ventas", ventaService.obtenerVentasPorUsuario());
			mV.addObject("venta", new VentaModel());
			mV.setViewName(ViewRouteHelper.VENTA_COMPRAS);
		} catch (IllegalArgumentException e) {
			mV.addObject("errorMessage", "No hay suficiente stock disponible \n para este producto.");
			mV.setViewName(ViewRouteHelper.VENTA_STOCK); 
			}
		return mV;
	}
}