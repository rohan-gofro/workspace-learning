package Gofro.HelloMaven;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class FetchUsingHttp {
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		//Define url
		String url = "http://apis.scopeafterug.xyz/domain.php";
		//create client
		CloseableHttpClient client = HttpClientBuilder.create().build();
		//make a http request
		HttpGet request = new HttpGet(url);
		//http response
		HttpResponse response = client.execute(request);
		//getting response code. If 200 , it is ok
		System.out.println("Response Code " + response.getStatusLine().getStatusCode());
		//getting response
		BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String str;
		//printing response
		while((str = br.readLine())!=null){
			System.out.println(str);
		}
	}
}
