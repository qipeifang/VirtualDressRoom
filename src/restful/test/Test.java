package restful.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import com.jnetdirect.jsql.s;

import restful.annotation.validate.Equals;
import restful.annotation.validate.Length;
import restful.annotation.validate.Max;
import restful.annotation.validate.Min;
import restful.annotation.validate.NotNull;
import restful.annotation.validate.PrimaryField;
import restful.bean.User;
import restful.utils.ClassUtils;
import restful.utils.ValidateResult;
import restful.utils.Validator;

public class Test {

	@Equals(parameName="s2", message="s1与s2不一致")
	String s1;
	
	@PrimaryField
	String s2;
	
	
	
	public Test(String s1, String s2) {
		super();
		this.s1 = s1;
		this.s2 = s2;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
System.out.println(Validator.getPrimaryFields(Test.class));
		
	}
}
