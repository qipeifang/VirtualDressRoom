package restful.service.extend;

/**
 * 测试api
 */
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import restful.bean.User;
@Path("/test")
public class TestService {
	private UserService userService = new UserService();
	private DressService dressService = new DressService();
	private DressTypeService dressTypeService = new DressTypeService();
	private OnWearService onWearService = new OnWearService();
	
	@POST
	@Path("/listUser")
	@Produces("application/json;charset=UTF-8")
	public List listUser() {
		List<User> findAll = null;
		try {
			findAll = userService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return findAll;
	}
	
	@POST
	@Path("/getUser")
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	public User getUser(User user) {
		User finded = null;
		try {
			finded = userService.findById(user.getUserName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finded;
	}
	
	@POST
	@Path("/upadte")
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	public User updateUser(User user) {
		try {
			userService.update(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		User updated = null;
		try {
			updated = userService.findById(user.getUserName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updated;
	}
	@POST
	@Path("/add")
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	public User addUser(User user) {
		try {
			userService.save(user);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		User saved = null;
		try {
			saved = userService.findById(user.getUserName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saved;
	}
	
	@POST
	@Path("/delete")
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	public User deleteUser(User user) {
		try {
			userService.delete(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
