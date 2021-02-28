package restful.service.extend;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import restful.bean.OnWearViewEntity;
import restful.service.AbstractService;

public class OnWearViewEntityService extends AbstractService<OnWearViewEntity> {
	/**
	 * 	通过联合主键(userName-dressId)查询视图表
	 * @param userName
	 * @param dressId
	 * @return
	 */
	public OnWearViewEntity findByUserNameAndDressId(String userName, Integer dressId) {
		OnWearViewEntity onWearViewEntity = null;
		List<OnWearViewEntity> list = null;
		Criterion userNameEQ = Restrictions.eq("userName", userName);
		Criterion dressIdEQ = Restrictions.eq("dressId", dressId);
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(OnWearViewEntity.class);
		criteria.add(userNameEQ).add(dressIdEQ);
		list = criteria.list();
		onWearViewEntity = list.get(0);
		//绕过jpa缓存，从数据库更新对象状态
		entityManager.refresh(onWearViewEntity);
		return onWearViewEntity;
	}
}
