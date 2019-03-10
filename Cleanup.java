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
class Cleanup
{
		public static void main(String[] args) 
		{
			try{
			Files.delete(Paths.get("sample.txt"));
			Files.delete(Paths.get("ServerApplication.class"));
			Files.delete(Paths.get("ServerApplication$1.class"));
			Files.delete(Paths.get("ServerApplication$2.class"));
			Files.delete(Paths.get("ClientApplication.class"));
			Files.delete(Paths.get("ClientApplication$1.class"));
			Files.delete(Paths.get("sample.txt"));
			Files.delete(Paths.get("Cleanup.class"));
			}
			catch (Exception ex)
			{
					System.out.println("File Missing Error");
			}
		}
}