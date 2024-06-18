package com.unla.grupo12OO22024.helpers;

public class ViewRouteHelper {

	// HOME
	public final static String INDEX = "home/index";
	public final static String HELLO = "home/hello";
	public final static String USER_LOGIN = "home/login";
	public final static String USER_LOGOUT = "home/login2";
	public final static String USER_REGISTER = "home/register";

	// PRODUCTO
	public final static String PRODUCTO_FORM = "producto/form";
	public final static String PRODUCTO_NEW = "producto/new";
	public final static String PRODUCTO_INDEX = "producto/productos";

	// LOTE
	public final static String LOTE = "lote/lotes";
	public final static String LOTE_NEW = "lote/new";
	public final static String LOTE_FORM = "lote/form";

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
	public final static String ROUTE_INDEX = "redirect:/index";
	public final static String ROUTE_USER = "redirect:/venta/compras";

}
