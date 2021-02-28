package restful.filter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;


public class LoginFilter implements Filter {

	//	需要验证是否的登录的URL的集合
	private String[] URL = {
			"/suit/jsp/main.jsp"
	};

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest request= (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		boolean isLogined = false;
		Object user = request.getSession().getAttribute("user");
		if(null != user) {
			isLogined = true;
		}
		
		String theVisitedURL = request.getRequestURI();
		System.out.println("theVisitedURL: " + theVisitedURL);
		System.out.println("is logined? " + isLogined);
		
		//	如果未登录，且访问的是要求已经登录的页面，则返回404
		if(!isLogined) {
			for (int i = 0; i < URL.length; i++) {
				String url = URL[i];
				if(url.equals(theVisitedURL)){
					System.out.println("未登录访问" + theVisitedURL);
					response.sendRedirect("/suit/jsp/login.jsp");
					return;
				}
			}
		}
		filterChain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	



	
}
