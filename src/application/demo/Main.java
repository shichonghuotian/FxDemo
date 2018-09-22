package application.demo;

import application.fx.ann.BindMainController;
import application.fx.app.FxApplication;
import javafx.stage.Stage;

@BindMainController(MainController.class)
public class Main extends FxApplication {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		super.start(primaryStage);
		
	}


}
