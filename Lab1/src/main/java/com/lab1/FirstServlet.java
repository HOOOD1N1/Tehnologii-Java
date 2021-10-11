package com.lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Scanner;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {

		String key = req.getParameter("key");
		int valueNumber = Integer.parseInt(req.getParameter("valueNumber"));
		String isMocked = req.getParameter("mock");
		String isSynced = req.getParameter("sync");
		Date date = new Date();

		PrintWriter out = res.getWriter();
		File myObj = new File("D:\\Cursuri\\Cursuri master\\Tehnologii Java\\Tehnologii-Java\\Lab1\\repository.txt");
		FileWriter repository = new FileWriter(
				"D:\\Cursuri\\Cursuri master\\Tehnologii Java\\Tehnologii-Java\\Lab1\\repository.txt", true);

		FileSort fileSort = new FileSort(myObj, out);

		System.out.println(
				req.getMethod() + " " + date + " " + req.getHeader("USER-AGENT") + " " + req.getLocale() + " ");

		Enumeration enumeration = req.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String parameterName = (String) enumeration.nextElement();
			System.out.println(parameterName + " ");
		}
		System.out.println("\n");

		if (isMocked == null) {
			if (isSynced == null) {

				try {
					for (int i = 0; i < valueNumber; i++) {
						repository.write(key + " ");
					}

					repository.append(date + "\n");

					repository.flush();
					repository.close();
				} catch (IOException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}

				fileSort.showLines();

			} else {
				synchronized (repository) {
					try {
						for (int i = 0; i < valueNumber; i++) {
							repository.write(key + " ");
						}
						repository.append(date + "\n");

						repository.flush();

					} catch (IOException e) {
						System.out.println("An error occurred.");
						e.printStackTrace();
					} finally {
						repository.close();
					}
					fileSort.showLines();
				}
			}

		} else {
			out.println("The mock is finished");
		}

	}
}
