package Spring.MongoSpring;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GofroDataCsvToList implements CsvToCollection {

	private BufferedReader br;
	

	// To get buffered reader
	private boolean getReader(String filePath) {
		try {
			this.br = new BufferedReader(new FileReader(filePath));
			return true;
		} catch (FileNotFoundException e) {
			return false;
		}
	}

	// to close buffered reader
	private boolean closeReader() {
		try {
			this.br.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public List<Base> getList(String filePath, Class clazz) {

		String line;
		String []args;
		getReader(filePath);
		List<Base> list = new ArrayList<Base>();
		
		if (clazz.getSuperclass() == Base.class) {
			
			try {
				while((line = br.readLine())!=null)
				{
					Base obj = (Base)clazz.newInstance();
					args = line.split(",");
					obj.fillInstance(args[0], args[1]);
					list.add(obj);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		closeReader();
		return list;
	}

	public <E> HashSet<E> getHashSet(String filePath, Class<E> t) {
		// TODO Auto-generated method stub
		return null;
	}

}
