package com.lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet{
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		String key = req.getParameter("key");
		int valueNumber = Integer.parseInt(req.getParameter("valueNumber"));
		String isMocked = req.getParameter("mock");
		String isSynced = req.getParameter("sync");
		
		PrintWriter out = res.getWriter();
		System.out.println("key " + key + " valueNumber " + valueNumber + " isMocked " + isMocked + " isSynced " + isSynced);
		if(isMocked == null) {
			System.out.println(req.getMethod() + " " + req.getHeader("X-FORWARDED-FOR") + " " + req.getHeader("USER-AGENT") + " " + req.getLocale() + " " + req.getParameterNames());
			try {
				FileWriter repository = new FileWriter("D:\\Cursuri\\Cursuri master\\Tehnologii Java\\Tehnologii-Java\\Lab1\\repository.txt");
				for(int i = 0; i <= valueNumber; i++) {
					repository.append("Files in Java might be tricky, but it is fun enough! ");
				}
				
				long GMTDate = req.getDateHeader("If-Unmodified-Since");
				repository.append(Long.toString(GMTDate) + "\n");
				
				repository.flush();
				repository.close();
			}catch(IOException e) {
				System.out.println("An error occurred.");
			      e.printStackTrace();
			}
			out.println("<html><head><title>Server Response</title></head><body>");

		    try {
		        File myObj = new File("D:\\Web development\\Spring misc\\Lab1\\repository.txt");
		        Scanner myReader = new Scanner(myObj);
		        while (myReader.hasNextLine()) {
		        	out.println("<div>" + myReader.nextLine() + "</div>");
		        }
		        myReader.close();
		      } catch (FileNotFoundException e) {
		        System.out.println("An error occurred.");
		        e.printStackTrace();
		      }
			
			out.println("</body></html>");
			
		
		} else {
			out.println("The mock has been done");
		}
	
	
	}
}
