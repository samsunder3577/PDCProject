import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*; 
import java.lang.*;
import java.util.*;
import java.nio.file.*;
import javax.swing.Timer;
class ServerApplication {
	static String[] alpha = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
        "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
        "w", "x", "y", "z", "1", "2", "3", "4", "5", "6", "7", "8",
        "9", "0","!", ",", "?", ".", "'"," ","-","@"};
	static String[] morse = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.",
        "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.",
        "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-",
        "-.--", "--..", ".----", "..---", "...--", "....-", ".....",
        "-....", "--...", "---..", "----.", "-----","-.-.--", "--..--", "..--..", ".-.-.-", ".----."," ","_...._",".__._."};

 public static void main(String args[]) throws Exception {
	 
  String clientSentence;
  String capitalizedSentence;
  ServerSocket welcomeSocket = new ServerSocket(7878);

  while (true) {
   Socket connectionSocket = welcomeSocket.accept();
   BufferedReader br =new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
   clientSentence = br.readLine();
  
	
	String build = "";
    String change = clientSentence.trim();
    String[] words = change.split("   ");
    for (String word : words) {
        for(String letter : word.split(" ")){
            for(int x=0;x<morse.length;x++){
                if(letter.equals(morse[x]))
                    build=build+alpha[x];
            }
        }
        build+=" ";
    }
	
	
	String newS = ""; 
  int k=28;
        
        for (int i = 0; i < clientSentence.length(); ++i) { 
              
            int val = clientSentence.charAt(i); 
            
            int dup = k; 
  
            
            if (val + k > 122) { 
                k -= (122 - val); 
                k = k % 26; 
                  
                newS += (char)(96 + k); 
            } else { 
                newS += (char)(val + k); 
            } 
  
            k = dup; 
		}
	
	FileWriter fw = new FileWriter("output.txt", false);
	
				fw.write(newS); 
				fw.flush();
				
			br.close();
			fw.close();
			
			JPanel panel = new JPanel(); 
			JFrame frame = new JFrame("ApMSaM");
			frame.setSize(400, 100);
			JLabel label = new JLabel("Password");
			JLabel label21 = new JLabel("10secs\n");
			JTextField tf = new JTextField(4); 
			JButton send = new JButton("OK");
			
			panel.add(label21);
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
		BufferedWriter bw=new BufferedWriter(new FileWriter("Passwd.txt"));
		bw.write(tf.getText());
		bw.close(); 
	  }
	  catch(Exception ex)
		  
		  {
			  System.out.println("Error"+ex);
		  }
		 frame.dispose(); 
	  }	
			});
			
			Thread.sleep(10000);
			
			BufferedReader reader = new BufferedReader(new FileReader("Passwd.txt"));
			String pass=reader.readLine();
			reader.close();
			
			
			String compass=build.substring(0,4);
			String timer1=build.substring(5,10);
			String timer12=build.substring(5,7);
			String build1=build.substring(11);
			//System.out.println(timer);
			int c=Integer.parseInt(timer1);
			
			if(pass.equals(compass))
			{
			
              
            final JOptionPane optionPane = new JOptionPane(build1+"\n You Got "+timer12+" Seconds", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);

			final JDialog dialog = new JDialog();
			dialog.setTitle("Message");
			dialog.setModal(true);

			dialog.setContentPane(optionPane);

			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			dialog.pack();

			
			Timer timer = new Timer(c, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
			dialog.dispose();
    }
});
timer.setRepeats(false);


timer.start();

dialog.setVisible(true);
           			 
			Files.delete(Paths.get("output.txt"));
			Files.delete(Paths.get("passwd.txt"));
			
			}
			else 
			{
				System.out.println("All DATA DELETED");
				Files.delete(Paths.get("output.txt"));
				Files.delete(Paths.get("passwd.txt"));
				
			}
  }
 }
}