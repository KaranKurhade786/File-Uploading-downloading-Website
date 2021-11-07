package mybeans;
import java.sql.*;

public class PasswordChanger 
{
	private String userid;
	private String curpass;
	private String newpass;
	private String status;
	
	public PasswordChanger()
	{
		status="Failed";
	}

	public String getStatus() {
		return status;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setCurpass(String curpass) {
		this.curpass = curpass;
	}

	public void setNewpass(String newpass) {
		this.newpass = newpass;
		changePassword();
	}
	
	private void changePassword()
	{
		Connection con;
		PreparedStatement pst;
		try
		{
			DBConnector dbc=new DBConnector();
			con=dbc.getDbconnection();	
			pst=con.prepareStatement("update users set pswd=? where userid=? and pswd=?;");
			pst.setString(1, newpass);
			pst.setString(2, userid);
			pst.setString(3, curpass);
			int cnt=pst.executeUpdate();
			
			con.close();
			
			if(cnt>0)
				status="password changed successfully";
			else
				status="sorry! authentication failed";
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	

}
