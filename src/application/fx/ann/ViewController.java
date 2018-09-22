package application.fx.ann;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ViewController {

	/**
	 * fxml 资源
	 * @return fxml file
	 */
	String res() default "";
	
	/**
	 * title
	 * @return
	 */
	String title() default "";
	
	/**
	 * css资源
	 * @return
	 */
	String css() default "";
	
}
