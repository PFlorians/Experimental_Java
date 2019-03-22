package application;

import java.sql.*;
import java.util.ArrayList;

import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import application.graphics.GraphicExperimenter;
import application.systemcheck.ProgramDependentSysCheck;
import application.view.*;
/*
 * len tato a trieda main nemusia implementovat Popup
 * lebo toto je primarne okno
 */

public class Catalyst extends Application{
	private Stage primaryStage;
	private Connection c;
	private TabPane root;
	private TableController tc;
	private String user, pw;
	private CoreData cd;
	
	public Catalyst()
	{
		this.cd=new CoreData();
	}
	@Override
	public void start(Stage primaryStage) {
		try 
		{
			
			this.primaryStage=primaryStage;
			this.root = new TabPane();
			//graphic();
			
			//styles
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			ProgramDependentSysCheck pdsc=new ProgramDependentSysCheck();
			if(pdsc.serverMysqlRunning()==false)//&& pdsc.serverPostgresRunning()==false)
			{
				config();
			}
			else
			{
				spark();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void graphic() throws Exception
	{
		FXMLLoader l = new FXMLLoader(getClass().getResource("view/3Dgraphics.fxml"));
		l.setLocation(Main.class.getResource("view/3Dgraphics.fxml"));
		BorderPane bp = new BorderPane();
//		GraphicExperimenter ge = new GraphicExperimenter();
		bp=l.load();
		//Scene s = new Scene(ge.getObject());
		Scene s = new  Scene(bp);
		//s.setFill(Color.BLACK);
		//s.setCamera(new PerspectiveCamera());
		primaryStage.setScene(s);
		primaryStage.setTitle("Graphics");
		primaryStage.show();
	}
	public void ignite(Connection c, String user, String pw) throws Exception//nacitanie fxml a zobrazenie, spojenie fxml a java
	{
		System.out.println("Igniting " + c);
		this.c=c;
///////////////////////////////////////////////////////
//table controller
FXMLLoader tabloader=new FXMLLoader(getClass().getResource("view/Table.fxml"));
tabloader.setLocation(getClass().getResource("view/Table.fxml"));
VBox v=tabloader.load();
this.tc=tabloader.getController();
this.tc.setConnection(this.c);
System.out.println("after controller table");
////////////////////////////////////////////////////////
		FXMLLoader loader=new FXMLLoader(getClass().getResource("view/win1.fxml"));
		loader.setLocation(Main.class.getResource("view/Win1.fxml"));//vsimnut si ze view je podbalik application
		this.root=loader.load();
		Win1Controller w1c;
		w1c=loader.getController();
		//////////////////////////////////////////////
		//table setting
		this.tc.setW1c(w1c);//must be called before updateTable
		this.tc.updateTable();
		System.out.println("after update table");
		/////////////////////////////////////////////
		try
		{
		w1c.getBp().setLeft(v);//pokus o nastavenie tabulky
		System.out.println("preparing to show " + primaryStage);
		Scene scene = new Scene(this.root);

		primaryStage.setScene(scene);
		w1c.activate(this.user, this.pw, this.tc, this);
		}
		catch(Exception e)
		{
			System.out.println("catalyst exception reporting " + e);
			e.printStackTrace();
		}
	}
	public void reload()
	{
		Win1Controller w1c=null;
		VBox v=null;
		try
		{
			///////////////////////////////////////////////////////
			//table controller
			FXMLLoader tabloader=new FXMLLoader(getClass().getResource("view/Table.fxml"));
			tabloader.setLocation(getClass().getResource("view/Table.fxml"));
			v=tabloader.load();
			this.tc=tabloader.getController();
			this.tc.setConnection(this.c);
			System.out.println("after controller table");
			////////////////////////////////////////////////////////
			FXMLLoader loader=new FXMLLoader(getClass().getResource("view/win1.fxml"));
			loader.setLocation(Main.class.getResource("view/Win1.fxml"));//vsimnut si ze view je podbalik application
			this.root=loader.load();
			w1c=loader.getController();
			//////////////////////////////////////////////
			//table setting
			this.tc.setW1c(w1c);//must be called before updateTable
			this.tc.updateTable();
			System.out.println("after update table");
			/////////////////////////////////////////////
		}
		catch(Exception e)
		{
			System.out.println("reload exception " + e);
			e.printStackTrace();
		}
		try
		{
			w1c.getBp().setLeft(v);//pokus o nastavenie tabulky
			System.out.println("preparing to show " + primaryStage);
			Scene scene = new Scene(this.root);
			
			primaryStage.setScene(scene);
			w1c.activate(this.user, this.pw, this.tc, this);
		}
		catch(Exception e)
		{
			System.out.println("catalyst exception reporting " + e);
			e.printStackTrace();
		}
	}
	public void upadteTable()
	{
		try
		{
			FXMLLoader tabloader=new FXMLLoader(getClass().getResource("view/Table.fxml"));
			tabloader.setLocation(getClass().getResource("view/Table.fxml"));
			tabloader.load();
			this.tc=tabloader.getController();
			this.tc.setConnection(this.c);
			this.tc.updateTable();
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	public void spark() throws Exception//start this from main
	{

		FXMLLoader loader=new FXMLLoader(getClass().getResource("view/Login.fxml"));
		BorderPane bp=new BorderPane();
		loader.setLocation(getClass().getResource("view/Login.fxml"));//vsimnut si ze view je podbalik application
		bp=loader.load();
		LoginController ll=loader.getController();//vziat tuto instanciu
		ll.setCatalyst(this);//nastavit ju loaderu, ktory spusti ignite(), cim vlastne primaryStage uz existuje
		
		Scene scene = new Scene(bp);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public void config() throws Exception
	{
		FXMLLoader l=new FXMLLoader(getClass().getResource("view/Config.fxml"));
		BorderPane bp=new BorderPane();
		l.setLocation(getClass().getResource("view/Config.fxml"));
		bp=l.load();
		ConfigController c=l.getController();
		c.setCatalyst(this);
		Scene s=new Scene(bp);
		//init stylesheet
		s.getStylesheets().add(getClass().getResource("style/config.css").toExternalForm());
		primaryStage.setTitle("Local server config");
		primaryStage.setScene(s);
		primaryStage.show();
		
	}
	public void refreshConfig() throws Exception //call this if significant changes in window
	{
		FXMLLoader l=new FXMLLoader(getClass().getResource("view/Config.fxml"));
		BorderPane bp=new BorderPane();
		l.setLocation(getClass().getResource("view/Config.fxml"));
		bp=l.load();
		Scene s=new Scene(bp);
		primaryStage.setTitle("Local server config");
		primaryStage.setScene(s);
		primaryStage.show();
	}
	public boolean errorMsg(int type)
	{
		try
		{
			Stage secondaryStage=new Stage();
			Error e=new Error(type);
			e.setStage(secondaryStage);
			e.init();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			ex.printStackTrace();
			System.exit(1);
		}
		return false;
	}
	public void setCoreData()
	{ 
		
	}
}
