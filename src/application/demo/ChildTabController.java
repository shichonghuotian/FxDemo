package application.demo;

import java.net.URL;
import java.rmi.server.SkeletonNotFoundException;
import java.util.ResourceBundle;

import application.fx.ann.ViewController;
import application.fx.controller.FxIntent;
import application.fx.controller.FxViewController;
import javafx.event.ActionEvent;

@ViewController(res="/application/demo/childtab")

public class ChildTabController extends FxViewController {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCreate(FxIntent intent) {
		// TODO Auto-generated method stub
		
	}

	public void addAction(ActionEvent event) throws Exception {
		
		getTabBarController().addTabController(new FxIntent(ChildTabController.class));
	}
	
}
