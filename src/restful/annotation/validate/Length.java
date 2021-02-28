/**
 * 
 */
package restful.annotation.validate;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

@Retention(RUNTIME)
/**
 * @author WuZhen
 * 字符串长度校验
 */
public @interface Length {
	public int max();
	public int min() default 0;
	public String message() default "长度不合法";
}
