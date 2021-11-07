package mybeans;
import java.sql.*;

public class GName {

	
	public String getGName(String type)
	{
		Connection con;
		Statement st;
		PreparedStatement pst;
		ResultSet rs,rs1;
		String s="",z="";
		int p;
		boolean f;
		
		try {

			DBConnector db = new DBConnector();
			con=db.getDbconnection();
			st=con.createStatement();	
			rs=st.executeQuery("select count(*) from uploads;");
			rs.next();
			s=rs.getString("count(*)");
			System.out.println(s);			
			do {
				z=s+'.'+type;
				pst=con.prepareStatement("select * from uploads where fname=?");
				pst.setString(1, z);
				rs1=pst.executeQuery();
				System.out.println(s);	
				if(rs1.next())
				{
					p=Integer.parseInt(s);
					p++;
					s=Integer.toString(p);
					f=true;
				}
				else
					f=false;
			}while(f);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return s;
	}
	
	public boolean checkName(String uid,String fno)
	{
		Connection con;
		PreparedStatement pst;
		ResultSet rs;
		
		try {
			DBConnector db = new DBConnector();
			con=db.getDbconnection();
			pst=con.prepareStatement("select * from uploads where userid=? and rfname=?;");
			pst.setString(1, uid);
			pst.setString(2, fno);
			rs=pst.executeQuery();
			if(rs.next())
				return true;
			else
				return false;
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
	}
}
