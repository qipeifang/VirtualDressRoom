package restful.annotation.validate;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

/**
 * 用于校验时，标记某一属性是否为主键 
 *
 */
@Retention(RUNTIME)
public @interface PrimaryField {

}
