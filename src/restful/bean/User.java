package restful.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.ws.rs.DefaultValue;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import restful.annotation.validate.Equals;
import restful.annotation.validate.Length;
import restful.annotation.validate.Max;
import restful.annotation.validate.Min;
import restful.annotation.validate.NotNull;
import restful.annotation.validate.PrimaryField;

@Entity
@Table(name = "T_User")
@NamedQueries({
		@NamedQuery(name = "User.findAll", query = "SELECT user FROM User user"),
		@NamedQuery(name = "User.findByUserName", query = "SELECT user FROM User user where user.userName=:userName") })
public class User implements Serializable, Cloneable {
//	private static final long serialVersionUID = -3031631855774223510L;
	@Generated(GenerationTime.INSERT)
	@Column(name = "id", insertable = false, updatable = false)
	private int id;

	@Id
	@NotNull(message = "用户名不能为空")
	@Length(min = 6, max = 16, message = "用户名长度必须为6-16位")
	@PrimaryField
	private String userName;

	@NotNull(message = "用户实名不能为空")
	@Length(min = 6, max = 16, message = "用户实名长度必须为6-16位")
	private String realName;

	@NotNull(message = "密码不能为空")
	@Length(min = 6, max = 16, message = "密码长度必须为6-16位")
	private String password;

	@Min(value = 0, message = "性别必须为男或女")
	@Max(value = 1, message = "性别必须为男或女")
	private int sex = 1;

	@DefaultValue("0") // 0对应false 1对应true
	private int isAdmin = 0;

	@NotNull(message = "头像不能为空")
	private String model;

	@Transient // hibernate默认持久化所有属性, 用此注释排除
	@NotNull(message = "确认密码不能为空")
	@Equals(parameName = "password", message = "密码与确认密码不一致")
	private String rePassword; // 确认密码

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "{\n" + "\t" + "id: " + id + ", \n" + "\t" + "userName: " + userName + ", \n" + "\t"
				+ "realName: " + realName + ", \n" + "\t" + "password: " + password + ", \n" + "\t" + "rePassword: "
				+ rePassword + ", \n" + "\t" + "sex: " + sex + ", \n" + "\t" + "isAdmin: " + isAdmin + ", \n" + "\t"
				+ "model: " + model + ", \n" + "\t" + "" + "\n}";
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 深克隆 返回和User值一样的不同对象
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		User thisObj = (User) super.clone();
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream objOut = null;
		try {
			objOut = new ObjectOutputStream(byteOut);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			objOut.writeObject(thisObj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
		ObjectInputStream objIn = null;
		try {
			objIn = new ObjectInputStream(byteIn);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			thisObj = (User) objIn.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return thisObj;
	}
}