package com.revature.daos;

import java.util.List;

import com.revature.models.MockBuster;

public interface MockBusterDAO {
	
	MockBuster getMockBusterbyID (MockBuster mockbuster, int movieId);
	
	List<Object> getAllMockBusters(MockBuster mockbuster);
	
	void insertSingleMockBuster(MockBuster mockBuster);
	
	void deleteSingleMockBusterByID (MockBuster mockbuster, int movieId);
	
	void updateSingleMockBusterByID (MockBuster mockbuster, int movieId, String movieDescr);

}
