package com.login;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.login.dao.DBUtils;


/**
 * Servlet implementation class ShoppingRequest
 */
@WebServlet("/ShoppingRequest")
public class ShoppingRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("Inside ShoppingRequest Servlet");
		
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
		
			Integer jqt =0 , sqt=0; 
				
			try {
				String jeanQty = request.getParameter("JeansQty");
				String shirtQty = request.getParameter("ShirtQty");		
			
				jqt = Integer.parseInt(jeanQty);
				sqt =  Integer.parseInt(shirtQty);
			
			} catch (NumberFormatException e){
				e.printStackTrace();			
			}
			
			DBUtils db = DBUtils.getInstance();
			if (jqt == 0 && sqt == 0) {
				System.out.println("User specified 0 items to buy");
				request.getRequestDispatcher("Error.jsp").forward(request,response);
			}		
					
			HashMap<String,Integer> hashList = new HashMap<String,Integer>();
			if (sqt > 0) {
				hashList.put("Shirt", sqt);
			}
			if (jqt > 0 ) {
				hashList.put("Jeans", jqt);
			}
			Integer noOfRecs = db.buyItems(userid, hashList);
			System.out.println("No of Records inserted doPost " + noOfRecs);
			if (noOfRecs == -1) {
				request.getRequestDispatcher("Error.jsp").forward(request,response);
			}
			
			request.getRequestDispatcher("Added.jsp").forward(request,response);
			
			//response.getWriter().append("Served at: ").append(request.getContextPath());
			System.out.println("Exiting ShoppingRequest Servlet");
		}
	}

}
