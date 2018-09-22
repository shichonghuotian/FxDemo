package application.fx.controller;

import java.net.URL;

import application.fx.ann.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.util.BuilderFactory;

/**
 * 用来加载controller
 * @author Apple
 *
 */
public class ViewControllerFactory {

	
	private static ViewControllerFactory INSTANCE;
	
	public static synchronized ViewControllerFactory getInstance() {
		
		if(INSTANCE == null) {
			
			INSTANCE = new ViewControllerFactory();
		}
		
		return INSTANCE;
	}
	
	public <T> ViewContext<T> createController(final Class<T> controllerClass) throws Exception {
		//1.create controller
		T controller = controllerClass.newInstance();
		
		ViewController controllerAnnotation = controllerClass.getAnnotation(ViewController.class) ;
		
		//title
        if (controllerAnnotation != null && !controllerAnnotation.title().isEmpty()) {

        }
        
        FXMLLoader loader = createLoader(controller, getFxmlName(controllerClass));
        //获取parent
        Parent parent = loader.load();
        
        ViewContext<T> context = new ViewContext<T>(parent, controller);
        
        //处理一下生命周期 -- init destory
        		
		return context;
	}
	
	
	
    private FXMLLoader createLoader(final Object controller, String fxmlName)
            throws Exception {
        Class<?> controllerClass = controller.getClass();
        String foundFxmlName = getFxmlName(controllerClass);
        if (fxmlName != null) {
            foundFxmlName = fxmlName;
        }
        if (foundFxmlName == null) {
            throw new Exception("No FXML File specified!");
        }

        URL fxmlUrl = controllerClass.getResource(foundFxmlName);

        if (fxmlUrl == null) {
            throw new Exception("Can't find FXML file for controller " + controller.getClass());
        }

        FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
        
        BuilderFactory builderFactory = new JavaFXBuilderFactory();
        fxmlLoader.setBuilderFactory(builderFactory);
        //编码
//        fxmlLoader.setCharset(viewConfiguration.getCharset());
        //resource
//        fxmlLoader.setResources(viewConfiguration.getResources());
        fxmlLoader.setController(controller);
        fxmlLoader.setControllerFactory(c -> controller);
        return fxmlLoader;
    }

    
    /**
     * 获取fxml name
     * 如果没有写，默认搜索class的package下，同名的fxml
     * 如果填写的value，直接使用value
     * @param controllerClass
     * @return fxml name
     */
    private String getFxmlName(Class<?> controllerClass) {
        String foundFxmlName = null;

        if (controllerClass.getSimpleName().endsWith("Controller")) {
            String nameByController = controllerClass.getSimpleName()
                    .substring(
                            0,
                            controllerClass.getSimpleName().length()
                                    - "Controller".length())
                    + ".fxml";
            if (FxUtils.canAccess(controllerClass, nameByController)) {
                foundFxmlName = nameByController;
            }
        }

        ViewController controllerAnnotation = controllerClass
                .getAnnotation(ViewController.class);
        if (controllerAnnotation != null && !controllerAnnotation.res().isEmpty()) {
        	
        	//如果结尾没有添加fxml，那么自动加上
            foundFxmlName = controllerAnnotation.res();
            
            if(!foundFxmlName.endsWith(".fxml")) {
            	foundFxmlName += ".fxml";
            }
        }
        return foundFxmlName;
    }



}
