package com.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.login.dao.DBUtils;
//import com.login.dao.LoginDao;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//LoginDao dao = new LoginDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside Login Servlet");
		
		String fullName = request.getParameter("fullName");
		String passwd = request.getParameter("pass");			
		
		
		if (fullName.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
			
			// 1st method to forward to another
			rd.forward(request, response);			
		} else {
			DBUtils db = DBUtils.getInstance();
				//DBUtıls.getInstance().check();
			Integer UserId = db.checkUser(fullName, passwd);
			if (UserId > -1) {
				HttpSession session = request.getSession();
				session.setAttribute("username", fullName);
				session.setAttribute("userid", UserId);
				request.getRequestDispatcher("Welcome.jsp").forward(request,response);
				
			}
			else {
				response.getWriter().append("<h1> Data Entered is incorrect... </h1>");
				request.getRequestDispatcher("Error.jsp").forward(request,response);
				
			}		
	
			System.out.println("Exit Login Servlet");
		}
	}

}
