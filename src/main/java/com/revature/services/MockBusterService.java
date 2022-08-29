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
		if(movieId>0){
			return mockBusterDAO.getMockBusterbyID(mockObject, movieId);
			//insert log here
		}else {
			return null;
			//insert log here
		}
		
	}
	
	public List<Object> getAllMockBustersService(){
		return mockBusterDAO.getAllMockBusters(mockObject);
	}
	
	public boolean insertMockBusterService(MockBuster mockBuster) {
		if(mockBuster!=null) {
		mockBusterDAO.insertSingleMockBuster(mockBuster);
		//insert log here
		return true;
		}
		//insert log here
		return false;
	}
	
	public boolean deleteMockBusterService(int movieId) {
		if(movieId>0) {
			//insert log here
		 mockBusterDAO.deleteSingleMockBusterByID(mockObject, movieId);
		 return true;
		}else {
			//insert log here
			return false;
		}
	}
	
	public boolean updateMockBusterService(int id, String newDescr) {
		if(id>0) {
		mockBusterDAO.updateSingleMockBusterByID(mockObject, id, newDescr);
		return true;
			}
		return false;
	}

}
