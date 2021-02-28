package restful.api.backup;

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
import restful.bean.Result;
import restful.bean.User;
import restful.database.EM;
import restful.utils.UpdateUtils;

@Path("/api")
public class Test {
	
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
}
