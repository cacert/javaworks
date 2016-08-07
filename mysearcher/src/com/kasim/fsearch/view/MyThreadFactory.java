package com.kasim.fsearch.view;

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {
	private int count =0;
	@Override
	public Thread newThread(Runnable r) {
		return new Thread(r,"kasim"+count++);
	}

}
