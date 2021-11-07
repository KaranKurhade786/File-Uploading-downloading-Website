package myservlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mybeans.DBConnector;

import java.sql.*;

/**
 * Servlet implementation class Check
 */
@WebServlet("/Check")
public class Check extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Check() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String id,ps,ldtm,typ,usernm;
		int status=0;
		boolean f;
		id=request.getParameter("uid");
		ps=request.getParameter("psw");
		ldtm=request.getParameter("logdt");
		//PrintWriter out=response.getWriter();
		
		Connection con;
		PreparedStatement pst,pst1;
		ResultSet rs,rs1;
		
		try
		{
			DBConnector dbc=new DBConnector();
			con=dbc.getDbconnection();
			pst=con.prepareStatement("select * from users where userid=? and pswd=?;");
			pst.setString(1, id);
			pst.setString(2, ps);
			rs=pst.executeQuery();
			
			/*pst1=con.prepareStatement("select * from members where userid=?;");
			pst1.setString(1, id);
			rs1=pst1.executeQuery();*/
		
		if(rs.next())
		{
			

			HttpSession ses=request.getSession(true);
			ses.setAttribute("userid", id);
			ses.setAttribute("logindtm", ldtm);
			
			typ=rs.getString("usertype");

			
			
			if(typ.equals("admin"))
				response.sendRedirect("admin.jsp");
			else
			{
			//if(rs1.next())
			status=rs.getInt("status");
			usernm=rs.getString("usernm");
			ses.setAttribute("usernm", usernm);
			if(typ.equals("member") && status==1)
			response.sendRedirect("members.jsp");
			
			if(typ.equals("member") && status==0)
			response.sendRedirect("deactivatedlogin.jsp");
			}
		}
		else
			response.sendRedirect("Failure.jsp");
		
		con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}

}
