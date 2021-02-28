package restful.api;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import restful.bean.Dress;
import restful.bean.DressType;
import restful.bean.OnWear;
import restful.bean.OnWear.OnWearCompositeKey;
import restful.bean.Result;
import restful.bean.User;
import restful.database.EM;
import restful.service.extend.DressService;
import restful.service.extend.OnWearService;
import restful.utils.UpdateUtils;

@Path("/api")
public class Test {
	private OnWearService onWearService = new OnWearService();
	@Context
	HttpServletRequest request;
	@POST
	@Path("/test/listDress")
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	public Result testDress() {
		List<Dress> result = EM.getEntityManager().createNamedQuery("Dress.findAll", Dress.class).getResultList();
		return new Result(0, "查询成功", result, "");
	}
	
	@POST
	@Path("/test/updateUser")
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	public Result testForUpdateUtils(User user) {
		UpdateUtils updateUtils = new UpdateUtils(user, user);
		Set<Field> noFieldSet = new HashSet<Field>();
		boolean success = true;
		System.out.println("前端user: \n" + user);
		try {
			//添加不允许更新的字段 其中updateXxx方法的可以是状态码
			//updateXxx方法内可以根据operateUser和targetUser判断是否有权限
			noFieldSet.add(User.class.getDeclaredField("userName"));
			noFieldSet.add(User.class.getDeclaredField("model"));
			noFieldSet.add(User.class.getDeclaredField("sex"));
//			success = updateUtils.updateUserInfo(user, user, noFieldSet);
			success = updateUtils.updateSelfInfo(user, user, noFieldSet);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
			return new Result(-1, "更新失败", user, "");
		}
		if(success)
			return new Result(0, "更新成功", updateUtils.getTargetUser(), "");
		else 
			return new Result(-1, "更新失败", updateUtils.getTargetUser(), "");
	}
	@POST
	@Path("/test/updateDress")
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	public Result testForUpdateUtils2(Dress dress) {
		User user = (User) request.getSession().getAttribute("user");
		UpdateUtils updateUtils = new UpdateUtils(user, dress);
		Set<Field> noFieldSet = new HashSet<Field>();
		boolean success = true;
		System.out.println("前端user: \n" + user);
		try {
			//添加不允许更新的字段
			noFieldSet.add(Dress.class.getDeclaredField("dressName"));
			noFieldSet.add(Dress.class.getDeclaredField("sex"));
//			success = updateUtils.updateUserInfo(user, user, noFieldSet);
//			success = updateUtils.updateSelfInfo(user, user, noFieldSet);
			success = updateUtils.updateDress(user, dress, noFieldSet);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
			return new Result(-1, "更新失败", dress, "");
		}
		if(success)
			return new Result(0, "更新成功", updateUtils.getDress(), "");
		else 
			return new Result(-1, "更新失败", updateUtils.getDress(), "");
	}
	@POST
	@Path("/test/updateDressType")
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	public Result testForUpdateUtils3(DressType dressType) {
		User user = (User) request.getSession().getAttribute("user");
		UpdateUtils updateUtils = new UpdateUtils(user, dressType);
		Set<Field> noFieldSet = new HashSet<Field>();
		boolean success = true;
		System.out.println("前端user: \n" + user);
		try {
			//添加不允许更新的字段
			noFieldSet.add(DressType.class.getDeclaredField("dressTypeName"));
			success = updateUtils.updateDressType(user, dressType, noFieldSet);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
			return new Result(-1, "更新失败", updateUtils.getDressType(), "");
		}
		if(success)
			return new Result(0, "更新成功", updateUtils.getDressType(), "");
		else 
			return new Result(-1, "更新失败", updateUtils.getDressType(), "");
	}
	
	@POST
	@Path("/test/uploadRecords")
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	public Result uploadRecords(List<Dress> dresses){
		System.out.println(dresses.size());
		for(int i = 0; i < dresses.size(); i++) {
			try {
				DressService dressService = new DressService();
				dressService.update(dresses.get(i));
			} catch (Exception e) {
				return new Result(-1, "上传失败" + e.toString(), dresses, "");
			}
		}
		return new Result(0, "上传成功", dresses, "");
	}
	
