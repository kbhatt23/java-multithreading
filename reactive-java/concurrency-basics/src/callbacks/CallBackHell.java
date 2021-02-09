package callbacks;

import util.ThreadUtil;

public class CallBackHell {

	public static void main(String[] args) {
		System.out.println("Main Thread started running");
		CallBackHell obj = new CallBackHell();
		//async example
		
		ICallBack callBack = () -> System.out.println("Call back recieved");
		Thread t = new Thread(() -> obj.executeAsync(callBack));
		t.setDaemon(true);
		t.start();
		//since we are joinign even though thread is daemon
		//all task will be compelted by other thread
		
		ThreadUtil.join(t);
		
		System.out.println("Main Thread completed");
	}
	
	public void executeAsync(ICallBack callBack) {
		System.out.println("Async task running");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Async task completed");
		callBack.call();
	}
}
