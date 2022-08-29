package com.revature.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.revature.daos.ORMDAOImpl;
import com.revature.models.MockBuster;

class MockBusterServiceTest {
	
	private static int passingMovieId;
	private static int failingMovieId;
	private static MockBuster mockBuster;
	private static MockBusterService mockBusterService;
	private static boolean result;
	private static Object resultObject;
	private static String testDescr;
	private static List<Object> testList;
	


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("Creating mockBusterService to be used for all classes.");
		mockBusterService = new MockBusterService();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("Clearing the service");
		mockBuster = null;
		mockBusterService = null;
	}

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("Clearing the mockBuster Object after every test");
		mockBuster = null;
		passingMovieId = 1;
		failingMovieId = -1;
		testDescr = "test";
	}
	
	@Test
	void TestReturnAllMockBusters() {
		testList = mockBusterService.getAllMockBustersService();
		assertNotEquals(testList, null);
	}

	@Test
	void testGetMockBusterByIdServiceReturnsAMockBuster() {
	mockBuster = new MockBuster(1,"test",1.11,"test",2,"test","test");
	resultObject = mockBusterService.getMockBusterByIdService(passingMovieId);
	assertNotEquals(resultObject, null);		
	}
	
	@Test
	void testGetMockBusterByIdServiceReturnsNull() {
	resultObject = mockBusterService.getMockBusterByIdService(failingMovieId);
	assertEquals(resultObject, null);
	}

	@Test
	void testInsertMockBusterServiceReturnsFalse() {
		System.out.println("Testing if this returns false");
		result = mockBusterService.insertMockBusterService(mockBuster);
		assertFalse(result);
	}
	
	@Test
	void testInsertMockBusterServiceReturnsTrue() {
		mockBuster = new MockBuster(1,"test",1.11,"test",2,"test","test");
		result = mockBusterService.insertMockBusterService(mockBuster);
		assertTrue(result);
	}


	@Test
	void testDeleteMockBusterServiceReturnsFalse() {
		result = mockBusterService.deleteMockBusterService(failingMovieId);
		assertFalse(result);
	}
	
	@Test
	void testDeleteMockBusterServiceReturnsTrue() {
		result = mockBusterService.deleteMockBusterService(passingMovieId);
		assertTrue(result);
	}
	
	@Test
	void testUpdateMockBusterServiceReturnsFalse() {
		mockBuster = new MockBuster(1,"test",1.11,"test",2,"test","test");
		result = mockBusterService.updateMockBusterService(failingMovieId, testDescr);
		assertFalse(result);
	}
	
	@Test
	void testUpdateMockBusterServiceReturnsTrue() {
		mockBuster = new MockBuster(1,"test",1.11,"test",2,"test","test");
		result = mockBusterService.updateMockBusterService(passingMovieId, testDescr);
		assertTrue(result);
	}

}
