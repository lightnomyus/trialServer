import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class server3 extends Thread {
	   private ServerSocket serverSocket;
	   
	   public server3(int port) throws IOException {
	      serverSocket = new ServerSocket(port);
	      serverSocket.setSoTimeout(10000);
	   }

	   public void run() {
	      while(true) {
	         try {
	            System.out.println("Waiting for client on port " + 
	               serverSocket.getLocalPort() + "...");
	            Socket server = serverSocket.accept();
	            
	            System.out.println("Just connected to " + server.getRemoteSocketAddress());
	            DataInputStream in = new DataInputStream(server.getInputStream());
	            
	            System.out.println(in.readUTF());
	            DataOutputStream out = new DataOutputStream(server.getOutputStream());
	            out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress()
	               + "\nGoodbye!");
	            server.close();
	            
	         } catch (SocketTimeoutException s) {
	            System.out.println("Socket timed out!");
	            break;
	         } catch (IOException e) {
	            e.printStackTrace();
	            break;
	         }
	      }
	   }
	   
	   public static void main(String [] args) {
	      int port = 3079;
	      try {
	         Thread t = new server3(port);
	         t.start();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	   }
	}
