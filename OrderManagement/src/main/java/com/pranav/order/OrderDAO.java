package com.pranav.order;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class OrderDAO {

	@Autowired
	private OrderBean resObj;

	public OrderBean createOrder(OrderBean order) {
		int id = (int) (Math.random() * 100);
		order.setOrderID(id);
		order.setOrderName("DTDC");
		order.setOrderSrc("Delhi");
		order.setOrderDest("Bangalore");
		String url = "http://localhost:8080/orderApi/createOrder/";
		RestTemplate restTemplate = new RestTemplate();
		OrderBean resOrder = restTemplate.postForObject(url, order, OrderBean.class);

		try {

			java.sql.Connection con = null;

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/IBM", "root", "Pranav@123");
			PreparedStatement prepStmt;

			prepStmt = con.prepareStatement("insert into order(id,name,src,dest) values(?,?,?,?);");
			prepStmt.setInt(1, id);
			prepStmt.setString(2, "DTDC");
			prepStmt.setString(3, "Delhi");
			prepStmt.setString(4, "Bangalore");
			prepStmt.execute();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return resOrder;
	}

	public List<OrderBean> getAllOrders() {
		List<OrderBean> resList = new ArrayList<>();
		try {

			java.sql.Connection con = null;

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/IBM", "root", "Pranav@123");
			java.sql.Statement stmt = con.createStatement();
			ResultSet results = stmt.executeQuery("select * from order");
			while(results.next())
			{
	
				resObj.setOrderID(results.getInt("id"));
				resObj.setOrderName(results.getString("name"));
				resObj.setOrderSrc(results.getString("src"));
				resObj.setOrderDest(results.getString("dest"));
				resList.add(resObj);
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return resList;
	}

	public String deleteOrder(int id) {
		try {

			java.sql.Connection con = null;

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/IBM", "root", "Pranav@123");
			PreparedStatement prepStmt;
			prepStmt = con.prepareStatement("delete from order where id = ?");
			prepStmt.setInt(1, id);
			prepStmt.execute();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return "success";
	}

	public OrderBean updateOrder(int id) {
		
		
		try {

			java.sql.Connection con = null;

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/IBM", "root", "Pranav@123");
			PreparedStatement prepStmt;
			prepStmt = con.prepareStatement("update order set name='Flipkart' where id = ?");
			prepStmt.setInt(1, id);
			prepStmt.executeUpdate();
			resObj.setOrderID(id);
			resObj.setOrderName("Flipkart");
			resObj.setOrderSrc("Delhi");
			resObj.setOrderDest("Bangalore");
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return resObj;
	}

	public OrderBean getOrderByID(int id) {
		
		try {

			java.sql.Connection con = null;

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/IBM", "root", "Pranav@123");
			PreparedStatement prepStmt;
			prepStmt = con.prepareStatement("select * from order where id = ?");
			prepStmt.setInt(1, id);
			ResultSet results = prepStmt.executeQuery();
			while(results.next())
			{
				resObj.setOrderID(results.getInt("id"));
				resObj.setOrderName(results.getString("name"));
				resObj.setOrderSrc(results.getString("src"));
				resObj.setOrderDest(results.getString("dest"));
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return resObj;
	}

}
