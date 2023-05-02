package com.login.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.login.UserOrderHistory;

public class DBUtils {
	
	private Connection con;
	private String url="jdbc:mysql://localhost:3306/";
	private String uname="root";
	private String pass="pass";
	private String dbname = "amazon_Shopping";

	private String val = "Hello! ";
	
	
	//  1st step
	public static DBUtils DBUtilsInstance = null;	
	
	//second step
	private DBUtils() {
		System.out.println("creating new ...");
		StringBuffer sb = new StringBuffer(url);		
		try {
			
			String dbDriver = "com.mysql.cj.jdbc.Driver";
			Class.forName(dbDriver);
			con = DriverManager.getConnection(sb.append(dbname).toString(), uname, pass);
			System.out.println("connection successfull...");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		
		System.out.println("connection successfull...");	
	}
	
	//3rd step
	//synchronized public static DBUtils getInstance() {
	public static DBUtils getInstance() {
		System.out.println("inside getInstance...");
		
		if (DBUtilsInstance == null) {
			
			synchronized (DBUtils.class) {
				
				if(DBUtilsInstance == null) {					
					DBUtilsInstance = new DBUtils();
				}
			}
		}
		return DBUtilsInstance;		
	}
	
	public void finalize() {
		
		try {
			con.close();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}
	
	public Integer checkUser(String username, String passwd) {			
		
		if (con == null) {
			System.out.println("Invalid database connection");
			return -1;
		}
		//Connection  is an interface. so we use DriverManager that has many classes and one method returns the connection method
		try {
			String sql = "select * from users where UserName=? and passwd=?";
						
			PreparedStatement ps= con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, passwd);
			ResultSet rs = ps.executeQuery();
											
			if (rs.next()) {
				Integer id = rs.getInt(1);
				return id;
			}				
			
			ps.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}	
		return -1;
	}
	
	public void registerUser(String username, String passwd) {
	}
	
	public Integer buyItems(Integer userid, HashMap<String,Integer> hashList ) {
		
		Integer noOfRecs = -1;
		if (con == null) {
			System.out.println("Invalid database connection");
			return -1;
		}
		try {
			
//			INSERT INTO orders (Item, Quantity, purchase_Date, purchase_time, User_Id)
//			VALUES ("Shirt", 2, CURDATE(), CURRENT_TIME(), 1);
			
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            java.sql.Time sqlTime = new java.sql.Time(utilDate.getTime());
            
            
            StringBuffer mySql = new StringBuffer("INSERT INTO orders (Item, Quantity, purchase_Date, purchase_time, User_Id) "
            		+ "values (?, ?, ?, ?, ?) ");

            for (int i = 0; i < hashList.size() - 1; i++) {
                mySql.append(", (?, ?, ?, ?, ?)");
            }

            PreparedStatement myStatement = con.prepareStatement(mySql.toString());
            
            Integer i=1;
            for (String str : hashList.keySet()) {
                myStatement.setString(i++, str);
                myStatement.setInt(i++, hashList.get(str));
                myStatement.setDate(i++, sqlDate);
                myStatement.setTime(i++, sqlTime);
                myStatement.setInt(i++, userid);
            }            
            
            //System.out.println(" stmt to be executed is " + myStatement);
            noOfRecs  = myStatement.executeUpdate();
		
			System.out.println("No of Records inserted is " + noOfRecs);
			myStatement.close();
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		return noOfRecs;
	}
	
	public ArrayList<UserOrderHistory > showPurchaseHistory(Integer userid, Integer noOfOrders) {
		
		ArrayList<UserOrderHistory > OrderList = new ArrayList<>();
		String query = "SELECT * FROM orders WHERE User_id=? LIMIT ?";
		
		if (noOfOrders == 0 ) {
			System.out.println("No of Records not specified");			
			return OrderList;
		}
		
		try {			
			PreparedStatement ps= con.prepareStatement(query);
			ps.setInt(1, userid);
			ps.setInt(2, noOfOrders);			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				String Item = rs.getString(2);
				Integer qty = rs.getInt(3);
				java.sql.Date sqlDate = rs.getDate(4); //Purchase date
				java.sql.Time sqlTime = rs.getTime(5); // Purchase time
				
				UserOrderHistory orderHistory = new UserOrderHistory(Item, qty, sqlDate, sqlTime);
				OrderList.add(orderHistory);			
			}
			rs.close();
			ps.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return OrderList;
	}
	
	
}
