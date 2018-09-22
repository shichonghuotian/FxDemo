package application.fx.controller;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * 
 * @author Apple
 *
 */
public abstract class FxViewController implements Initializable{

	//导航controller
	private FxNavigationController navigationController;
	
	private Window keyWindow;
	
	//
	private Node view;

	public abstract void onCreate(FxIntent intent);
	
	
	
	
	public Window getKeyWindow() {
		return keyWindow;
	}




	public void setKeyWindow(Window keyWindow) {
		this.keyWindow = keyWindow;
	}




	public FxNavigationController getNavigationController() {
		return navigationController;
	}




	public void setNavigationController(FxNavigationController navigationController) {
		this.navigationController = navigationController;
	}




	public Node getView() {
		return view;
	}




	public void setView(Node view) {
		this.view = view;
	}




	/**
	 * 弹出controller
	 * @param intent
	 * @throws Exception 
	 */
	public void presentController(FxIntent intent) throws Exception {
		
		ViewContext<? extends FxViewController> viewContext = getViewContext(intent.getControllerClass());
		
		
		FxViewController controller = viewContext.getController();
		
		Stage stage = new Stage();
		Scene scene = new Scene((Parent) viewContext.getRootNode());
		// viewContext.getController().setParentStage(primaryStage);
		stage.setScene(scene);
		stage.initModality(Modality.WINDOW_MODAL);
		if (this.keyWindow != null) {
			stage.initOwner(this.keyWindow);
		}

		controller.setView(viewContext.getRootNode());
		controller.onCreate(intent);

		// //会阻塞下面的代码
		stage.showAndWait();
		

	}
	
	//关闭controller
	public void dismissController() {
		
		Stage stage = (Stage)getKeyWindow();
		
		stage.close();
	}
	
	/**
	 * 添加child ---默认添加到主stage上
	 */
	public void addChildController(FxViewController controller,Pane conatinerView) {
		
		conatinerView.getChildren().add(controller.getView());
		
	}
	
	
	
	 <T> ViewContext<T> getViewContext(Class<T> controllerClass) throws Exception {
		ViewContext<T> viewContext = ViewControllerFactory.getInstance().createController(controllerClass);
		return viewContext;
	}
	 
	public void setBackgoundColor(Color color) {
		
		
		if(getView() != null && (getView() instanceof Pane)) {
			Pane pane = (Pane) getView();
			
			pane.setBackground(new Background(new BackgroundFill(color, null, null)));
			
		}
	}
	

}
