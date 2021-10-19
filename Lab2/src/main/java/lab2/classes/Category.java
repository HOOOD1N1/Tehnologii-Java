package lab2.classes;

import java.util.ArrayList;
import java.util.List;

public class Category {
	private List<String> Categories = new ArrayList<String>();

	
	public List<String> getCategories() {
		return Categories;
	}


	public void setCategories(String newCategory) {
		Categories.add(newCategory);
	}

	public Category() {
	}


	public void show() {
		for(String c: Categories) {
			System.out.println(c + "\n");
		}
	}
	
	
	
}
