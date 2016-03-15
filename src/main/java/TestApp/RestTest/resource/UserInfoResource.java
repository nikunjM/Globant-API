package TestApp.RestTest.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import TestApp.RestTest.Model.FileList;
import TestApp.RestTest.Model.UserInfo;
import TestApp.RestTest.Service.UserInfoDAO;


@Path("Users")
public class UserInfoResource {

	UserInfoDAO dao = new UserInfoDAO();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserInfo> findAll(@QueryParam("city") String city,
			  					   @QueryParam("state") String state,
			  					   @QueryParam("Groupby") String Groupby,
			  					   @QueryParam("profession") String profession,
			  					   @QueryParam("start") int start,
			  					   @QueryParam("size") int size)
	{
		System.out.println("findAll");
		return dao.findAll(city, state, Groupby, profession,start,size);
	}
	
	@POST
	@Path("/Login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserInfo Login(UserInfo user, @Context UriInfo uriInfo) {
		System.out.println("Checking UserInfo"+user.getUsername()+ user.getPassword());
		return dao.login(user.getUsername(),user.getPassword(), uriInfo);
	}
	@POST
	@Path("/Create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserInfo Create(UserInfo user, @Context UriInfo uriInfo) {
		System.out.println("Checking UserInfo"+user.getUsername()+ user.getPassword());
		return dao.create(user.getUsername(),user.getPassword(), uriInfo);
	}
	
	@GET
	@Path("/Check")
	public String check()
	{
		System.out.println("Checking component is working");
		return dao.CheckConnection();
	}
	@POST
	@Path("/GetFiles")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<FileList> getFile(FileList lfile)
	{
		System.out.println("Checking getfiles"+lfile.getFileParentPath());
		return dao.returnFilePath(lfile.getFileParentPath());
	}
	
}
