package restful.annotation.validate;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

/**
 * 对象非空校验
 */
@Retention(RUNTIME)
public @interface NotNull {
	public String message() default "对象不能为空";
}
