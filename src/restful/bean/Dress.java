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
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "T_Dress")
@NamedQueries({ 
		@NamedQuery(name = "Dress.findAll", query = "SELECT dress FROM Dress dress"),
		@NamedQuery(name = "Dress.findByDressType", query = "SELECT dress FROM Dress dress where dress.dressType=:dressType"),
		@NamedQuery(name = "Dress.findBySex", query = "SELECT dress FROM Dress dress where dress.sex=:sex"),
		@NamedQuery(name = "Dress.findByDressName", query = "SELECT dress FROM Dress dress where dress.dressName=:dressName")
})
public class Dress implements Cloneable, Serializable{

	@Id
	private Integer dressId;

	@Column(unique=true)
	private String dressName;

	private String dress_url;

	private String dressType;

	private double dressPrice;

	private boolean sex = false;
	
	public Integer getDressId() {
		return dressId;
	}

	@Override
	public String toString() {
		return "{\n"
				+ "\t" + "dressId: " + dressId + ", \n"
				+ "\t" + "dressName: " + dressName + ", \n"
				+ "\t" + "dress_url: " + dress_url + ", \n"
				+ "\t" + "dressType: " + dressType + ", \n"
				+ "\t" + "dressPrice: " + dressPrice + "\n"
			+ "\n}";
	}

	public void setDressId(Integer dressId) {
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

	public boolean getSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}
	
	/**
	 * 深克隆 返回和Dress值一样的不同对象
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		Dress thisObj = (Dress) super.clone();
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
			thisObj = (Dress) objIn.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return thisObj;
	}

}
