package application.error;

import javafx.*;
import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.event.*;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.*;
import javafx.scene.input.*;
import application.*;
import application.Popup;
/*
 * legenda chyb
 * "typ vyznam"
 * 1 treba vyplnit url, descr
 */
public class ErrorDialog implements Popup{
	private int type;
	private String msg;
	private Stage stage;
	public ErrorDialog(int type)//nastavit typ chyby podla legendy
	{
		switch(type)
		{
			case 1:
				this.msg="URL and Description field must be filled!";
				break;
			case 2:
				this.msg="Selected location must be a file or executable!";
				break;
		}
	}
	@Override
	public void init() {
		try
		{
			Alert a=new Alert(AlertType.ERROR);
			a.setTitle("ERROR");
			a.setHeaderText(this.msg);
			a.showAndWait();
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
	@Override
	public void initStage() {
		this.stage=new Stage();
	}
	@Override
	public Popup getInstance() {
		return this;
	}
	@Override
	public Stage getStage() {
		return this.stage;
	}
	@Override
	public void setStage(Stage stage) {
		this.stage=stage;
	}
	@Override
	public Scene getScene() {
		return null;
	}
	
}
