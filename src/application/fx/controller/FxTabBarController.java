package application.fx.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;

public class FxTabBarController extends FxViewController{
	private List<FxViewController> controllerList;

	private TabPane mTabPane;
	
	private int currentIndex;
	
	public FxTabBarController(Class<? extends FxViewController> rootClass,double width,double height) {
		
		this.controllerList = new ArrayList<>();

		mTabPane= new TabPane();
		mTabPane.setPrefSize(width, height);
		setView(mTabPane);
//				
//		Rectangle rectangle = new Rectangle(pane.getPrefWidth(), pane.getPrefHeight());
//		pane.setClip(rectangle);

		FxIntent intent = new FxIntent(rootClass);

		try {
			addTabController(intent);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	 * @param rootClass
	 * @param view
	 */
	public FxTabBarController(Class<? extends FxViewController> rootClass, Pane parentView) {

		this(rootClass, parentView.getPrefWidth(), parentView.getPrefHeight());
		
		parentView.getChildren().add(getView());

	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	@Override
	public void onCreate(FxIntent intent) {
		
	}
	
	public void addTabController(FxIntent intent) throws Exception {
		ViewContext<? extends FxViewController> viewContext = getViewContext(intent.getControllerClass());

		FxViewController controller = viewContext.getController();

		controller.setTabBarController(this);
		controller.setView(viewContext.getRootNode());
		controller.onCreate(intent);

//		FxViewController currentController = getTopController();
//
//		Node currentNode = currentController != null ? currentController.getView() : null;

//		addControllerToList(controller);

		Tab tab = new Tab("Tab" + (mTabPane.getTabs().size() +1));
		
		tab.setContent(controller.getView());
		
		mTabPane.getTabs().add(tab);
		
		mTabPane.getSelectionModel().select(tab);
	}

}
