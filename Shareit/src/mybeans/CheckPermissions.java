package mybeans;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CheckPermissions {
	private List<Object> lst;
	String uid;


	public void setUid(String uid) {
		this.uid = uid;
	}
	

	public List<Object> ckPermission()
	{
		Connection con;
		PreparedStatement pst1,pst2,pst3;
		ResultSet rs1,rs2,rs3;
		String gp;
		
		try
		{
			ViewDoc vd;
			DBConnector dbc=new DBConnector();
			con=dbc.getDbconnection();
			pst1=con.prepareStatement("select * from permissionslog where givep=?;");
			pst1.setString(1, uid);
			rs1=pst1.executeQuery();
			
			lst=new ArrayList<Object>();
			while(rs1.next())
			{
				pst2=con.prepareStatement("select members.usernm,members.userid,uploads.fname,uploads.rfname,uploads.udate,uploads.utime,uploads.ftype,uploads.descpt from members inner join uploads on members.userid=uploads.userid where uploads.userid=? and uploads.rfname=?;");
				pst2.setString(1,rs1.getString("userid"));
				pst2.setString(2,rs1.getString("rfname"));
				rs2=pst2.executeQuery();
				
				rs2.next();
				
				vd=new ViewDoc();
				
				vd.setFnm(rs2.getString("fname"));
				vd.setRfnm(rs2.getString("rfname"));
				vd.setUdt(rs2.getString("udate"));
				vd.setUsid(rs2.getString("userid"));
				vd.setUsnm(rs2.getString("usernm"));
				vd.setUtm(rs2.getString("utime"));
				vd.setFtyp(rs2.getString("ftype"));
				vd.setDesc(rs2.getString("descpt"));
				
				lst.add(vd);
			}
			
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return lst;
	}
	
	public List<Object> ckPermissionSelf()
	{
		Connection con;
		PreparedStatement pst1,pst2,pst3;
		ResultSet rs1,rs2,rs3;
		String gp;
		
		try
		{
			ViewDoc vd;
			DBConnector dbc=new DBConnector();
			con=dbc.getDbconnection();
			pst1=con.prepareStatement("select * from permissionslog where givep=?;");
			pst1.setString(1, uid);
			rs1=pst1.executeQuery();
			
			lst=new ArrayList<Object>();
			pst3=con.prepareStatement("select members.usernm,members.userid,uploads.fname,uploads.rfname,uploads.udate,uploads.utime,uploads.ftype,uploads.descpt from members inner join uploads on members.userid=uploads.userid where uploads.userid=?;");
			pst3.setString(1,uid);
			rs3=pst3.executeQuery();
			while(rs3.next())
			{
				vd=new ViewDoc();
				
				vd.setFnm(rs3.getString("fname"));
				vd.setRfnm(rs3.getString("rfname"));
				vd.setUdt(rs3.getString("udate"));
				vd.setUsid(rs3.getString("userid"));
				vd.setUsnm(rs3.getString("usernm"));
				vd.setUtm(rs3.getString("utime"));
				vd.setFtyp(rs3.getString("ftype"));
				vd.setDesc(rs3.getString("descpt"));
				
				lst.add(vd);
			}
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return lst;
	}
}


