package application.systemcheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.*;
import java.lang.*;

public class ProgramDependentSysCheck implements Serializable{

	/**
	 * serializable version for java beans standard attempt
	 */
	private static final long serialVersionUID = 1L;
	
	public String getOsType()
	{
		return System.getProperty("os.name");
	}
	public boolean webServerRunning()//ci bezi server ako apache(vseobecne netreba
	{
		if(this.getOsType().equals("Windows") || this.getOsType().contains("Windows"))
		{
			try
			{
				Process p=Runtime.getRuntime().exec("tasklist /fi \"status eq running\"");
				BufferedReader b=new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line;
				ArrayList<String> a=new ArrayList<String>(20);
				int i;
				while((line=b.readLine())!=null)
				{
					a.add(line);
				}
				for(i=0;i<a.size();i++)
				{
					if(a.get(i).contains("httpd"))
					{
						return true;
					}
				}
				//druhy prikaz kontrola stavu unknown
				p=Runtime.getRuntime().exec("tasklist /fi \"status eq unknown\"");
				b=new BufferedReader(new InputStreamReader(p.getInputStream()));
				a=new ArrayList<String>(20);
				while((line=b.readLine())!=null)
				{
					a.add(line);
				}
				for(i=0;i<a.size();i++)
				{
					if(a.get(i).contains("httpd"))
					{
						return true;
					}
				}
			}
			catch(IOException e)
			{
				System.out.println("windows server check exception " + e);
				e.printStackTrace();
				System.exit(0);
			}
			
		}
		else if(this.getOsType().equals("Linux") || this.getOsType().contains("Linux"))
		{
			try
			{
				Process p=Runtime.getRuntime().exec("ps -A");
				BufferedReader b=new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line;
				ArrayList<String> a=new ArrayList<String>(20);
				int i;
				while((line=b.readLine())!=null)
				{
					a.add(line);
				}
				for(i=0;i<a.size();i++)
				{
					if(a.get(i).contains("apache"))
					{
						return true;
					}
				}
			}
			catch(Exception e)
			{
				System.out.println("linux server check exception " + e);
				
			}
		}
		return false;
	}
	public boolean serverMysqlRunning()//ci bezi mysql
	{
		if(this.getOsType().equals("Windows") || this.getOsType().contains("Windows"))
		{
			try
			{
				Process p=Runtime.getRuntime().exec("tasklist /fi \"status eq running\"");
				BufferedReader b=new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line;
				ArrayList<String> a=new ArrayList<String>(20);
				int i;
				while((line=b.readLine())!=null)
				{
					a.add(line);
				}
				for(i=0;i<a.size();i++)
				{
					if(a.get(i).contains("mysql"))
					{
						return true;
					}
				}
				//druhy prikaz kontrola stavu unknown
				p=Runtime.getRuntime().exec("tasklist /fi \"status eq unknown\"");
				b=new BufferedReader(new InputStreamReader(p.getInputStream()));
				a=new ArrayList<String>(20);
				while((line=b.readLine())!=null)
				{
					a.add(line);
				}
				for(i=0;i<a.size();i++)
				{
					if(a.get(i).contains("mysql"))
					{
						return true;
					}
				}
			}
			catch(IOException e)
			{
				System.out.println("windows db check exception " + e);
				e.printStackTrace();
				System.exit(0);
			}
			
		}
		else if(this.getOsType().equals("Linux") || this.getOsType().contains("Linux"))
		{
			try
			{
				Process p=Runtime.getRuntime().exec("ps -A");
				BufferedReader b=new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line;
				ArrayList<String> a=new ArrayList<String>(20);
				int i;
				while((line=b.readLine())!=null)
				{
					a.add(line);
				}
				for(i=0;i<a.size();i++)
				{
					if(a.get(i).contains("mysql"))
					{
						return true;
					}
				}
			}
			catch(Exception e)
			{
				System.out.println("linux db check exception " + e);
				
			}
		}
		return false;
	}
	public boolean serverPostgresRunning()
	{
		if(this.getOsType().equals("Windows") || this.getOsType().contains("Windows"))
		{
			try
			{
				Process p=Runtime.getRuntime().exec("tasklist /fi \"status eq running\"");
				BufferedReader b=new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line;
				ArrayList<String> a=new ArrayList<String>(20);
				int i;
				while((line=b.readLine())!=null)
				{
					a.add(line);
				}
				for(i=0;i<a.size();i++)
				{
					if(a.get(i).contains("postgres"))
					{
						return true;
					}
				}
				//druhy prikaz kontrola stavu unknown
				p=Runtime.getRuntime().exec("tasklist /fi \"status eq unknown\"");
				b=new BufferedReader(new InputStreamReader(p.getInputStream()));
				a=new ArrayList<String>(20);
				while((line=b.readLine())!=null)
				{
					a.add(line);
				}
				for(i=0;i<a.size();i++)
				{
					if(a.get(i).contains("postgres"))
					{
						return true;
					}
				}
			}
			catch(IOException e)
			{
				System.out.println("windows db check exception " + e);
				e.printStackTrace();
				System.exit(0);
			}
			
		}
		else if(this.getOsType().equals("Linux") || this.getOsType().contains("Linux"))
		{
			try
			{
				Process p=Runtime.getRuntime().exec("ps -A");
				BufferedReader b=new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line;
				ArrayList<String> a=new ArrayList<String>(20);
				int i;
				while((line=b.readLine())!=null)
				{
					a.add(line);
				}
				for(i=0;i<a.size();i++)
				{
					if(a.get(i).contains("postgres"))
					{
						return true;
					}
				}
			}
			catch(Exception e)
			{
				System.out.println("linux db check exception " + e);
				
			}
		}
		return false;
	}
	
}
