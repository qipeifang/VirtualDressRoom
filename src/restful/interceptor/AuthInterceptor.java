package restful.interceptor;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;
import restful.annotation.Auth;
import restful.bean.Result;
import restful.bean.User;

@SuppressWarnings("deprecation")
public class AuthInterceptor implements PreProcessInterceptor {
	@Context
	HttpServletRequest request;

	@Override
	public ServerResponse preProcess(HttpRequest httpRequest, ResourceMethodInvoker resourceMethodInvoker)
			throws Failure, WebApplicationException {
		System.out.println("AuthInterceptor");
		Method method = resourceMethodInvoker.getMethod();
		// @Auth("login") 需要登录
		// @Auth("admin") 需要是管理员
		boolean needAuth = method.isAnnotationPresent(Auth.class);

		if (needAuth) {
			Auth auth = method.getAnnotation(Auth.class);
			String authValue = auth.value();
			User user = (User) request.getSession().getAttribute("user");
			
			boolean isNeedLogin = "login".equals(authValue);
			boolean isNeedAdmin = "admin".equals(authValue);
			//	如果需要权限校验，在之前也应先进行登录校验
			if(isNeedAdmin)
				isNeedLogin = true;
			
			//	登录校验
			if (isNeedLogin ) {				
				if (user == null) {
					return new ServerResponse(new Result(-1, "请登录!", null, "/jsp/login.jsp"), 200, new Headers<Object>());
				}
			}

			//	权限校验
			if (isNeedAdmin) {
				if(user.getIsAdmin() != 1) {
					return new ServerResponse(new Result(-1, "权限不足!", null, ""), 200, new Headers<Object>());
				}
			}
		}
		return null;
	}
}