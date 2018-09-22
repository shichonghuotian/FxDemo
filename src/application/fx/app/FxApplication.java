package application.fx.app;

import java.lang.annotation.Annotation;

import application.fx.ann.BindMainController;
import application.fx.ann.ViewController;
import application.fx.controller.FxIntent;
import application.fx.controller.FxViewController;
import application.fx.controller.ViewContext;
import application.fx.controller.ViewControllerFactory;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class FxApplication extends Application {

	// 定义一个单例
	// private static FxApplication instance;
	//
	// //root controler
	// private FxViewController roootController;
	//
	// //主窗口
	// private Window keyWindow;
	//
	// public static synchronized FxApplication shareApplication() {
	//
	// if(instance == null) {
	// instance = new FxApplication();
	// }
	//
	// return instance;
	//
	// }
	//

	public FxApplication() {

		// 添加一个

	}

	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		super.init();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// this.setKeyWindow(primaryStage);
		;

		System.out.println("start");
		for (Annotation annotation : this.getClass().getAnnotations()) {

			System.out.println(annotation);
		}

		BindMainController mainControllerAnno = this.getClass().getAnnotation(BindMainController.class);

		if (mainControllerAnno != null && !(mainControllerAnno.value() == null)) {

			Class<? extends FxViewController> viewControllerClass = mainControllerAnno.value();

			ViewContext<? extends FxViewController> viewContext = ViewControllerFactory.getInstance()
					.createController(viewControllerClass);
			
			FxViewController controller = viewContext.getController();
			
			controller.setKeyWindow(primaryStage);
			controller.onCreate(new FxIntent());
			Scene scene = new Scene((Parent) viewContext.getRootNode());
			primaryStage.setScene(scene);
			primaryStage.show();


		} else {
			throw new Exception("need add mainController");
		}
	}

	public <T> ViewContext<T> createController(final Class<T> controllerClass) throws Exception {
		// 1.create controller
		T controller = controllerClass.newInstance();

		this.getClass().getAnnotations();

		ViewController controllerAnnotation = controllerClass.getAnnotation(ViewController.class);

		// title
		if (controllerAnnotation != null && !controllerAnnotation.title().isEmpty()) {

		}

		// FXMLLoader loader = createLoader(controller,
		// getFxmlName(controllerClass));
		// //获取parent
		// Parent parent = loader.load();
		//
		ViewContext<T> context = new ViewContext<T>(null, controller);
		//
		// 处理一下生命周期 -- init destory

		return context;
	}

}
