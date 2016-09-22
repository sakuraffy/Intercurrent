package cn.sakuraffy.base;

public class StopThread {
	
	private static User user = new User();
	
	public static class User {
		private String name;
		private int id;
		
		//����NumberFormatException
		public User() {
			id = 0;
			name = "0";
		}
		
		public final String getName() {
			return name;
		}
		public final void setName(String name) {
			this.name = name;
		}
		public final int getId() {
			return id;
		}
		public final void setId(int id) {
			this.id = id;
		}
		
		@Override
		public String toString() {
			return "User [name=" + name + ", id=" + id + "]";
		}
	
	}
	
	public static class WriteThread extends Thread {
		private boolean stop = false;
		
		public void stopMe() {
			stop = true;
		}
		
		@Override
		public void run() {
			while(true) {
				
				if(stop) {
					System.out.println("exit by stopMe()");
					break;
				}
				
				synchronized (user) {
					int num = (int) (System.currentTimeMillis()/1000);
					user.setId(num);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					user.setName(String.valueOf(num));
				}
				//��yield()�ȼ�
				Thread.yield();
			}
		}
	}
	
	public static class ReadThread extends Thread {
		@Override
		public void run() {
			while(true) {
				synchronized (user) {
					while(user.getId() != Integer.parseInt(user.getName())) {
						System.out.println(user);
						break;
					}
				}
				Thread.yield();
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis()/1000);
		new ReadThread().start();
		while(true) {
			WriteThread write = new WriteThread();
			write.start();
			//��Ϣ������ʱ�����÷�������
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			write.stop();
		}
	}
}
