package com.login;

public class UserOrderHistory {
	
	private String Item;
	private Integer qty;
	private java.sql.Date sqlDate; //Purchase date
	private java.sql.Time sqlTime; // Purchase Time
	
	public UserOrderHistory(String Item, Integer qty, java.sql.Date sqlDate, java.sql.Time sqlTime) {
		
		this.Item = Item;
		this.qty = qty;
		this.sqlDate = sqlDate;
		this.sqlTime = sqlTime;
	}

	@Override
	public String toString() {
		return "[Item=" + Item + ", qty=" + qty + ", Date=" + sqlDate + ", Time=" + sqlTime + "]";
	}
	

}
