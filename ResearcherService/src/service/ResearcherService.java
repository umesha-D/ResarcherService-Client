package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import model.Category;
import model.Researcher;
import repository.DBConnection;

public class ResearcherService {
	
	private DBConnection connection = new DBConnection();

	public Response addResearcher(HashMap<String, ?> researcherData) throws SQLException {
		Researcher research = new Researcher();
		research.setName((String) researcherData.get("name"));
		research.setEmail((String) researcherData.get("email"));
		research.setPassword((String) researcherData.get("password"));
		research.setResearchCategory((new Long((long) researcherData.get("researchCategory"))).intValue());
		research.setCreatedAt("Just now");
		research.setUpdatedAt("Just yet updated");
		research.setToken("not logged in");
		research.setId(1);
		

		      Connection con = connection.getConnection();
		      if (con == null) return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity("DataBase connectivity Error")
		        .build();

		      String query = "INSERT INTO researcher(name,email,password,researchCategory) VALUES (?, ?, ?, ?)";
		      PreparedStatement preparedStmt = con.prepareStatement(query);

		      preparedStmt.setString(1, (String) researcherData.get("name"));
		      preparedStmt.setString(2, (String) researcherData.get("email"));
		      preparedStmt.setString(3, (String) researcherData.get("password"));
		      preparedStmt.setInt(4, (new Long((long) researcherData.get("researchCategory")).intValue()));


		      preparedStmt.execute();
		      con.close();

		   
		    return Response
		      .status(Response.Status.CREATED)
		      .entity(research)
		      .build();
	}

	public Response getAllResearchers() throws SQLException {
		List <Researcher> researchers = new ArrayList<Researcher> ();


	      Connection con = connection.getConnection();
	      if (con == null) return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity("DataBase connectivity Error")
	        .build();

	      String query = "select * from researcher";
	      Statement stmt = con.createStatement();
	      ResultSet rs = stmt.executeQuery(query);

	      while (rs.next()) {
	        int id = rs.getInt("id");
	        String name = rs.getString("name");   
	        String email = rs.getString("email");
	        String password = rs.getString("password");
	        String createdAt = rs.getString("createdAt");
	        String updatedAt = rs.getString("updatedAt");
	        int researchCategory = rs.getInt("researchCategory");
	        
	        Researcher researcher = new Researcher(name, email, password, researchCategory);
	        researcher.setId(id);
	        researcher.setCreatedAt(createdAt);
	        researcher.setUpdatedAt(updatedAt);
	        
	        researchers.add(researcher);
	      }
	      con.close();

	

	    return Response
	      .status(Response.Status.OK)
	      .entity(researchers)
	      .build();
	}

	public Response getResearcherById(Integer researcherid) throws SQLException {
		Researcher researcher = null;

	      Connection con = connection.getConnection();
	      if (con == null) return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity("DataBase connectivity Error")

	        .build();

	      String query = "select * from researcher where id = " + researcherid;
	      Statement stmt = con.createStatement();
	      ResultSet rs = stmt.executeQuery(query);

	      while (rs.next()) {
	    	  	int id = rs.getInt("id");
		        String name = rs.getString("name");   
		        String email = rs.getString("email");
		        String password = rs.getString("password");
		        String createdAt = rs.getString("createdAt");
		        String updatedAt = rs.getString("updatedAt");
		        int researchCategory = rs.getInt("researchCategory");
		        
		        researcher = new Researcher(name, email, password, researchCategory);
		        researcher.setId(id);
		        researcher.setCreatedAt(createdAt);
		        researcher.setUpdatedAt(updatedAt);
		        researcher.setId(id);

	      }
	      con.close();

	

	    return Response
	      .status(Response.Status.OK)
	      .entity(researcher)
	      .build();
	}

	public Response deleteById(Integer researcherid) throws SQLException {
	
		      Connection con = connection.getConnection();
		      if (con == null) return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity("DataBase connectivity Error")
		        .build();

		      String query = "DELETE from researcher WHERE id=?";
		      PreparedStatement preparedStmt = con.prepareStatement(query);

		      preparedStmt.setInt(1, researcherid);

		      preparedStmt.execute();
		      con.close();

		

		    return Response
		      .status(Response.Status.OK)
		      .entity("Succesfully Delected the Researcher data")
		      .build();
	}

