package cn.sakuraffy.pattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketClient {
	public static void main(String[] args) throws IOException {
		Socket client = null;
		PrintWriter pw = null;
		BufferedReader br = null;
		try {
			client = new Socket();
			client.connect(new InetSocketAddress("localhost", 8000));
			pw = new PrintWriter(client.getOutputStream());
			pw.println("Hello");
			pw.flush();
			
			br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			System.out.println("from server: " + br.readLine());
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(pw != null) {
				pw.close();
			}
			if(br != null) {
				br.close();
			}
			if (client != null) {
				client.close();
			}
		}
	}
}
