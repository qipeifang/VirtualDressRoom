package restful.service;

import java.util.List;

public interface Service<T> {
	void save(T model) throws Exception;// 持久化

	void delete(T model) throws Exception;// 通过@Id刪除

	void update(T model) throws Exception;// 更新

	T findById(Object id) throws Exception;// 通过@Id查找

	List<T> findAll() throws Exception;// 获取所有

	List<T> findByFieldName(String fieldName, Object fieldValue) throws Exception; // 通过属性名查询所有
}
