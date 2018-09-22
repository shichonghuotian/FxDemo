package application.fx.controller;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import application.fx.ann.FXMLWindow;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class FxViewHander {

	/**
	 * 显示controller
	 * 
	 * @param controllerClass
	 * @param primaryStage
	 * @return
	 * @throws Exception
	 */
	public static <T> ViewContext<T> showController(Class<T> controllerClass, Stage primaryStage) throws Exception {

		ViewContext<T> viewContext = getViewContext(controllerClass);
//		injectWindowInController(viewContext.getController(), primaryStage);

		FxViewController controller =(FxViewController) viewContext.getController();
		controller.setKeyWindow(primaryStage);
		controller.onCreate(new FxIntent());
		Scene scene = new Scene((Parent) viewContext.getRootNode());
		primaryStage.setScene(scene);
		primaryStage.show();

		return viewContext;

	}

	/**
	 * 作为子窗口显示，阻塞主窗口
	 * 
	 * @param controllerClass
	 * @return
	 * @throws Exception
	 */
	public static <T> ViewContext<T> showControllerInWindow(Class<T> controllerClass, Window parentWindow)
			throws Exception {
		ViewContext<T> viewContext = getViewContext(controllerClass);
		Stage stage = new Stage();

		injectWindowInController(viewContext.getController(), stage);
		Scene scene = new Scene((Parent) viewContext.getRootNode());
		// viewContext.getController().setParentStage(primaryStage);
		stage.setScene(scene);
		stage.initModality(Modality.WINDOW_MODAL);
		if (parentWindow != null) {
			stage.initOwner(parentWindow);
		}
		// //会阻塞下面的代码
		stage.showAndWait();
		return viewContext;

	}

	/**
	 * 添加到子view中
	 * 
	 * @param controllerClass
	 * @param pane
	 * @return
	 * @throws Exception
	 */
	public static <T> ViewContext<T> showInPane(Class<T> controllerClass, Pane pane) throws Exception {
		ViewContext<T> viewContext = getViewContext(controllerClass);

		if (pane != null) {
			System.out.println(viewContext.getRootNode());
			pane.getChildren().clear();
			pane.getChildren().add(viewContext.getRootNode());
		}

		return viewContext;

	}

	private static <T> ViewContext<T> getViewContext(Class<T> controllerClass) throws Exception {
		ViewContext<T> viewContext = ViewControllerFactory.getInstance().createController(controllerClass);
		return viewContext;
	}

	// 使用注解，检查一下class有没有这个属性
	private static <T> void injectWindowInController(T controller, Stage stage) throws Exception {

		Field[] fields =controller.getClass().getDeclaredFields();
		// 获取一个成员变量上的注解
		if (fields.length > 0) {

			for (Field field : fields) {

				if (field.isAnnotationPresent(FXMLWindow.class)) {
					for (Annotation anno : field.getDeclaredAnnotations()) {// 获得所有的注解
						if (anno.annotationType().equals(FXMLWindow.class)) {// 自动注入

//							FXMLWindow windowAnno = (FXMLWindow) anno;

							if(field.getType() == Window.class) {
								field.setAccessible(true);
								
								field.set(controller, stage);
							}else {
								
					            throw new Exception("Window need javafx.stage.Window");
							}
							

						}

					}


			        //获取属性声明时类型对象（返回class对象）

			        System.err.println(field.getType());

			        //返回属性声的Type类型

			        System.out.println(field.getGenericType());

			        //如果属性是一个泛型，从getType只能得到这个属性的接口类型

			        System.out.println(field.getType());

			        //如果属性是一个参数化类型，从getGenericType还能得到这个泛型的参数类型

			        System.out.println(field.getGenericType());

			        //获取属性声明时名字

			        System.out.println(field.getName());

			        //获得这个属性上所有的注释

			        System.out.println(field.getAnnotations().length);

			        //获取属性的修饰

			        System.out.println(Modifier.toString(field.getModifiers()));

			         

			        //判断这个属性是否是枚举类

			        System.out.println(field.isEnumConstant());

			        //判断这个属性是否是 复合类

			        System.out.println(field.isSynthetic());				}
			}

		}
	}

}
