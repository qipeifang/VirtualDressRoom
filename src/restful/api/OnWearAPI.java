package restful.api;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import restful.annotation.Auth;
import restful.bean.OnWearViewEntity;
import restful.bean.OnWear;
import restful.bean.OnWear.OnWearCompositeKey;
import restful.bean.Result;
import restful.bean.User;
import restful.service.extend.OnWearService;
import restful.service.extend.OnWearViewEntityService;
import restful.utils.ValidateResult;
import restful.utils.Validator;

@Path("/onWearAPI")
public class OnWearAPI {

	@Context
	HttpServletRequest request;

	private OnWearService onWearService = new OnWearService();

	private OnWearViewEntityService onWearViewEntityService = new OnWearViewEntityService();

	@Path("/addOnWear")
	@POST
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	@Auth("login")
	public Result add(OnWear onWear) {
		System.out.println(onWear);
		// 前端只传dressId
		User user = (User) request.getSession().getAttribute("user");
		OnWearCompositeKey onWearId = onWear.getOnWearId();
		onWearId.setUserName(user.getUserName());
//		System.out.println("onWearId: " + onWearId);
		onWear.setOnWearId(onWearId);
//		System.out.println("onWear: " + onWear);
		OnWearViewEntity addedOnWear = null;
		ValidateResult validateResult = Validator.validateAllFields(onWear, OnWear.class);
		if (validateResult.isValid() == false) {
			return validateResult.toResult();
		}

		try {
			onWearService.save(onWear);
			addedOnWear = onWearViewEntityService.findByUserNameAndDressId(onWear.getUserName(), onWear.getDressId());
		} catch (EntityExistsException e) {
			e.printStackTrace();
			return new Result(-1, "添加穿着服装失败, 服饰已穿着", addedOnWear, "");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(-1, "添加穿着服装失败", addedOnWear, "");
		}

		return new Result(1, "添加穿着服装成功", addedOnWear, "");
	}

	@Path("/deleteOnWear")
	@POST
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	@Auth("login")
	public Result delete(OnWear onWear) {
		User user = (User) request.getSession().getAttribute("user");
		OnWearCompositeKey onWearId = null;
		OnWearViewEntity deletedOnWear = null;
		try {
			deletedOnWear = onWearViewEntityService.findByUserNameAndDressId(user.getUserName(), onWear.getDressId());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库错误！！！");
		}
		ValidateResult validateResult = Validator.validatePrimaryFields(onWear, OnWear.class);
		if (validateResult.isValid() == false) {
			return validateResult.toResult();
		}
		try {
			onWearId = onWear.getOnWearId();
			onWearId.setUserName(user.getUserName());
			onWear.setOnWearId(onWearId);
			onWearService.delete(onWear);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(-1, "删除穿着服装失败，该服装尚未穿着", deletedOnWear, "");
		}
		return new Result(1, "删除穿着服装成功", deletedOnWear, "");

	}

	@Path("/dressMoveUp")
	@POST
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	public Result moveUp(OnWear onWear) {
		User user = (User) request.getSession().getAttribute("user");
		OnWearViewEntity updatedOnWear = null;
		ValidateResult validateResult = Validator.validatePrimaryFields(onWear, OnWear.class);
		if (validateResult.isValid() == false) {
			return validateResult.toResult();
		}
		OnWearCompositeKey onWearCompositeKey = null;
		try {
			onWearCompositeKey = new OnWearCompositeKey();
			onWearCompositeKey.setUserName(user.getUserName());
			onWearCompositeKey.setDressId(onWear.getDressId());
			onWear = onWearService.findById(onWearCompositeKey);
		} catch (Exception e) {
			return new Result(-1, "更新服装层次失败，该服装尚未穿着", onWear, "");
		}
		onWear.setLayer(onWear.getLayer() + 1);
		try {
			onWearService.update(onWear);
			updatedOnWear = onWearViewEntityService.findByUserNameAndDressId(onWear.getUserName(), onWear.getDressId());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(-1, "更新服装层次失败", updatedOnWear, "");
		}

		return new Result(1, "更新服装层次成功", updatedOnWear, "");
	}

	@Path("/dressMoveDown")
	@POST
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	public Result moveDown(OnWear onWear) {
		User user = (User) request.getSession().getAttribute("user");
		OnWearViewEntity updatedOnWear = null;
		ValidateResult validateResult = Validator.validatePrimaryFields(onWear, OnWear.class);
		if (validateResult.isValid() == false) {
			return validateResult.toResult();
		}
		OnWearCompositeKey onWearCompositeKey = null;
		try {
			onWearCompositeKey = new OnWearCompositeKey();
			onWearCompositeKey.setUserName(user.getUserName());
			onWearCompositeKey.setDressId(onWear.getDressId());
			onWear = onWearService.findById(onWearCompositeKey);
		} catch (Exception e) {
			return new Result(-1, "更新服装层次失败，该服装尚未穿着", onWear, "");
		}
		onWear.setLayer(onWear.getLayer() - 1);
		if (onWear.getLayer() < 0) {
			return new Result(-1, "更新服装层次失败，服装层次不能小于0", onWear, "");
		}
		try {
			onWearService.update(onWear);
			updatedOnWear = onWearViewEntityService.findByUserNameAndDressId(onWear.getUserName(), onWear.getDressId());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(-1, "更新服装层次失败", updatedOnWear, "");
		}
		return new Result(1, "更新服装层次成功", updatedOnWear, "");
	}

	/**
	 * session内当前用户通过T_User和T_Dress的视图查询
	 * 
	 * @return
	 */
	@Path("/getAllOnWear")
	@POST
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	@Auth("login")
	public Result getAll() {
		User user = (User) request.getSession().getAttribute("user");
		ValidateResult validateResult = Validator.validatePrimaryFields(user, User.class);
		if (validateResult.isValid() == false) {
			return validateResult.toResult();
		}
		List<OnWearViewEntity> onWearList = null;
		try {
			onWearList = onWearViewEntityService.findByFieldName("userName", user.getUserName());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Result(-1, "获取用户穿着服装失败，用户不存在", null, "");
		}

		return new Result(1, "获取用户穿着服装成功", onWearList, "");

	}

}
