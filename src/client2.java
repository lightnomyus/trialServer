import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class client2 {

	public static void main(String args[]) throws UnknownHostException, IOException {
		String send = "Hai";
		String received;
		int number,temp;
		Scanner sc = new Scanner(System.in);
		Socket s = new Socket("192.168.3.186", 2058);
		Scanner sc1 = new Scanner(s.getInputStream());
		System.out.println("Enter number");
		number = sc.nextInt();
		PrintStream p = new PrintStream(s.getOutputStream());
		//p.println(number);
		//temp = sc1.nextInt();
		//System.out.println(temp);
		p.println(send);
		received = sc1.next();
		System.out.println(received);
	}
}