	@POST
	@Path("/test/getDress")
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	public Result getDressById(Dress dress) {
		Dress findedDress = null;
		try {
			findedDress = new DressService().findById(dress.getDressId());
			return new Result(0, "获取成功", findedDress, "");
		} catch (Exception e) {
			return new Result(-1, "获取失败" + e.toString(), dress, "");
		}
	}
	@POST
	@Path("/test/getAllOnWear")
	@Produces("application/json;charset=UTF-8")
	public Result getAllOnWear() {
		List<OnWear> onWears = null;
		try {
			onWears = onWearService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(-1, "获取所有OnWear失败", onWears, "");
		}
		return new Result(0, "获取所有OnWear成功", onWears, "");
	}
	@POST
	@Path("/test/getAllOnWearByUserName")
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	public Result getAllOnWearByUserName(OnWear onWear) {
		List<OnWear> onWears = null;
		System.out.println(onWear);
		try {
			onWears = onWearService.findByFieldName("onWearId.userName", onWear.getUserName());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(-1, "通过用户名获取所有OnWear失败", onWears, "");
		}
		return new Result(0, "通过用户名获取所有OnWear成功", onWears, "");
	}
	@POST
	@Path("/test/getAllOnWearByDressId")
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	public Result getAllOnWearByDressId(OnWear onWear) {
		List<OnWear> onWears = null;
		System.out.println(onWear);
		try {
			onWears = onWearService.findByFieldName("onWearId.dressId", onWear.getDressId());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(-1, "通过用户名获取所有OnWear失败", onWears, "");
		}
		return new Result(0, "通过用户名获取所有OnWear成功", onWears, "");
	}
	@POST
	@Path("/test/addOnWear")
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	public Result addOnWear(OnWear onWear) {
		OnWear saved = null;
		System.out.println(onWear);
		try {
			OnWearCompositeKey onWearCompositeKey = new OnWearCompositeKey();
			onWearCompositeKey.setUserName(onWear.getUserName());
			onWearCompositeKey.setDressId(onWear.getDressId());
			onWear.setOnWearId(onWearCompositeKey);
			onWearService.save(onWear);//正常情况下， 联合主键重复会报onWear表主键约束错误
			saved = onWearService.findById(onWear.getOnWearId());//除以特殊字段的查(通过联合主键的一个主键Field),未完成
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(-1, "插入OnWear失败", saved, "");
		}
		return new Result(0, "插入OnWear成功", saved, "");
	}
	@POST
	@Path("/test/deleteOnWear")
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	public Result deleteOnWear(OnWear onWear) {
		OnWear saved = null;
		System.out.println(onWear);
		try {
			OnWearCompositeKey onWearCompositeKey = new OnWearCompositeKey();
			onWearCompositeKey.setUserName(onWear.getUserName());
			onWearCompositeKey.setDressId(onWear.getDressId());
			onWear.setOnWearId(onWearCompositeKey);
			onWearService.delete(onWear);
//			saved = onWearService.findById(onWear.getOnWearId());//除以特殊字段的查(通过联合主键的一个主键Field),未完成
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(-1, "删除OnWear失败", saved, "");
		}
		return new Result(0, "删除OnWear成功", saved, "");
	}
	@POST
	@Path("/test/updateOnWear")
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	public Result updateOnWear(OnWear onWear) {
		OnWear saved = null;
		System.out.println(onWear);
		try {
			OnWearCompositeKey onWearCompositeKey = new OnWearCompositeKey();
			onWearCompositeKey.setUserName(onWear.getUserName());
			onWearCompositeKey.setDressId(onWear.getDressId());
			onWear.setOnWearId(onWearCompositeKey);
			onWearService.update(onWear);//正常情况下， 联合主键重复会报onWear表主键约束错误
			saved = onWearService.findById(onWear.getOnWearId());//除以特殊字段的查(通过联合主键的一个主键Field),未完成
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(-1, "更新OnWear失败", saved, "");
		}
		return new Result(0, "更新OnWear成功", saved, "");
	}
	@POST
	@Path("/test/findByCompositeKey")
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	public Result findByCompositeKey(OnWear onWear) {
		OnWear obj = null;
		System.out.println(onWear);
		try {
			OnWearCompositeKey onWearCompositeKey = new OnWearCompositeKey();
			onWearCompositeKey.setUserName(onWear.getUserName());
			onWearCompositeKey.setDressId(onWear.getDressId());
			onWear.setOnWearId(onWearCompositeKey);
//			onWearService.findById(onWearCompositeKey);//正常情况下， 联合主键重复会报onWear表主键约束错误
			System.out.println(onWearCompositeKey);
			obj = onWearService.findById(onWearCompositeKey);//除以特殊字段的查(通过联合主键的一个主键Field),未完成
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(-1, "通过联合主键获取所有OnWear失败", obj, "");
		}
		return new Result(0, "通过联合主键获取OnWear成功", obj, "");
	}
	
}
