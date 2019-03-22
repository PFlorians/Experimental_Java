package application;

import javafx.scene.Scene;
import javafx.stage.Stage;

public interface Popup {
	public void init();
	public void initStage();
	public Popup getInstance();
	public Stage getStage();
	public void setStage(Stage stage);
	public Scene getScene();
}
