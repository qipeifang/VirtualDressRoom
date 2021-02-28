package restful.annotation.validate;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

@Retention(RUNTIME)
public @interface Equals {
	public String parameName();
	public String message() default "属性不符合一致性要求";
}
