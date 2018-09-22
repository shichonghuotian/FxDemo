package application.demo;

import java.net.URL;
import java.util.ResourceBundle;

import application.fx.ann.ViewController;
import application.fx.controller.FxIntent;
import application.fx.controller.FxViewController;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;

@ViewController(res="/application/demo/second")
public class SecondController extends FxViewController {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	
		
	}
	@Override
	public void onCreate(FxIntent intent) {
		// TODO Auto-generated method stub
		
		int random = (int) (Math.random() *2);
		
		if(random % 2 == 0) {
			
			setBackgoundColor(Color.RED);

		}else {
			setBackgoundColor(Color.GREEN);

		}
	}

	
	public void clickAction(ActionEvent event) {
		
		getNavigationController().popController();
	}

	public void nextAction(ActionEvent event) throws Exception {
		
		getNavigationController().pushController(new FxIntent(SecondController.class));
	}


}
