import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class MyServer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	private static ServerSocket ss;
	private static Socket s;
	private static BufferedReader br;
	private static InputStreamReader isr;
	private static String message;
	private static int heartRate;
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
	private static String mark = "#";
	private static String[] arrOfData;
	private static int netID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyServer frame = new MyServer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
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
				
				netID = Integer.parseInt(arrOfData[0]);
				latitude = Double.parseDouble(arrOfData[2]);
				longitude = Double.parseDouble(arrOfData[3]);
				
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
				System.out.println( "Net ID : " + netID );
				System.out.println( "Heart Rate : " + HR );
				System.out.println( "Latitude : " + latitude );
				System.out.println( "Longitude : " + longitude );
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Create the frame.
	 */
	public MyServer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel textData = new JLabel("Received:");
		contentPane.add(textData, BorderLayout.WEST);
	}

}
