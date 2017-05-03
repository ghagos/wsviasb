package com.ghagos.wsviasb.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

	@Autowired
	OrderRepoService orderRepoService;

	public List<Order> all() {
		List<Order> list = new ArrayList<>();
		orderRepoService.findAll().forEach(list::add);
		return list;
	}

	public Order getOrderById(String customerId, Long orderId) {
		//return orderRepoService.findOrderByCustomerById(customerId, orderId);
		return orderRepoService.findByCustomerIdAndOrderId(customerId, orderId);
	}

	public List<Order> getOrdersByCountry(String country) {
		List<Order> list = new ArrayList<>();
		orderRepoService.findByShipCountryStartingWith(country).forEach(list::add);
		return list;
	}

	public List<Order> getOrderByCustomerId(String customerId) {
		List<Order> list = new ArrayList<>();
		orderRepoService.findByCustomerId(customerId).forEach(list::add);
		return list;
	}

	public void postOrder(Order order) {
		orderRepoService.save(order);
	}

	public void deleteOrder(Long id) {
		orderRepoService.delete(id);
	}

	public void updateOrder(Order order, Long id) {
		orderRepoService.delete(id);
		orderRepoService.save(order);
	}

	public List<Order> getOrderByShipCountryAndState(String country, String state) {
		List<Order> list = new ArrayList<>();
		//orderRepoService.findByShipCountryAndShipRegion(country, state).forEach(list::add);
		orderRepoService.findByShipCountryEqualsAndShipRegionEquals(country, state).forEach(list::add);
		return list;
	}

	public List<Order> getTop10Price() {
		List<Order> list = new ArrayList<>();
		orderRepoService.findTop10ByFreight().forEach(list::add);
		return list;
	}
}
