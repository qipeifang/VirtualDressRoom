package restful.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import restful.bean.Dress;
import restful.bean.DressType;
import restful.bean.OnWear_old;
import restful.bean.User;
import restful.database.EM;

public class UpdateUtils {
	/**
	 * 操作者user
	 */
	private User operateUser;
	/**
	 * 操作目标用户user
	 */
	private User targetUser;
	/**
	 * 具体服饰
	 */
	private Dress dress;
	/**
	 * 服饰类别
	 */
	private DressType dressType;
	/**
	 * 在穿衣服
	 */
	private OnWear_old onWear;

	/**
	 * 数据库操作(DAO)
	 */
	private static final EntityManager entityManager = EM.getEntityManager();
	
	public UpdateUtils() {
		this.operateUser = null;
		this.targetUser = null;
		this.dress = null;
		this.dressType = null;
		this.onWear = null;
	}

	/**
	 * 操作用户(管理员自身 || 其他普通用户 || 普通用户自身)
	 * 
	 * @param operateUser
	 * @param targetUser
	 */
	public UpdateUtils(User operateUser, User targetUser) {
		this.operateUser = operateUser;
		this.targetUser = targetUser;
	}

	/**
	 * 操作服饰类别
	 * 
	 * @param operateUser
	 * @param dressType
	 */
	public UpdateUtils(User operateUser, DressType dressType) {
		this.operateUser = operateUser;
		this.dressType = dressType;
	}

	/**
	 * 操作自身在穿服饰
	 * 
	 * @param operateUser
	 * @param onWear
	 */
	public UpdateUtils(User operateUser, OnWear_old onWear) {
		this.operateUser = operateUser;
		this.onWear = onWear;
	}

	/**
	 * 操作自身在穿服饰 || 管理员操作普通用户在穿服饰
	 * 
	 * @param operateUser
	 * @param targetUser
	 * @param onWear
	 */
	public UpdateUtils(User operateUser, User targetUser, OnWear_old onWear) {
		this.operateUser = operateUser;
		this.targetUser = targetUser;
		this.onWear = onWear;
	}
	/**
	 * 更新具体服饰
	 * @param operateUser
	 * @param dress
	 */
	public UpdateUtils(User operateUser, Dress dress) {
		this.operateUser = operateUser;
		this.dress = dress;
	}

