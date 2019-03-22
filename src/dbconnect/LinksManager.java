package dbconnect;
import java.sql.*;
import java.util.*;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tableobjects.*;

public class LinksManager {
	private Connection c;
	public LinksManager(Connection c)
	{
		this.c=c;
	}
	public void insert(String url, String genre, String descr)
	{
		Statement stmt;
		//security concern
		String sql="insert into links(url, genre, description) values(\'"+url+"\', \'" + genre +"\', " + "\'" + descr+"\');";
		try
		{
	    	 Class.forName("com.mysql.jdbc.Driver").newInstance();
	         stmt=c.createStatement();
	         c.setAutoCommit(false);
	         stmt.executeUpdate(sql);
	         c.commit();
		}
	    catch(Exception e)
	    {
	    	System.out.println("Insert exception: " + e);
	    	System.out.println(sql);
	    	e.printStackTrace();
	    	try
	    	{
	    		c.rollback();
	    	}
	    	catch(Exception ex)
	    	{
	    		System.out.println("Insert exception inner: " + e);
		    	e.printStackTrace();
	    	}
	    }
	}
	public void update(String oldUrl, String newUrl, String genre, String descr)
	{
		Statement stmt;
		//security concern
		String sql="update links set url=\'"+newUrl+"\', genre=\'"+genre+"\', description=\'"+descr+"\' "+
				   "where url=\'"+oldUrl+"\';";
		try
		{
	    	 Class.forName("com.mysql.jdbc.Driver").newInstance();
	         stmt=c.createStatement();
	         c.setAutoCommit(false);
	         stmt.executeUpdate(sql);
	         c.commit();
		}
	    catch(Exception e)
	    {
	    	System.out.println("Update exception: " + e);
	    	System.out.println(sql);
	    	e.printStackTrace();
	    	try
	    	{
	    		c.rollback();
	    	}
	    	catch(Exception ex)
	    	{
	    		System.out.println("Update exception inner: " + e);
		    	e.printStackTrace();
	    	}
	    }
	}
	public ObservableList<Url> getAllLinks()
	{
		ResultSet s;
		Statement stmt;
		String sql=new String("select url, genre from links;");//zmena na url+descr
		List<Url> l=new ArrayList<Url>();
		try 
	    {
			stmt=c.createStatement();
			s=stmt.executeQuery(sql);
			while(s.next())
			{
				l.add(new Url(new SimpleStringProperty(s.getString("url")), new SimpleStringProperty(s.getString("genre"))));
			}
	    }
	    catch (Exception e) 
	    {
	    	e.printStackTrace();
	        System.err.println("FATAL EXCEPTION:");
	        System.err.println(e.getClass().getName()+": "+e.getMessage());
	    }
		ObservableList<Url> o=FXCollections.observableList(l);
		return o;
	}
	public String getDescription(String url)//popis danej url
	{
		ResultSet s;
		Statement stmt;
		String sql=new String("select description from links where url = \'"+url+"\';");
		List<String> l=new ArrayList<String>();
		try
		{
			stmt=c.createStatement();
			s=stmt.executeQuery(sql);
			while(s.next())
			{
				l.add(new String(s.getString("description")));
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
			System.exit(2);
		}
		ObservableList<String> o=FXCollections.observableList(l);
		String txt=new String("");
		
		for(int i=0;i<o.size();i++)
		{
			txt=new String(txt + o.get(i));
		}
		return txt.trim().toString();
	}
	public ObservableList<Url> getEditData(String url)
	{
		ResultSet s;
		Statement stmt;
		String sql=new String("select url, genre, description from links where url = \'"+url+"\';");
		List<Url> l=new ArrayList<Url>();
		try 
	    {
			stmt=c.createStatement();
			s=stmt.executeQuery(sql);
			while(s.next())
			{
				l.add(new Url(new SimpleStringProperty(s.getString("url")), 
						new SimpleStringProperty(s.getString("genre")), 
						new SimpleStringProperty(s.getString("description"))));
			}
	    }
	    catch (Exception e) 
	    {
	    	e.printStackTrace();
	        System.err.println("FATAL EXCEPTION:");
	        System.err.println(e.getClass().getName()+": "+e.getMessage());
	    }
		ObservableList<Url> o=FXCollections.observableList(l);
		return o;
	}
	public void delete(String url)
	{
		Statement stmt;
		String sql=new String("delete from links where url=\'"+url+"\';");
		try
		{
			c.setAutoCommit(false);
			stmt=c.createStatement();
			stmt.executeUpdate(sql);
			c.commit();
		}
		catch(Exception e)
		{
			System.out.println("delte error: " + e);
			e.printStackTrace();
			try
			{
				c.rollback();
			}
			catch(Exception e1)
			{
				System.out.println("rollback exception " +e);
				e.printStackTrace();
				System.exit(1);
			}
			System.exit(2);
		}
	}
}
