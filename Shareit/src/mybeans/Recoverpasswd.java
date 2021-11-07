package mybeans;

import java.sql.*;


public class Recoverpasswd {
	private String id,sq,an,status;
	
public Recoverpasswd()
	{
		status="";
	}

public String getStatus() {
	return status;
}

public void setId(String id) {
	this.id = id;
}

public void setSq(String sq) {
	this.sq = sq;
}

public void setAn(String an) {
	this.an = an;
	onrecover();
}
public void onrecover()
{
	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	try
	{
		DBConnector dbc=new DBConnector();
		con=dbc.getDbconnection();
		pst=con.prepareStatement("select * from members where userid=? and secques=? and answer=?;");
		pst.setString(1,id);
		pst.setString(2,sq);
		pst.setString(3,an);
		rs=pst.executeQuery();
		if(rs.next())
		{
			
			pst=con.prepareStatement("update users set pswd='123123123123' where userid=?;");
			pst.setString(1,id);
			pst.executeUpdate();
           status="your new pass =123123123123";
		}
		else
			 status="password changed failed";
		con.close();
	}
	catch(Exception e)
	{
		System.out.println(e);
	}

}

}
