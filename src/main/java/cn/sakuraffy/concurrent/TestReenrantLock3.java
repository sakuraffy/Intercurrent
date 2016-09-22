package cn.sakuraffy.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author 	deadlyBoy
 * @date 	2016��7��26��
 * @describe ����ReentrantLock��Ч����������һ�ַ�ʽ
 */
public class TestReenrantLock3 implements Runnable {
	private static ReentrantLock lock1 = new ReentrantLock();
	private static ReentrantLock lock2 = new ReentrantLock();
	
	private int lock;
	
	public TestReenrantLock3(int lock) {
		this.lock = lock;
	}
	
	@Override
	public void run() {
		if(lock == 1) {
			while(true) {
				if(lock1.tryLock()) {
					try {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(lock2.tryLock()) {
							try {
								System.out.println(Thread.currentThread().getName()
										+ ": work done");
								return ;
							}finally {
								lock2.unlock();
							}
						}
					}finally {
						lock1.unlock();
					}
				}
			}
		}else {
			while(true) {
				if(lock2.tryLock()) {
					try {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(lock1.tryLock()) {
							try {
								System.out.println(Thread.currentThread().getName()
										+ ": work done");
								return ;
							}finally {
								lock1.unlock();
							}
						}
					}finally {
						lock2.unlock();
					}
				}
			}
		}
	}	
				
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new TestReenrantLock3(1));
		Thread t2 = new Thread(new TestReenrantLock3(2));
		t1.start();
		t2.start();
	}
}
