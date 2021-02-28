package restful.annotation.validate;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/*
 * 分组校验时，只针对主键做校验，用于方法参数。
 */
@Target(ElementType.PARAMETER)
@Retention(RUNTIME)
public @interface ValidatePrimarykey {
	public Class objecClass() ;
}
