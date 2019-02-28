package com.wwl.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 使用JDK1.5新特性 ReentrantLock锁和线程等待唤醒操作
 * @author Administrator
 *
 */
public class ThreadDemo {
	
	public static void main(String[] args) {
		final A a= new A();
		new Thread() {
			public void run() {
				while(true) {
					a.a();
				}
			}
		}.start();
		new Thread() {
			public void run() {
				while(true) {
					a.b();
				}
			}
		}.start();
	}
}
class A {
	//第一步 创建 ReentrantLock锁：代替同步锁：synchronized
	private ReentrantLock r = new ReentrantLock();
	//第二步  使用ReentrantLock对象创建Condition对象：有多少线程创建多少对象
	private Condition c1 = r.newCondition();
	private Condition c2 = r.newCondition();
	 int i=1;	
	public void a(){
		//synchronized (this) { 被r.lock()代替：从这里开始上锁
		  r.lock();  //开始上锁
			if(i !=1) {
				try {
					//this.wait(); 被：c1.await()代替 
					c1.await(); //让c1的线程等待
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.print("我");
			System.out.print("是");
			System.out.print("线");
			System.out.print("程");
			System.out.println("A"+"\r\n");
		
			i=2;
			//this.notify();被c1.signal()代替 随机唤醒别的等待线程
			c2.signal(); //指定c2的锁释放
		//} r.unlock()
			r.unlock();//：从这里结束：释放锁
	}
	public void b(){
		 r.lock();
			
			if(i !=2) {
				try {
					//this.wait(); 被：c2.await()代替 //让此线程等待
					c2.await();//指定c2的上锁
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.print("我");
			System.out.print("是");
			System.out.print("线");
			System.out.print("程");
			System.out.println("B"+"\r\n");
			i=1;
			//this.notify();被c2.signal()代替 
			c1.signal();//指定c1的解锁
			r.unlock();
	}

}