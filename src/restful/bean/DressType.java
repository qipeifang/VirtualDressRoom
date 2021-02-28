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

import restful.annotation.validate.NotNull;
import restful.annotation.validate.PrimaryField;

@Entity
@Table(name = "T_DressType")
@NamedQueries({ @NamedQuery(name = "DressType.findAll", query = "SELECT dressType FROM DressType dressType"),
		@NamedQuery(name = "DressType.findByDressTypeId", query = "SELECT dressType FROM DressType dressType where dressType.dressTypeId=:dressTypeId") })
public class DressType implements Cloneable, Serializable {

	@Id
	@PrimaryField
	@NotNull(message="服装类别Id不能为空")
	private String dressTypeId;
	@Column(unique = true)
	@NotNull(message="服装类别名称不能为空")
	private String dressTypeName;

	@Override
	public String toString() {
		return "[dressTypeId: " + dressTypeId + ", dressTypeName: " + dressTypeName + "]";
	}

	public String getDressTypeId() {
		return dressTypeId;
	}

	public void setDressTypeId(String dressTypeId) {
		this.dressTypeId = dressTypeId;
	}

	public String getDressTypeName() {
		return dressTypeName;
	}

	public void setDressTypeName(String dressTypeName) {
		this.dressTypeName = dressTypeName;
	}

	/**
	 * 深克隆 返回和Dress值一样的不同对象
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		DressType thisObj = (DressType) super.clone();
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
			thisObj = (DressType) objIn.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return thisObj;
	}
}
