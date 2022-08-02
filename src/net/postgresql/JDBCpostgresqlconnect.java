//Please modify the PostgreSQL driver source to return outputs in JSON string format.

package net.postgresql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JDBCpostgresqlconnect {
	
	 private final String url = "jdbc:postgresql://localhost/postgres";
	 private final String user = "postgres";
	 private final String password = "user123";
	 private void connect() {
		 // Query for fetching required data from postgresql database.
		 String SQL = "SELECT user_id,name,age, coalesce(phone, ' ') as phone FROM user_table   order by user_id";
		 try(Connection connection=DriverManager.getConnection(url, user, password);
				 Statement stmt = connection.createStatement();		
	                ResultSet rs = stmt.executeQuery(SQL)
				 ){
			 //If connection to database is successful then call function to display data 
			 display(rs);
			 
			 
			 
		 }catch (SQLException e) {
	            e.printStackTrace();
	        }
	 }
		 
		 private void display(ResultSet rs) throws SQLException{
			 
			 //Creating JSON object 
			 JSONObject jsonObject = new JSONObject();
		      //Creating array
		      JSONArray array = new JSONArray();
		      int statuscode=0;
		      if (rs.next()) {
		    	  statuscode=200;
		      }
		      
		      
			 while(rs.next())
			 {
				 
				 JSONObject record = new JSONObject();
		         //Inserting key-value pairs into the json object
				 
		         record.put("ID", rs.getInt("user_id"));
		         record.put("Name", rs.getString("name"));
		         record.put("Age", rs.getString("age"));
		         record.put("Phone",rs.getString("phone"));
		         array.add(record);
		         
			 }
			 
			 
			 jsonObject.put("status_code:",statuscode );
			 jsonObject.put("Data:", array);
			 //Printing data from JSON object.
			 System.out.println(jsonObject.toJSONString(jsonObject));
	 }
	 
	 
	 public static void main(String[] args) {
		 
		 JDBCpostgresqlconnect sqlconnect= new JDBCpostgresqlconnect();
		 sqlconnect.connect();
	 }

}
