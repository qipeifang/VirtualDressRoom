package restful.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.ObjectUtils.Null;

import com.jnetdirect.a.f;
import com.jnetdirect.a.m;

import java.util.Set;

import antlr.collections.List;
import restful.bean.User;

public class ClassUtils {

	public static final int OBJECT_CONTENT_EXISTS = 0;
	
	public static final int OBJECT_CONTENT_NULL = -1;
	
	public static final int OBJECT_CONTENT_EMPTY_COLLECTION = -2;
	
	public static final int OBJECT_CONTENT_BLANK_CHAR_SEQUENCEE = -3;
	
	/**
	 * 	判断一个对象是否为空
	 * @param obj
	 * @return	表示状态的值，可使用 getObjectContentDescription 获取具体描述
	 */
	public static int getObjectContentType(Object obj) {
		if(obj == null) {
			return OBJECT_CONTENT_NULL;
		}else if (obj instanceof Collection<?>) {
			Collection<?> collection = (Collection<?>) obj;
			if(collection.isEmpty()) {
				return OBJECT_CONTENT_EMPTY_COLLECTION;
			}
		}else if (obj instanceof CharSequence) {
			CharSequence cs = (CharSequence) obj;
			if(cs.length() == 0) {
				return OBJECT_CONTENT_BLANK_CHAR_SEQUENCEE;
			}
		}
		return OBJECT_CONTENT_EXISTS;
	}
	
	/**
	 * 	根据状态码，返回对象是否为null、容器是否为空、序列是否为空的具体描述
	 * @param type
	 * @return
	 */
	public static String getObjectContentDescription(int type) {
		switch (type) {
		case 0:
			return "Content Exists";
		case -1:
			return "Null Object";
		case -2:
			return "Empty Collection";
		case -3:
			return "Blank Char Squence";
		default:
			return "Content Exists";
		}
	}
	
