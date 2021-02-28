package restful.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import restful.annotation.Auth;
import restful.bean.Dress;
import restful.bean.DressType;
import restful.bean.Result;
import restful.bean.User;
import restful.service.extend.DressService;
import restful.service.extend.DressTypeService;
import restful.utils.ClassUtils;
import restful.utils.ValidateResult;
import restful.utils.Validator;

@Path("/dressAPI")
public class DressAPI {
	@Context
	HttpServletRequest request;
	private DressService dressService = new DressService();
	private DressTypeService dressTypeService = new DressTypeService();

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
			dressTypeService.save(dressType);
			return new Result(1, "服装类别添加成功", dressType, "");
		} catch (Exception e) {
			return new Result(-1, "服装类别添加失败，该服装类别已存在", dressType, "");
		}
	}

	@Path("/updateDressType")
	@POST
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	@Auth("admin")
	public Result updateType(DressType dressType) {

		// 无空属性，尝试写入数据库
		try {
			dressTypeService.update(dressType);
			return new Result(1, "服装类别修改成功", dressType, "");
		} catch (Exception e) {
			return new Result(-1, "服装类别修改失败，该服装类别不存在", dressType, "");
		}

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
			dressTypeService.delete(dressType);
			return new Result(1, "服装类别删除成功", dressType, "");
		} catch (Exception e) {
			return new Result(-1, "服装类别删除失败，该类别不存在", dressType, "");
		}

	}

	@Path("/getAllDressType")
	@POST
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	@Auth("login")
	public Result getAllType() {
		List<DressType> list = null;
		try {
			list = dressTypeService.findAll();
			return new Result(1, "获取所有服装类别成功", list, "");
		} catch (Exception e) {
			return new Result(-1, "获取所有服装类别失败，与服务器连接失败", null, "");
		}

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
			dressService.save(dress);
			return new Result(1, "服装添加成功", dress, "");
		} catch (Exception e) {
			return new Result(-1, "服装添加失败", dress, "");
		}
	}

	@Path("/updateDress")
	@POST
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	@Auth("admin")
	public Result updateDress(Dress dress) {

		System.out.println("front end Dress: " + dress);
		Dress findedDress = null;
		try {
			findedDress = dressService.findById(dress.getDressId());
			System.out.println("findedDress(通过id查找):" + findedDress);
		} catch (Exception e) {
			System.out.println("通过id为找到, 继续通过name找");
//			return new Result(-1, "服装修改失败，该服装不存在", dress, "");
		}
		// 前台传过来可能不带主键id，用dressName查询带主键的实例用于删除
		if (findedDress == null) {
			try {
				List<Dress> dresses = dressService.findByFieldName("dressName", dress.getDressName());
				findedDress = dresses.get(0);
				System.out.println("findedDress(通过name查找):" + findedDress);
			} catch (Exception e) {
				return new Result(-1, "服装修改失败，该服装不存在", dress, "");
			}
		}
		try {
			System.out.println("findedDress 工具类更新前:" + findedDress);
			System.out.println("dress:" + dress);
			ClassUtils.updateUseNotNullField(findedDress, dress, Dress.class);
			System.out.println("findedDress 工具类更新后:" + findedDress);
			System.out.println("dress:" + dress);
			dressService.update(findedDress);
			return new Result(1, "服装修改成功", findedDress, "");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(-1, "服装修改失败", dress, "");
		}
	}

	@Path("/deleteDress")
	@POST
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	@Auth("admin")
	public Result deleteDress(Dress dress) {

		Dress findedDress = null;
		try {
			findedDress = dressService.findById(dress.getDressId());
		} catch (Exception e) {
			System.out.println("通过id为找到, 继续通过name找");
//			return new Result(-1, "服装删除失败，该服装不存在", dress, "");
		}
		// 前台传过来可能不带主键id，用dressName查询带主键的实例用于删除
		if (findedDress == null) {
			try {
				List<Dress> dresses = dressService.findByFieldName("dressName", dress.getDressName());
				findedDress = dresses.get(0);
			} catch (Exception e) {
				return new Result(-1, "服装删除失败，该服装不存在", dress, "");
			}
		}
		try {
			dressService.delete(findedDress);
			return new Result(1, "服装删除成功", dress, "");
		} catch (Exception e) {
			return new Result(-1, "服装删除失败，数据库错误" + e.toString(), dress, "");
		}
	}

	/**
	 * 
	 * 	通过当前用户的性别过滤服饰
	 * @return
	 */
	@Path("/getAllDress")
	@POST
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	@Auth("login")
	public Result getAllDress() {
		User user = (User) request.getSession().getAttribute("user");
		List<Dress> list = null;
		try {
			list = dressService.findByFieldName("sex", user.getSex()==0 ? false : true);
			return new Result(1, "获取所有服装成功", list, "");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(-1, "获取所有服装失败，与服务器连接失败", null, "");
		}
	}

}
