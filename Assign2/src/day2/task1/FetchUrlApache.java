package day2.task1;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
public class FetchUrlApache {
	
	public static void main(String[] args) throws Exception {
		
		String url = "http://apis.scopeafterug.xyz/domain.php";
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		request.setHeader("content-type", "text/html; charset=UTF-8");
		
		HttpResponse response = client.execute(request);
		
		HttpEntity httpEntity = response.getEntity();
		String output = EntityUtils.toString(httpEntity);
		System.out.println(output);
		
	}


}
