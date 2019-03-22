package application.fileWorker;
import java.io.*;

import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class DirChooser {
	
	public File init(BorderPane bp)
	{
		FileChooser fileChooser = new FileChooser();
		 fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		 fileChooser.getExtensionFilters().addAll(
		         new ExtensionFilter("Scripts(Windows)", "*.bat"),
		         new ExtensionFilter("Scripts(Linux/UNIX", "*.sh"),
		         new ExtensionFilter("All Files", "*.*"));
		 File selected=fileChooser.showOpenDialog(bp.getScene().getWindow());
		 return selected;
	}
}
