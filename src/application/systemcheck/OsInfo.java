package application.systemcheck;

import java.io.File;

public class OsInfo extends Info{
	public String getBasics()
	{
		String s = new String("############ OS Info ############" + "\n" + 
							"os name: " + System.getProperty("os.name") + "\n" +
							"os architecture: " + System.getProperty("os.arch") + "\n" 
							+ "os version: " + System.getProperty("os.version") + "\n" + 
							"############ User information ############" + "\n" + 
							"username: " + System.getProperty("user.name") + "\n" +
							"home dir: " + System.getProperty("user.home") + "\n" + 
							"work directory: " + System.getProperty("user.dir") + "\n" +
							"############ Hardware ############" + "\n" +
							"procesory pre JVM: " + Runtime.getRuntime().availableProcessors() + "\n" +
							"celkova pamat pre JVM: " + Runtime.getRuntime().totalMemory() + "\n" +
							"volna pamat: " + Runtime.getRuntime().freeMemory() + "\n" + 
							"max dostupna pamat: " + Runtime.getRuntime().maxMemory());
		File roots[]=File.listRoots();
		System.out.println(s);
		for(File root : roots)
		{
			System.out.println("root abs.path " + root.getAbsolutePath());
			System.out.println("space available: " + (root.getFreeSpace() / 1000000000.0) + " GB");
			System.out.println("space total: " + (root.getTotalSpace()/1000000000.0) + " GB");
			System.out.println("used space: " + ((root.getTotalSpace() - root.getFreeSpace())/1000000000.0));
		}
		return s;
	}
}
