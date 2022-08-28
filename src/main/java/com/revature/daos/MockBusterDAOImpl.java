package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.revature.controllers.ORMcontroller;
import com.revature.models.MockBuster;

public class MockBusterDAOImpl implements MockBusterDAO{
	
    String databaseURL = "jdbc:postgresql://javafs220725.c7uqwmtdtwsg.us-east-1.rds.amazonaws.com:5432/project1";
	String databaseUN = "postgres";
	String databasePW = "password";
	String databaseDriverName = "org.postgresql.Driver";
	ORMcontroller ormController = new ORMcontroller();

	@Override
	public MockBuster getMockBusterbyID(MockBuster mockbuster, int movieId) {
		return (MockBuster) ormController.getObjectByIdController(mockbuster, movieId, databaseURL, databaseUN, databasePW, databaseDriverName);
	}

	@Override
	public List<Object> getAllMockBusters(MockBuster mockBuster) {
		List<Object> mockbusterList = ormController.retrieveAllObjectsController(mockBuster, databaseURL, databaseUN, databasePW, databaseDriverName);
		return mockbusterList;	
	}
	
	@Override
	public void insertSingleMockBuster(MockBuster mockBuster) {
		ormController.insertObjectController(mockBuster, databaseURL, databaseUN, databasePW, databaseDriverName);	
	}

	@Override
	public void deleteSingleMockBusterByID(MockBuster mockBuster, int movieId) {
		ormController.deleteObjectContoller(mockBuster, movieId, databaseURL, databaseUN, databasePW, databaseDriverName);
	}


	@Override
	public void updateSingleMockBusterByID(MockBuster mockbuster, int movieId, String movieDescr) {
		ormController.updateObjectController(mockbuster, movieId, movieDescr, movieDescr, movieDescr, movieDescr, movieDescr);
	}
	
}
	