	/**
	 * 	获取某一个对象所有属性的键值对（属性名、属性值）
	 * @param obj
	 * @param c
	 * @return Map 类型，方便查找
	 * @throws Exception
	 */
	public static HashMap<String,Object> getAllFieldAsMap(Object obj, Class c) {
		HashMap<String, Object> map = new HashMap<String ,Object>();
		for(Field f:c.getDeclaredFields()) {
			try {
				f.setAccessible(true);
				//	暂时不处理基本数据类型（int、double等），建议设置为包装类（Integer、Double等）
//				if (f.get(obj) instanceof Object) 					
					map.put(f.getName(), f.get(obj));
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		return map;
	}
	/**
	 * 	获取某一个对象所有属性的键值对（属性名、属性值）
	 * @param obj
	 * @param c
	 * @return Set 类型，方便遍历
	 * @throws Exception
	 */
	public static Set<Entry<String,Object>> getAllFieldAsSet(Object obj, Class c) {
		return getAllFieldAsMap(obj, c).entrySet();
	}
	
	/**
	 * 	打印一个对象所有属性
	 * @param obj
	 * @param c
	 */
	public static void showAllField(Object obj, Class c) {
		Set<Entry<String,Object>> set = getAllFieldAsSet(obj, c);
		Iterator<Entry<String, Object>> it = set.iterator();
		System.out.println(c.getName()+"{");
		while(it.hasNext()) {
			Entry<String, Object> t = it.next();
			String key = t.getKey();
			Object value = t.getValue();
			int type = getObjectContentType(value);
			String string = String.format("%s:%s", key, type != OBJECT_CONTENT_EXISTS ? getObjectContentDescription(type) : value.toString());
			System.out.println("\t"+string);
		}
		System.out.println("}");
	}
	
	/**
	 * 	获取一个实例中所有为null或长度为零的属性,返回值中Object为该属性的描述（一般为 String类型）
	 * @param obj
	 * @param c
	 * @return	HashMap，方便查找
	 */
	public static HashMap<String, Object> getNullFieldAsMap(Object obj, Class c) {
		Set<Entry<String, Object>> set = getAllFieldAsSet(obj, c);
		Iterator<Entry<String, Object>> it = set.iterator();
		HashMap<String, Object> map = new HashMap<>();
		while(it.hasNext()) {
			Entry<String, Object> en = it.next();
			String key = en.getKey();
			Object f = en.getValue();
			int type = getObjectContentType(f);
			// 如果对象不存在，将 属性名和描述 放入map中
			if(type != OBJECT_CONTENT_EXISTS) {
				map.put(key, getObjectContentDescription(type));
			}
		}
		return map;
	}

	/**
	 * 	以Set的形式返回null的属性或者空的集合属性
	 * @param obj
	 * @param c
	 * @return Set，方便遍历
	 */
	public static Set<Entry<String,Object>> getNullFieldAsSet(Object obj, Class c) {
		return getNullFieldAsMap(obj, c).entrySet();
	}
	
	/**
	 * 	输出所有为null的属性或者空的集合属性
	 * @param obj
	 * @param c
	 */
	public static void showNullField(Object obj, Class c) {
		Set<Entry<String,Object>> set = getNullFieldAsSet(obj, c);
		Iterator<Entry<String, Object>> it = set.iterator();
		System.out.println(c.getName()+" null or empty fields:{");
		while(it.hasNext()) {
			Entry<String, Object> t = it.next();
			String key = t.getKey();
			Object value = t.getValue();
			String string = String.format("%s: %s", key, value);
			System.out.println("\t"+string);
		}
		System.out.println("}");
	}

	/**
	 * 	获取一个实例中所有不为null或长度不为零的属性,返回值中Object为该属性的值
	 * @param obj
	 * @param c
	 * @return	HashMap，方便查找
	 */
	public static HashMap<String, Object> getNotNullFieldAsMap(Object obj, Class c) {
		Set<Entry<String, Object>> set = getAllFieldAsSet(obj, c);
		Iterator<Entry<String, Object>> it = set.iterator();
		HashMap<String, Object> map = new HashMap<>();
		while(it.hasNext()) {
			Entry<String, Object> en = it.next();
			String key = en.getKey();
			Object f = en.getValue();
			
			int type = getObjectContentType(f);
			
			//	如果不为空，则将 (属性名<String>、值<Object>) 放入map
			if(type == OBJECT_CONTENT_EXISTS) {
				map.put(key, f);
			}
			
		}
		return map;
	}

	/**
	 * 	以Set的形式返回null的属性或者空的集合属性
	 * @param obj
	 * @param c
	 * @return Set，方便遍历
	 */
	public static Set<Entry<String,Object>> getNotNullFieldAsSet(Object obj, Class c) {
		return getNotNullFieldAsMap(obj, c).entrySet();
	}
	
	/**
	 * 	输出所有不为null的属性或者不为空的集合属性
	 * @param obj
	 * @param c
	 */
	public static void showNotNullField(Object obj, Class c) {
		Set<Entry<String,Object>> set = getNotNullFieldAsSet(obj, c);
		Iterator<Entry<String, Object>> it = set.iterator();
		System.out.println(c.getName()+" not null and empty fields:{");
		while(it.hasNext()) {
			Entry<String, Object> t = it.next();
			String key = t.getKey();
			Object value = t.getValue();
			String string = String.format("%s: %s", key, value.toString());
			System.out.println("\t"+string);
		}
		System.out.println("}");
	}

	/**
	 * 	用 src 中非空的的属性去更新 obj 中对应的属性，src和obj应该属于同一类型
	 * @param obj
	 * @param src
	 * @param c
	 * @throws Exception obj 和 src不是同一类型 或 没有对应属性
	 */
	public static void updateUseNotNullField(Object obj, Object src, Class c) throws Exception {
		
		try {
			Set<Entry<String, Object>> set = getNotNullFieldAsSet(src, c);
			for(Entry<String, Object> entry: set) {
				String key = entry.getKey();
				Object value = entry.getValue();
				Field field = c.getDeclaredField(key);
				field.setAccessible(true);
				field.set(obj, value);
				System.out.println(String.format("set %s to %s", key, value.toString()));
			}
		} catch (Exception e) {
			// TODO: handle exception
			// obj 和 src不是同一类型 或 没有对应属性
//			e.printStackTrace();
			throw new Exception(String.format("%s and %s is not the same class", obj.getClass().getName(), src.getClass().getName()) );
		}
	}
	
	/**
	  * 工具类禁止实例化
	 */
	private ClassUtils() {}
}
