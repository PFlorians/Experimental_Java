package application.view;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.*;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;

public class GraphicsController implements Initializable{
	@FXML private BorderPane bp;
	@FXML private Label val_sl;
	@FXML private Slider rotation_slidex, rotation_slidey, rotation_slidez;
	//others
	//tvar
	private MeshView pyramida;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		this.bp.setCenter(initSs());
	}
	@FXML private void rotated()
	{	
		if(this.rotation_slidex.isValueChanging())
		{
			this.pyramida.setRotationAxis(Rotate.X_AXIS);
			this.pyramida.setRotate(this.rotation_slidex.valueProperty().doubleValue());
		}
		else if(this.rotation_slidey.isValueChanging())
		{
			this.pyramida.setRotationAxis(Rotate.Y_AXIS);
			this.pyramida.setRotate(this.rotation_slidey.valueProperty().doubleValue());
		}
		else if(this.rotation_slidez.isValueChanging())
		{
			this.pyramida.setRotationAxis(Rotate.Z_AXIS);
			
			this.pyramida.setRotate(this.rotation_slidez.valueProperty().doubleValue());
		}
		System.out.println("x: " + this.rotation_slidex.valueProperty().doubleValue() + " y: " + this.rotation_slidey.valueProperty().doubleValue()
				+ " z: " + this.rotation_slidez.valueProperty().doubleValue());
	}
	
	
	private SubScene initSs()
	{
		SubScene ss;
		Group g = new Group();
		
		//textura
		PhongMaterial textura = new PhongMaterial();
		textura.setDiffuseMap(new Image(Main.class.getResource("style/img/grid.png").toExternalForm()));
		//svetlo
		PointLight p =new PointLight();
		//objektovy model
		TriangleMesh m=new TriangleMesh();
		
		
		m.getPoints().addAll(//koordinaty x, y, z
				0, -100, -50, //0
				-100, 100, -50, //1
				100, 100, -50,  //2
				-100, 100, 50, //3
				100, 100, 50); //4
		//pri texture plati (0, 0) - lavy horny okraj, (0, 1) - lavy dolny okraj, (1, 0) - pravy horny, (1, 1) - pravy dolny
		m.getTexCoords().addAll(//koordinaty (u, v) - specifikuju bod v 2d
				0, 0.5f, //0
				0, 0.8f, //1
				0.3f, 0.8f,//2 //koniec tvare 1
				0.4f, 0, //3
				0.4f, 0.2f, //4
				0.5f, 0.2f, //5 // koniec tvare 2
				0.7f, 0.9f, //6
				0.8f, 0.9f, //7
				0.8f, 0.6f //8 //koniec tvare 3
				);
		m.getFaces().addAll(
				0, 0, 3, 1, 4, 2, //predna 
				0, 3, 1, 4, 2, 5, //zadna
				0, 6, 2, 7, 4, 8, //prava
				0, 6, 1, 7, 3, 8, //lava
				3, 0, 4, 1, 1, 2, //dolny lavy 
				4, 0, 2, 1, 1, 2 //dolny pravy
				);
		this.pyramida=new MeshView(m);
		this.pyramida.setDrawMode(DrawMode.FILL);
		//pyramida.setMaterial(new PhongMaterial(Color.GREENYELLOW));
		this.pyramida.setMaterial(textura);
		this.pyramida.setRotationAxis(Rotate.Y_AXIS);
		this.pyramida.setRotate(75);
		this.pyramida.setRotationAxis(Rotate.X_AXIS);
		this.pyramida.setRotate(20);
		this.pyramida.setTranslateX(480);
		this.pyramida.setTranslateY(220);
//		p.setTranslateZ(-5d);
		//p.setTranslateY(20d);
		p.setTranslateX(50d);
		p.setScaleX(40d);
		p.setScaleY(40d);
		p.setRotationAxis(Rotate.X_AXIS);
		p.setRotate(-60d);
		AmbientLight p2 = new AmbientLight();
		
		g.getChildren().addAll(this.pyramida, p2);
		ss = new SubScene(g, 800, 600, true, SceneAntialiasing.BALANCED);
		ss.setFill(Color.BLACK);
		ss.setCamera(new PerspectiveCamera());
		return ss;
	}
	
}
