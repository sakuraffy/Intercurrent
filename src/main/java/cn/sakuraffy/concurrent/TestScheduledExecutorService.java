package cn.sakuraffy.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestScheduledExecutorService {
	public static void main(String[] args) {
		ScheduledExecutorService ses = Executors.newScheduledThreadPool(5);
		//����scheduleAtFixedRate()��˵��Ƶ����һ���ģ��������ϸ�����ʼΪ��㣬֮���periodʱ�䣬������һ������
		//��scheduleWithFixedDelay(),���ʱ����һ���ģ���������һ���������Ϊ���
		ses.scheduleWithFixedDelay(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(System.currentTimeMillis()/100 + " "
						+ Thread.currentThread().getName());
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, 0, 2, TimeUnit.SECONDS);
	
	/*ses.scheduleAtFixedRate(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(System.currentTimeMillis()/100 + " "
							+ Thread.currentThread().getName());
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}, 0, 2, TimeUnit.SECONDS);*/
	}
}
