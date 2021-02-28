package restful.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.commons.lang.ObjectUtils.Null;

import com.jnetdirect.a.n;

import java.util.Set;

import restful.annotation.validate.PrimaryField;
import restful.annotation.validate.Equals;
import restful.annotation.validate.Length;
import restful.annotation.validate.Max;
import restful.annotation.validate.Min;
import restful.annotation.validate.NotNull;

public class Validator {

	/**
	 * 校验某个实例的所有属性，返回校验结果和相关描述
	 * 
	 * @param obj
	 * @param c
	 * @return
	 */
	public static ValidateResult validateAllFields(Object obj, Class c) {
		return validateUseFields(obj, c, c.getDeclaredFields());
	}

	/**
	 * 校验某个实例的所有属性，返回boolean类型的校验结果
	 * 
	 * @param obj
	 * @param c
	 * @return
	 */
	public static boolean simpleValidateAllFields(Object obj, Class c) {
		return simpleValidateUseFields(obj, c, c.getDeclaredFields());
	}

	/**
	 * 校验某个实例的主键属性，返回校验结果和相关描述
	 * 
	 * @param obj
	 * @param c
	 * @return
	 */
	public static ValidateResult validatePrimaryFields(Object obj, Class c) {
		return validateUseFields(obj, c, getPrimaryFields(c));
	}

	/**
	 * 校验某个实例的主键属性，返回boolean类型的校验结果
	 * 
	 * @param obj
	 * @param c
	 * @return
	 */
	public static boolean simpleValidatePrimaryFields(Object obj, Class c) {
		return simpleValidateUseFields(obj, c, getPrimaryFields(c));
	}

	/**
	 * 获取 Class c 中所有使用了 @PrimaryField 注解的属性
	 * 
	 * @param c
	 * @return
	 */
	public static Field[] getPrimaryFields(Class c) {
		ArrayList<Field> list = new ArrayList<>();

		for (Field field : c.getDeclaredFields()) {
			if (field.isAnnotationPresent(PrimaryField.class)) {
				list.add(field);
			}
		}
		Field[] fields = new Field[list.size()];
		list.toArray(fields);
		return fields;
	}

	/**
	 * 对 Object 的 fields 中的属性进行校验 返回值中含boolean型的校验结果和String类型的描述信息
	 * 
	 * @param obj
	 * @param c
	 * @param fields 所以用的属性列表
	 * @return ValidateResult{valid:boolean, message:String}
	 */
	public static ValidateResult validateUseFields(Object obj, Class c, Field[] fields) {
		// 对每个属性进行校验
		for (Field field : fields) {
			field.setAccessible(true);
			ValidateResult result = validateField(obj, field);
			if (result.isValid() == false) {
				return result;
			}
		}

		return new ValidateResult(true, "");
	}

	/**
	 * 对 Object 的 fields 中的属性进行校验 返回为 boolean 型的校验结果
	 * 
	 * @param obj
	 * @param c
	 * @param fields
	 * @return 校验结果
	 */
	private static boolean simpleValidateUseFields(Object obj, Class c, Field[] fields) {
		return validateUseFields(obj, c, fields).isValid();
	}

	/**
	 * 对某个 Object 的某个 Field 进行校验 返回值中含boolean型的校验结果和String类型的描述信息
	 * 
	 * @param obj
	 * @param field
	 * @return ValidateResult{valid:boolean, message:String}
	 */
	public static ValidateResult validateField(Object obj, Field field) {
		field.setAccessible(true);
		try {
			// 非空校验
			if (field.isAnnotationPresent(NotNull.class)) {
				NotNull annotation = field.getAnnotation(NotNull.class);
				String message = annotation.message();
				if ((field.get(obj)) == null) {
					return new ValidateResult(false, message);
				}
			}

			// 长度校验
			if (field.isAnnotationPresent(Length.class)) {
				Length annotation = field.getAnnotation(Length.class);
				int min = annotation.min();
				int max = annotation.max();
				String message = annotation.message();
				String string = (String) field.get(obj);
				int len = string.length();
				if (len < min || len > max) {
					return new ValidateResult(false, message);
				}
			}

			// 最小值校验
			if (field.isAnnotationPresent(Min.class)) {
				Min annotation = field.getAnnotation(Min.class);
				double min = annotation.value();
				String message = annotation.message();
				if (min > field.getDouble(obj)) {
					return new ValidateResult(false, message);
				}
			}

			// 最大值校验
			if (field.isAnnotationPresent(Max.class)) {
				Max annotation = field.getAnnotation(Max.class);
				double max = annotation.value();
				String message = annotation.message();
				if (max < field.getDouble(obj)) {
					return new ValidateResult(false, message);
				}
			}

			// 相等校验
			if (field.isAnnotationPresent(Equals.class)) {
				Equals annotation = field.getAnnotation(Equals.class);
				String parameName = annotation.parameName();
				String message = annotation.message();
				Class c = obj.getClass(); 
				Field declaredField = c.getDeclaredField(parameName);
				declaredField.setAccessible(true);
				Object pra = declaredField.get(obj);
				
//				Class<?> declaringClass = pra.getClass();
				
				Object value = field.get(obj);
				if( ! pra.equals(value)) {
//				if(!declaringClass.cast(pra).equals(declaringClass.cast(value))) {
					return new ValidateResult(false,message);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ValidateResult(false, "数据类型出错，无法校验");
		}

		// 没有进行校验相关的注解 或 校验合法
		return new ValidateResult(true, "");
	}

	/**
	 * 对某个 Object 的某个 Field 进行校验 返回值中含boolean型的校验结果
	 * 
	 * @param obj
	 * @param field
	 * @return 校验结果
	 */
	public static boolean simpleValidateField(Object obj, Field field) {
		ValidateResult result = validateField(obj, field);
		return result.isValid();
	}

	/**
	 * 禁止实例化
	 */
	private Validator() {
	}

}
