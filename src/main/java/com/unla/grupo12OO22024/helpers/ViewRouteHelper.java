package com.unla.grupo12OO22024.helpers;

public class ViewRouteHelper {

	// HOME
	public final static String INDEX = "home/index"; //vista principal de admin
	public final static String USER_LOGIN = "home/login"; //vista login
	public final static String USER_REGISTER = "home/register"; //vista registro
	public static final String USER_LOGOUT = "redirect:/login"; //redireccion al login

	// PRODUCTO
	public final static String PRODUCTO_FORM = "producto/form";
	public final static String PRODUCTO_NEW = "producto/new";
	public final static String PRODUCTO_INDEX = "producto/productos";

	public final static String PRODUCTO_INFORMES = "producto/informes";



	// LOTE
	public final static String LOTE = "lote/lotes"; //vista de todos los lotes
	public final static String LOTE_NEW = "lote/new"; //post de lote
	public final static String LOTE_FORM = "lote/form"; //vista registro nuevo lote

	// PEDIDO - Rocio
	public final static String PEDIDO_FORM = "pedido/form";
	public final static String PEDIDO_NEW = "pedido/new";
	public final static String PEDIDO_INDEX = "pedido/pedidos";
	public final static String PEDIDO_REDIRECT = "redirect:/pedido/pedidos";

	// VENTA - Rocio
	public final static String VENTA_FORM = "venta/form";
	public final static String VENTA_COMPRAS = "venta/compras";
	public final static String VENTA_INDEX = "venta/index";
	public final static String VENTA_STOCK = "venta/stock";

 // REDIRECTS
  public final static String ROUTE_INDEX = "redirect:/index"; //login redirect para admin
	public final static String ROUTE_USER = "redirect:/venta/compras"; //login redirect de usuario
	
}
