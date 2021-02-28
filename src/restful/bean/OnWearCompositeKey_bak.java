package restful.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

import restful.annotation.validate.NotNull;
import restful.annotation.validate.PrimaryField;

@Embeddable
public class OnWearCompositeKey_bak implements Serializable {
	@Column(name = "userName", nullable = false)
	@NotNull(message = "用户名不能为空")
	@PrimaryField
	private String userName;

	@Column(name = "dressId", nullable = false)
	@NotNull(message = "服装不能为空")
	@PrimaryField
	private Integer dressId;

	public OnWearCompositeKey_bak() {

	}

	public OnWearCompositeKey_bak(String userName, Integer dressId) {
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
		OnWearCompositeKey_bak other = (OnWearCompositeKey_bak) obj;
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
