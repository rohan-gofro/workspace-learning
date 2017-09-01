package File.Download;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.MalformedInputException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) 
    {
        URL url = null;
        URLConnection connection = null;
        int i;
        try
        {
        	url = new URL("http://10.197.6.95/api/queries/330/results.csv?api_key=FoN6TS501B67YgJ2t1NrRLJ3RIZZFzMYwYpA8rtt");
        	connection = url.openConnection();
        	File file = new File("report.csv");
        	BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
        	BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file.getName()));
        	while((i = bis.read())!=-1)
        	{
        		bos.write(i);
        	}
        	bos.flush();
        	bis.close();
        	bos.close();
        }
        catch(MalformedInputException e)
        {
        	e.printStackTrace();
        }
        catch(IOException e)
        {
        	e.printStackTrace();
        }
        
       
    }
}
