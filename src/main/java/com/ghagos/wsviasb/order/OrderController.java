package com.ghagos.wsviasb.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

	@Autowired
	OrderService orderService;

	/**
	 * Return all orders or orders shipped to a country or orders shipped to USA of a given state
	 * @param shipCountry
	 * @return
	 */
	@RequestMapping(value = "/orders")
	public List<Order> getAll(@RequestParam(value = "shipCountry", defaultValue = "all") String shipCountry, 
			                  @RequestParam(value = "shipRegion", defaultValue = "none") String state) {
		if (shipCountry.equals("all")) {
			return orderService.all();
		} else if (shipCountry.equals("USA") && !state.equals("none")) {
			return orderService.getOrderByShipCountryAndState(shipCountry, state);
		} else {
			return orderService.getOrdersByCountry(shipCountry);
		}
	}

	/**
	 * Return an order for a customer with an order id
	 * @param customerId
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/orders/{customerId}/{orderId}")
	public Order getOrderById(@PathVariable String customerId, @PathVariable Long orderId) {
		return orderService.getOrderById(customerId, orderId);
	}
	
	/**
	 * Return all orders for a customer or top 10 expensive orders
	 * @param customerId
	 * @return
	 */
	@RequestMapping(value = "/orders/{pathVar}")
	public List<Order> getOrderByCustomerId(@PathVariable String pathVar) {
/*		if (pathVar.equals("top10")) {
			return orderService.getTop10Price();
		}*/
		return orderService.getOrderByCustomerId(pathVar);
	}
	
	/**
	 * Post order to database
	 * @param order
	 */
	@RequestMapping(value = "/orders", method=RequestMethod.POST)
	public void postOrder(@RequestBody Order order) {
		orderService.postOrder(order);
	}
	
	/**
	 * Delete order from database
	 * @param id
	 */
	@RequestMapping(value = "/orders/{id}", method=RequestMethod.DELETE)
	public void deleteOrder(@PathVariable Long id) {
		orderService.deleteOrder(id);
	}
	
	/**
	 * Update order in database
	 * @param order
	 * @param id
	 */
	@RequestMapping(value = "/orders/{id}", method=RequestMethod.PUT)
	public void updateOrder(@RequestBody Order order, @PathVariable Long id) {
		orderService.updateOrder(order, id);
	}	
	
}
