package Spring.MongoSpring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

class App
{
	public static void main(String[] args) {
		
		Resource resource = new ClassPathResource("Beans.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		
		MongoDbService service  = (MongoDbService)factory.getBean("MongoDbBean");
		GofroDataToCollection data = (GofroDataToCollection)factory.getBean("data");
		
		service.writeCountry(data.createCountryList("countries.csv"));
		service.writeState(data.createStatesList("states.csv"));
		service.writeCity(data.createCityList("states.csv"));
		
	} 
}