	/**
	 *  <br>调用Setter和Getter 
	 * 	<br>将obj的Set<Field>字段的值设置为origin相应字段
	 *  <br>将Set中字段的值设置为原来的值, 禁止用户修改某些字段
	 * @param obj
	 * @param origin
	 * @param clazz
	 * @param noUpdateFields Set<Field> noUpdateFields为限制修改字段
	 */
	private void setObjToOriginFields(Object obj, Object origin, Class clazz, Set<Field> noUpdateFields) {
		for(Field f : noUpdateFields) {
			String setterMethodName = "set" + Character.toUpperCase(f.getName().charAt(0)) + f.getName().substring(1);
			String getterMethodName = "get" + Character.toUpperCase(f.getName().charAt(0)) + f.getName().substring(1);
			try {
				Method setMethod = clazz.getMethod(setterMethodName, f.getType());
				Method getMethod = clazz.getMethod(getterMethodName);
				Object getterValue = getMethod.invoke(origin);
				setMethod.invoke(obj, getterValue);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 修改用户信息
	 * Set<Field> noUpdateFields为限制修改字段
	 * @param operateUser 管理员
	 * @param targetUser  管理员自身 || 普通用户
	 * @return
	 */
	public boolean updateUserInfo(User operateUser, User targetUser, Set<Field> noUpdateFields) {
		try {
			User autoFilled = autoFillUser(targetUser, noUpdateFields);
			entityManager.persist(EM.getEntityManager().merge(autoFilled));
			entityManager.getTransaction().commit();
		}catch(Exception e) {
			System.out.println("updateUserInfo失败" + " @" + getClass().getName());
			return false;
		}
		return true;
	}

	/**
	 * 修改自身信息
	 * Set<Field> noUpdateFields为限制修改字段
	 * @param operateUser : 普通用户 || 管理员
	 * @param targetUser  : 普通用户 || 管理员
	 * @return
	 */
	public boolean updateSelfInfo(User operateUser, User targetUser, Set<Field> noUpdateFields) {
		try {
			User autoFilled = autoFillUser(targetUser, noUpdateFields);
			entityManager.persist(EM.getEntityManager().merge(autoFilled));
			entityManager.getTransaction().commit();
		}catch(Exception e) {
			System.out.println("updateSelfInfo失败" + " @" + getClass().getName());
			return false;
		}
		
		return true;
	}

	/**
	 * 修改服饰类别dressType
	 * 
	 *Set<Field> noUpdateFields为限制修改字段
	 * @param operator : 管理员
	 * @return
	 */
	public boolean updateDressType(User operateUser, DressType dressType, Set<Field> noUpdateFields) {
		try {
			DressType autoFilled = autoFillDressType(dressType, noUpdateFields);
			entityManager.persist(EM.getEntityManager().merge(autoFilled));
			entityManager.getTransaction().commit();
		}catch(Exception e) {
			System.out.println("updateDressType失败" + " @" + getClass().getName());
			return false;
		}
		
		return true;
	}

	/**
	 * 修改具体服饰dress
	 * Set<Field> noUpdateFields为限制修改字段
	 * @param operator : 管理员 || 普通用户
	 * @return
	 */
	public boolean updateDress(User operateUser, Dress dress, Set<Field> noUpdateFields) {
		try {
			Dress autoFilled = autoFillDress(dress, noUpdateFields);
			entityManager.persist(EM.getEntityManager().merge(autoFilled));
			entityManager.getTransaction().commit();
		}catch(Exception e) {
			System.out.println("updateDress失败" + " @" + getClass().getName());
			return false;
		}
		return true;
	}

	/**
	 * 修改正在穿的服饰onWear
	 * Set<Field> noUpdateFields为限制修改字段
	 * @param operator   : 管理员 || 普通用户
	 * @param targetUser : 普通用户
	 * @return
	 */
	public boolean updateOnwear(User operateUser, User targetUser, User onWear, Set<Field> noUpdateFields) {
//		try {
//			onWear autoFilled = autoFillDress(dress, noUpdateFields);
//			entityManager.persist(EM.getEntityManager().merge(autoFilled));
//			entityManager.getTransaction().commit();
//		}catch(Exception e) {
//			System.out.println("updateDress失败" + " @" + getClass().getName());
//			return false;
//		}
		return false;
	}
	
	
	/*
	 * 自动填充对象 前端获取值后，进行合法性检查后再保存到数据库
	 */
	/**
	 * 根据提交的数据和限制修改的字段 自动填充User
	 * Set<Field> noUpdateFields为限制修改字段
	 * @param src
	 * @param noUpdateFields
	 * @return
	 */
	private User autoFillUser(User src, Set<Field> noUpdateFields) {
		User findedUser = null;
		User updateUser = null;
		try {
			findedUser = entityManager.find(User.class, src.getUserName());
			//用src 非空值填充findedUser
			updateUser = (User) findedUser.clone();
			ClassUtils.updateUseNotNullField(updateUser, src, User.class);
			setObjToOriginFields(updateUser, findedUser, User.class, noUpdateFields);
		} catch (Exception e) {
			System.out.println("autoFillUser失败" + " @" + this.getClass().getName());
		}
		return updateUser;
	}
	/**
	 * 根据提交的数据和限制修改的字段 自动填充Dress
	 * Set<Field> noUpdateFields为限制修改字段
	 * @param src
	 * @param noUpdateFields
	 * @return
	 */
	private Dress autoFillDress(Dress src, Set<Field> noUpdateFields) {
		Dress findedDress = null;
		Dress updateDress = null;
		try {
			findedDress = entityManager.find(Dress.class, src.getDressId());
			//用src 非空值填充 findedDress
			updateDress = (Dress) findedDress.clone();
			ClassUtils.updateUseNotNullField(updateDress, src, Dress.class);
			setObjToOriginFields(updateDress, findedDress, Dress.class, noUpdateFields);
		} catch (Exception e) {
			System.out.println("autoFillDress失败" + " @" + this.getClass().getName());
		}
		return updateDress;
	}
	
	/**
	 * 根据提交的数据和限制修改的字段 自动填充DressType
	 * Set<Field> noUpdateFields为限制修改字段
	 * @param src
	 * @param noUpdateFields
	 * @return
	 */
	private DressType autoFillDressType(DressType src, Set<Field> noUpdateFields) {
		DressType findedDressType = null;
		DressType updateDressType = null;
		try {
			findedDressType = entityManager.find(DressType.class, src.getDressTypeId());
			//用src 非空值填充findedDressType
			updateDressType = (DressType)findedDressType.clone();
			ClassUtils.updateUseNotNullField(updateDressType, src, DressType.class);
			setObjToOriginFields(updateDressType, findedDressType, DressType.class, noUpdateFields);
		}
		catch (Exception e) {
			System.out.println("autoFillDressType失败" + " @" + this.getClass().getName());
		}
		return updateDressType;
	}
	/**
	 * 根据提交的数据和限制修改的字段 自动填充OnWear
	 * Set<Field> noUpdateFields为限制修改字段
	 * @param src
	 * @param noUpdateFields
	 * @return
	 */
	private OnWear_old autoFillOnWear(OnWear_old src, Set<Field> noUpdateFields) {
		OnWear_old updateOnWear = new OnWear_old();
		List<OnWear_old> findedOnWear = null;
//		try {
//			
//		}
		return updateOnWear;
	}

	/*
	 * Getter 和 Setter
	 */
	/**
	 * 直接返回执行操作的user(因为一定不为空)
	 * @return
	 */
	public User getOperateUser() {
		return operateUser;
	}
	public void setOperateUser(User operateUser) {
		this.operateUser = operateUser;
	}
	/**
	 * 从数据库再次获取被更新的User对象
	 * @return
	 */
	public User getTargetUser() {
		User finded = null;
		try {
			finded = entityManager.find(User.class, targetUser.getUserName());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return finded;
	}

	public void setTargetUser(User targetUser) {
		this.targetUser = targetUser;
	}

	/**
	 * 从数据库再次获取被更新的Dress对象
	 * @return
	 */
	public Dress getDress() {
		Dress finded = null;
		try {
			finded = entityManager.find(Dress.class, this.dress.getDressId());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return finded;
	}

	public void setDress(Dress dress) {
		this.dress = dress;
	}

	/**
	 * 从数据库再次获取被更新的DressType对象
	 * @return
	 */
	public DressType getDressType() {
		DressType finded = null;
		try {
			finded = entityManager.find(DressType.class, this.dressType.getDressTypeId());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return finded;
	}

	public void setDressType(DressType dressType) {
		this.dressType = dressType;
	}

	/**
	 * 从数据库再次获取被更新的OnWear对象
	 * @return
	 */
	public OnWear_old getOnWear() {
		OnWear_old finded = null;
		try {
			finded = entityManager.find(OnWear_old.class, this.onWear.getUserName());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return finded;
	}

	public void setOnWear(OnWear_old onWear) {
		this.onWear = onWear;
	}

}
