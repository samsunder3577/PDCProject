import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;
class Russia
{
		public static void main(String[] args)throws IOException,InterruptedException
		{
			while(true)
			{
			String clientSentence;
			ServerSocket Server=new ServerSocket(8888);
				
			Socket Client=Server.accept();
			BufferedReader br =new BufferedReader(new InputStreamReader(Client.getInputStream()));
			clientSentence = br.readLine();
			System.out.println("Data File Recieved");
			
			Thread.sleep(1000);
			Socket toClient=new Socket("localhost",7878);
			System.out.println("To Destination...");
			DataOutputStream outToServer = new DataOutputStream(toClient.getOutputStream());
			outToServer.writeBytes(clientSentence);
			Server.close();
			toClient.close();
			}
		}
}