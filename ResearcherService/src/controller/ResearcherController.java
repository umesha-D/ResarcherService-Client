package controller;

import java.sql.SQLException;
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

import model.Researcher;
import service.ResearcherService;

/*
 *default Port : 8090 
 *http://localhost:8090/ResearcherService/api/v2/researcher/*
*/
@Path("/researcher") 
public class ResearcherController {
	private ResearcherService researcherService = new ResearcherService();
	
	@GET
	@Path("/getresearchers")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllResearchers() throws SQLException {
		return researcherService.getAllResearchers();
	}
	
	@GET
	@Path("/getresearcher/{researcherid}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResearcherById(@PathParam("researcherid") Integer researcherid) throws SQLException {
		return researcherService.getResearcherById(researcherid);
	}
	
	@DELETE
	@Path("/deletebyid/{researcherid}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteById(@PathParam("researcherid") Integer researcherid) throws SQLException {
		return researcherService.deleteById(researcherid);
	}
	
	@PUT
	@Path("/update/{researcherid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateResearcher(HashMap<String, ?> researcherData, @PathParam("researcherid") Integer researcherid) throws SQLException {
		return researcherService.updateResearcher(researcherData, researcherid);
	}
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addResearcher(HashMap<String, ?> researcherData) throws SQLException {
		return researcherService.addResearcher(researcherData);
	}
	
	@GET
	@Path("/getresearchercat/{researcherid}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResearcherWithCat(@PathParam("researcherid") Integer researcherid) throws SQLException {
		return researcherService.getResearcherWithCat(researcherid);
	}

	
	@GET
	@Path("/getresearchercat/all")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResearcherWithCatAll() throws SQLException {
		return researcherService.getResearcherWithCatAll();
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(HashMap<String, ?> Data) throws SQLException {
		String email = (String) Data.get("email");
		String password = (String) Data.get("password");
		return researcherService.login(email, password);
	}
	
	@POST
	@Path("/researchertokenvertify")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response researcherTokenVertify(HashMap<String, ?> Data) throws SQLException {
		String token = (String) Data.get("token");
		String email = (String) Data.get("email");
		return researcherService.vertify(email, token);
	}
	
	@POST
	@Path("/logout")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response logout(HashMap<String, ?> Data) throws SQLException {
		String email = (String) Data.get("email");
		String token = (String) Data.get("token");
		return researcherService.logout(email, token);
	}
}
