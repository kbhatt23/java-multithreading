package callbacks;

public interface PushableCallBack<T> {

	//called when data is passed one by one
	void call(T data);
	
	//clled when all data are sucesfully passed
	void callComplete();
	
	//called when an exception occurred
	void callError();
}
