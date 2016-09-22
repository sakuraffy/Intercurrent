package cn.sakuraffy.base;

public class InterruptThread {
	public static void main(String[] args) throws InterruptedException {
		//  ���ǳ�����ͽӿ�Ҳ����������д����
		Thread t1 = new Thread() {
			@Override
			public void run() {
				while(true) {
					//�ж��Ƿ�Ϊ�ж�״̬ ---û�г����򲻻�ֹͣ
					//Thread.currentThread() ��ȡ��ǰ�߳�
					if(Thread.currentThread().isInterrupted()) {
						System.out.println("Interrupted !");
						break;
					}
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						System.out.println("Interrupted when sleeping");
						//�����жϱ�־λ
						Thread.currentThread().interrupt();
					}
					Thread.yield();
				}
			}
		};
		
		t1.start();
		Thread.sleep(200);
		t1.interrupt();
	}
}
