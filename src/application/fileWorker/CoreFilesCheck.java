package application.fileWorker;
import java.io.*;
import application.*;

public class CoreFilesCheck extends FileChecker{
	public boolean coreDataPresent()//zistujem ci su pritomne subory v ktorych su zaznamenane conf.data
	{
		try
		{
			//CoreData cd;
			FileInputStream fis=new FileInputStream("/tmp/locations.ser");
			ObjectInputStream ois=new ObjectInputStream(fis);
			ois.readObject();
			ois.close();
			fis.close();
		}
		catch(ClassNotFoundException e)
		{
			System.out.println(" deserialisation Class not found not existent " + e);
			return false;
		}
		catch(Exception e)
		{
			System.out.println("Chyba volaco s Core files check a nieje to neexistujuci file.");
		}
		return true;
	}
	public boolean checkDirs()
	{
		
		return false;
	}
}
