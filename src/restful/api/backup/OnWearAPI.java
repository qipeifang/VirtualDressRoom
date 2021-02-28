package restful.api.backup;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import restful.bean.OnWear;
import restful.bean.Result;

@Path("/onWearAPI")
public class OnWearAPI {

	@Path("/add")
	@POST
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	public Result add(OnWear onWear) {

		return null;
	}

	@Path("/update")
	@POST
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	public Result update(OnWear onWear) {

		return null;
	}

	@Path("/delete")
	@POST
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;charset=UTF-8")
	public Result delete(OnWear onWear) {

		return null;
	}
}
