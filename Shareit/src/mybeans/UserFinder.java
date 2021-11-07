package mybeans;
import java.sql.*;

public class UserFinder 
{
	private String userid;
	private String status;
	
	public UserFinder() 
	{
		userid="";
		status="";
	}

	public String getStatus() {
		return status;
	}

	public void setUserid(String userid) {
		this.userid = userid;
		findUser();
	}
	
	private void findUser()
	{
		Connection con;
		PreparedStatement pst;
		ResultSet rs;
		try
		{
			DBConnector dbc=new DBConnector();
			con=dbc.getDbconnection();
			pst=con.prepareStatement("select * from users where userid=?;");
			pst.setString(1,userid);
			rs=pst.executeQuery();
			
			if(rs.next())
			{
				status="failed";
			}
			else
			{
				status="success";
			}
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	
	
	

}
