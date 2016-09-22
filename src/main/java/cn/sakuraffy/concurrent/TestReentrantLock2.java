package cn.sakuraffy.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author 	deadlyBoy
 * @date 	2016��7��26��
 * @describe ����ReentantLock���жϴ���
 */
public class TestReentrantLock2 implements Runnable {
	private static ReentrantLock lock1 = new ReentrantLock();
	private static ReentrantLock lock2 = new ReentrantLock();
	
	private int lock;
	//���Ƽ���˳�򣬷����γ�����
	public TestReentrantLock2(int lock) {
		this.lock = lock;
	}
	
	
	@Override
	public void run() {
		try {
			if(lock == 1) {
				lock1.lockInterruptibly();
				//��Ҫ��һ��try�ﲶ����Exception ---�ڸ���������
				try {
					Thread.sleep(1000);
				}catch (InterruptedException e) {
					e.printStackTrace();
					lock2.lockInterruptibly();
				}
			} else{
				lock2.lockInterruptibly();
				try {
					Thread.sleep(1000);
				}catch (InterruptedException e) {
					e.printStackTrace();
					lock1.lockInterruptibly();
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//һ��Ҫ�жΡ��� Exception
			if(lock1.isHeldByCurrentThread()) {
				lock1.unlock();
			}
			if (lock2.isHeldByCurrentThread()) {
				lock2.unlock();
			}
			System.out.println(Thread.currentThread().getName() + ": exit");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new TestReentrantLock2(1));
		Thread t2 = new Thread(new TestReentrantLock2(2));
		t1.start();
		t2.start();
		Thread.sleep(1000);
		t1.interrupt();
	}
}
