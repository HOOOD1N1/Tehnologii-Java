package com.lab1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class FileSort {
	private File currentFile;
	private ArrayList<String> list = new ArrayList<String>();
	private PrintWriter out;
	
	public FileSort(File currentFile, PrintWriter out) {
		this.currentFile = currentFile;
		this.out = out;
	}

	private void sortLines() throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("D:\\Cursuri\\Cursuri master\\Tehnologii Java\\Tehnologii-Java\\Lab1\\repository.txt"));
		String line = "";
		while((line=reader.readLine())!=null){
				list.add(line);
		}
		Collections.sort(list);
		reader.close();
	}
	
	public void showLines() {
		try {
			this.sortLines();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("here2");
			e.printStackTrace();
		}
		out.println("<html><head><title>Server Response</title></head><body>");
		for(String s: list) {
			out.println("<div>" + s + "</div>");
		}
		out.println("</body></html>");
	}
}
