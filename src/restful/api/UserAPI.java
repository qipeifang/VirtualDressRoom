package restful.api;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import restful.annotation.Auth;
import restful.bean.Result;
import restful.bean.User;
import restful.database.EM;
import restful.service.extend.UserService;
import restful.utils.ClassUtils;
import restful.utils.ValidateResult;
import restful.utils.Validator;

@Path("/userAPI")
public class UserAPI {
	@Context
	HttpServletRequest request;
	private UserService userService = new UserService();

	/**
	 * 通过userId查询用户后和password比较(划掉） 用userName查询，因为在前端用户尚未登录时可能无法获取数据库中的userId
	 * 
	 * @param user
	 * @return Result对象(data为单个json对象)
	 */
	@POST
	@Path("/login")
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	public Result login(User user) {
		String submitPassword = user.getPassword();
		System.out.println("submitUser" + user);
		User submitInfo = user;
		User findedUser;
		try {
			findedUser = userService.findById(user.getUserName());
			System.out.println("findedUser: " + findedUser);
			user = findedUser;
			if (user.getPassword().equals(submitPassword)) {
				request.getSession().setAttribute("user", user);
				return new Result(1, "登录成功", user, "/jsp/main.jsp");
			} else {
				// 密码错误
				return new Result(-1, "用户名或密码错误", submitInfo, "");
			}
		} catch (Exception e) {
			// 用户名不存在的情况
			return new Result(-1, "用户名或密码错误", submitInfo, "");
		}
	}

	/**
	 * 注册成功,向T_User表添加一条记录
	 * 
	 * @param user
	 * @return Result对象(data为该对象Json串)
	 */
	@POST
	@Path("/register")
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	public Result add(User user) {

		ValidateResult validateResult = Validator.validateAllFields(user, User.class);
		if (validateResult.isValid() == false) {
			return validateResult.toResult();
		}

		// 去掉空格
		user.setUserName(user.getUserName().trim());
		user.setRealName(user.getRealName().trim());
		user.setPassword(user.getPassword().trim());
		user.setModel(user.getModel().trim());

		try {
			System.out.println(user);
			userService.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(-1, "注册失败，用户名已存在", null, "");
		}
		// 是否要加一个判断 判断是否成功插入
		// 从数据库中getUser(插入的user) == 插入的user ? 成功 : 失败
		return new Result(1, "注册成功", user, "/jsp/login.jsp");
	}

	/**
	 * 修改的个人信息
	 * 
	 * @param user
	 * @return Result对象(data为该对象Json串)
	 */
	@POST
	@Path("/updateInfo")
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	@Auth("login") // 怎么判断是否修改自己的信息 -> 存放userId到session 拦截器拦截非法更新请求
	public Result userUpdate(User user) {
		ValidateResult validateResult = Validator.validatePrimaryFields(user, User.class);
		if (validateResult.isValid() == false) {
			return validateResult.toResult();
		}

		String pwd = user.getPassword();
		String rePwd = user.getRePassword();
		if (rePwd != null && pwd != null && !pwd.trim().equals(rePwd.trim()))
			return new Result(-1, "注册失败，密码与确认密码不一致", user, "");

		User submitUserInfo = user;
		User oldUserInfo = null;
		System.out.println("submitUserInfo: " + user);
		User sessionUser = (User) request.getSession().getAttribute("user");
		System.out.println("sessionUser: " + sessionUser);
		if (null != sessionUser) {
			try {
				oldUserInfo = userService.findById(user.getUserName());
				// 使用session中的保存的用户主键userName来进行修改
				oldUserInfo.setUserName(sessionUser.getUserName());
			} catch (Exception e) {
				return new Result(-1, "修改个人信息失败，当前用户不存在", user, "");
			}
			try {
				user.setIsAdmin(oldUserInfo.getIsAdmin());
				ClassUtils.updateUseNotNullField(oldUserInfo, submitUserInfo, oldUserInfo.getClass());
			} catch (Exception e) {
				// 正常情况下能保证 oldUserInfo 和 submitUserInfo 都是 User 的实例，不会出现 Exception
				e.printStackTrace();
			}
			try {
				userService.update(oldUserInfo);
			} catch (Exception e) {
				return new Result(-1, "修改个人信息失败，Exception：" + e.toString(), oldUserInfo, "");
			}
			// 从更新后, 数据库中重新获取user
			try {
				User datebaseUser = userService.findById(oldUserInfo.getUserName());
				System.out.println("databaseUser:" + datebaseUser);
				request.getSession().setAttribute("user", datebaseUser); // 更新session中的user对象
				System.out.println("返回前端:\n" + oldUserInfo);
				return new Result(1, "修改个人信息成功", oldUserInfo, "/jsp/main.jsp");
			} catch (Exception e) {
				System.out.println("存入数据库失败");
				return new Result(1, "修改个人信息失败, 数据库错误", submitUserInfo, "/jsp/main.jsp");
			}
		} else
			return new Result(-1, "修改个人信息失败，用户未登录", user, "");
	}

	@POST
	@Path("/logout")
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	@Auth("login")
	public Result logout() {
		try {
			request.getSession().removeAttribute("user");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(-1, "登出成功", null, "/jsp/login.jsp");
	}

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("application/json;charset=utf-8")
	@Auth("login")
	public Result getFile() {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		FileItem file = null;
		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				List items = upload.parseRequest(request);
				Iterator iterator = items.iterator();
				System.out.println("上传的文件个数: " + items.size());
				while (iterator.hasNext()) {
					FileItem item = (FileItem) iterator.next();
					file = item;
					if (!item.isFormField()) {
						String fileName = item.getName();

						String root = request.getRealPath("/");
						File path = new File(root + "/dressImgs");
						if (!path.exists()) {
							boolean status = path.mkdirs();
						}

						File uploadedFile = new File(path + "/" + fileName);
						System.out.println("上传到: " + uploadedFile.getAbsolutePath());
						item.write(uploadedFile);
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new Result(1, file.getName(), null, "");
	}
	
	@POST
	@Path("/getUserInfo")
	@Produces("application/json;charset=utf-8")
	@Auth("login")
	public Result getUserInfo() {
		User user = (User) request.getSession().getAttribute("user");
		try {
			user = userService.findById(user.getUserName());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(-1, "数据库错误!!!", "", "");
		}
		return new Result(1, "获取当前用户信息成功", user, "");
	}
}
