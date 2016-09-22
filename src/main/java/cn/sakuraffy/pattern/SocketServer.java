package cn.sakuraffy.pattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer implements Runnable {
	private Socket socketClient;
	
	public SocketServer(Socket socketClient) {
		super();
		this.socketClient = socketClient;
	}
	
	@Override
	public void run() {
		BufferedReader br = null;
		String line = null;
		PrintWriter pw = null;
		System.out.println(socketClient.getRemoteSocketAddress() + " connected !");
		try {
			br = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			pw = new PrintWriter(socketClient.getOutputStream());
			while((line = br.readLine()) != null) {
				pw.println(line);
			}
			System.out.println("haha");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if (br != null){
					br.close();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
			if (pw != null) {
				pw.close();
			}
		}	
	}
	
	public static void main(String[] args) {
		ExecutorService es = Executors.newCachedThreadPool();
		ServerSocket server = null;
		Socket socket = null;
		try {
			try {
				server = new ServerSocket(8000);
			}catch(IOException e){
				e.printStackTrace();
			}
			while(true) {
				try {
					socket = server.accept();
					es.execute(new SocketServer(socket));		
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}finally {
			try {
				server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