	public Response getResearcherWithCat(Integer researcherid) throws SQLException {
		Researcher researcher = null;
		Category category = null;
		Map < String, Object > res = new HashMap < String, Object > ();
		int researchCategory = -99;

	
	      Connection con = connection.getConnection();
	      if (con == null) return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity("DataBase connectivity Error")
	        .build();

	      String query = "select * from researcher where id = " + researcherid;
	      Statement stmt = con.createStatement();
	      ResultSet rs = stmt.executeQuery(query);

	      while (rs.next()) {
	    	  	int id = rs.getInt("id");
		        String name = rs.getString("name");   
		        String email = rs.getString("email");
		        String password = rs.getString("password");
		        String createdAt = rs.getString("createdAt");
		        String updatedAt = rs.getString("updatedAt");
		        researchCategory = rs.getInt("researchCategory");
		        
		        researcher = new Researcher(name, email, password, researchCategory);
		        researcher.setId(id);
		        researcher.setCreatedAt(createdAt);
		        researcher.setUpdatedAt(updatedAt);
		        researcher.setId(id);

		        res.put("researcher", researcher);
	      }
	      
	      
	      String queryForGetCategory = "select * from category where id = " + researchCategory;
	      Statement stmtForCategory = con.createStatement();
	      ResultSet resultSet = stmtForCategory.executeQuery(queryForGetCategory);

	      while (resultSet.next()) {
	    	  	int id = resultSet.getInt("id");
		        String categoryName = resultSet.getString("categoryName");   
		        String description = resultSet.getString("description");
		        
		        category = new Category(categoryName, description);
		        category.setId(id);
		        category.setId(id);

	      }
	      
	      res.put("category", category);
	      con.close();

	 

	    return Response
	      .status(Response.Status.OK)
	      .entity(res)
	      .build();
	}

	public Response getResearcherWithCatAll() throws SQLException {
		Researcher researcher = null;
		Category category = null;
		List<Map < String, Object >> finalResul = new ArrayList<>();
		
		Map < String, Object > res = new HashMap < String, Object > ();
		int researchCategory = -99;

	
	      Connection con = connection.getConnection();
	      if (con == null) return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity("DataBase connectivity Error")
	        .build();

	      String query = "select * from researcher";
	      Statement stmt = con.createStatement();
	      ResultSet rs = stmt.executeQuery(query);

	      while (rs.next()) {
	    	  	int id = rs.getInt("id");
		        String name = rs.getString("name");   
		        String email = rs.getString("email");
		        String password = rs.getString("password");
		        String createdAt = rs.getString("createdAt");
		        String updatedAt = rs.getString("updatedAt");
		        researchCategory = rs.getInt("researchCategory");
		        
		        researcher = new Researcher(name, email, password, researchCategory);
		        researcher.setId(id);
		        researcher.setCreatedAt(createdAt);
		        researcher.setUpdatedAt(updatedAt);
		        researcher.setId(id);

		        res.put("researcher", researcher);
		        
		        String queryForGetCategory = "select * from category where id = " + researchCategory;
			      Statement stmtForCategory = con.createStatement();
			      ResultSet resultSet = stmtForCategory.executeQuery(queryForGetCategory);

			      while (resultSet.next()) {
			    	  	int idT = resultSet.getInt("id");
				        String categoryName = resultSet.getString("categoryName");   
				        String description = resultSet.getString("description");
				        
				        category = new Category(categoryName, description);
				        category.setId(idT);
				        
			      }
			      
			      res.put("category", category);
			      finalResul.add(res);
	      }
	      
	      
	      
	      con.close();

	    return Response
	      .status(Response.Status.OK)
	      .entity(finalResul)
	      .build();
	}

	public Response updateResearcher(HashMap<String, ?> researcherData, Integer researcherid) throws SQLException {
			  Connection con = connection.getConnection();
		      if (con == null) return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity("DataBase connectivity Error")
		        .build();
		  
		  String query = "UPDATE researcher SET name=?,email=?,password=?,updatedAt=CURRENT_TIMESTAMP,researchCategory=? WHERE id=?";
		  PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		  preparedStmt.setString(1, (String) researcherData.get("name"));
		  preparedStmt.setString(2, (String) researcherData.get("email"));
		  preparedStmt.setString(3, (String) researcherData.get("password"));
		  preparedStmt.setInt(4, (new Long((long) researcherData.get("researchCategory"))).intValue());
		  preparedStmt.setInt(5, researcherid);
		  
		  preparedStmt.execute();
		  con.close();

		  
		  return Response
			      .status(Response.Status.CREATED)
			      .entity("Researcher updated")
			      .build();
	}

