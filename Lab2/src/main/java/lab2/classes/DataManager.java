package lab2.classes;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

	private List<String> chosenCategories = new ArrayList<String>();
	private List<String> keys = new ArrayList<String>();
	private List<String> values = new ArrayList<String>();
	
	public List<String> getChosenCategories() {
		return chosenCategories;
	}
	public List<String> getKeys() {
		return keys;
	}
	public List<String> getValues() {
		return values;
	}
	
	public void addData(String category, String key, String value) {
		chosenCategories.add(category);
		keys.add(key);
		values.add(value);
	}
	
	
}
