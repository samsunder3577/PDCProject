import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*; 
import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;
class ClientApplication extends JFrame {
    public static void main(String args[]) {

        
        JFrame frame = new JFrame("ApMSaM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 100);

        
        JPanel panel = new JPanel(); 
        JLabel label = new JLabel("Enter Text");
        JTextField tf = new JTextField(50); 
        JButton send = new JButton("Send");
        
        panel.add(label); 
        panel.add(label); 
        panel.add(tf);
        panel.add(send);
        

       
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
       
        frame.setVisible(true);
		 
	send.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {try
		  {
        BufferedWriter bw=new BufferedWriter(new FileWriter("sample.txt"));
		bw.write(tf.getText());
		
		tf.setText("");
		bw.close();
		
		char[] english = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
                  'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 
                  'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
                  ',', '.', '?' , ' ' ,'-' ,'@'};

		String[] morse = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", 
                ".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.",
                "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
                "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.",
                "-----", "--..--", ".-.-.-", "..--..", " " ,"_...._" ,".__._."};
				
		FileReader fre = new FileReader("sample.txt");
		BufferedReader infr=new BufferedReader(fre);
		String sss=infr.readLine();
		String userInput = sss.toLowerCase();

		char[] chars = userInput.toCharArray();

		String stre = "";
		for (int i = 0; i < chars.length; i++)
		{
        for (int j = 0; j < english.length; j++)
		{

            if (english[j] == chars[i])
			{
                stre = stre + morse[j] + " ";  
			}
		}
		}
        
		FileWriter fwe = new FileWriter("sample.txt", false);
		fwe.write(stre); 
		fwe.flush();
		infr.close();
		fwe.close();
		
		Socket client=new Socket("localhost",6666);
		System.out.println("To Brooklyn...");
		FileReader fr = new FileReader("sample.txt");
		BufferedReader br = new BufferedReader(fr);
		
		DataOutputStream outToServer = new DataOutputStream(client.getOutputStream());
		
		String s;
		
		while ((s = br.readLine()) != null) { 
				
				outToServer.writeBytes(s);
				
			}
			br.close();
			client.close();
		  }catch(Exception ex)
		  
		  {
			  System.out.println("Error"+ex);
		  }
      
	  }
    });
	
    }
}
