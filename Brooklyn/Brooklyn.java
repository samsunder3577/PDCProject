import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;
class Brooklyn
{
		public static void main(String[] args)throws IOException,InterruptedException
		{
			while(true)
			{
			String clientSentence;
			ServerSocket Server=new ServerSocket(6666);
				
			Socket Client=Server.accept();
			BufferedReader br =new BufferedReader(new InputStreamReader(Client.getInputStream()));
			clientSentence = br.readLine();
			System.out.println("Data File Received");
			
			Thread.sleep(1000);
			Socket toClient=new Socket("localhost",8888);
			System.out.println("To Russia...");
			DataOutputStream outToServer = new DataOutputStream(toClient.getOutputStream());
			outToServer.writeBytes(clientSentence);
			Server.close();
			toClient.close();
			}
		}
}