	public Response login(String email, String password) throws SQLException {
		String currentPassword = "";
	    
	      Connection con = connection.getConnection();
	      if (con == null) return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity("DataBase connectivity Error")
	        .build();

	      String query = "select * from researcher where email = '"+ email +"'";
	      Statement stmt = con.createStatement();
	      ResultSet rs = stmt.executeQuery(query);

	      while (rs.next()) {
	         currentPassword = rs.getString("password");
	      }
 
	      
	      if(!currentPassword.equals(password)) {
	    	  return Response
	    		        .status(Response.Status.FORBIDDEN)
	    		        .entity("Invalid credetials")
	    		        .build();
	      }else {
	    	  Random rand = new Random();
	    	  double int_random = rand.nextDouble() * 6543793F; 
	    	  
	    	  String createToken = "UPDATE researcher SET token = ?,updatedAt=CURRENT_TIMESTAMP WHERE email=?";
	    	  PreparedStatement preparedStmt = con.prepareStatement(createToken);
	    	 
	    	  preparedStmt.setString(1, String.valueOf(int_random));
	    	  preparedStmt.setString(2, email);

	    	  preparedStmt.execute();
	    	  con.close();
	    	  
	    	  Map<String, String> tokenResult = new HashMap<String, String>(); 
	    	  tokenResult.put("token",  String.valueOf(int_random)+email);
	    	  tokenResult.put("metadata",  "Add email and token in seperate headers when making requests.");
	    	  
	    	  return Response
	    		        .status(Response.Status.OK)
	    		        .entity(tokenResult)
	    		        .build(); 
	      }
	     
	}

	public Response vertify(String email, String token) throws SQLException {
		String tokenFromDB = "";
	    
	      Connection con = connection.getConnection();
	      if (con == null) return Response
	        .status(Response.Status.FORBIDDEN)
	        .entity("DataBase connectivity Error")
	        .build();

	      String query = "select * from researcher where email = '"+ email +"'";
	      Statement stmt = con.createStatement();
	      ResultSet rs = stmt.executeQuery(query);

	      while (rs.next()) {
	    	  tokenFromDB = rs.getString("token");
	      }
 
	      if(tokenFromDB.equals(token)) {
	    	  return Response
	    		        .status(Response.Status.OK)
	    		        .entity("authenticated")
	    		        .build(); 
	      }else {
	    	  return Response
	    		        .status(Response.Status.FORBIDDEN)
	    		        .entity("Invalid token")
	    		        .build();
	      }

	}

	public Response logout(String email, String token) throws SQLException {
		  String currentToken = "";
	    
	      Connection con = connection.getConnection();
	      if (con == null) return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity("DataBase connectivity Error")
	        .build();

	      String query = "select * from researcher where email = '"+ email +"'";
	      Statement stmt = con.createStatement();
	      ResultSet rs = stmt.executeQuery(query);

	      while (rs.next()) {
	         currentToken = rs.getString("token");
	      }
 
	      
	      if(!currentToken.equals(token)) {
	    	  return Response
	    		        .status(Response.Status.FORBIDDEN)
	    		        .entity("Invalid credetials")
	    		        .build();
	      }else {	  
	    	  String createToken = "UPDATE researcher SET token = ?,updatedAt=CURRENT_TIMESTAMP WHERE email=?";
	    	  PreparedStatement preparedStmt = con.prepareStatement(createToken);
	    	 
	    	  preparedStmt.setString(1, null);
	    	  preparedStmt.setString(2, email);

	    	  preparedStmt.execute();
	    	  con.close();
	    	  
	    	  Map<String, String> tokenResult = new HashMap<String, String>(); 
	    	  tokenResult.put("status",  "logout succesfully");
	    	  
	    	  return Response
	    		        .status(Response.Status.OK)
	    		        .entity(tokenResult)
	    		        .build(); 
	      }
	}

}
