package com.wrox.tag;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wrox.tag.DBManager;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

@WebServlet(
        name = "CreateAccountServlet",
        urlPatterns = "/createAccount"
)

public class CreateAccountServlet extends HttpServlet{
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
		System.out.println("Inside contrroller ");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("userName");
		String userID = request.getParameter("userID");
		String password = request.getParameter("password");
		String newPassword = request.getParameter("newPassword");
		
		if(firstName == null || lastName == null || userID == null || password == null){
			request.setAttribute("ConfirmAccount", true);
		}
		else {
			if(!password.equals(newPassword))
			{
				request.setAttribute("Confirmpassword", true);
		        request.getRequestDispatcher("/WEB-INF/jsp/view/CreatAccount.jsp")
		               .forward(request, response);
			}
			else{
				DBManager manager = new DBManager();
				manager.createUser(Integer.parseInt(userID), userName, password, firstName, lastName);
				response.sendRedirect("tickets");
			}
		}
    }

}
