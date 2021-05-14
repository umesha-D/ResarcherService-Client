package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import model.Researcher;
import model.Review;
import repository.DBConnection;

public class ReviewService {

	private DBConnection connection = new DBConnection();
	public Response addReview(Review review) {		
		try {
		      Connection con = connection.getConnection();
		      if (con == null) return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity("DataBase connectivity Error")
		        .build();

		      String query = "INSERT INTO review(title,details,researcherId,submiterId) VALUES (?, ?, ?, ?)";
		      PreparedStatement preparedStmt = con.prepareStatement(query);

		      preparedStmt.setString(1, review.getTitle());
		      preparedStmt.setString(2, review.getDetails());
		      preparedStmt.setInt(3, review.getResearcherId());
		      preparedStmt.setInt(4, review.getSubmiterId());

		      review.setSumbitedAt("A few seconds ago");
		      preparedStmt.execute();
		      con.close();

		    } catch (Exception e) {
		      return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity(e)
		        .build();
		    }
		    return Response
		      .status(Response.Status.CREATED)
		      .entity(review)
		      .build();
	}

	public Response getAllReviews() {
		List <Review> reviews = new ArrayList<Review> ();

	    try {
	      Connection con = connection.getConnection();
	      if (con == null) return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity("DataBase connectivity Error")
	        .build();

	      String query = "select * from review";
	      Statement stmt = con.createStatement();
	      ResultSet rs = stmt.executeQuery(query);

	      while (rs.next()) {
	        int id = rs.getInt("id");
	        String title = rs.getString("title");   
	        String details = rs.getString("details");
	        int researcherId = rs.getInt("researcherId");
	        int submiterId = rs.getInt("submiterId");
	        String sumbitedAt = rs.getString("sumbitedAt");
	        String updatedAt = rs.getString("updatedAt");
	        
	        Review review = new Review(title, details, researcherId, submiterId);
	        review.setId(id);
	        review.setSumbitedAt(sumbitedAt);
	        review.setUpdatedAt(updatedAt);
	        
	        reviews.add(review);
	      }
	      con.close();

	    } catch (Exception e) {
	      return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity(e)
	        .build();
	    }

	    return Response
	      .status(Response.Status.OK)
	      .entity(reviews)
	      .build();
	}

	public Response getreviewById(Integer reviewid) {
		Review review = null;

	    try {
	      Connection con = connection.getConnection();
	      if (con == null) return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity("DataBase connectivity Error")
	        .build();

	      String query = "select * from review where id = " + reviewid;
	      Statement stmt = con.createStatement();
	      ResultSet rs = stmt.executeQuery(query);

	      while (rs.next()) {
	    	  int id = rs.getInt("id");
		        String title = rs.getString("title");   
		        String details = rs.getString("details");
		        int researcherId = rs.getInt("researcherId");
		        int submiterId = rs.getInt("submiterId");
		        String sumbitedAt = rs.getString("sumbitedAt");
		        String updatedAt = rs.getString("updatedAt");
		        
		        review = new Review(title, details, researcherId, submiterId);
		        review.setId(id);
		        review.setSumbitedAt(sumbitedAt);
		        review.setUpdatedAt(updatedAt);

	      }
	      con.close();

	    } catch (Exception e) {
	      return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity(e)
	        .build();
	    }

	    return Response
	      .status(Response.Status.OK)
	      .entity(review)
	      .build();
	}

	public Response deleteById(Integer reviewid) {
		try {
		      Connection con = connection.getConnection();
		      if (con == null) return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity("DataBase connectivity Error")
		        .build();

		      String query = "DELETE from review WHERE id=?";
		      PreparedStatement preparedStmt = con.prepareStatement(query);

		      preparedStmt.setInt(1, reviewid);

		      preparedStmt.execute();
		      con.close();

		    } catch (Exception e) {
		      return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity(e)
		        .build();
		    }

		    return Response
		      .status(Response.Status.OK)
		      .entity("Succesfully Delected the review data")
		      .build();
	}

