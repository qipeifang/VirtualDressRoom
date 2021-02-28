package restful.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.ws.rs.DefaultValue;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import restful.annotation.validate.NotNull;
import restful.annotation.validate.PrimaryField;

@Entity
@Table(name = "T_OnWear")
@NamedQueries({ @NamedQuery(name = "OnWear_old.findAll", query = "SELECT onWear FROM OnWear onWear") })
public class OnWear_old implements Cloneable, Serializable {

	@Generated(GenerationTime.INSERT)
	@Column(name = "id", insertable = false, updatable = false)
	private int id;

	// 默认为 0
	@DefaultValue("0")
	private int layer = 0;

	@Transient
	private String userName;
	@Transient
	private Integer dressId;

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setDressId(Integer dressId) {
		this.dressId = dressId;
	}

	@EmbeddedId
	@AttributeOverrides( {
		@AttributeOverride(name = "userName", column = @Column(name = "userName", nullable = false)),
		@AttributeOverride(name = "dressId", column = @Column(name = "dressId", nullable = false)) })
	private OnWearCompositeKey onWearId;

	public OnWear_old() {
	}

	@Override
	public String toString() {
		return "{\n" + "\t" + "id: " + id + ", \n" + "\t" + "userName: " + userName + ", \n" + "\t" + "dressId: "
				+ dressId + ", \n" + "\t" + "layer: " + layer + ", \n" + "\n}";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public String getUserName() {
		return onWearId == null ? this.userName : onWearId.getUserName();
	}

	public Integer getDressId() {
		return onWearId == null ? this.dressId : onWearId.getDressId();
	}

	public OnWearCompositeKey getOnWearId() {
		onWearId.setUserName(userName);
		onWearId.setDressId(dressId);
		return onWearId;
	}

	public void setOnWearId(OnWearCompositeKey onWearId) {
		this.onWearId = onWearId;
	}

	/**
	 * 通过反射把 User、和 Dress 类的对应属性自动赋值到 OnWear 中
	 * 
	 * @param user
	 * @param dress
	 * @throws Exception
	 */
	public OnWear_old(User user, Dress dress) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		// 将 User、Dress 中的属性先统一存到一个 Map 中
		for (Field f : User.class.getDeclaredFields()) {
			map.put(f.getName(), f.get(user));
		}
		for (Field f : Dress.class.getDeclaredFields()) {
			map.put(f.getName(), f.get(dress));
		}
		Set<Entry<String, Object>> s = map.entrySet();
		Iterator<Entry<String, Object>> it = s.iterator();
		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			String name = entry.getKey();
			Object value = entry.getValue();
			// 如果存在对应属性，则写入
			if (OnWear_old.class.getDeclaredField(name) != null)
				OnWear_old.class.getField(name).set(this, value);
		}
	}

	/**
	 * 深克隆 返回和OnWear值一样的不同对象
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		OnWear_old thisObj = (OnWear_old) super.clone();
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
			thisObj = (OnWear_old) objIn.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return thisObj;
	}
	@Embeddable
	public static class OnWearCompositeKey implements Serializable {
		@Column(name = "userName", nullable = false)
		@NotNull(message = "用户名不能为空")
		@PrimaryField
		private String userName;

		@Column(name = "dressId", nullable = false)
		@NotNull(message = "服装不能为空")
		@PrimaryField
		private Integer dressId;

		public OnWearCompositeKey() {

		}

		public OnWearCompositeKey(String userName, Integer dressId) {
			this.userName = userName;
			this.dressId = dressId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public Integer getDressId() {
			return dressId;
		}

		public void setDressId(Integer dressId) {
			this.dressId = dressId;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((userName == null) ? 0 : userName.hashCode());
			result = prime * result + ((dressId == null) ? 0 : dressId.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			OnWearCompositeKey other = (OnWearCompositeKey) obj;
			if (userName == null) {
				if (other.userName != null)
					return false;
			} else if (!userName.equals(other.userName))
				return false;
			if (dressId == null) {
				if (other.dressId != null)
					return false;
			} else if (!dressId.equals(other.dressId))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "OnWearCompositeKey:[userName=" + userName + ", dressId=" + dressId + "]";
		}
	}
}
