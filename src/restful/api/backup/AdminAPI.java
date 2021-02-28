package restful.api.backup;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import restful.annotation.Auth;
import restful.bean.Result;
import restful.bean.User;
import restful.database.EM;
import restful.utils.ClassUtils;
import restful.utils.ValidateResult;
import restful.utils.Validator;

@Path("/adminAPI")
public class AdminAPI {
	/**
	 * 查询所有用户
	 * 
	 * @return Result对象(data为json对象数组)
	 */
	@POST
	@Path("/getAllUser")
	@Produces("application/json;charset=UTF-8")
	@Auth("admin")
	public Result list() {
		List<User> result = null;
		try {
		 result = EM.getEntityManager().createNamedQuery("User.findAll", User.class).getResultList();
		}catch (Exception e) {
			// TODO: handle exception
			return new Result(-1, "查询所有用户失败", result, "");
		}
		return new Result(1, "查询所有用户成功", result, "");
	}

	/**
	 * 通过userName查询用户
	 * 
	 * @param user
	 * @return Result对象(data为单个json对象)
	 */
	@POST
	@Path("/getUser")
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	@Auth("admin")
	public Result getUser(User user) {
		ValidateResult validateResult = Validator.validatePrimaryFields(user, User.class);
		if(validateResult.isValid() == false) {
			return validateResult.toResult();
		}
		
		List<User> result = EM.getEntityManager().createNamedQuery("User.findByUserName", User.class)
				.setParameter("userName", user.getUserName()).getResultList();
		if (!result.isEmpty()) {
			return new Result(1, "查询成功", result.get(0), "");
		} else
			return new Result(-1, "用户不存在", null, "");
	}

	/**
	 * 向T_User表添加一条记录
	 * 
	 * @param user
	 * @return Result对象(data为该对象Json串)
	 */
	@POST
	@Path("/addUser")
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	@Auth("admin")
	public Result add(User user) {
		//	不需要输入“重复密码
		user.setRePassword(user.getPassword());
		//	直接调用注册接口
		Result result = (new UserAPI()).add(user);	
		result.getDescription().replaceAll("注册", "增加");
		return result;
		
	}

	/**
	 * 修改的对象属性
	 * 
	 * @param user
	 * @return Result对象(data为该对象Json串)
	 */
	@POST
	@Path("/updateUser")
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	@Auth("admin")
	public Result update(User user) {
		//	避免需要再次设置“重复密码”
		user.setRePassword(user.getRePassword());
		
		List<User> list = EM.getEntityManager().createNamedQuery("User.findByUserName", User.class)
				.setParameter("userName", user.getUserName()).getResultList();
		User db_user = null;
		if(list.isEmpty())
		{
			return new Result(-1, "修改用户信息失败，用户不存在", user, "");
		}
		else {			
			try {
				db_user = list.get(0);
//				System.out.println("db_user before:" + db_user);
//				System.out.println("user before:" + user);
				//	用不为 null 的属性进行更新
				ClassUtils.updateUseNotNullField(db_user, user, User.class);
//				System.out.println("db_user after:" + db_user);
//				System.out.println("user after:" + user);
				EM.getEntityManager().persist(EM.getEntityManager().merge(db_user));
				EM.getEntityManager().getTransaction().commit();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return new Result(-1, "修改用户信息失败，数据库连接失败", user, "");
			}
		}
		// 从数据库中getUser(修改的user) == 修改的user ? 成功 : 失败
		return new Result(1, "修改用户信息成功", db_user, "");
	}

	/**
	 * 在T_User删除一条记录
	 * 
	 * @param user
	 * @return
	 */
	@POST
	@Path("/deleteUser")
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	@Auth("admin")
	public Result delete(User user) {
		ValidateResult validateResult = Validator.validatePrimaryFields(user, User.class);
		if(validateResult.isValid() == false) {
			return validateResult.toResult();
		}
		
		
		try {
		
		EM.getEntityManager().remove(EM.getEntityManager().merge(user));
		EM.getEntityManager().getTransaction().commit();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new Result(-1, "删除用户失败，该用户不存在", user, "");
		}
		return new Result(1, "删除用户成功", user, "");
	}
}