	public Response getReviewwithData(int reviewid) {
		Map < String, Object > res = new HashMap < String, Object > ();
		Review review = null;
		Researcher researcher = null;
		int researcherId = -99;
		int submiterId = -99;
		try {
		      Connection con = connection.getConnection();
		      if (con == null) return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity("DataBase connectivity Error")
		        .build();

		      String query = "select * from review where id = "+reviewid;
		      Statement stmt = con.createStatement();
		      ResultSet rs = stmt.executeQuery(query);

		      while (rs.next()) {
		        int id = rs.getInt("id");
		        String title = rs.getString("title");   
		        String details = rs.getString("details");
		        researcherId = rs.getInt("researcherId");
		        submiterId = rs.getInt("submiterId");
		        String sumbitedAt = rs.getString("sumbitedAt");
		        String updatedAt = rs.getString("updatedAt");
		        	        
		        review = new Review(title, details, researcherId, submiterId);
		        review.setId(id);
		        review.setSumbitedAt(sumbitedAt);
		        review.setUpdatedAt(updatedAt);
		      }
		      
		      res.put("review", review);
		      
		      
		      String queryForGetResearcher = "select * from researcher where id = "+researcherId;
		      Statement stmtForGetRes = con.createStatement();
		      ResultSet resultSet = stmtForGetRes.executeQuery(queryForGetResearcher);

		      while (resultSet.next()) {
		        int id = resultSet.getInt("id");
		        String name = resultSet.getString("name");   
		        String email = resultSet.getString("email");
		        String password = resultSet.getString("password");
		        String createdAt = resultSet.getString("createdAt");
		        String updatedAt = resultSet.getString("updatedAt");
		        int researchCategory = resultSet.getInt("researchCategory");
		        
		        researcher = new Researcher(name, email, password, researchCategory);
		        researcher.setId(id);
		        researcher.setCreatedAt(createdAt);
		        researcher.setUpdatedAt(updatedAt);
		        researcher.setResearchCategory(researchCategory);
		      }
		      
		      res.put("researcher", researcher);
		      con.close();
		      
		      Client client = Client.create();

		        WebResource webResource = client
		          .resource("http://localhost:8080/BuyerService/api/v2/buyer/getbuyerbyid/"+submiterId);

		        ClientResponse response = webResource.accept("application/json")
		          .get(ClientResponse.class);

		        if (response.getStatus() != 200) {
		          throw new RuntimeException("Failed : HTTP error code : " +
		            response.getStatus());
		        }
		        Object output = response.getEntity(Object.class);
		        res.put("sumbitby", output);

		    } catch (Exception e) {
		      return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity(e)
		        .build();
		    }

		    return Response
		      .status(Response.Status.OK)
		      .entity(res)
		      .build();
	}

	public Response getReviewwithDataUser(Integer researcherid) {
		List<Map < String, Object >> res = new ArrayList<>();
		
		
		Review review = null;
		Researcher researcher = null;
		int researcherId = -99;
		int submiterId = -99;
		try {
		      Connection con = connection.getConnection();
		      if (con == null) return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity("DataBase connectivity Error")
		        .build();

		      String query = "select * from review where researcherId = "+researcherid;
		      Statement stmt = con.createStatement();
		      ResultSet rs = stmt.executeQuery(query);

		      while (rs.next()) {
		        int id = rs.getInt("id");
		        String title = rs.getString("title");   
		        String details = rs.getString("details");
		        researcherId = rs.getInt("researcherId");
		        submiterId = rs.getInt("submiterId");
		        String sumbitedAt = rs.getString("sumbitedAt");
		        String updatedAt = rs.getString("updatedAt");
		        	        
		        review = new Review(title, details, researcherId, submiterId);
		        review.setId(id);
		        review.setSumbitedAt(sumbitedAt);
		        review.setUpdatedAt(updatedAt);
		        
		        String queryForGetResearcher = "select * from researcher where id = "+researcherId;
			      Statement stmtForGetRes = con.createStatement();
			      ResultSet resultSet = stmtForGetRes.executeQuery(queryForGetResearcher);

			      while (resultSet.next()) {
			        int rid = resultSet.getInt("id");
			        String name = resultSet.getString("name");   
			        String email = resultSet.getString("email");
			        String password = resultSet.getString("password");
			        String createdAt = resultSet.getString("createdAt");
			        String tupdatedAt = resultSet.getString("updatedAt");
			        int researchCategory = resultSet.getInt("researchCategory");
			        
			        researcher = new Researcher(name, email, password, researchCategory);
			        researcher.setId(rid);
			        researcher.setCreatedAt(createdAt);
			        researcher.setUpdatedAt(tupdatedAt);
			        researcher.setResearchCategory(researchCategory);
			      } 
			      
			      Client client = Client.create();

			        WebResource webResource = client
			          .resource("http://localhost:8080/BuyerService/api/v2/buyer/getbuyerbyid/"+submiterId);

			        ClientResponse response = webResource.accept("application/json")
			          .get(ClientResponse.class);

			        if (response.getStatus() != 200) {
			          throw new RuntimeException("Failed : HTTP error code : " +
			            response.getStatus());
			        }
			        Object output = response.getEntity(Object.class);
			        
			        Map < String, Object > data = new HashMap < String, Object > ();
			        data.put("review", review);
			        data.put("sumbitterby", output);
		
			        res.add(data);
		      }
		      
		      con.close();

		    } catch (Exception e) {
		      return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity(e)
		        .build();
		    }

		    return Response
		      .status(Response.Status.OK)
		      .entity(res)
		      .build();
	}


}