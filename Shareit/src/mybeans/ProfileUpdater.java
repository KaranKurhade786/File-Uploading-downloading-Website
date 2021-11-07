package mybeans;
import java.sql.*;
public class ProfileUpdater {
	
	private String uid,usernm,email,mob,status;
    int age;
    
    public ProfileUpdater() {
    	status="";
    }
  
    public String getStatus() {
		return status;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}


	public void setUsernm(String usernm) {
		this.usernm = usernm;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setMob(String mob) {
		this.mob = mob;
	}


	public void setAge(int age) {
		this.age = age;
		onupdate();
	}


	public void onupdate()
    {

    	Connection con;
    	PreparedStatement pst;
    	int x;

    	try
    	{
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shareit?user=root&password=volkswagen");
    		
    		pst=con.prepareStatement("update members set usernm=?,age=?,mobile=?,emailid=? where userid=?;");
    		pst.setString(1,usernm);
    		pst.setInt(2,age);
    		pst.setString(3,mob);
    		pst.setString(4,email);
    		pst.setString(5,uid);
    		x=pst.executeUpdate();
    		if(x>0)
    		{
    			               status="Profile updated successfully";
    		}
    		else
    			 status="Profile update failed";
    		con.close();
    	}
    	catch(Exception e)
    	{
    		System.out.println(e);
    	}
    }
}
