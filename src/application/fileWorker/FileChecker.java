package application.fileWorker;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.*;

public abstract class FileChecker {
	public boolean checkFolderExists()
	{
		Path p=null;
		if(System.getProperty("os.name").contains("Windows"))
		{
			try
			{
				p=Paths.get("\\tmp");
			}
			catch(InvalidPathException e)
			{
				System.err.println("something went wrong linux/bsd path search was unable to get path");
				System.exit(2);
			}
			if(Files.exists(p, LinkOption.NOFOLLOW_LINKS))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			try
			{
				p=Paths.get("/tmp");
			}
			catch(InvalidPathException e)
			{
				System.err.println("something went wrong linux/bsd path search was unable to get path");
				System.exit(2);
			}
			if(Files.exists(p, LinkOption.NOFOLLOW_LINKS))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	public void createNonExistentTmpFolder()//cisto len tmp folder nic ine
	{
		File f;
		f=new File("./tmp");
		f.mkdirs();
	}
	public boolean checkFileExists(String path)//path must be formatted accordingly to each os
	{
		try
		{
			FileInputStream f=new FileInputStream(path);
			f.close();
			return true;
		}
		catch(FileNotFoundException fnfe)
		{
			System.out.println(fnfe + " error file not found");
			return false;
		}
		catch(Exception e)
		{
			System.err.println("looks like something went seriously wrong");
			System.err.println("possible io exception on closing file input stream");
			return false;
		}
	}
}
