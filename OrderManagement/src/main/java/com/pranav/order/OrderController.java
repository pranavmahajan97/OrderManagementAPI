package com.pranav.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/orderApi")
public class OrderController {
	
	@Autowired
	private OrderDAO orderDAO;
	
	@RequestMapping(path="/createOrder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderBean> createOrder(@RequestBody OrderBean order)
		{
		OrderBean resOrder = orderDAO.createOrder(order);
		return new ResponseEntity<OrderBean>(resOrder, HttpStatus.CREATED);
		}
	
	@RequestMapping(path="/getAllOrders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OrderBean> getAllOrders()
	{
		return orderDAO.getAllOrders();
	}
	
	@RequestMapping(path="/deleteOrder/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String deleteOrder(@PathVariable String id)
	{
		return orderDAO.deleteOrder(Integer.parseInt(id));
	}
	
	@RequestMapping(path="/getOrderByID/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderBean> getOrderByID(@PathVariable String id)
	{
		OrderBean resOrder = orderDAO.getOrderByID(Integer.parseInt(id));
		HttpStatus httpstatus = (resOrder==null) ? HttpStatus.NO_CONTENT : HttpStatus.OK;
		return new ResponseEntity<OrderBean>(resOrder, httpstatus);
	}
	
	@RequestMapping(path="/updateOrder/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderBean> updateOrder(@PathVariable String id)
	{
		OrderBean resOrder = orderDAO.updateOrder(Integer.parseInt(id));
		HttpStatus httpstatus = (resOrder==null) ? HttpStatus.NO_CONTENT : HttpStatus.OK;
		return new ResponseEntity<OrderBean>(resOrder, httpstatus);
	}
	
	
}

