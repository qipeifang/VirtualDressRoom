package restful.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 与数据库中的视图对应的类 请完善对应的Table name和NamedQuery
 */
@Entity
@Table(name = "V_User_Dress")
@NamedQueries({
		// 请添加参数 userName
		@NamedQuery(name = "OnWearViewEntity.findByUserName", query = "SELECT onWearViewEntity FROM OnWearViewEntity onWearViewEntity where onWearViewEntity.userName=:userName") })
public class OnWearViewEntity implements Serializable {

	// 服装层次，默认为 0
	private int layer = 0;

	// 用户相关信息……
	@Id
	private String userName;

	private String realName;

	private String password;

	private int sex = 1;

	private int isAdmin = 0;

	private String model;

	// 服装相关信息……
	@Id
	private int dressId;

	private String dressName;

	private String dress_url;

	private String dressType;

	private double dressPrice;

	public OnWearViewEntity() {
	}

	public OnWearViewEntity(int layer, String userName, String realName, String password, int sex, int isAdmin,
			String model, int dressId, String dressName, String dress_url, String dressType, double dressPrice) {
		super();
		this.layer = layer;
		this.userName = userName;
		this.realName = realName;
		this.password = password;
		this.sex = sex;
		this.isAdmin = isAdmin;
		this.model = model;
		this.dressId = dressId;
		this.dressName = dressName;
		this.dress_url = dress_url;
		this.dressType = dressType;
		this.dressPrice = dressPrice;
	}

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

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

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getDressId() {
		return dressId;
	}

	public void setDressId(int dressId) {
		this.dressId = dressId;
	}

	public String getDressName() {
		return dressName;
	}

	public void setDressName(String dressName) {
		this.dressName = dressName;
	}

	public String getDress_url() {
		return dress_url;
	}

	public void setDress_url(String dress_url) {
		this.dress_url = dress_url;
	}

	public String getDressType() {
		return dressType;
	}

	public void setDressType(String dressType) {
		this.dressType = dressType;
	}

	public double getDressPrice() {
		return dressPrice;
	}

	public void setDressPrice(double dressPrice) {
		this.dressPrice = dressPrice;
	}

}
