package dbconnect;


import java.sql.DriverManager;
import java.sql.*;

public class TestConnection {
	private String user, pw;
	
	public boolean test(String user, String passwd) throws Exception
	{
		  Connection c = null;
	      //result data
		  System.out.println("testing");
	      try 
	      {
	         Class.forName("com.mysql.jdbc.Driver").newInstance();
	         c = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_data",
	            user, passwd);
	         c.close();
	         if(c!=null)
	         {
	        	 this.user=user;
	        	 this.pw=passwd;
	         }
	         return (c!=null)?true:false;
	      }
	      catch (Exception e) 
	      {
	         e.printStackTrace();
	         System.err.println("FATAL EXCEPTION CONNECTION FAILURE, DB NOT RUNNING:");
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         throw e;
	         //return false;
	      }
	}
	public Connection getValidConnection()
	{
		Connection c = null;
	      //result data
	      try 
	      {
	         Class.forName("com.mysql.jdbc.Driver").newInstance();
	         c = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_data",
	            this.user, this.pw);
	      }
	      catch (Exception e) 
	      {
	         e.printStackTrace();
	         System.err.println("FATAL EXCEPTION:");
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	      }
	      System.out.println("C: " + c);
	      return (c!=null)?c:null;
	}
	public void setUser(String user)
	{
		this.user=user;
	}
	public void setPw(String pw)
	{
		this.pw=pw;
	}
}
