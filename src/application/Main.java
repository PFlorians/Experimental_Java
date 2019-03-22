package application;
	
import application.systemcheck.ProgramDependentSysCheck;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import application.systemcheck.*;

public class Main{
	public static void main(String[] args) {
		OsInfo o = new OsInfo();
		
		o.getBasics();
		System.exit(0);
		Catalyst c=new Catalyst();
		try
		{
			System.out.println(new ProgramDependentSysCheck().serverMysqlRunning());
			javafx.application.Application.launch(Catalyst.class);
		}
		catch(Exception e)
		{
			System.out.println("main filter " + e);
			e.printStackTrace();
			System.exit(0);
		}
	}
}
