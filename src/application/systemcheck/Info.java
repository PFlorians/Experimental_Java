package application.systemcheck;

public abstract class Info {
	public String getBasics()
	{
		String s = new String("Java version: " + System.getProperty("java.version") + "\n" +
				"java home: " + System.getProperty("java.home") + "\n" +
				"java vendor: " + System.getProperty("java.vendor") + "\n" + 
				"java vendor url: " + System.getProperty("java.vendor.url") + "\n" +
				"java path: " + System.getProperty("java.class.path"));
		return s;
		
	}
}
