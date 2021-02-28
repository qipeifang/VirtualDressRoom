package restful.filter;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.core.ServerResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import restful.annotation.Auth;
import restful.bean.Result;
import restful.bean.User;
import restful.database.EM;

public class AuthRequestFilter implements ContainerRequestFilter {
	@Context
	HttpServletRequest request;

	@Override
	public void filter(ContainerRequestContext context) throws IOException {

		
		
		System.out.println("AuthRequestFilter");
		ResourceMethodInvoker methodInvoker = (ResourceMethodInvoker) context
				.getProperty("org.jboss.resteasy.core.ResourceMethodInvoker");
		ObjectMapper mapper = new ObjectMapper();
		// 从请求流中读取Parameter
		String objJson = IOUtils.toString(context.getEntityStream(), "UTF-8");
		Method method = methodInvoker.getMethod();
		boolean needAuth = method.isAnnotationPresent(Auth.class);
		System.out.println("needAuth: " + needAuth);
		boolean executeFailed = false;
		if (needAuth) {
			// User类型 所提交的操作(删除/修改)的目标对象
			User user = null;
			user = (User) request.getSession().getAttribute("user");

			// String类型 当前登录的用户
			Object userId = null;
			// Auth注解中的值 login为普通用户 admin为管理员
			Auth auth = method.getAnnotation(Auth.class);
			String authValue = auth.value();
			System.out.println(authValue);
			// session当前对象的userId
			userId = request.getSession().getAttribute("userId");

			System.out.println("当前session登录用户:  " + ((User) user).getUserName());
			if ("login".equals(authValue) || "admin".equals(authValue)) {
				// 普通用户
				if ("login".equals(authValue)) { // 修改目标对象
					if (user != null) {
						List<User> result = EM.getEntityManager().createNamedQuery("User.findByUserName", User.class)
								.setParameter("userName", user.getUserName()).getResultList();
						if (!result.isEmpty()) {
							User currentUser = result.get(0);
							// 当前用户非管理员 修改其他用户
//							if (currentUser.getIsAdmin() != 1 )
//								if (!((String) userId).equals(((User) user).getUserId())) {
									executeFailed = true;
//								}
						} else
							executeFailed = true;
						// 管理员可以操作其他人信息
					} else
						executeFailed = true;
					// 管理员
					// 判断是否是管理员
				} else if ("admin".equals(authValue)) {
					List<User> result = EM.getEntityManager().createNamedQuery("User.findByUserId", User.class)
							.setParameter("userId", (String) userId).getResultList();
					if (!result.isEmpty()) {
						User adminUser = result.get(0);
						if (adminUser.getIsAdmin() != 1) {
							executeFailed = true;
						}
					}
				}
				if (executeFailed)
					context.abortWith(Response.ok(new Result(-1, "权限不足", null, "")).status(400).build());
				else
					context.setEntityStream(IOUtils.toInputStream(objJson));
			}
		} else
			context.setEntityStream(IOUtils.toInputStream(objJson));
	}

}
