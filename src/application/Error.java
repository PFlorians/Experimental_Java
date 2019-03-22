package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Error extends Application implements Popup{
	private String error;
	private Stage stage;
	private Error me;
	private Scene s;
	public Error(int type)
	{
		this.me=this;
		if(type==1)
		{
			this.error="Please Fill in the username";
		}
		else if(type==2)
		{
			this.error="Database connection problem!";
		}
		else if(type==3)
		{
			
		}
		else
		{
			this.error="Error";
		}
	}
	public void init()
	{
		try
		{
			Alert a=new Alert(AlertType.ERROR);
			a.setTitle("Error");
			a.setHeaderText(this.error);
			//a.setOnCloseRequest(new closeError());
			a.showAndWait();
		}
		catch(Exception e)
		{
			System.out.println("Singular menu exception " + e);
			e.printStackTrace();
		}
	}
	public void initDbErr()
	{
		try
		{
			Alert a=new Alert(AlertType.CONFIRMATION);
			a.setTitle("Database error");
			a.setHeaderText(this.error);
			a.setOnCloseRequest(new dbActionHandler());
			a.showAndWait();
		}
		catch(Exception e)
		{
			System.err.println("Error creating debug window " + e);
			e.printStackTrace();
			System.exit(1);
		}
	}
	public void init(String args[])
	{
		try
		{
			launch(args);
		}
		catch(Exception e)
		{
			System.out.println("New Client exception " + e);
			e.printStackTrace();
		}
	}
	public void start(Stage stage)//nepouziva sa za normalnych okolnosti
	{
		this.stage=stage;
		System.out.println("super stage: " + this.stage);
		createDialog();
	}
	private Scene createDialog()//nepouziva sa
	{
		Scene s1;
		BorderPane bp=new BorderPane();
		
		this.stage.setTitle("ERROR");
		
		bp.setCenter(initData());
		
		s1=new Scene(bp, 200, 100);
		return s1;
	}
	private VBox initData()
	{
		VBox v=new VBox();
		Alert a=new Alert(AlertType.ERROR);
		a.setTitle("Error");
		a.setContentText(this.error);
		return v;
	}
	public void initStage()
	{
		this.stage=new Stage();
	}
	public Error getInstance()
	{
		return this.me;
	}
	public Stage getStage()
	{
		return this.stage;
	}
	public void setStage(Stage stage)
	{
		this.stage=stage;
	}
	public Scene getScene()
	{
		return this.s;
	}
	class closeError implements EventHandler<DialogEvent>
	{
		public void handle(DialogEvent e)
		{
			System.exit(0);
		}
	}
	class dbActionHandler implements EventHandler<DialogEvent>
	{
		public void handle(DialogEvent e)
		{
			System.out.println("Trigerred event.");
		}
	}
}
