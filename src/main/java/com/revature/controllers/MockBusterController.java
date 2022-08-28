package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.MockBuster;
import com.revature.services.MockBusterService;

public class MockBusterController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MockBusterService mockBusterService = new MockBusterService();
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String URI = request.getRequestURI();
		System.out.println(URI);
		
		String[] urlSections = URI.split("/");
		
		if (urlSections.length==3) {
			List<Object> list = mockBusterService.getAllMockBustersService();
			
			String json = objectMapper.writeValueAsString(list);
			System.out.println(json);
			
			PrintWriter printWriter = response.getWriter();
			
			printWriter.print("\nHere are all of the MockBusters in stock!\n");
			
			printWriter.print(json);
			
			
			response.setStatus(200);
			
			response.setContentType("application/json");
			
		}else if (urlSections.length==4) {
			try {
				int movieId = Integer.valueOf(urlSections[3]);
				
				MockBuster mockBuster = mockBusterService.getMockBusterByIdService(movieId);
				
				PrintWriter printWriter = response.getWriter();
				
				String json = objectMapper.writeValueAsString(mockBuster);
				
				printWriter.print(json);
				response.setStatus(200);
				response.setContentType("application/json");
				
			} catch (NumberFormatException e) {
				response.setStatus(404);
				return;
			}
		}else {
			response.setStatus(404);
		}			
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		StringBuilder sb = new StringBuilder();
		//Getting ready to construct the message to send to the DAO
		
		BufferedReader reader = request.getReader();
		
		//Reading input to send to the DAO
		String line = reader.readLine();
		
		while(line!=null) {
			sb.append(line);
			line=reader.readLine();
		}
		
		String json = new String(sb);
		
		System.out.println(json);
		
		MockBuster mockBuster = objectMapper.readValue(json, MockBuster.class);
		
		mockBusterService.insertMockBusterService(mockBuster);
		response.setStatus(201);
		
	}
	//Will need to edit this MORE IS BROKEN PLEASE FIXX 
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		BufferedReader reader = request.getReader();
		
		String line = reader.readLine();
		
	    String json = new String(line);
	    
	    MockBuster id = objectMapper.readValue(json, MockBuster.class);
	    
	    int movieid = id.getMovieId();
		
	    mockBusterService.deleteMockBusterService(movieid);
	    
	    response.setStatus(202);
		
		
		
	}
	
 protected void doPatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	 
 }
	
}
