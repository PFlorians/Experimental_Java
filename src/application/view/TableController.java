package application.view;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.net.*;
import java.sql.*;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import tableobjects.*;
import dbconnect.*;
//pokracovat tu: 		http://stackoverflow.com/questions/26555828/how-to-populate-tableview-dynamically-with-fxml-and-javafx
//http://stackoverflow.com/questions/14370183/passing-parameters-to-a-controller-when-loading-an-fxml
public class TableController implements Initializable{
	@FXML private TableView<Url> mainTable;
	@FXML private TableColumn<Url, String> url_col;
	@FXML private TableColumn<Url, String> genre;
	private Win1Controller w1c;
	private Connection c;//toto musi prist cez setter z Catalyst.ignite()
	
	@Override
	public void initialize(URL location, ResourceBundle reources)
	{
		System.out.println("initialized table");
	}
	public void updateTable()
	{
		try
		{
			this.mainTable.getSelectionModel().selectedItemProperty().addListener(new UrlChangedEvent());
			this.url_col.setCellValueFactory(new PropertyValueFactory<Url, String>("url"));
			this.genre.setCellValueFactory(new PropertyValueFactory<Url, String>("genre"));
			this.mainTable.getItems().setAll(new LinksManager(this.c).getAllLinks());
		}
		catch(Exception e)
		{
			System.out.println("table update exception " + e);
			e.printStackTrace();
		}
	}
	
	public void setConnection(Connection c)
	{
		this.c=c;
	}
	public Connection getConnection()
	{
		return this.c;
	}
	public TableView<Url> getTable()
	{
		return this.mainTable;
	}
	public void setW1c(Win1Controller w1c)
	{
		this.w1c=w1c;
	}
	protected void invokeDelete(String url)
	{
		new LinksManager(c).delete(url);
	}
	class UrlChangedEvent implements ChangeListener<Url>
	{
		@Override
		public void changed(ObservableValue<? extends Url> obs, Url oldVal, Url newVal) 
		{	
			try
			{
				if(oldVal!=null && newVal!=null)
				{
					System.out.println("table changed has been called " + newVal.getUrl() + " oldval " + oldVal.getUrl());
				}
				else
				{
					throw new Exception();
				}
			}
			catch(Exception e)
			{
				try
				{
					if(oldVal!=null){System.out.println("Old val only " + oldVal.getUrl());}
					else{throw new Exception();}
				}catch(Exception e2)
				{
					try
					{
						if(newVal!=null){System.out.println("new val only " + newVal.getUrl());}
						else{throw new Exception();}
					}catch(Exception e3)
					{
						System.out.println("all excepted ");
					}
				}
			}
			
			try
			{
				if(newVal!=null)
				{
					System.out.println(obs.getValue().getUrl() + " " +newVal.getUrl());
					w1c.setAddressFieldValue(newVal.getUrl());
					w1c.setDescription(new LinksManager(c).getDescription(newVal.getUrl()));
					System.out.println("descr " + new LinksManager(c).getDescription(newVal.getUrl()));
					w1c.setSelected(true);
				}
				else
				{
					w1c.setDescription("");
				}
			}
			catch(Exception e3)
			{
				System.out.println("ex3 " +e3 + " " + newVal + " " + oldVal);
				e3.printStackTrace();
				System.exit(0);
			}
		}	
	}
}
