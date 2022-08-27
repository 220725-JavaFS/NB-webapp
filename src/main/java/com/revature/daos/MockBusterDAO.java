package com.revature.daos;

import java.util.List;

import com.revature.models.MockBuster;

public interface MockBusterDAO {
	
	MockBuster getMockBusterbyID (int movieId);
	
	List<MockBuster> getAllMockBusters(MockBuster mockbuster);
	
	MockBuster insertSingleMockBuster(MockBuster mockBuster);
	
	MockBuster deleteSingleMockBusterByID (int movieId, MockBuster mockbuster);
	
	MockBuster updateSingleMockBusterByID (int movieId, String releaseYear);

}
