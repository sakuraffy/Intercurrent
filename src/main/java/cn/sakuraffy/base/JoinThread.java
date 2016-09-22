package cn.sakuraffy.base;

public class JoinThread extends Thread {
	private volatile static int i = 0;
	@Override
	public void run() {
		for(; i < 100; i++) ;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(; i < 200; i++) ;
	}
	
	public static void main(String[] args) throws InterruptedException {
		JoinThread jt = new JoinThread();
		jt.start();
		//��100ms���޷���ɣ����������ִ��
		jt.join(100);
		System.out.println(i);
	}
}
