package application.graphics;
import javafx.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.*;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import application.Main;
public class GraphicExperimenter {
	public BorderPane getObject()
	{
		BorderPane bp = new BorderPane();
		SubScene ss;
		Group g = new Group();
		
		//tvar
		MeshView pyramida;
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
		pyramida=new MeshView(m);
		pyramida.setDrawMode(DrawMode.FILL);
		//pyramida.setMaterial(new PhongMaterial(Color.GREENYELLOW));
		pyramida.setMaterial(textura);
		pyramida.setRotationAxis(Rotate.Y_AXIS);
		pyramida.setRotate(75);
		pyramida.setTranslateX(480);
		pyramida.setTranslateY(220);
//		p.setTranslateZ(-5d);
		//p.setTranslateY(20d);
		p.setTranslateX(50d);
		p.setScaleX(40d);
		p.setScaleY(40d);
		p.setRotationAxis(Rotate.X_AXIS);
		p.setRotate(-60d);
		PointLight p2 = new PointLight();
		//p2.setTranslateY(70d);
		p2.setLayoutX(0d);
		p2.setLayoutY(170d);
		g.getChildren().addAll(pyramida, p, p2);
		ss = new SubScene(g, 800, 600, true, SceneAntialiasing.BALANCED);
		
		bp.setCenter(ss);
		
		return bp;
	}
}
