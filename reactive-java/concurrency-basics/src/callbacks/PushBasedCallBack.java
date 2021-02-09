package callbacks;

import util.ThreadUtil;

public class PushBasedCallBack {
	public static void main(String[] args) {

		System.out.println("Main thread started running");
		PushableCallBack<String> callBack = new PushableCallBack<String>() {
			
			@Override
			public void callError() {
				System.out.println("callBack: Callback threw Error");
			}
			
			@Override
			public void callComplete() {
				System.out.println("callBack: Callback tasks completed");
			}
			
			@Override
			public void call(String data) {
				System.out.println("callBack: Callback sent data "+data);
			}
		};
		PushBasedCallBack obj = new PushBasedCallBack();
		Runnable r = () -> obj.runTaskAsync(callBack);
		Thread thread = new Thread(r);
		
		//thread.setDaemon(true);
		
		thread.start();
		//if thread is daemon we must join
		//ThreadUtil.join(thread);
		
		System.out.println("main completed");
	}

	public  void runTaskAsync(PushableCallBack<String> callBack) {
		System.out.println("runTaskAsync: Async Task started");
		ThreadUtil.sleep(2000);
		callBack.call("jai shree ram");
		
		ThreadUtil.sleep(1000);
		callBack.call("jai radha madhav");
		ThreadUtil.sleep(1000);
		callBack.call("jai krishna murari");
		ThreadUtil.sleep(1000);
		callBack.callComplete();
	}
}
