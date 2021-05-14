package controller;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Category;
import service.CategoryService;

/*
 *default Port : 8090 
 *http://localhost:8090/ResearcherService/api/v2/category/*
*/
@Path("/category") 
public class CategoryContoller {
	private CategoryService categoryService = new CategoryService();
	
	@POST
	@Path("/addcategory")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCategory(HashMap<String, ?> categoryData) {
		return categoryService.addCategory(categoryData);
	}
	
	@PUT
	@Path("/update/{categoryid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCategory(HashMap<String, ?> categoryData, @PathParam("categoryid") Integer categoryid) {
		return categoryService.updateCategory(categoryData,categoryid);
	}
	
	@GET
	@Path("/getcategories")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCategories() {
		return categoryService.getAllCategories();
	}
	
	@GET
	@Path("/getcategory/{categorytid}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategoryById(@PathParam("categorytid") Integer categorytid) {
		return categoryService.getCategoryById(categorytid);
	}
	

	@DELETE
	@Path("/deletebyid/{categorytid}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteById(@PathParam("categorytid") Integer categorytid) {
		return categoryService.deletePayment(categorytid);
	}
}
