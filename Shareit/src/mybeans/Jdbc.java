package mybeans;


import java.sql.*;

public class Jdbc {

	private String nm, ag, mo, em, id;

	public Jdbc() {
     nm="";
    ag="";
	mo="";
	em="";
	}

	public String getNm() {
		return nm;
	}

	public String getAg() {
		return ag;
	}

	public String getMo() {
		return mo;
	}

	public String getEm() {
		return em;
	}

	public void setId(String id) {
		this.id = id;
		onFunction();
	}

	public void onFunction() {
		Connection con;
		PreparedStatement pst;
		ResultSet rs;

		try {
			DBConnector dbc=new DBConnector();
			con=dbc.getDbconnection();
			pst = con.prepareStatement("select * from members where userid=?;");
			pst.setString(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				nm = rs.getString("usernm");
				ag = rs.getString("age");
				mo = rs.getString("mobile");
				em = rs.getString("emailid");
			}
		} 
		catch (Exception e) {
			System.out.println(e);
		}
	}

}
