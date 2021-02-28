package restful.service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import restful.database.EM;

public abstract class AbstractCompositeKeyService<T> implements Service<T> {
	private EntityManager entityManager = EM.getEntityManager();
	private Class<T> modelClass; // 当前泛型真实类型的Class
	private Class compositeKeyClass;
	private String compositeKeyFieldName;
	private Field compositeKeyField;

	public AbstractCompositeKeyService() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		modelClass = (Class<T>) pt.getActualTypeArguments()[0];
		// modelClass应该获取带@EmbeddedId 注解的Field的类型， 作为增删查改的id
		Field[] fields = modelClass.getDeclaredFields();
		for (Field field : fields) {
			boolean compositeKeyMark = field.isAnnotationPresent(EmbeddedId.class);
			if (compositeKeyMark) {
				compositeKeyClass = field.getType();
				compositeKeyFieldName = field.getName();
				compositeKeyField = field;
				break;
			}
		}
	}

	/**
	 * 适合联合主键Bean的单个主键名和主键值查询
	 * 
	 * @param fieldName  格式 compositeKey.primaryKey, 例如:onWearId.userName
	 * @param fieldValue primaryKey的值
	 * @return 返回所有根据部分主键查询的结果
	 */
	@Override
	public List<T> findByFieldName(String fieldName, Object fieldValue) throws Exception {
		List list = null;
		Criterion eq = Restrictions.eq(fieldName, fieldValue);
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(modelClass);
		criteria.add(eq);
		list = criteria.list();
		return list;
	}

	@Override
	public void save(T model) throws Exception {
		entityManager.persist(model);
		entityManager.getTransaction().commit();
	}

	@Override
	public void delete(T model) throws Exception {
		//解决Entitymanager em.remove(em.merge(bean))的bug
		Object idObj = null;
		compositeKeyField.setAccessible(true);
		idObj = compositeKeyField.get(model);
		compositeKeyField.setAccessible(false);
		T finded = this.findById(idObj);
		entityManager.remove(entityManager.merge(finded));
		entityManager.getTransaction().commit();
	}

	@Override
	public void update(T model) throws Exception {
		entityManager.persist(entityManager.merge(model));
		entityManager.getTransaction().commit();
	}

	@Override
	public List<T> findAll() throws Exception {
		List<T> list = null;
		list = entityManager.createNamedQuery(modelClass.getSimpleName() + ".findAll", modelClass).getResultList();
		return list;
	}

	@Override
	public T findById(Object idObj) throws Exception {
		T model = null;
		List<T> list = null;
		System.out.println(
				"[compositeKeyFieldName: " + compositeKeyFieldName + ", compositeKeyClass: " + compositeKeyClass + "]");
		Criterion eq = Restrictions.eq(compositeKeyFieldName, idObj);
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(modelClass);
		criteria.add(eq);
		list = criteria.list();
		model = list.get(0);
		entityManager.refresh(model);
		return model;
	}
}
