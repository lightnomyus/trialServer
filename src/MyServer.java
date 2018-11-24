import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyServer {


	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	private static ServerSocket ss;
	private static Socket s;
	private static Socket s2;//send unique number to phone
	private static BufferedReader br;
	private static InputStreamReader isr;
	/*private static String message;
	private static String phoneIP;
	private static double latitude;
	private static double longitude;
	private static char open;
	private static char a;
	private static char b;
	private static char c;
	private static int sat;
	private static int pul;
	private static int rat;
	private static int HR;
	private static String[] arrOfData;
	private static int netID;*/
	private static String mark = "#";
	private static Date date;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	
	public static void main(String[] args) {
		
		int sat,pul,rat;
		int HR = 0;
		char open, a, b, c;
		String[] arrOfData;
		String message;
		
		try {
			while(true) {
				ss = new ServerSocket(5000);//5000 ini port nya
				System.out.println("Data received");
				s = ss.accept();
				
				isr = new InputStreamReader(s.getInputStream());
				br = new BufferedReader(isr);
				message = br.readLine();
				System.out.println("Message Retrieved");
				System.out.println(message);
				
				isr.close();
				br.close();
				ss.close();
				s.close();
				
				
				arrOfData = message.split(mark, 4);
				
				double latitude = Double.parseDouble(arrOfData[2]);
				double longitude = Double.parseDouble(arrOfData[3]);
				String phoneIP = arrOfData[0];
				
				open = arrOfData[1].charAt(0);
				if( open=='[' ) {
					a = arrOfData[1].charAt(4);
					b = arrOfData[1].charAt(5);
					c = arrOfData[1].charAt(6);
					
					if( c==']' ) {
						sat = Character.getNumericValue(b);
						pul = Character.getNumericValue(a);
						HR = pul*10 + sat;
					} else {
						sat = Character.getNumericValue(c);
						pul = Character.getNumericValue(b);
						rat = Character.getNumericValue(a);
						HR = rat*100 + pul*10 + sat;
					}
				}
				
				//get timestamp
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				
				System.out.println("Client Address : " + phoneIP);
				System.out.println( "Heart Rate : " + HR );
				System.out.println( "Latitude : " + latitude );
				System.out.println( "Longitude : " + longitude );
				System.out.println("Timestamp : " + sdf.format(timestamp));

				
				//export to csv file
				FileOutputStream outFile = new FileOutputStream("victim.csv", true);
				PrintWriter fileWrite = new PrintWriter(outFile);
				
				fileWrite.println(phoneIP + "," + HR + "," + latitude + "," + longitude + "," + sdf.format(timestamp));
				fileWrite.close();
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*public void sendButton(java.awt.event.ActionEvent evt) {
		try {
			s2 = new Socket(phoneIP, 5002);
			PrintWriter pw = new PrintWriter(s2.getOutputStream());
			pw.write(sendID);
			pw.flush();
			pw.close();
			s2.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
