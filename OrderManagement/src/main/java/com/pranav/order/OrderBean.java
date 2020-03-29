package com.pranav.order;

public class OrderBean {
	private int orderID;
	private String orderName;
	private String orderSrc;
	private String orderDest;

	public OrderBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderBean(int orderID, String orderName, String orderSrc, String orderDest) {
		super();
		this.orderID = orderID;
		this.orderName = orderName;
		this.orderSrc = orderSrc;
		this.orderDest = orderDest;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderSrc() {
		return orderSrc;
	}

	public void setOrderSrc(String orderSrc) {
		this.orderSrc = orderSrc;
	}

	public String getOrderDest() {
		return orderDest;
	}

	public void setOrderDest(String orderDest) {
		this.orderDest = orderDest;
	}

	@Override
	public String toString() {
		return "OrderBean [orderID=" + orderID + ", orderName=" + orderName + ", orderSrc=" + orderSrc + ", orderDest="
				+ orderDest + "]";
	}

}
