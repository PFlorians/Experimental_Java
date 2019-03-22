package application.view;
import java.io.Serializable;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import application.error.*;
import application.Catalyst;
import dbconnect.LinksManager;
import dbconnect.TestConnection;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import tableobjects.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.stage.*;
/*
 * descr textarea nevypise nic po  funkcii new
 */
public class Win1Controller implements Initializable, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@FXML private BorderPane bp;
	@FXML private ComboBox<String> actionCombo; //action combo box
	@FXML private Button sub1;
	@FXML private TextField url;
	@FXML private TextArea descr;
	@FXML private TextField address;//adresa v textovom poli
	@FXML private TableColumn<Url, String> url_col;
	@FXML private TextField zaner; //zaner zadany v NewLink
	@FXML private TextField editUrl;
	@FXML private TextField editGenre;
	@FXML private TextArea editDescr;
	
	private TableController tc;
	private TableView<Url> mainTable;
	private Stage stage;
	private Parent root;
	private Catalyst c;
	//potetntial login data
	private String user, pw;
	private boolean select=false;
	@Override
	public void initialize(URL location, ResourceBundle reources)
	{	
		this.descr.setText("");
	}
	public void newSubDone()//listener pre submit okno
	{	
		//umysel, je select("") ma bezat az neskor, lebo na aplikacnom vlakne
		//bezia veci tykajuce sa comboBox - listener a tie musia prebehnut prve
		//chyba 1
		Timer t=new Timer();
		TimerTask delayed=new TimerTask()
		{
			public void run()
			{
				new Thread(){
					public void run()
					{
						Platform.runLater(new Runnable(){
							@Override
							public void run()
							{
								actionCombo.getSelectionModel().select("");
								tc.updateTable();
								c.reload();
							}
						});
					}
				}.start();
			}
		};
		t.schedule(delayed,  1000);//1 sekunda*/
		///////////////////////////////////////////
		//mozno by sa ten segment dal nejako optimalizovat?
		///////////////////////////////////////////
		
		if((this.url.getText()==null || this.url.getText().trim().equals("")) 
				|| (this.descr.getText()==null || this.descr.getText().trim().equals(""))
				|| (this.zaner.getText()==null || this.zaner.getText().trim().equals("")))
		{//chybovy stav
			try
			{
				Stage secondaryStage=new Stage();
				ErrorDialog error=new ErrorDialog(1);
				error.setStage(secondaryStage);
				error.init();
			}
			catch(Exception ex)
			{
				System.out.println("emptiness test exception " +ex);
				ex.printStackTrace();
			}
		}
		else
		{//vkladanie dat
			try
			{
				LinksManager lm=new LinksManager(this.tc.getConnection());
				lm.insert(this.url.getText().trim(), this.zaner.getText().trim(), this.descr.getText().trim());
			}
			catch(Exception e)
			{
				System.out.println("error 1 onSubmit" + e);
				e.printStackTrace();
			}
			try
			{//zavretie okna update tabulky
				//tc.updateTable();//prevancia
				//this.stage=(Stage)this.sub1.getScene().getWindow();
				this.stage.close();
			}
			catch(Exception e1)
			{
				System.out.println("chyba po databaze " + e1);
				e1.printStackTrace();
			}
		}
	}
	public void editAction()//rozdiel je v update miesto insert inak to iste
	{
		Timer t=new Timer();
		TimerTask delayed=new TimerTask()
		{
			public void run()
			{
				new Thread(){
					public void run()
					{
						Platform.runLater(new Runnable(){
							@Override
							public void run()
							{
								actionCombo.getSelectionModel().select("");
								tc.updateTable();
								c.reload();
							}
						});
					}
				}.start();
			}
		};
		t.schedule(delayed,  1000);//1 sekunda*/
		
		if((this.editUrl.getText()==null || this.editUrl.getText().trim().equals("")) 
				|| (this.editDescr.getText()==null || this.editDescr.getText().trim().equals(""))
				|| (this.editGenre.getText()==null || this.editGenre.getText().trim().equals("")))
		{//chybovy stav
			try
			{
				Stage secondaryStage=new Stage();
				ErrorDialog error=new ErrorDialog(1);
				error.setStage(secondaryStage);
				error.init();
			}
			catch(Exception ex)
			{
				System.out.println("emptiness test exception " +ex);
				ex.printStackTrace();
			}
		}
		else
		{
			try
			{
				LinksManager lm=new LinksManager(this.tc.getConnection());
				lm.update(this.address.getText().trim(),
						this.editUrl.getText().trim(), 
						this.editGenre.getText().trim(), 
						this.editDescr.getText().trim());
			}
			catch(Exception e)
			{
				System.out.println("error 1 onSubmit" + e);
				e.printStackTrace();
			}
			try
			{//zavretie okna update tabulky
				//tc.updateTable();//prevancia
				//this.stage=(Stage)this.sub1.getScene().getWindow();
				this.stage.close();
			}
			catch(Exception e1)
			{
				System.out.println("chyba po databaze " + e1);
				e1.printStackTrace();
			}
		}
	}
	private void delWait()//napravi combobox po delete operacii
	{
		Timer t=new Timer();
		TimerTask delayed=new TimerTask()
		{
			public void run()
			{
				new Thread(){
					public void run()
					{
						Platform.runLater(new Runnable(){
							@Override
							public void run()
							{
								actionCombo.getSelectionModel().select("");
								c.reload();
							}
						});
					}
				}.start();
			}
		};
		t.schedule(delayed,  1000);//1 sekunda*/
	}
	public void activate(String user, String pw, TableController tc, Catalyst c)
	{//spustacia metoda
		//actionCombo.getSelectionModel().selectedItemProperty().addListener(new ComboAction());
		System.out.println("descr start: " + descr.getText());
		actionCombo.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			
			public void handle(ActionEvent e)
			{
				
				FXMLLoader nl=null;
				BorderPane bp=null;
				TableController t=tc;
				if(e.getSource() instanceof ComboBox)
				{
					ComboBox<String> tst=(ComboBox<String>)e.getSource();
					System.out.println(tst.getSelectionModel().getSelectedItem());
					if(tst.getSelectionModel().getSelectedItem().equals("New"))
					{
						try
						{
							stage = new Stage();
							nl = new FXMLLoader(Win1Controller.class.getResource("actions/NewLink.fxml"));
							nl.setControllerFactory(new Callback<Class<?>, Object>(){
								@Override
								public Object call(Class<?> type) {
									return getMe();
								}
							});
							nl.setLocation(Win1Controller.class.getResource("actions/NewLink.fxml"));
							bp=nl.load();
							
							stage.setScene(new Scene(bp));
							stage.setTitle("New Link");
							stage.initModality(Modality.APPLICATION_MODAL);
							stage.showAndWait();
						}
						catch(Exception ex)
						{
							System.out.println(bp + " " + nl);
							System.out.println(ex + " " + getClass().getResource("actions/NewLink.fxml"));
							ex.printStackTrace();
							//System.exit(1);
						}
						//tc.updateTable();
					}
					else if(tst.getSelectionModel().getSelectedItem().equals("Edit"))
					{
						if(select)
						{
							ObservableList<Url> o=new LinksManager(tc.getConnection()).getEditData(address.getText().trim());
							if(o.size()==1)
							{	
								try
								{
									stage = new Stage();
									nl = new FXMLLoader(Win1Controller.class.getResource("actions/EditLink.fxml"));
									nl.setControllerFactory(new Callback<Class<?>, Object>(){
										@Override
										public Object call(Class<?> type) {
											return getMe();
										}
									});
									nl.setLocation(Win1Controller.class.getResource("actions/EditLink.fxml"));
									bp=nl.load();
									
									stage.setScene(new Scene(bp));
									stage.setTitle("Edit Link");
									stage.initModality(Modality.APPLICATION_MODAL);
									editUrl.setText(address.getText().trim());
									editGenre.setText(o.get(0).getGenre().trim());
									editDescr.setText(o.get(0).getDescr().trim());
									stage.showAndWait();
									
								}
								catch(Exception ex)
								{
									System.out.println(bp + " " + nl);
									System.out.println(ex + " " + getClass().getResource("actions/EditLink.fxml"));
									ex.printStackTrace();
									//System.exit(1);
								}
							}
							select=false;
						}
					}
					else if(tst.getSelectionModel().getSelectedItem().equals("Delete"))
					{
						if(select)
						{
							tc.invokeDelete(address.getText().trim());
							select=false;
							tc.updateTable();
							address.setText("");
							descr.setText("");
							delWait();
						}
					}
				}
				else
				{
					System.out.println(e.getSource());
				}
			}
		});
		
		this.user=user;
		this.pw=pw;
		this.tc=tc;
		this.c=c;
	}
	public BorderPane getBp()
	{
		return this.bp;
	}
	public void setCatalyst(Catalyst c)
	{
		this.c=c;
	}
	public ComboBox<String> getAC()
	{
		return this.actionCombo;
	}
	public Win1Controller getMe()
	{
		return this;
	}
	public TextField getAddressField()
	{
		return this.address;
	}
	protected void setAddressFieldValue(String value)
	{
		this.address.setText(value);
	}
	protected void setSelected(boolean selected)
	{
		this.select=selected;
	}
	protected void setDescription(String des)//nezobrazi nic
	{
		this.descr.setEditable(true);
		System.out.println(this.descr.isDisable() + " dsbld " + this.descr.isDisabled()
		+ " " +this.descr.isVisible());
		this.descr.setText(des);
		this.descr.setEditable(false);
	}
	//classes
	class cAction implements EventHandler<ActionEvent>
	{

		@Override
		public void handle(ActionEvent e) {
			System.out.println("handled");
			stage.close();
			
		}
		
	}
	class OnCloseAction implements EventHandler<WindowEvent>
	{
		@Override
		public void handle(WindowEvent e)
		{
			TestConnection t=new TestConnection();
			try
			{
				t.setUser(user);
				t.setPw(pw);
				c.ignite(t.getValidConnection(), user, pw);
			}
			catch(Exception ex)
			{
				System.out.println("chyba pri close " + ex);
				ex.printStackTrace();
			}
		}
	}
}
