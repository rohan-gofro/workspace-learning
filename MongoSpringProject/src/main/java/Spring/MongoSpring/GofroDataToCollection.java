package Spring.MongoSpring;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GofroDataToCollection implements CsvToCollection {
	
	
    private MongoDbService db;
	private BufferedReader br;
	private static int stateCount = 0;
	private static int cityCount = 0;
	
	public void showdbdetails()
	{
		db.showDb();
	}
	
	public void setDb(MongoDbService db) {
		this.db = db;
	}

	private boolean getReader(String filepath) {
		try {
			br = new BufferedReader(new FileReader(filepath));
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

	private boolean closeReader() {
		try {
			br.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<Country> createCountryList(String filepath) {

		List<Country> countryList = new ArrayList<Country>();

		if (getReader(filepath)) {
			String line;
			String[] args;
			try {
				while ((line = br.readLine()) != null) {
					args = line.split(",");
					countryList.add(new Country(args[1], args[0]));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error Reading File");
				closeReader();
			}
		} else {
			System.out.println("File not found");
			return null;
		}

		return countryList;

	}

	public HashSet<State> createStatesList(String filepath) {

		HashSet<State> stateSet = new HashSet<State>();
		if (getReader(filepath)) {
			String line;
			String[] args;
			String code;
			try {
				while ((line = br.readLine()) != null) {
					args = line.split(",");
					if (!(stateSet.contains(new State(args[1])))) {
						stateCount++;
						code = "S" + stateCount;
						stateSet.add(new State(code, args[1], db.getCountryByName(args[2])));
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("error reading file " + filepath);
				closeReader();
				return null;
			}
		} else {
			System.out.println(filepath + " not found");
			return null;
		}
		closeReader();
		return stateSet;
	}

	public ArrayList<City> createCityList(String filepath) {

		ArrayList<City> cityList = new ArrayList<City>();
		if (getReader(filepath)) {
			String line;
			String[] args;
			String code;
			try {
				while ((line = br.readLine()) != null) {
					cityCount++;
					code = "C" + cityCount;
					args = line.split(",");
					cityList.add(new City(code, args[0], db.getStatebyName(args[1])));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Unable to read file " + filepath);
				closeReader();
				e.printStackTrace();
			}
		} else {
			System.out.println("file not found " + filepath);
			return null;
		}
		return cityList;
	}
}
