package restful.service;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import restful.database.EM;

public abstract class AbstractService<T> implements Service<T> {
	protected EntityManager entityManager = EM.getEntityManager();// 持久化管理器
	private Class<T> modelClass; // 当前泛型真实类型的Class

	public AbstractService() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		modelClass = (Class<T>) pt.getActualTypeArguments()[0];
	}

	@Override
	public void save(T model) throws Exception {
		entityManager.persist(model);
		entityManager.getTransaction().commit();
	}

	@Override
	public void delete(T model) throws Exception {
		entityManager.remove(entityManager.merge(model));
		entityManager.getTransaction().commit();
	}

	@Override
	public void update(T model) throws Exception {
		entityManager.persist(entityManager.merge(model));
		entityManager.getTransaction().commit();
	}

	@Override
	public T findById(Object idValue) throws Exception {
		T model = null;
		model = entityManager.find(modelClass, idValue);
		entityManager.refresh(model);
		return model;
	}

	@Override
	public List<T> findAll() throws Exception {
		List<T> list = null;
		list = entityManager.createNamedQuery(modelClass.getSimpleName() + ".findAll", modelClass).getResultList();
		return list;
	}

	/**
	 * 适合非联合主键Bean的按属性名和属性值查询
	 */
	@Override
	public List<T> findByFieldName(String fieldName, Object fieldValue) throws Exception {
		List<T> list = null;
		Query query = entityManager.createNamedQuery(modelClass.getSimpleName() + ".findBy"
				+ fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), modelClass);
		query.setParameter(fieldName, fieldValue);
		list = query.getResultList();
		return list;
	}

}