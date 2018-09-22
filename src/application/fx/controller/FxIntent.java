package application.fx.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * controller 跳转管理
 * @author Apple
 *
 */
public class FxIntent {

	
	private Class<? extends FxViewController> controllerClass;
	
	private Map<String, Object> extras;
	
	
	public FxIntent() {
		super();
		// TODO Auto-generated constructor stub
	}


	public FxIntent(Class<? extends FxViewController> controllerClass) {
		
		this.controllerClass = controllerClass;
	}
	

	public Class<? extends FxViewController> getControllerClass() {
		return controllerClass;
	}

	public void setControllerClass(Class<FxViewController> controllerClass) {
		this.controllerClass = controllerClass;
	}
	
	public void putExtra(String key,Object value) {
		
		if(extras == null) {
			extras = new HashMap<>();
		}
		
		extras.put(key, value);
	}


	public Map<String, Object> getExtras() {
		return extras;
	}


	public void setExtras(Map<String, Object> extras) {
		this.extras = extras;
	}
	
	
}
