package thread.basic;

class Count {
	int i;
	// synchronized void increment() { i++;}			// synchronized 동기화 (작업이 끝날 때까지 기다리게 하는 것)
	void increment() {
		synchronized (this) {							// 값이 변화할 때만 동기화
			i++;
		}
		// 다른 코딩
	}
	
}

class ThreadCount extends Thread {
	Count cnt;
	ThreadCount(Count _cnt) { cnt = _cnt; }
	public void run() {
		for(int i=0; i<100000000; i++) {
			cnt.increment();
		}
	}
}

public class Ex07_Synch {

	public static void main(String[] args) {
		
		Count count = new Count();
		ThreadCount tc1 = new ThreadCount(count);
		tc1.start();
		
		ThreadCount tc2 = new ThreadCount(count);
		tc2.start();
		
		try {
			tc1.join();					// 끝까지 실행하도록 기다리게 하는 함수 (메인 이전에)
			tc2.join();
		} catch (Exception ex) {
			
		}
		
		System.out.println("i의 결과 : " + count.i);
		
	}

}
