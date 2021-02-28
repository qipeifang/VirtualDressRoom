package restful.api.backup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.commons.lang.ObjectUtils.Null;

import restful.annotation.Auth;
import restful.bean.Dress;
import restful.bean.DressType;
import restful.bean.Result;
import restful.database.EM;
import restful.utils.ClassUtils;
import restful.utils.ValidateResult;
import restful.utils.Validator;

@Path("/dressAPI")
public class DressAPI {

	// 服装类别相关
	@Path("/addDressType")
	@POST
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	@Auth("admin")
	public Result addType(DressType dressType) {

		ValidateResult validateResult = Validator.validateAllFields(dressType, DressType.class);
		if (validateResult.isValid() == false) {
			return validateResult.toResult();
		}

		// 尝试写入数据库
		try {
			EM.getEntityManager().persist(dressType);
			EM.getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			return new Result(-1, "服装类别添加失败，该服装类别已存在", dressType, "");
		}

		return new Result(1, "服装类别添加成功", dressType, "");
	}

	@Path("/updateDressType")
	@POST
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	@Auth("admin")
	public Result updateType(DressType dressType) {


			// 无空属性，尝试写入数据库
			try {
				EM.getEntityManager().merge(dressType);
				EM.getEntityManager().getTransaction().commit();
			} catch (Exception e) {
				// TODO: handle exception
				return new Result(-1, "服装类别修改失败，该服装类别不存在", dressType, "");
			}
		
		return new Result(1, "服装类别修改成功", dressType, "");
	}

	@Path("/deleteDressType")
	@POST
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	@Auth("admin")
	public Result deleteType(DressType dressType) {
		ValidateResult validateResult = Validator.validateAllFields(dressType, DressType.class);
		if (validateResult.isValid() == false) {
			return validateResult.toResult();
		}
		try {
			EM.getEntityManager().remove(EM.getEntityManager().merge(dressType));
			EM.getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			return new Result(-1, "服装类别删除失败，该类别不存在", dressType, "");
		}
		return new Result(1, "服装类别删除成功", dressType, "");

	}

	@Path("/getAllDressType")
	@POST
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	@Auth("login")
	public Result getAllType() {
		List<DressType> list = null;
		try {
			list = (ArrayList<DressType>) EM.getEntityManager().createNamedQuery("DressType.findAll", DressType.class)
					.getResultList();
			;
		} catch (Exception e) {
			// TODO: handle exception
			return new Result(-1, "获取所有服装类别失败，与服务器连接失败", null, "");
		}

		return new Result(1, "获取所有服装类别成功", list, "");
	}

	// 服装相关
	@Path("/addDress")
	@POST
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	@Auth("admin")
	public Result addDress(Dress dress) {

		ValidateResult validateResult = Validator.validateAllFields(dress, Dress.class);
		if (validateResult.isValid() == false) {
			return validateResult.toResult();
		}

		try {
			EM.getEntityManager().persist(dress);
			EM.getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			return new Result(-1, "服装添加失败", dress, "");
		}
		return new Result(1, "服装添加成功", dress, "");
	}

	@Path("/updateDress")
	@POST
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	@Auth("admin")
	public Result updateDress(Dress dress) {

//		判断是否有空属性
		Set<Entry<String, Object>> fieldsSet = ClassUtils.getAllFieldAsSet(dress, Dress.class);
		Iterator<Entry<String, Object>> it = fieldsSet.iterator();
		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value == null) {
				return new Result(-1, "服装修改失败，" + key + " 属性不能为空", dress, "");
			}
		}

		try {
			EM.getEntityManager().merge(dress);
			EM.getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			return new Result(-1, "服装修改失败", dress, "");
		}
		return new Result(1, "服装修改成功", dress, "");
	}

	@Path("/deleteDress")
	@POST
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	@Auth("admin")
	public Result deleteDress(Dress dress) {

		try {
			// 前台传过来可能不带主键id，用dressName查询带主键的实例用于删除
			Dress deleteDress = EM.getEntityManager().createNamedQuery("Dress.findByDressName", Dress.class)
					.setParameter("dressName", dress.getDressName()).getSingleResult();
			EM.getEntityManager().remove(deleteDress);
			EM.getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			return new Result(-1, "服装删除失败，该服装不存在", dress, "");
		}
		return new Result(1, "服装删除成功", dress, "");
	}

	@Path("/getAllDress")
	@POST
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	@Auth("login")
	public Result getAllDress() {
		ArrayList<Dress> list = null;
		try {
			list = (ArrayList<Dress>) EM.getEntityManager().createNamedQuery("Dress.findAll", Dress.class)
					.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			return new Result(-1, "获取所有服装失败，与服务器连接失败", null, "");
		}
		return new Result(1, "获取所有服装成功", list, "");
	}

}
