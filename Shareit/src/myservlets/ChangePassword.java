package myservlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mybeans.PasswordChanger;


/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out=response.getWriter();
		HttpSession ses=request.getSession(true);
		String id,curps,newps;
		
		id=String.valueOf(ses.getAttribute("userid"));
		curps=request.getParameter("curps");
		newps=request.getParameter("newps");
		
		PasswordChanger pc=new PasswordChanger();
		pc.setUserid(id);
		pc.setCurpass(curps);
		pc.setNewpass(newps);
		
		out.println("<h3>"+pc.getStatus()+"</h3>");
		out.println("<br><br>");
		
	
			out.println("<a href='members.jsp'>Back to User Home</a>");
		
	}

}
