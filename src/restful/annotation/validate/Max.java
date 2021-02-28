package restful.annotation.validate;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

/**
 * 数值最大值校验
 */
@Retention(RUNTIME)
public @interface Max {
	public double value() default 0;
	
	public String message() default "数值过大";
	
}
