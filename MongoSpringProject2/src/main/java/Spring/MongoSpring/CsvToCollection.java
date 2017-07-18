package Spring.MongoSpring;

import java.util.HashSet;
import java.util.List;

public interface CsvToCollection {

	public List<Base> getList(String filePath, Class clazz);

	public <E> HashSet<E> getHashSet(String filePath, Class<E> t);

}
