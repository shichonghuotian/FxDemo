package application.demo;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import application.fx.ann.ViewController;
import application.fx.controller.FxIntent;
import application.fx.controller.FxViewController;
import javafx.event.ActionEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

@ViewController(res="/application/demo/first")
public class FirstController extends FxViewController{

	//先执行这个方法
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		System.out.println("init ");
		
		
	}

	//再执行这个方法，可以在这个方法里面获取数据之类
	@Override
	public void onCreate(FxIntent intent) {
		// TODO Auto-generated method stub
		System.out.println("oncreate" + getView());
		
		Color color = Color.BLUE;
		
		setBackgoundColor(color);

		
		if(intent !=null && intent.getExtras() !=null) {
			
			Map<String, Object> data = intent.getExtras();
			
			System.out.println(data.get("data"));
		}
	}
	
	public void clickAction(ActionEvent event) throws Exception {
		
		this.getNavigationController().pushController(new FxIntent(SecondController.class));
		
	}

	
}
