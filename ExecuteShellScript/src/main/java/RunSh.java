import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RunSh {

	public static void main(String[] args) throws Throwable {
		
		ProcessBuilder pb = new ProcessBuilder("./something.sh");
		Process p = pb.start();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = null;
		while((line = br.readLine()) != null)
		{
			System.out.println(line);
		}
		
	}
	
	
}