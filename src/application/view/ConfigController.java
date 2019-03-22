package application.view;
import java.io.*;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.net.URL;
import java.util.ResourceBundle;

import application.*;
import application.Error;
import application.error.ErrorDialog;
import application.fileWorker.DirChooser;
import dbconnect.*;
public class ConfigController {
	@FXML private Button location1;
	@FXML private Button location2;
	@FXML private BorderPane bp;
	@FXML private TextField location1Text, location2Text;
	@FXML private Button sac;
	@FXML private Button astart, astop, dbstart, dbstop, msqlstart, msqlstop;
	@FXML private TextArea sysDetails;
	@FXML private TextField astartText, astopText, dbstartText, dbstopText, msqlstartText, msqlstopText;
	
	/*
	 *serialization variables local start here
	 */
	//scripts
	private String apacheStart, apacheStop, msqlStart, msqlStop;
	/*
	 * serialization vars local end here
	 */
	//misc vars
	private Catalyst c;
	private String path1, path2;
	@FXML private void openFileChooserLocation1()
	{
		FileChooser fileChooser = new FileChooser();
		 fileChooser.setTitle("Pick webserver location");
		 fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		 fileChooser.getExtensionFilters().addAll(
		         new ExtensionFilter("Text Files", "*.txt"),
		         new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
		         new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
		         new ExtensionFilter("All Files", "*.*"));
		 File selected=fileChooser.showOpenDialog(this.bp.getScene().getWindow());
		 if(selected!=null)
		 {
			 if(selected.isFile())
			 {
				 this.path1=selected.getPath();
				 this.location1Text.setText(this.path1);
				 if((this.path1.trim()!=null || !this.path1.trim().equals("")) 
						 && (this.path2.trim()!=null || !this.path2.trim().equals(""))) 
				 {
					 this.sac.setDisable(false);
				 }
			 }
			 else
			 {
				new ErrorDialog(2).init();
			 }
		 }
	}
	@FXML private void openFileChooserLocation2()
	{
		FileChooser fileChooser = new FileChooser();
		 fileChooser.setTitle("Pick webserver location");
		 fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		 fileChooser.getExtensionFilters().addAll(
		         new ExtensionFilter("Text Files", "*.txt"),
		         new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
		         new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
		         new ExtensionFilter("All Files", "*.*"));
		 File selected=fileChooser.showOpenDialog(this.bp.getScene().getWindow());
		 if(selected!=null)
		 {
			 if(selected.isFile())
			 {
				 this.path2=selected.getPath();
				 this.location2Text.setText(this.path1);
				 if((this.path1.trim()!=null || !this.path1.trim().equals("")) 
				   && (this.path2.trim()!=null || !this.path2.trim().equals("")))
				 {
					 this.sac.setDisable(false);
				 }
			 }
			 else
			 {
				 new ErrorDialog(2).init();
			 }
		 }
	}
	@FXML private void universalOpenFileChooserLocation(ActionEvent event)
	{
		Button b=(Button)event.getSource();
		DirChooser d=new DirChooser();
		File selected=d.init(this.bp);
		
		if(b.getId().equals("astart"))
		{
			this.astartText.setText(selected.getAbsolutePath());
			this.apacheStart=new String(selected.getAbsolutePath());
			//TODO prenastavit text v textfielde + lokalna premenna pre zapis a serializaciu core
		}
		else if(b.getId().equals("astop"))
		{
			
		}
		else if(b.getId().equals("dbstart"))
		{
			
		}
		else if(b.getId().equals("dbstop"))
		{
			
		}
		else if(b.getId().equals("msqlstart"))
		{
			
		}
		else if(b.getId().equals("msqlstop"))
		{
			
		}
		
		if(selected!=null)
		{
			
		}
	}
	@FXML private void saveAndClose()//malo by volat aj ulozenie core data, to by sa malo serializovat
	{
		try
		{
			this.c.spark();
		}
		catch(Exception e)
		{
			System.out.println("sac exception " + e);
			e.printStackTrace();
			System.exit(0);
		}
	}
	public void setCatalyst(Catalyst c)
	{
		this.c=c;
	}
	public void setDetails(String s)
	{
		this.sysDetails.setText(s);
	}
}