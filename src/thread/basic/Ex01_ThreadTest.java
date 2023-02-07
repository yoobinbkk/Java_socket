package thread.basic;

public class Ex01_ThreadTest {

	public static void main(String[] args) {
		
		MakeCar1 mc1 = new MakeCar1("차트 만들기");		// mc1 쓰레드
		mc1.start();									// run() 말고 start()를 사용한다
		
		MakeCar1 mc2 = new MakeCar1("엔진 부착");		// mc2 쓰레드
		mc2.start();
		
		System.out.println("작업완료");				// main 쓰레드
		
	}

}

