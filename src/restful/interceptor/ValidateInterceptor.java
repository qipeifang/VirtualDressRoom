package restful.interceptor;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.persistence.Parameter;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;
import javax.ws.rs.ext.WriterInterceptor;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONString;
import restful.annotation.validate.ValidateAllKey;
import restful.annotation.validate.ValidatePrimarykey;
import restful.utils.ValidateResult;
import restful.utils.Validator;

public class ValidateInterceptor implements PreProcessInterceptor {
	@Context
	HttpServletRequest request;

	@Override
	public ServerResponse preProcess(HttpRequest httpRequest, ResourceMethodInvoker resourceMethodInvoker)
			throws Failure, WebApplicationException {
		// TODO Auto-generated method stub
		System.out.println("\nValidateInterceptor:");

		boolean validateAll = false;
		boolean validatePrimary = false;
		try {

			ObjectMapper objectMapper = new ObjectMapper();
			String string = IOUtils.toString(request.getInputStream());
			Method method = resourceMethodInvoker.getMethod();
			Parameter parameter = method.getParameters()[0];
			Class parameterClass = null;
			for (Annotation annotation : parameter.getAnnotations()) {
				if (annotation instanceof ValidatePrimarykey) {
					ValidatePrimarykey validatePrimarykey = (ValidatePrimarykey) annotation;
					parameterClass = validatePrimarykey.objecClass();
					Object object = parameterClass.newInstance();
					Object value = objectMapper.readValue(string, parameterClass);
					System.out.println(parameterClass.getName() + value.toString());
				} else if (annotation instanceof ValidateAllKey) {
					ValidateAllKey validateAllKey = (ValidateAllKey) annotation;
					parameterClass = validateAllKey.objecClass();
					Object object = parameterClass.newInstance();

				}
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
