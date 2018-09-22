package application.fx.controller;

import java.net.URL;

public class FxUtils {

	/**
	 * 检查资源是否可用
	 * @param controllerClass
	 * @param resourceName
	 * @return
	 */
	 public static boolean canAccess(final Class<?> controllerClass, final String resourceName) {
	        try {
	            URL url = controllerClass.getResource(resourceName);
	            if (url == null) {
	                return false;
	            }
	            return true;
	        } catch (Exception e) {
	        }
	        return false;
	    }
}
