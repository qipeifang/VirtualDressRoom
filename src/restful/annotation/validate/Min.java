package restful.annotation.validate;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

/**
 * 数值最小值校验
 */
@Retention(RUNTIME)
public @interface Min {
	public double value() default Integer.MAX_VALUE;
	
	public String message() default "数值过小";
}
