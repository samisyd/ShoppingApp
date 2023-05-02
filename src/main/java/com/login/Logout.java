package com.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.login.dao.DBUtils;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try { 
			HttpSession session = request.getSession();
			session.removeAttribute("username");
			session.removeAttribute("userid");
			session.invalidate();
			
//			System.out.println("Deleting Database connection");
//			DBUtils db = DBUtils.getInstance();
			//db.destroy();
			
			System.out.println("Exiting Logout Servlet...");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
			
			//response.getWriter().append("Served at: ").append(request.getContextPath());
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
