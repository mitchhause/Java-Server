import java.io.BufferedReader;
import java.lang.management.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

public class javaServer {

	public static void main(String[] args) throws IOException 
	{
		final int portNumber = 8080;
		final ServerSocket server = new ServerSocket(portNumber);
		// TODO Auto-generated method stub
		System.out.print("Listening now...");
		
		 while (true){
			 Socket client = server.accept();
			
		/*	 InputStreamReader isr = new InputStreamReader(client.getInputStream());
			 BuffredReader reader = new BufferedReader(isr);
			 String line = reader.readLine();
			 while (!line.isEmpty()) 
			 { 
				 System.out.println(line); 
				 line = reader.readLine();
				 }
*/
	
		      // spin forever
		    try{
		            //client= server.accept();
		            System.out.println("connection Established");
		            ServerThread st = new ServerThread(client);
		            st.start();
		            }

		    catch(Exception e){
		        e.printStackTrace();
		        System.out.println("Connection Error");
		    }
		    
	}

}
}


class ServerThread extends Thread{ 
	Socket s = null;
	String message = null;
	PrintWriter os = null;
	//CPUdata dataget = new CPUdata();
	//OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
	ServerThread(Socket s) 
	{
		this.s = s;
	}
	public void run() {
		    try{
		    	BufferedReader reader= new BufferedReader(new InputStreamReader(s.getInputStream()));
		    	message = reader.readLine();
		    	while( message!= null)
		    	{
		    		System.out.println(message);
		    		message = reader.readLine();
		    	
		    	 PrintWriter out = new PrintWriter(s.getOutputStream());
		    	 
		            out.println("HTTP/1.1 200 OK");
		    	    out.println("Content-Type: text/html");
		    	    out.println("\r\n");
		    	    out.println("<p> System Information </p>");
		    	    out.println("<p>---------------JVM Runtime Details-------------</p>");
		    	    out.println("<p>Available processors (Cores): " +   Runtime.getRuntime().availableProcessors()+ "</p>");
		    	    out.println("<p>Initial Memory (-Xms)       : " +  (Runtime.getRuntime().freeMemory()/(1024*1024))+" MB</p>");
		    	    long maxMemory = Runtime.getRuntime().maxMemory() ;
		    	    out.println("<p>Maximum JVM Memory (-Xmx)   : " + (maxMemory/(1024*1024))+" MB</p>");
		    	    out.println("<p>Total Used JVM Memory       : " + (Runtime.getRuntime().totalMemory()/(1024*1024))+" MB</p>");
		    	     
		    	    File[] roots = File.listRoots();
		    	    out.println("<p>---------------FileSystem Details-------------</p>");
		    	    for (File root : roots)
		    	    {
		    	    out.println("<p>FileSystem Root Details: " + root.getAbsolutePath());
		    	    out.println("<p>Total Space              : " + (root.getTotalSpace()/(1024*1024))+" MB</p>");
		    	    out.println("<p>Free Space               : " + (root.getFreeSpace()/(1024*1024))+ " MB</p>");
		    	    out.println("<p>Usable Space             : " + (root.getUsableSpace()/(1024*1024))+ " MB</p>");
		    	   
		    	    }
		    	    out.flush();
		    	    s.close();
		    	 }
	
		       // os=new PrintWriter(s.getOutputStream());
		    }
		    
		    catch(IOException e){
		        System.out.println("IO error in server thread");
		        }
	
		    
	}
}

	


	
	
	


