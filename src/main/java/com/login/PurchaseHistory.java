package com.login;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.login.dao.DBUtils;

/**
 * Servlet implementation class PurchaseHistory
 */
@WebServlet("/PurchaseHistory")
public class PurchaseHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PurchaseHistory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Inside PurchaseHistory Servlet");
		
		// get userid from session
		HttpSession requestSession = request.getSession(false);
		if (requestSession == null) {
			//redirect user to login page
			request.getRequestDispatcher("Login.jsp").forward(request,response);
		}
		Integer userid = (Integer ) requestSession.getAttribute("userid");
		
		if (userid == null ) {
			System.out.println("userid from session is :" + userid);
			request.getRequestDispatcher("Login.jsp").forward(request,response);
		}
		else {
		
			// get user input parameter for no of records he wants to see
			String noOfRecStr = request.getParameter("noOfRec");
			Integer noOfRec = Integer.parseInt(noOfRecStr);
			
			// get the purchase history for the userid from database
			DBUtils db = DBUtils.getInstance();
			ArrayList<UserOrderHistory >  orderList = db.showPurchaseHistory(userid, noOfRec);
			
			response.getWriter().append("<h1>Order details</h1>");
			
			for (int i=0; i< orderList.size(); i++) {			
				System.out.println( orderList.get(i).toString());
				
				response.getWriter().append("<h3 style=\"color: green; font-family:serif\">"  + orderList.get(i).toString() + "</h3>");
			}
			
	//		<h1 style="color: blue; font-family : serif; font-style:italic" > ReLogin
	//				<a href="./Login.jsp"> Login</a>
	//				</h1>
	//		
			response.getWriter().append("<h4 style=\"color: blue; font-family:serif; font-style:italic\" > Continue shopping" 
					+ "<a href=\"./Welcome.jsp\"> Continue shopping</a> </h4>");
			response.getWriter().append("<h4 style=\"color: blue; font-family:serif; font-style:italic\" > Logout" 
					+ "<a href=\"./Logout.jsp\"> Logout</a> </h4>");
			
					
			
			//request.getRequestDispatcher("Welcome.jsp").forward(request,response);		
		}
	}

}
