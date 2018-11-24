import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class server2 {

	public static void main(String args[]) throws IOException {
		String message, message2;
		int number, temp;
		ServerSocket s1 = new ServerSocket(2058);
		Socket ss = s1.accept();
		
		int portNo = s1.getLocalPort();
		
		Scanner sc = new Scanner(ss.getInputStream());
		message = sc.next();
		//number = sc.nextInt();
		//temp = number * 2;
		message2 = message + "Muach";
		PrintStream p = new PrintStream(ss.getOutputStream());
		//p.println(temp);
		p.println(message2);
	}
}
