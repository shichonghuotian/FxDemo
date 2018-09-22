package application.demo;

import java.net.URL;
import java.util.ResourceBundle;

import application.fx.ann.ViewController;
import application.fx.controller.FxIntent;
import application.fx.controller.FxNavigationController;
import application.fx.controller.FxViewController;
import application.fx.controller.FxViewHander;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

@ViewController(res="/application/demo/main")
public class MainController extends FxViewController {

	
	
	@FXML
	private Button myButton;

	@FXML
	private TextField myTextField;

	@FXML
	Pane containerPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
//			FxViewHander.showInPane(FirstController.class, containerPane);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	@Override
	public void onCreate(FxIntent intent) {
		// TODO Auto-generated method stub
		
	}

	public void showText(ActionEvent event) {
//		System.out.println("button click " + myButton + " " + myTextField);

		// myTextField.setText("Hello world");

		try {
			show2();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}

	public void show() throws Exception {

		//获取window
//		FxViewHander.showControllerInWindow(SecondController.class,window);
		
		FxIntent intent = new FxIntent(FirstController.class);
		intent.putExtra("data", "test data");
		presentController(intent);
	}


	public void show2() throws Exception{
		
		FxNavigationController controller = new FxNavigationController(FirstController.class, containerPane);
		
		//
	}

}
