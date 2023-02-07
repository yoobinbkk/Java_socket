package thread.basic;

public class Ex02_RunnableTest {

	public static void main(String[] args) {
		
		MakeCar2 mc1 = new MakeCar2("차트 만들기");
		Thread t1 = new Thread(mc1);
		t1.start();									// runnable에는 start() 메소드가 없기 때문에 Thread()를 생성해서 사용해야 한다
		
		// Thread t2 = new Thread(new MakeCar2("엔진 부착"));
		// t2.start();
		
		new Thread(new MakeCar2("엔진 부착")).start();		// 한 줄 버전
		
		System.out.println("작업완료");				// main 쓰레드

	}

}
