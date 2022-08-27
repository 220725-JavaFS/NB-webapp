package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.revature.models.MockBuster;
import com.revature.utils.ConnectionUtil;

public class MockBusterDAOImpl implements MockBusterDAO{

	@Override
	public MockBuster getMockBusterbyID(int movieId) {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sq1 = "SELECT * FROM mockbuster WHERE movie_id = ?;";
			PreparedStatement statement1 = conn.prepareStatement(sq1);
			statement1.setInt(1, movieId);
			
			ResultSet result = statement1.executeQuery();
			
			if(result.next()) {
				MockBuster mockbuster = new MockBuster(
						result.getInt("movie_id"),
						result.getString("title"),
						result.getDouble("price"),
						result.getString("rating"),
						result.getInt("rented_id"),
						result.getString("release_year"),
						result.getString("movie_descr"));
				
				return mockbuster;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<MockBuster> getAllMockBusters(MockBuster ockBuster) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sq1 = "SELECT * FROM mockbuster;";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sq1);
			
			List<MockBuster> mockBusterList = new LinkedList<MockBuster>();
			
			while (result.next()) {
				MockBuster mockBuster = new MockBuster(
						result.getInt("movie_id"),
						result.getString("title"),
						result.getDouble("price"),
						result.getString("rating"),
						result.getInt("rented_id"),
						result.getString("release_year"),
						result.getString("movie_descr"));
				
				mockBusterList.add(mockBuster);
			}
			
			return mockBusterList;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public MockBuster insertSingleMockBuster(MockBuster mockBuster) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sq1 = "INSERT INTO mockbuster (title, price, rating, rented_id, release_year, movie_descr) VALUES (?, ?, ?, ?, ?, ?);";
			
			PreparedStatement statement = conn.prepareStatement(sq1);
			
			int count = 0;
			statement.setString(++count, mockBuster.getTitle());
			statement.setDouble(++count, mockBuster.getPrice());
			statement.setString(++count, mockBuster.getRating());
			statement.setInt(++count, mockBuster.getRentedId());
			statement.setString(++count, mockBuster.getReleaseYear());
			statement.setString(++count, mockBuster.getMovieDescr());
			
			statement.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MockBuster deleteSingleMockBusterByID(int movieId, MockBuster mockBuster) {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sq1 = "DELETE * FROM mockbuster WHERE movie_id = ?;";
			PreparedStatement statement1 = conn.prepareStatement(sq1);
			statement1.setInt(1, movieId);
			
			statement1.execute();
			
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public MockBuster updateSingleMockBusterByID(int movieId, String releaseYear) {
//		try (Connection conn = ConnectionUtil.getConnection()){
//			String sq1 = "UPDATE "
//		}
		return null;
	}
}
