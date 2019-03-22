package application.view;
import javafx.fxml.*;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import application.*;
import application.Error;
import dbconnect.*;
public class LoginController implements Initializable{
	//locals
	Catalyst c;//=new Catalyst();
	@FXML private TextField user;
	@FXML private PasswordField pw;
	
	@Override
	public void initialize(URL location, ResourceBundle reources)
	{
		System.out.println("init login");
	}
	@FXML private void checkLogin()
	{
		TestConnection t=new TestConnection();
		boolean status=false;
		System.out.println("login check " + this.user.getText().trim());
		try
		{
		status=(((this.user.getText().trim().equals(""))?this.c.errorMsg(1):true)==false)?
				false:t.test(this.user.getText().trim(), this.pw.getText().trim());
		}
		catch(Exception e)
		{
			System.err.println("Exception caught " + e);
			e.printStackTrace();
			new Error(2).initDbErr();
			System.exit(1);
		}
		this.pw.setText((status)?this.pw.getText():new String(""));
		if(status==true)
		{
			try
			{
				this.c.ignite(t.getValidConnection(), this.user.getText().trim(), this.pw.getText().trim());
			}
			catch(Exception e)
			{
				System.out.println("chyba pri init");
				e.printStackTrace();
			}
		}
	}
	public void setCatalyst(Catalyst c)
	{
		this.c=c;
	}
}
