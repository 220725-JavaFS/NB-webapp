package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.MockBuster;
import com.revature.services.MockBusterService;

public class MockBusterController extends HttpServlet{

	
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
			
			printWriter.print("\nHere are all of the MockBusters in stock!\n\n");
			
			printWriter.print(json);
			
			
			response.setStatus(200);
			
			response.setContentType("application/json");
			
		}else if (urlSections.length==4) {
			try {
				int movieId = Integer.valueOf(urlSections[3]);
				
				MockBuster mockBuster = mockBusterService.getMockBusterByIdService(movieId);
				
				PrintWriter printWriter = response.getWriter();
				
				String json;
				
				if (mockBuster==null) {
					json = null;
				} else {
					json = objectMapper.writeValueAsString(mockBuster);
				}
				
					if(json==null){
						printWriter.print("Sorry, it looks like that Id was very very wrong. You probably entered a negative number. Please try again.");
						response.setStatus(404);
					} else {
						printWriter.print(json);
						response.setStatus(200);
						response.setContentType("application/json");
					}
				
				
				
			} catch (NumberFormatException e) { //Ideal location for Stream API 
				
				response.setStatus(404);
				e.printStackTrace();
				return;
			}
		}else if (urlSections.length==4){
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
	//Finally works!
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String URI = request.getRequestURI();
		System.out.println(URI);
		
		String[] urlSections = URI.split("/");
		if (urlSections.length==4) {
			try {
				int movieId = Integer.valueOf(urlSections[3]);
				
				mockBusterService.deleteMockBusterService(movieId);
				PrintWriter printWriter = response.getWriter();
				printWriter.print("You deleted the MockBuster with the Movie Id of "+movieId+"!");
				response.setStatus(201);
				
			} catch (NumberFormatException e) {
				response.setStatus(404);
				}
				return;
			}
				
	}
	
	@Override
 protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
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
	    int movieId = mockBuster.getMovieId();
	    String movieDescr = mockBuster.getMovieDescr();
		
		mockBusterService.updateMockBusterService(movieId, movieDescr);
		
		PrintWriter printWriter = response.getWriter();
		response.setStatus(200);
		
		
 }
	
}
