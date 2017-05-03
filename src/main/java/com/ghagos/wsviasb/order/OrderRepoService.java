package com.ghagos.wsviasb.order;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepoService extends CrudRepository<Order, Long> {
	//public List<Order> findByShipCountryLike(String country);
	public List<Order> findByShipCountryStartingWith(String country); // same as above except like requires '%' to be appended after country in the calling method

	public List<Order> findByCustomerId(String customerId);

	//@Query(value = "SELECT * FROM Orders o WHERE LOWER(o.customerId) = LOWER(:customerId) AND LOWER(o.orderId) = LOWER(:orderId)", nativeQuery = true)
	//public Order findOrderByCustomerById(@Param("customerId") String customerId, @Param("orderId") Long orderId);
	
	public Order findByCustomerIdAndOrderId(String customerId, Long orderId); // same as above
	
	//public List<Order> findByShipCountryAndShipRegion(String country, String state);
	public List<Order> findByShipCountryEqualsAndShipRegionEquals(String country, String state); // same result as above
	
	public List<Order> findTop10ByFreight();
}
