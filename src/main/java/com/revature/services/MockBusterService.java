package com.revature.services;

import java.util.List;

import com.revature.daos.MockBusterDAO;
import com.revature.daos.MockBusterDAOImpl;
import com.revature.models.MockBuster;

public class MockBusterService {
	
	MockBusterDAO mockBusterDAO = new MockBusterDAOImpl();
	MockBuster mockObject = new MockBuster();
	//make sure to pass the empty object over in getMockBusterByIdService
	public MockBuster getMockBusterByIdService(int movieId) {
		return mockBusterDAO.getMockBusterbyID(mockObject, movieId);
	}
	
	public List<Object> getAllMockBustersService(){
		return mockBusterDAO.getAllMockBusters(mockObject);
	}
	
	public void insertMockBusterService(MockBuster mockBuster) {
		mockBusterDAO.insertSingleMockBuster(mockBuster);
	}
	
	public void deleteMockBusterService(int movieId) {
		 mockBusterDAO.deleteSingleMockBusterByID(mockObject, movieId);
	}
	
	public void updateMockBusterService(int id, String newDescr) {
		mockBusterDAO.updateSingleMockBusterByID(mockObject, id, newDescr);
	}

}
