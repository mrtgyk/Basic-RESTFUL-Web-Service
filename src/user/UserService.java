package user;

import java.util.List;
import javax.websocket.server.PathParam;
import javax.ws.rs.GET; 
import javax.ws.rs.Path; 
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType;  
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Context;
@Path("/UserService") 

public class UserService {  
	
   UserDao userDao = new UserDao();  
   private static final String SUCCESS_RESULT="<result>success</result>";
   private static final String FAILURE_RESULT="<result>failure</result>";
      
  
   @GET 
   @Path("/users") 
   @Produces(MediaType.APPLICATION_XML) 
   public List<User> getUsers(){ 
      return userDao.getAllUsers(); 
   }  
   
     
   @GET
   @Path("/users/getUser/{userid}")
   @Produces(MediaType.APPLICATION_XML)
   public User getUser(@PathParam("userid") int userid){
      return userDao.getUser(userid);
   }
   
   
   @POST
   @Path("/users/createUser")
   @Produces(MediaType.APPLICATION_XML)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String createUser(@FormParam("id") int id,
	   @FormParam("name") String name,
	   @FormParam("profession") String profession,
	   @Context HttpServletResponse servletResponse) throws IOException{
	   User user = new User(id,name,profession);
	   int result = userDao.addUser(user);
	   if(result == 1) {
		   return SUCCESS_RESULT;
	   }
	   return FAILURE_RESULT;
   }
   
   
   @PUT
   @Path("/users/updateUser")
   @Produces(MediaType.APPLICATION_ATOM_XML)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String updateUser(@FormParam("id") int id, 
		   @FormParam("name") String name ,
		   @FormParam("profession") String profession,
		   @Context HttpServletResponse servletResponse) throws IOException{
	   User user = new User(id,name,profession);
	   int result = userDao.updateUser(user);
	   if(result == 1) {
		   return SUCCESS_RESULT;
	   }
	   return FAILURE_RESULT;
   }
   
   @DELETE
   @Path("/users/delete/{userid}")
   @Produces(MediaType.APPLICATION_ATOM_XML)
   public String deleteUser(@PathParam("userid") int userid){
	   int result = userDao.deleteUser(userid);
	   if(result == 1) {
		   return SUCCESS_RESULT;
	   }
	   return FAILURE_RESULT;
   }
   
   
   @OPTIONS
   @Path("/users")
   @Produces(MediaType.APPLICATION_ATOM_XML)
   public String getSupportedOperations() {
	   return "<operations>GET, PUT, POST, DELETE</operations>";
   }
    
     
}
