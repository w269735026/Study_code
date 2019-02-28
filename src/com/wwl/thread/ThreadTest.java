package com.wwl.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTest {
	public static void main(String[] args) {
		ExecutorService  es=Executors.newFixedThreadPool(3);
		es.submit(new Runnable() {
			
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println(Thread.currentThread().getName()+"_"+i);
				}
			}
		});
		es.submit(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println(Thread.currentThread().getName()+"_"+i);
				}
				
			}
		});
		es.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				for (int i = 0; i < 10; i++) {
					System.out.println(Thread.currentThread().getName()+"_"+i);
				}
				return null;
			}
		});
		es.shutdown();
	}
}
